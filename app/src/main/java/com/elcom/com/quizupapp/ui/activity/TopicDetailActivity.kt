package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.support.design.widget.Snackbar
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.SoloMatch
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.TopicDetail
import com.elcom.com.quizupapp.ui.activity.presenter.TopicDetailViewPresenter
import com.elcom.com.quizupapp.ui.listener.OnSocketInviteOpponentListener
import com.elcom.com.quizupapp.ui.view.TopicDetailView
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.PreferUtils
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_topic_detail.*
import kotlinx.android.synthetic.main.activity_topic_detail.view.*
import org.json.JSONObject

class TopicDetailActivity : BaseActivityQuiz(), TopicDetailView, View.OnClickListener, OnSocketInviteOpponentListener {



    override fun onSomeoneInviteYouToPlayGame(resultQuestion: JSONObject) {
        runOnUiThread {
            val snack = Snackbar.make(rlRoot, "Someone invite you to play a game", Snackbar.LENGTH_SHORT)
            val view = snack.getView()
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.BOTTOM
            view.layoutParams = params
            snack.show()
        }
    }

    private var mTopicId = ""
    private var mMatchId = ""
    private var mTopicDetailViewPresenter = TopicDetailViewPresenter(this)
    private var mFavourite = "0"
    private var mLike = false
    private var mTotalLikes = 0
    private var PLAY_GAME_TYPE = 0

    override fun getLayout(): Int {
        return  R.layout.activity_topic_detail
    }

    override fun initView() {
        btn_single_play.setOnClickListener(this)
        imv_favorite.setOnClickListener(this)
        btnChallenge.setOnClickListener(this)
        imvClose.setOnClickListener(this)
        tvRanking.setOnClickListener(this)
        tvRank.setOnClickListener(this)
        tvArchiMore.setOnClickListener(this)
    }

    override fun initData() {
        if (intent.hasExtra(ConstantsApp.KEY_TOPIC_ID)){
            mTopicId = intent.getStringExtra(ConstantsApp.KEY_TOPIC_ID)
        }
        getTopicViewDetail()

        setOnlineUser()


    }

    private fun setOnlineUser(){
        if( ConstantsApp.socketManage != null){
            ConstantsApp.socketManage.sendOnlineTopicMyself(this, mTopicId)
        }
    }

    override fun onResume() {
        super.onResume()
        if( ConstantsApp.socketManage != null){
            ConstantsApp.socketManage.initToInventionFromFriend(this)
        }
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btn_single_play -> {
                if(mTopicId != ""){
                    PLAY_GAME_TYPE = ConstantsApp.PLAY_GAME_SOLO
                    getMatchId()
                } else {
                    Toast.makeText(this, getString(R.string.can_not_get_data), Toast.LENGTH_SHORT).show()
                }
            }

            R.id.imv_favorite -> {
                followAndUnfollowTopic()
            }

            R.id.btnChallenge -> {
                if(mTopicId != ""){
                    PLAY_GAME_TYPE = ConstantsApp.PLAY_GAME_CHALLENGE
//                    var  intent = Intent(applicationContext, ChallengeFindingRandomOpponentActivity::class.java)
                    val  intent = Intent(applicationContext, ChallengeFromFriendsActivity::class.java)
                    intent.putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId)
                    startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }

            R.id.imvClose -> {
                onBackPressed()
            }

            R.id.tvRanking -> {
                val  intent = Intent(applicationContext, RankingActivity::class.java)
                intent.putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId)
                startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
            }

            R.id.tvArchiMore -> {
                val  intent = Intent(applicationContext, AchievementActivity::class.java)
                intent.putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId)
                startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
            }

            R.id.tvRank -> {
                val  intent = Intent(applicationContext, RatingTopicActivity::class.java)
                intent.putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId)
                startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
            }
        }
    }

    private fun getTopicViewDetail(){
        ProgressDialogUtils.showProgressDialog(this, 0, 0)
        mTopicDetailViewPresenter.getTopicViewDetail(mTopicId)
    }

    private fun followAndUnfollowTopic(){
        ProgressDialogUtils.showProgressDialog(this, 0, 0)
        mTopicDetailViewPresenter.followAndUnfollowTopic(mTopicId, mFavourite)
    }

    private fun getMatchId(){
        ProgressDialogUtils.showProgressDialog(this, 0, 0)
        mTopicDetailViewPresenter.getMatchId(this, mTopicId)
    }

    override fun getTopicDetailSuccess(pTopic: TopicDetail) {
        ProgressDialogUtils.dismissProgressDialog()
        mTotalLikes = pTopic.totalFollower!!.toInt()
        txt_topic_title.text = pTopic.name
//        txt_like.text = mTotalLikes.toString() + " Followers"
//        txt_topic_des.text = pTopic.description
        Picasso.get()
                .load(pTopic.url)
                .into(imvAvaTopic)

        if (pTopic.achievements!!.listDetail!!.isNotEmpty()){
            Picasso.get()
                    .load(pTopic.achievements!!.listDetail!![0].imageUnlock)
                    .into(imvAchi1)

            Picasso.get()
                    .load(PreferUtils().getAvatar(this))
                    .into(imvAva)
        }


        txt_topic_des.text = pTopic.description



        mFavourite = pTopic.statusFollow.toString()
        mLike = if (mFavourite == "0"){
            imv_favorite.setImageResource(R.drawable.ic_detail_not_favor)
            false
        } else {
            imv_favorite.setImageResource(R.drawable.ic_favor)
            true
        }

        tvLevel.text = pTopic.level.toString()
        tvJumpWins.text = pTopic.jumpWins.toString()
        tvNumberOfPlayer.text = pTopic.numberPlayed.toString()
        tvNumberOfFavourite.text = pTopic.totalFollower.toString()
        tvRanking.text = pTopic.ratings.toString()
        tvTotalXp.text = pTopic.xp.toString()
        tvNumberOfQuestion.text = pTopic.numberQuestion.toString()

        customProgress.progress = pTopic.promotionProcess!!.toInt()

    }

    override fun getMatchIdSuccess(pMatchId: SoloMatch) {
        var intent = Intent(applicationContext, SoloQuestionIntro::class.java)
        mMatchId = pMatchId.id!!
        intent.putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId)
        intent.putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId)
        startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun followAndUnfollowSuccess() {
        ProgressDialogUtils.dismissProgressDialog()
        mLike = !mLike
        if (mLike){
            imv_favorite.setImageResource(R.drawable.ic_favor)
            mTotalLikes += 1
            updateFollower((mTotalLikes).toString())
        } else {
            imv_favorite.setImageResource(R.drawable.ic_detail_not_favor)
            mTotalLikes -= 1
            updateFollower((mTotalLikes).toString())
        }
    }

    private fun updateFollower(numberOfLikes:String){
        tvNumberOfFavourite.text = numberOfLikes
    }

    override fun getTopicDetailFault() {

    }

    override fun onDestroy() {
        super.onDestroy()
        if(ConstantsApp.socketManage != null){
            ConstantsApp.socketManage.sendOfflineTopicMyself(this,mTopicId)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY -> {
                when (resultCode) {
                    ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY -> {
                        setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
                        finish()
                    }
                }
            }
        }
    }
}
