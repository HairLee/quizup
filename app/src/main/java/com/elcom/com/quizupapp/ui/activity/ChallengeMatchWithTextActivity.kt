package com.elcom.com.quizupapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.view.View
import android.widget.Button
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.*
import com.elcom.com.quizupapp.ui.activity.presenter.SoloMatchWithTextPresenter
import com.elcom.com.quizupapp.ui.custom.ProgressTimerView
import com.elcom.com.quizupapp.ui.dialog.StopGameDialog
import com.elcom.com.quizupapp.ui.listener.OnDialogYesNoListener
import com.elcom.com.quizupapp.ui.listener.OnMp3FinishListener
import com.elcom.com.quizupapp.ui.listener.OnSocketListener
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.view.SoloMatchWithTextView
import com.elcom.com.quizupapp.utils.*
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.challenge_with_text_layout.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList



class ChallengeMatchWithTextActivity : BaseActivityQuiz(), View.OnClickListener, SoloMatchWithTextView,ProgressTimerView.onFinishCountDown, OnSocketListener {

    private var mChallengeMatching: ChallengeMatching? = null
    private var mMatchId = ""
    private var mTopicId = ""
    private val mButtonList = ArrayList<Button>()
    private val pSoloMatchWithTextPresenter = SoloMatchWithTextPresenter(this)
    private var mCustomButton: Utils.CustomButtom? = null
    private var mQuestionNumber = 1

    private var numberOfRightAnswerFromMe = 0
    private var numberOfRightAnswerFromOpponent = 0
//    private val mSocketManage = SocketManage()
    override fun getLayout(): Int {
       return R.layout.challenge_with_text_layout
    }

    override fun initView() {

        ConstantsApp.socketManage.init(this)
        answer_1.setOnClickListener(this)
        answer_2.setOnClickListener(this)
        answer_3.setOnClickListener(this)
        answer_4.setOnClickListener(this)

        mButtonList.add(answer_1)
        mButtonList.add(answer_2)
        mButtonList.add(answer_3)
        mButtonList.add(answer_4)

        mCustomButton = Utils.CustomButtom(mButtonList)
    }

    override fun initData() {
        if(intent.hasExtra(ConstantsApp.KEY_INTRODUCTION_VALUE)){
            val bundle = intent.extras
            mChallengeMatching = bundle.getSerializable(ConstantsApp.KEY_INTRODUCTION_VALUE) as ChallengeMatching
            mMatchId = bundle.getString(ConstantsApp.KEY_SOLO_MATCH_ID)
            mTopicId = bundle.getString(ConstantsApp.KEY_QUESTION_ID)
            mQuestionNumber = bundle.getInt(ConstantsApp.KEY_QUESTION_NUMBER)
            numberOfRightAnswerFromMe = bundle.getInt(ConstantsApp.KEY_CHALLENGE_TOTAL_RIGHT_ANSWER_ME)
            updateUI()
            updateLineScoreLayout()

            ConstantsApp.socketManage.init(this)
            ConstantsApp.socketManage.connectSocket()

            val answerList = mChallengeMatching!!.question!![mQuestionNumber].answer as List<ChallengeAnswer>
            for (i in 0 until answerList.size) {
                if (answerList[i].correct == 1){
                    mCorrectAnswer = i
                    return
                }
            }
        }
    }

    @SuppressLint("NewApi")
    private fun beginToShowAnswerLayout() {
        val handler = Handler()
        handler.postDelayed({
            AnimationUtil.setAnimationSlideLeft(ln_answer_top, true)
            AnimationUtil.setAnimationSlideRight(ln_answer_bottom, true)
        }, 500)
    }

