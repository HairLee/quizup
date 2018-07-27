package com.elcom.com.quizupapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.Answer
import com.elcom.com.quizupapp.ui.activity.model.entity.AnswerQuestion
import com.elcom.com.quizupapp.ui.activity.model.entity.Introduction
import com.elcom.com.quizupapp.ui.activity.presenter.SoloMatchWithTextPresenter
import com.elcom.com.quizupapp.ui.custom.ProgressTimerView
import com.elcom.com.quizupapp.ui.dialog.CongratuationDialog
import com.elcom.com.quizupapp.ui.listener.OnDialogYesNoListener
import com.elcom.com.quizupapp.ui.listener.OnMp3FinishListener
import com.elcom.com.quizupapp.ui.view.SoloMatchWithTextView
import com.elcom.com.quizupapp.utils.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_solo_question_intro.*
import kotlinx.android.synthetic.main.activity_solo_with_image_layout.*
import kotlinx.android.synthetic.main.coin_and_ex_layout.*
import java.util.*


class SoloMatchWithImageActivity : FragmentActivity(), View.OnClickListener, SoloMatchWithTextView, ProgressTimerView.onFinishCountDown, OnMp3FinishListener {


    private val mp3Manage = Mp3Manage()
    private var mQuestion: Introduction? = null
    private var mMatchId = ""
    private var mTopicId = ""
    private val mButtonList = ArrayList<Button>()
    private val pSoloMatchWithTextPresenter = SoloMatchWithTextPresenter(this)
    private var mCustomButton: Utils.CustomButtom? = null
    private var mQuestionNumber = "1"
    private var mLastQuestion = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportActionBar!!.hide()
        setContentView(R.layout.activity_solo_with_image_layout)

        initLayoutId()

        if(intent.hasExtra(ConstantsApp.KEY_INTRODUCTION_VALUE)){
            mp3Manage.setOnMp3FinishListener(this)
            val bundle = intent.extras
            mQuestion = bundle.getSerializable(ConstantsApp.KEY_INTRODUCTION_VALUE)as Introduction
            mMatchId = bundle.getString(ConstantsApp.KEY_SOLO_MATCH_ID)
            mTopicId = bundle.getString(ConstantsApp.KEY_QUESTION_ID)
            mLastQuestion = bundle.getString(ConstantsApp.KEY_LAST_QUESTION)
            mQuestionNumber = bundle.getString(ConstantsApp.KEY_QUESTION_NUMBER)
            updateUI()
            val answerList = mQuestion!!.answer as List<Answer>
            for (i in 0 until answerList.size) {
                if (answerList[i].getCorrect() == "1"){
                    mCorrectAnswer = i
                    return
                }
            }


        }

    }

    private fun initLayoutId() {

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

    @SuppressLint("NewApi")
    private fun beginToShowAnswerLayout() {
        val handler = Handler()
        handler.postDelayed({
            AnimationUtil.setAnimationSlideLeft(ln_answer_top, true)
            AnimationUtil.setAnimationSlideRight(ln_answer_bottom, true)
            ptvCountDown.startStop()
            ptvCountDown.setListener(this)
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

    private fun updateUI(){
        if(mQuestion != null) {
            beginToShowAnswerLayout()
            mCustomButton!!.refreshColorAfterTheAnswer()
            txt_coins.text = mQuestion!!.coins
            txt_point.text = mQuestion!!.point
            txt_question.text = mQuestion!!.question
            answer_1.text = mQuestion!!.answer!![0].getText()
            answer_2.text = mQuestion!!.answer!![1].getText()
            answer_3.text = mQuestion!!.answer!![2].getText()
            answer_4.text = mQuestion!!.answer!![3].getText()

            if (!mQuestion!!.questionImageUrl.isEmpty()) {
                Picasso.get()
                        .load(mQuestion!!.questionImageUrl)
                        .into(roundedImageView)
            }
        }
    }

    /*User gives a question*/
    private var currentAnswerId = ""
    private fun answerTheQuestion(pAnswerIdPos:Int){
        ProgressDialogUtils.showProgressDialog(this, 0, 0)
        ptvCountDown.stopCountDownTimer()
        currentAnswerId = mQuestion!!.answer!![pAnswerIdPos].getId().toString()
        mCustomButton!!.changeColorWithCorrectAnswer(mAnswer,mCorrectAnswer)
        if(mQuestion != null){
            pSoloMatchWithTextPresenter.answerTheQuestion(PreferUtils().getUserId(this), mTopicId, mQuestion!!.answer!![pAnswerIdPos].getId().toString(), mQuestion!!.id!!, mMatchId,mLastQuestion )
        }
    }

    private fun goBackToQuestionIntroActivityBecauseOfRightAnswer(){
        mQuestionNumber += 1
        setResult(ConstantsApp.RESULT_CODE_FROM_RIGHT_ANSWER)
        finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun goToResultActivity(){


        val congratuationDialog = CongratuationDialog(this, mQuestionNumber.toInt(), mQuestion!!.nameTopic, object  : OnDialogYesNoListener {

            override fun clickNoAction() {
                pSoloMatchWithTextPresenter.endGame(baseContext,mTopicId,mMatchId)

            }

            override fun clickYesAction() {
                val intent = Intent(baseContext, SoloMatchResultActivity::class.java)
                intent.putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId)
                intent.putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId)
                startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                pSoloMatchWithTextPresenter.endGame(baseContext,mTopicId,mMatchId)
            }


        } )
        congratuationDialog.show()

    }

    /*If the answer is wrong */
    private fun goToBreakActivityBecauseOfWrongAnswer(){
        val intent = Intent(this, SoloMatchBreakActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(ConstantsApp.KEY_INTRODUCTION_VALUE, mQuestion)
        bundle.putString(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId)
        bundle.putString(ConstantsApp.KEY_ANSWER_ID,currentAnswerId)
        bundle.putString(ConstantsApp.KEY_LAST_QUESTION,mLastQuestion)
        bundle.putString(ConstantsApp.KEY_TOPIC_ID,mTopicId)
        intent.putExtras(bundle)

        startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    /*Request Api Answer the question is Ok. Return the response */
    override fun answerTheQuestionSuccess(mData: AnswerQuestion) {

        ProgressDialogUtils.dismissProgressDialog()
        if (mLastQuestion == "true") {
            goToResultActivity()
            return
        }

        if(mData.correct == ConstantsApp.KEY_CORRECT_ANSWER){
            mp3Manage.playSong(this,1)
        } else {
            mp3Manage.playSong(this,0)
        }
    }

    override fun onMp3RightOrWrongAnswerFinished(pos: Int) {
        when(pos){
            ConstantsApp.MP3_CORRECT_ANSWER ->{
                Utils.CustomButtom(mButtonList).enableButtonClick()
                goBackToQuestionIntroActivityBecauseOfRightAnswer()
            }

            ConstantsApp.MP3_WRONG_ANSWER ->{
                Utils.CustomButtom(mButtonList).enableButtonClick()
                goToBreakActivityBecauseOfWrongAnswer()
            }
        }
    }

    /*Request Api Answer the question isn't Ok. Return the response */
    override fun answerTheQuestionFault() {

    }

    /*Time's Up* 10s*/
    override fun onFinishCountDown(listDemo: Boolean) {
        goToBreakActivityBecauseOfWrongAnswer()
    }

    override fun onBackPressed() {
        // Go to Result Screen
    }

    override fun onStop() {
        super.onStop()
        LogUtils.e("hailpt", "SoloMatchWithTextActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e("hailpt", "SoloMatchWithTextActivity onDestroy")
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