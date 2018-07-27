package com.elcom.com.quizupapp.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.ChallengeQuestion
import com.elcom.com.quizupapp.ui.activity.model.entity.Cheeses
import com.elcom.com.quizupapp.ui.activity.model.entity.LiveChallenge
import com.elcom.com.quizupapp.ui.activity.model.entity.LiveQuestionData
import com.elcom.com.quizupapp.ui.activity.presenter.LiveChallengeInviteAndPlayGamePresenter
import com.elcom.com.quizupapp.ui.adapter.LiveChallengeCommentAdapter
import com.elcom.com.quizupapp.ui.custom.ProgressTimerView
import com.elcom.com.quizupapp.utils.AnimationUtil
import kotlinx.android.synthetic.main.activity_invite_friends.*
import java.util.*
import com.elcom.com.quizupapp.ui.dialog.ChallengeInventedFriendDialog
import com.elcom.com.quizupapp.ui.dialog.LiveChallengeWrongAnswerDialog
import com.elcom.com.quizupapp.ui.dialog.YouAreWinerDialog
import com.elcom.com.quizupapp.ui.listener.OnDialogInvitationListener
import com.elcom.com.quizupapp.ui.listener.OnLiveChallengeWinnerDialogListener
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.view.LiveChallengeInviteAndPlayGameView
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.LogUtils
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LiveChallengeInviteAndPlayGameActivity : BaseActivityQuiz(), LiveChallengeInviteAndPlayGameView, ProgressTimerView.onFinishCountDown {




    private val mLiveChallengeInviteAndPlayGamePresenter = LiveChallengeInviteAndPlayGamePresenter(this)
    private var mShowId:String = ""
    private var mLocationOfCurrentQuestion = 1
    private var mTotalNumberOfQuestion = 0
    private var mChallengeQuestions:LiveQuestionData? = null
    override fun getLayout(): Int {
        return R.layout.activity_invite_friends
    }

    override fun initView() {

    }

    override fun initData() {

        if(intent.hasExtra(ConstantsApp.KEY_LIVE_CHALLENGE_ID_VALUE)){
            mShowId = intent.getStringExtra(ConstantsApp.KEY_LIVE_CHALLENGE_ID_VALUE)
//            ProgressDialogUtils.showProgressDialog(this, 0, 0)
            mLiveChallengeInviteAndPlayGamePresenter.getTimeCountDownLiveQuestion(mShowId)
            mLiveChallengeInviteAndPlayGamePresenter.getIndivitualQuestion(mShowId,mLocationOfCurrentQuestion.toString())
            lnLivePlayGame.setLiveChallengeInviteAndPlayGamePresenter(mLiveChallengeInviteAndPlayGamePresenter,mShowId)
        } else {
            return
        }

        setupRecyclerView()

    }

    // Get Question
    override fun getChallengeQuestionListSuccessfully(challengeQuestions: List<ChallengeQuestion>) {

    }

    override fun getChallengeQuestionFault() {

    }

    override fun getTimeCountDownQuestionSuccessfully(timeCountDown: Long) {
        Handler().postDelayed(Runnable {
            lnTimer.visibility = View.INVISIBLE
            AnimationUtil.setAnimationSlideRight(lnLivePlayGame,true);

            lnRoot.setBackgroundResource(R.drawable.challenge_invite_bg2)
            viewShadow.setBackgroundResource(R.drawable.challenge_invite_item_top_shadow2)

            lnLivePlayGame.setListener(this)
            lnLivePlayGame.startCountDownTimer()

        }, timeCountDown*1000)
    }

    override fun getTimeCountDownChallengeQuestionFault() {

    }

    override fun getChallengeQuestionListFault() {

    }

    override fun getChallengeQuestionSuccessfully(challengeQuestions: LiveQuestionData) {
        Log.e("hailpt"," getChallengeQuestionSuccessfully  timeCountDown "+challengeQuestions.timeCountDown)
        mChallengeQuestions = challengeQuestions
        mTotalNumberOfQuestion = challengeQuestions.totalQuestion
        lnLivePlayGame.updateLayoutAfterGettingQuestionList(challengeQuestions, mLocationOfCurrentQuestion,mTotalNumberOfQuestion)
    }

    /* User clicks Back Button while playing */
    override fun breakLiveChallengeQuestion(data: JsonElement) {
        doItWhenUserGivesAWrongAnswerOrTimesUp()
        setResult(ConstantsApp.START_ACTIVITY_TO_MOVE_FROM_LIVECHALLENGE_EXIT)
        finish()
    }

    override fun getResultAfterAnsweringTheQuestion(data: JsonElement) {

        if(data.asJsonObject.get("correct").toString() == "0"){
            doItWhenUserGivesAWrongAnswerOrTimesUp()
            Toast.makeText(this, "Wrong Answer !", Toast.LENGTH_SHORT).show()
            return
        }

        mLocationOfCurrentQuestion += 1
        if(mLocationOfCurrentQuestion < mTotalNumberOfQuestion+1){
            lnLivePlayGame.updateData()

            Handler().postDelayed(Runnable {
                ProgressDialogUtils.dismissProgressDialog()
                lnLivePlayGame.resetLayOutWidthOfAnswerBar()
                AnimationUtil.setAnimationSlideLeft(lnLivePlayGame,false)
            },500)

            Handler().postDelayed(Runnable {
                lnLivePlayGame.visibility = View.INVISIBLE
                AnimationUtil.setAnimationSlideRight(lnLivePlayGame,true)
                lnLivePlayGame.startCountDownTimer()
                mLiveChallengeInviteAndPlayGamePresenter.getIndivitualQuestion(mShowId,mLocationOfCurrentQuestion.toString())
            }, 900)

        } else {
            // Answer right all of the questions
            ProgressDialogUtils.dismissProgressDialog()
            mLiveChallengeInviteAndPlayGamePresenter.endLiveChallengeGame(mShowId)
            val youAreWinnerDialog = YouAreWinerDialog(this, object : OnLiveChallengeWinnerDialogListener {
                override fun onViewTheResult(mContext:Context) {
                    startActivityForResult(Intent(mContext,LiveChallengeViewResultActivity::class.java).putExtra(ConstantsApp.KEY_LIVECHALLENGE_SHOWID,mShowId).putExtra(ConstantsApp.KEY_LIVECHALLENGE_TOTAL,mTotalNumberOfQuestion),ConstantsApp.START_ACTIVITY_TO_MOVE_FROM_LIVECHALLENGE)
                }

                override fun onShareTheResult() {

                }

            })
            youAreWinnerDialog.show()
            lnLivePlayGame.updateData()
        }
    }

    override fun onFinishCountDown(listDemo: Boolean) {
        doItWhenUserGivesAWrongAnswerOrTimesUp()
        val wrongAnswerDialog = LiveChallengeWrongAnswerDialog(this, object : OnLiveChallengeWinnerDialogListener {
            override fun onViewTheResult(context:Context) {
                setResult(ConstantsApp.START_ACTIVITY_TO_MOVE_FROM_LIVECHALLENGE_EXIT)
                finish()
            }

            override fun onShareTheResult() {
                Toast.makeText(applicationContext, "Share !!", Toast.LENGTH_SHORT).show()
            }

        })
        wrongAnswerDialog.show()
    }

    private fun doItWhenUserGivesAWrongAnswerOrTimesUp(){
        mLiveChallengeInviteAndPlayGamePresenter.breakLiveChallengeQuestion(mShowId,mChallengeQuestions!!.question.id.toString(),"11","6")
    }

    // Answer right all of the question, and END GAME
    override fun endLiveChallengeGameSuccessfully(data: JsonElement) {
        LogUtils.e("hailpt"," LiveChallengeInviteAndPlayGameActivity endLiveChallengeGameSuccessfully ")
    }

    override fun endLiveChallengeGameFault() {
        LogUtils.e("hailpt"," LiveChallengeInviteAndPlayGameActivity endLiveChallengeGameFault ")
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = LiveChallengeCommentAdapter(this,
                getRandomSublist(Cheeses.sCheeseStrings, 30))
    }

    private fun getRandomSublist(array: Array<String>, amount: Int): List<String> {
        val list = ArrayList<String>(amount)
        val random = Random()
        while (list.size < amount) {
            list.add(array[random.nextInt(array.size)])
        }
        return list
    }

    override fun onBackPressed() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ConstantsApp.START_ACTIVITY_TO_MOVE_FROM_LIVECHALLENGE ){
            when(resultCode){
                ConstantsApp.START_ACTIVITY_TO_MOVE_FROM_LIVECHALLENGE_EXIT -> {
                    finish()
                }
            }

        }
    }

}