    private var mAnswer = 4
    private var mCorrectAnswer = -1
    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.answer_1 -> {
                    mAnswer = 0
                    answerTheQuestion(0)
                }
                R.id.answer_2 -> {
                    mAnswer= 1
                    answerTheQuestion(1)
                }
                R.id.answer_3 -> {
                    mAnswer = 2
                    answerTheQuestion(2)
                }
                R.id.answer_4 -> {
                    mAnswer = 3
                    answerTheQuestion(3)
                }
                else -> {

                }
            }
        }
    }

    /*    SOCKET.IO*/
    override fun onSocketConnected() {
        LogUtils.e("SocketManage", "ChallengeMatchWithTextActivity onSocketConnected ")
        pSoloMatchWithTextPresenter.sendUserInformationBySocket(this, ConstantsApp.socketManage!!, mTopicId)
        pSoloMatchWithTextPresenter.countDownBySocket(ConstantsApp.socketManage!!, PreferUtils().getUserId(this), mChallengeMatching!!.opponent!!.userIdOpponent.toString(), mChallengeMatching!!.opponent!!.statusBotUser.toString() )
    }

    override fun onAuthentication() {
        LogUtils.e("SocketManage", "ChallengeMatchWithTextActivity onAuthentication ")
    }

    override fun onCountDown(timeToCountDown: Int) {
        LogUtils.e("SocketManage", "ChallengeMatchWithTextActivity onCountDown $timeToCountDown")
    }

    override fun onResultQuestion(resultQuestion: JSONObject) {
        LogUtils.e("SocketManage", "ChallengeMatchWithTextActivity resultQuestion ${resultQuestion["resultQuestion"]}")
//        Toast.makeText(this, resultQuestion.toString(), Toast.LENGTH_SHORT).show()


        if(resultQuestion["resultQuestion"] == "true"){
            runOnUiThread {
                numberOfRightAnswerFromOpponent +=1
                updateLineOpScoreLayout()
            }
        }
    }
    /*    SOCKET.IO*/

    /* 1. Update layout*/
    private fun updateUI(){
        if(mChallengeMatching != null){
            beginToShowAnswerLayout()
            mCustomButton!!.refreshColorAfterTheAnswer()
            txt_question.text = mChallengeMatching!!.question!![mQuestionNumber]!!.text
            answer_1.text = mChallengeMatching!!.question!![mQuestionNumber].answer!![0].text
            answer_2.text = mChallengeMatching!!.question!![mQuestionNumber].answer!![1].text
            answer_3.text = mChallengeMatching!!.question!![mQuestionNumber].answer!![2].text
            answer_4.text = mChallengeMatching!!.question!![mQuestionNumber].answer!![3].text
        }
    }

    /* 2. Give a question*/
    private fun answerTheQuestion(pAnswerIdPos:Int){
        ProgressDialogUtils.showProgressDialog(this, 0, 0)
        mCustomButton!!.changeColorWithCorrectAnswer(mAnswer,mCorrectAnswer)
        pSoloMatchWithTextPresenter.answerTheQuestion(PreferUtils().getUserId(this), mTopicId,   mChallengeMatching!!.question!![mQuestionNumber]!!.answer!![pAnswerIdPos].id.toString(),  mChallengeMatching!!.question!![mQuestionNumber].id!!.toString(), mMatchId, "false" )

        pSoloMatchWithTextPresenter.sendMyAnswerBySocket(ConstantsApp.socketManage!!, PreferUtils().getUserId(this), mChallengeMatching!!.opponent!!.userIdOpponent.toString(),mChallengeMatching!!.matchId.toString(),mTopicId, (mQuestionNumber+1).toString(),mChallengeMatching!!.question!![mQuestionNumber].answer!![pAnswerIdPos].correct.toString(), mChallengeMatching!!.opponent!!.statusBotUser.toString() )
    }

    /* 2.1 If the answer is true */
    private fun goBackToQuestionIntroActivityBecauseOfRightAnswer(){
        ln_answer_top.visibility = View.INVISIBLE
        ln_answer_bottom.visibility = View.INVISIBLE
        mQuestionNumber += 1
        numberOfRightAnswerFromMe +=1
        updateLineScoreLayout()
        updateUI()
    }

    /* 2.2 If the answer is wrong */
    private fun goToBreakActivityBecauseOfWrongAnswer(){
        ln_answer_top.visibility = View.INVISIBLE
        ln_answer_bottom.visibility = View.INVISIBLE
        mQuestionNumber += 1
        updateUI()
    }

    private fun goToResultActivity(){
        val intent = Intent(this, SoloMatchStatisticActivity::class.java)
        intent.putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId)
        intent.putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId)
        startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    /* From 2 ~> Request Api Answer the question is Ok. Return the response */
    override fun answerTheQuestionSuccess(mData: AnswerQuestion) {
        ProgressDialogUtils.dismissProgressDialog()
        if (mQuestionNumber == 6) {
            goToResultActivity()
            return
        }

        if(mData.correct == ConstantsApp.KEY_CORRECT_ANSWER){
            goBackToQuestionIntroActivityBecauseOfRightAnswer()
        } else {
            goToBreakActivityBecauseOfWrongAnswer()
        }

    }

    private fun updateLineScoreLayout(){
        lineScoreMe.changeColorOfItemWithRightAnswer(numberOfRightAnswerFromMe)
    }

    private fun updateLineOpScoreLayout(){
        lineScoreOp.changeColorOfItemWithRightAnswerOp(numberOfRightAnswerFromOpponent)
    }

    /*Request Api Answer the question isn't Ok. Return the response */
    override fun answerTheQuestionFault() {

    }

    /*Time's Up* 10s*/
    override fun onFinishCountDown(listDemo: Boolean) {

    }

    override fun onBackPressed() {
//        val mStopGameDialog = StopGameDialog(this, object : OnDialogYesNoListener {
//            override fun clickYesAction() {
//                goToResultActivity()
//            }
//        })
//        mStopGameDialog.show()
    }

    override fun onStop() {
        super.onStop()
        LogUtils.e("SocketManage", "ChallengeMatchWithTextActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e("SocketManage", "ChallengeMatchWithTextActivity onDestroy")
        RestClient().getRestService().removeTopicChallenge(mTopicId,mMatchId).enqueue(object: Callback<RestData<JsonElement>> {
            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                LogUtils.e("","Remove Ok")
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY){
            when(resultCode){

                ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY -> {
                    setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
                    finish()
                }

                ConstantsApp.RESULT_CODE_TO_CONTINUE_TO_PLAY_GAME_FROM_QUIZUPACTIVITY -> {
                    setResult(ConstantsApp.RESULT_CODE_FROM_RIGHT_ANSWER_USING_COINS)
                    finish()
                }
            }
        }
    }




}