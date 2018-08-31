package com.elcom.eonline.quizupapp.ui.activity.singleplay

import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.BaseActivityQuiz
import com.elcom.eonline.quizupapp.ui.activity.model.entity.Introduction
import com.elcom.eonline.quizupapp.ui.custom.ProgressTimerView
import com.elcom.eonline.quizupapp.utils.ConstantsApp
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_solo_with_image_choose_text.*
import kotlinx.android.synthetic.main.coin_and_ex_layout.*

class SoloWithImageChooseTextActivity : BaseActivityQuiz(), ProgressTimerView.onFinishCountDown, OnAnswerTheQuestionListener {


    private var mQuestion: Introduction? = null
    private var mMatchId = ""
    private var mTopicId = ""
    private var mLastQuestion = ""
    private var mQuestionNumber = "1"
    private var answerList:List<String>? = null
    private var suggestList:List<String>? = null
    override fun getLayout(): Int {
        return R.layout.activity_solo_with_image_choose_text
    }

    override fun initView() {

    }

    override fun initData() {

        val handler = Handler()
        handler.postDelayed({
            //            ptvCountDown.startStop()
//            ptvCountDown.setListener(this)
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
    }

    override fun onFinishCountDown(listDemo: Boolean) {

    }

    private fun updateUI(){
        if(mQuestion != null){
            txt_coins.text = mQuestion!!.coins
            txt_point.text = mQuestion!!.point
            txt_question.text = mQuestion!!.question
            Picasso.get().load(mQuestion!!.userImageUrl).into(roundedImageView)
        }
    }

    override fun onAnswerTheQuestionListener(answer: String?) {
        if(answer != ""){
            Toast.makeText(this,answer,Toast.LENGTH_SHORT).show()
        }
    }
}
