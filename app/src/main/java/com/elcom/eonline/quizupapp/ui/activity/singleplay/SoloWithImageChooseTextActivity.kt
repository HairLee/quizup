package com.elcom.eonline.quizupapp.ui.activity.singleplay

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.BaseActivityQuiz
import com.elcom.eonline.quizupapp.ui.activity.SoloMatchBreakActivity
import com.elcom.eonline.quizupapp.ui.activity.SoloMatchResultActivity
import com.elcom.eonline.quizupapp.ui.activity.model.entity.AnswerQuestion
import com.elcom.eonline.quizupapp.ui.activity.model.entity.Introduction
import com.elcom.eonline.quizupapp.ui.activity.presenter.SoloMatchWithTextPresenter
import com.elcom.eonline.quizupapp.ui.custom.ProgressTimerView
import com.elcom.eonline.quizupapp.ui.dialog.CongratuationDialog
import com.elcom.eonline.quizupapp.ui.listener.OnDialogYesNoListener
import com.elcom.eonline.quizupapp.ui.listener.OnMp3FinishListener
import com.elcom.eonline.quizupapp.ui.network.RestClient
import com.elcom.eonline.quizupapp.ui.network.RestData
import com.elcom.eonline.quizupapp.ui.view.SoloMatchWithTextView
import com.elcom.eonline.quizupapp.utils.*
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_solo_with_image_choose_text.*
import kotlinx.android.synthetic.main.coin_and_ex_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SoloWithImageChooseTextActivity : BaseActivityQuiz(), ProgressTimerView.onFinishCountDown, OnAnswerTheQuestionListener, OnMp3FinishListener, SoloMatchWithTextView {



    private var mQuestion: Introduction? = null
    private var mMatchId = ""
    private var mTopicId = ""
    private var mLastQuestion = ""
    private var mQuestionNumber = "1"
    private var mRightAnswer = ""
    private var answerList:List<String>? = null
    private var suggestList:List<String>? = null
    private val mp3Manage = Mp3Manage()
    private val pSoloMatchWithTextPresenter = SoloMatchWithTextPresenter(this)
    override fun getLayout(): Int {
        return R.layout.activity_solo_with_image_choose_text
    }

    override fun initView() {
        mp3Manage.setOnMp3FinishListener(this)
    }

    override fun initData() {

        val handler = Handler()
        handler.postDelayed({
            ptvCountDown.startStop(30)
            ptvCountDown.setListener(this)
        }, 500)


        if(intent.hasExtra(ConstantsApp.KEY_INTRODUCTION_VALUE)){
            val bundle = intent.extras
            mQuestion = bundle.getSerializable(ConstantsApp.KEY_INTRODUCTION_VALUE)as Introduction
            mMatchId = bundle.getString(ConstantsApp.KEY_SOLO_MATCH_ID)
            mTopicId = bundle.getString(ConstantsApp.KEY_QUESTION_ID)
            mLastQuestion = bundle.getString(ConstantsApp.KEY_LAST_QUESTION)
            mQuestionNumber = bundle.getString(ConstantsApp.KEY_QUESTION_NUMBER)
            updateUI()

            answerList = mQuestion!!.answer!![0].answer_corect
            suggestList = mQuestion!!.answer!![0].answer_incorrect
            setupData()
            Log.e("hailpt"," SoloWithImageChooseTextActivity "+ Gson().toJson(answerList))
        }

    }

    fun setupData(){
        val mData = ArrayList<String>()

        for (i in answerList!!.indices) {
            mData.add("")
        }


        lnSoloWithImageChooseView.setOnSoloChooseTextListener(this)
        lnSoloWithImageChooseView.setDataForAnswerList(mData)
        lnSoloWithImageChooseView.setDataForSuggestList(suggestList)

        for (i in 0 until mQuestion!!.answer!![0].answer_corect!!.size) {
            mRightAnswer += mQuestion!!.answer!![0].answer_corect!![i]
        }
    }

    override fun onFinishCountDown(listDemo: Boolean) {
        answerTheQuestion("")

    }

    private fun updateUI(){
        if(mQuestion != null){
            txt_coins.text = mQuestion!!.coins
            txt_point.text = mQuestion!!.point

            if(mQuestion!!.type == "3"){
                roundedImageView.visibility = View.GONE
                txt_question.visibility = View.GONE
                tvSingle.visibility = View.VISIBLE
                tvSingle.text = mQuestion!!.question
            } else {
                txt_question.visibility = View.VISIBLE
                txt_question.text = mQuestion!!.question
                Picasso.get().load(mQuestion!!.questionImageUrl).into(roundedImageView)
            }

        }
    }

    override fun onAnswerTheQuestionListener(answer: String?) {
        if(answer != ""){
            if(answer!!.length == mRightAnswer.length){
                lnSoloWithImageChooseView.changeLayoutWithWrongAnswer(mRightAnswer, answer)
                if(answer != mRightAnswer){

                } else {
                    currentAnswerId = answer!!
                    answerTheQuestion(currentAnswerId)
                }
            }

        }
    }


    private var currentAnswerId = ""
    fun answerTheQuestion(answer:String){
        showProgessDialog()
        ptvCountDown.stopCountDownTimer()
        RestClient().getInstance().getRestService().sendAnswerGuessWord(PreferUtils().getUserId(this),mTopicId,answer,mQuestion!!.id!!,mMatchId,mLastQuestion).enqueue(object : Callback<RestData<JsonElement>> {

            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                dismisProgressDialog()
            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                dismisProgressDialog()
                if(response?.body() != null){
                    if(response.body().message!!.contains("right answer")){
                        afterAnswerTheQuestion(true)
                    } else {
                        afterAnswerTheQuestion(false)
                    }
                }
            }

        });

    }

    fun afterAnswerTheQuestion(isAnswer:Boolean){
        ProgressDialogUtils.dismissProgressDialog()
        if (mLastQuestion == "true" /*&& isAnswer*/) {
            goToResultActivity()
            return
        } else if(mLastQuestion == "true" &&  !isAnswer) {
            intentGoToResultActivity()
            return
        }

        if(PreferUtils().getSoundSetting(this)){
            if(isAnswer){
                mp3Manage.playSong(this,1)
            } else {
                mp3Manage.playSong(this,0)
            }
        } else {
//            Utils.CustomButtom(mButtonList).enableButtonClick()
            if(isAnswer){
                goBackToQuestionIntroActivityBecauseOfRightAnswer()
            } else {
                goToBreakActivityBecauseOfWrongAnswer()
            }
        }

    }

    private fun goToResultActivity(){

        val congratuationDialog = CongratuationDialog(this, mQuestionNumber.toInt(), mQuestion!!.nameTopic, object  : OnDialogYesNoListener {

            override fun clickNoAction() {
                pSoloMatchWithTextPresenter.endGame(baseContext,mTopicId,mMatchId)

            }

            override fun clickYesAction() {
                intentGoToResultActivity()
            }


        } )
        congratuationDialog.show()

    }

    private fun intentGoToResultActivity(){
        val intent = Intent(baseContext, SoloMatchResultActivity::class.java)
        intent.putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId)
        intent.putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId)
        startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        pSoloMatchWithTextPresenter.endGame(baseContext,mTopicId,mMatchId)
    }

    private fun goBackToQuestionIntroActivityBecauseOfRightAnswer(){
        mQuestionNumber += 1
        setResult(ConstantsApp.RESULT_CODE_FROM_RIGHT_ANSWER)
        finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    /* 2.2 If the answer is wrong */
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

    override fun onMp3RightOrWrongAnswerFinished(pos: Int) {
        when(pos){
            ConstantsApp.MP3_CORRECT_ANSWER ->{
                goBackToQuestionIntroActivityBecauseOfRightAnswer()
            }

            ConstantsApp.MP3_WRONG_ANSWER ->{
                goToBreakActivityBecauseOfWrongAnswer()
            }
        }
    }

    override fun answerTheQuestionSuccess(mData: AnswerQuestion) {

    }

    override fun answerTheQuestionFault() {

    }

    override fun onBackPressed() {

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

                    val mMinus = data!!.extras.getString(ConstantsApp.KEY_MINUS_GAME)
                    val intent = Intent()
                    intent.putExtra(ConstantsApp.KEY_MINUS_GAME, mMinus)

                    setResult(ConstantsApp.RESULT_CODE_FROM_RIGHT_ANSWER_USING_COINS,intent)
                    finish()
                }
            }
        }

    }
}
