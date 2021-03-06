package com.elcom.eonline.quizupapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.db.MangerDB
import com.elcom.eonline.quizupapp.db.model.GamePause
import com.elcom.eonline.quizupapp.ui.activity.model.entity.Introduction
import com.elcom.eonline.quizupapp.ui.activity.model.entity.admod.AdmodAds
import com.elcom.eonline.quizupapp.ui.activity.singleplay.SoloWithImageChooseTextActivity
import com.elcom.eonline.quizupapp.ui.dialog.CongratuationNewLevelDialog
import com.elcom.eonline.quizupapp.ui.dialog.StopGameDialog
import com.elcom.eonline.quizupapp.ui.listener.OnDialogYesNoListener
import com.elcom.eonline.quizupapp.ui.listener.OnSocketInviteOpponentListener
import com.elcom.eonline.quizupapp.ui.network.RestClient
import com.elcom.eonline.quizupapp.ui.network.RestData
import com.elcom.eonline.quizupapp.utils.ConstantsApp
import com.elcom.eonline.quizupapp.utils.LogUtils
import com.elcom.eonline.quizupapp.utils.PreferUtils
import com.elcom.eonline.quizupapp.utils.ProgressDialogUtils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import com.google.gson.JsonElement
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_solo_question_intro.*
import kotlinx.android.synthetic.main.coin_and_ex_layout.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SoloQuestionIntro : BaseActivityQuiz(), RewardedVideoAdListener {

    private var mTopicId = ""
    private var mMatchId = ""
    private var mLastQuestion = "false"
    private var mQuestionNumber = 1
    private var mDisplayVideoAdmod = 0
    private var mDefaultDisplayVideoAdmod = 3
    private var mType = 1
    private var mIntroduction:Introduction? = null
    private var mMinus = ""
    override fun getLayout(): Int {
        return R.layout.activity_solo_question_intro
    }

    override fun initView() {
        btn_next.setOnClickListener {
            if(mIntroduction != null){
                val bundle = Bundle()
                bundle.putString(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId)
                bundle.putString(ConstantsApp.KEY_QUESTION_ID,mTopicId)
                bundle.putString(ConstantsApp.KEY_LAST_QUESTION,mLastQuestion)
                bundle.putString(ConstantsApp.KEY_QUESTION_NUMBER,mQuestionNumber.toString())
                bundle.putSerializable(ConstantsApp.KEY_INTRODUCTION_VALUE, mIntroduction)
                if(mIntroduction!!.type == "1"){
                    startActivityForResultQuiz(SoloMatchWithTextActivity::class.java,ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY,bundle)
                }  else if(mIntroduction!!.type == "2"){
                    startActivityForResultQuiz(SoloMatchWithImageActivity::class.java,ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY,bundle)
                } else if(mIntroduction!!.type == "3"){
                    startActivityForResultQuiz(SoloWithImageChooseTextActivity::class.java,ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY,bundle)
                } else if(mIntroduction!!.type == "4"){
                    startActivityForResultQuiz(SoloWithImageChooseTextActivity::class.java,ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY,bundle)
                }

            } else {
                Toast.makeText(this,  getString(R.string.can_not_get_data), Toast.LENGTH_SHORT).show()
            }
        }

        imvClose.setOnClickListener {
            if(mQuestionNumber == 1){
                onBackPressed()
            } else {
                val mStopGameDialog = StopGameDialog(this, object : OnDialogYesNoListener {
                    override fun clickNoAction() {

                    }

                    override fun clickYesAction() {
                        endGame()
                    }
                })
                mStopGameDialog.show()
            }
        }
    }


    override fun initData() {
        if (intent.hasExtra(ConstantsApp.KEY_QUESTION_ID)){
            mTopicId = intent.getStringExtra(ConstantsApp.KEY_QUESTION_ID)
            mMatchId = intent.getStringExtra(ConstantsApp.KEY_SOLO_MATCH_ID)
            if (intent.hasExtra(ConstantsApp.KEY_QUESTION_NUMBER)){
                mQuestionNumber = intent.getIntExtra(ConstantsApp.KEY_QUESTION_NUMBER,1)
                btn_next.text = "BẮT ĐẦU CÂU "+ mQuestionNumber
            }
            getIntroductionOfTheQuestion()
//            if( ConstantsApp.socketManage != null){
//                ConstantsApp.socketManage.initToInventionFromFriend(this)
//            }

            if(intent.hasExtra(ConstantsApp.KEY_TYPE_OF_GAME)){
                mType = 2
            }

            getSettingAdmods()
        }
    }

    override fun onResume() {
        super.onResume()
        if(mQuestionNumber > 1){
//            btnBack.visibility = View.INVISIBLE
        }
    }

    // Just for Test - Should remove as soon as possible
    private fun testPauseGame(){
        RestClient().getInstance().getRestService().pauseGame(mTopicId,mMatchId).enqueue(object: Callback<RestData<JsonElement>>{
            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                LogUtils.e("hailpt"," SoloQuestionIntro Pause Ok")
                val pauseGame = GamePause()
                pauseGame.id = 1
                pauseGame.question_number = mQuestionNumber.toString()
                pauseGame.topic_id = mTopicId
                pauseGame.matchId = mMatchId
                pauseGame.type = "2"
                MangerDB.getInstance().insertGamePause(baseContext, pauseGame)
            }
        })

    }

    private fun getIntroductionOfTheQuestion(){
        showProgessDialog()
        RestClient().getInstance().getRestService().getIntroductionOfQuestion(PreferUtils().getUserId(this),mTopicId,mQuestionNumber.toString(),mType.toString() ,mMatchId, mMinus).enqueue(object: Callback<RestData<Introduction>>{

            override fun onResponse(call: Call<RestData<Introduction>>?, response: Response<RestData<Introduction>>?) {
                if(response?.body() != null && response.body().data != null){

                    mIntroduction = response.body().data
                    tvLevel.text = "Level chủ đề : "+mIntroduction!!.level
                    tvJumpWins.text = "Chuỗi thắng hiện tại : "+mIntroduction!!.current_win_string.toString()
                    txt_coins.text = mIntroduction!!.coins
                    txt_point.text = mIntroduction!!.point
                    txt_topic.text = mIntroduction!!.nameTopic
                    tvDescription.text = mIntroduction!!.description
                    tvDes.text = mIntroduction!!.tips
                    mLastQuestion = mIntroduction!!.last_question!!

                    if(PreferUtils().getAvatar(baseContext) != ""){
                        Picasso.get()
                                .load(PreferUtils().getAvatar(baseContext))
                                .into(imvAva)
                    }

                    if(!response.body().data!!.topicImageUrl!!.isEmpty()){
                        Picasso.get()
                                .load(response.body().data!!.topicImageUrl)
                                .into(imv_topic)
                    }

                    if(mIntroduction!!.level_current == mIntroduction!!.next_level){
                        showNewLevelDialog()
                    }

                    dismisProgressDialog()

                } else {
                    Toast.makeText(baseContext,  getString(R.string.can_not_get_data), Toast.LENGTH_SHORT).show()
                    dismisProgressDialog()
                }
            }

            override fun onFailure(call: Call<RestData<Introduction>>?, t: Throwable?) {
                dismisProgressDialog()
            }

        })
    }

    private fun goToResultActivity(){
        val intent = Intent(this, SoloMatchStatisticActivity::class.java)
        intent.putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId)
        intent.putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId)
        startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun endGame(){
        showProgessDialog()
        RestClient().getInstance().getRestService().endGame(PreferUtils().getUserId(this),mTopicId,"3",mMatchId).enqueue(object : Callback<RestData<JsonElement>>{
            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                showProgessDialog()
                Log.e("hailpt","SoloMatchBreakActivity endGame onFailure")
            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                if (response?.body() != null){
                    showProgessDialog()
                    startActivityForResult(Intent(baseContext,SoloMatchResultActivity::class.java).putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId).putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId), ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
                }
            }
        })
    }

    private fun getSettingAdmods(){
        RestClient().getInstance().getRestService().getSettingAdmod().enqueue(object : Callback<RestData<AdmodAds>>{
            override fun onFailure(call: Call<RestData<AdmodAds>>?, t: Throwable?) {


            }

            override fun onResponse(call: Call<RestData<AdmodAds>>?, response: Response<RestData<AdmodAds>>?) {
                if (response?.body() != null){
                    mDefaultDisplayVideoAdmod = response.body().data!!.displayNumber!!
                }
            }
        })


    }

    private fun showVideoAdmod(){

        if(mDisplayVideoAdmod == mDefaultDisplayVideoAdmod){
            mDisplayVideoAdmod = 0
            MobileAds.initialize(this, "ca-app-pub-7842886552548626/2863752478")
            // Use an activity context to get the rewarded video instance.
            mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
            mRewardedVideoAd.rewardedVideoAdListener = this
            ProgressDialogUtils.showProgressDialog(this, 0, 0)
            mRewardedVideoAd.loadAd("ca-app-pub-7842886552548626/2863752478",
                    AdRequest.Builder().build())
        }
    }


    private fun showNewLevelDialog(){


        val congratuationDialog = CongratuationNewLevelDialog(this,1, "", object  : OnDialogYesNoListener {

            override fun clickNoAction() {

            }

            override fun clickYesAction() {


            }


        } )
        congratuationDialog.show()

    }


    private lateinit var mRewardedVideoAd: RewardedVideoAd
    override fun onRewardedVideoAdClosed() {
    }

    override fun onRewardedVideoAdLeftApplication() {
    }

    override fun onRewardedVideoAdLoaded() {
        ProgressDialogUtils.dismissProgressDialog()
        mRewardedVideoAd.show()
    }

    override fun onRewardedVideoAdOpened() {
    }

    override fun onRewardedVideoCompleted() {
    }

    override fun onRewarded(p0: RewardItem?) {
    }

    override fun onRewardedVideoStarted() {
    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        ProgressDialogUtils.dismissProgressDialog()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

        // user has given the right answer and go to the next question ( or -10 coins )
            ConstantsApp.REQUEST_CODE_FROM_QUESTION_INTRO_ACTIVITY -> LogUtils.e("hailpt", "SoloQuestionIntro resultCode " + resultCode) // Get the new Question id ( mQuestionId )

        // User does not want to play game any more
            ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY -> {
                when (resultCode) {
                    ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY -> {
                        setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
                        finish()
                    }

                    ConstantsApp.RESULT_CODE_FROM_RIGHT_ANSWER -> {
                        mMinus = "0"
                        mQuestionNumber++
                        mDisplayVideoAdmod++
                        mType = 1
                        btn_next.text = "BẮT ĐẦU CÂU "+ mQuestionNumber
                        getIntroductionOfTheQuestion()
                        showVideoAdmod()
                    }

                    ConstantsApp.RESULT_CODE_FROM_RIGHT_ANSWER_USING_COINS -> {
                        mQuestionNumber++
                        mDisplayVideoAdmod++
                        mType = 2
                        btn_next.text = "BẮT ĐẦU CÂU "+ mQuestionNumber
                        mMinus = data!!.extras.getString(ConstantsApp.KEY_MINUS_GAME)
                        getIntroductionOfTheQuestion()
                        showVideoAdmod()
                    }


                }
            }

        }

    }
}
