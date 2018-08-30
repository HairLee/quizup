package com.elcom.eonline.quizupapp.ui.activity.singleplay

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.BaseActivityQuiz
import com.elcom.eonline.quizupapp.ui.activity.model.entity.Introduction
import com.elcom.eonline.quizupapp.ui.custom.ProgressTimerView
import com.elcom.eonline.quizupapp.utils.ConstantsApp
import kotlinx.android.synthetic.main.activity_solo_with_image_choose_text.*
import kotlinx.android.synthetic.main.coin_and_ex_layout.*
import java.util.ArrayList

class SoloWithImageChooseTextActivity : BaseActivityQuiz(), ProgressTimerView.onFinishCountDown {

    private var mQuestion: Introduction? = null
    private var mMatchId = ""
    private var mTopicId = ""
    private var mLastQuestion = ""
    private var mQuestionNumber = "1"
    override fun getLayout(): Int {
        return R.layout.activity_solo_with_image_choose_text
    }

    override fun initView() {

    }

    override fun initData() {

        val handler = Handler()
        handler.postDelayed({
            ptvCountDown.startStop()
            ptvCountDown.setListener(this)
        }, 500)


        if(intent.hasExtra(ConstantsApp.KEY_INTRODUCTION_VALUE)){
            val bundle = intent.extras
            mQuestion = bundle.getSerializable(ConstantsApp.KEY_INTRODUCTION_VALUE)as Introduction
            mMatchId = bundle.getString(ConstantsApp.KEY_SOLO_MATCH_ID)
            mTopicId = bundle.getString(ConstantsApp.KEY_QUESTION_ID)
            mLastQuestion = bundle.getString(ConstantsApp.KEY_LAST_QUESTION)
            mQuestionNumber = bundle.getString(ConstantsApp.KEY_QUESTION_NUMBER)
            txt_coins.text ="mQuestion!!.coins"
            updateUI()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solo_with_image_choose_text)
        setupData()
    }

    fun setupData(){
        val mData = ArrayList<String>()
        mData.add("")
        mData.add("")
        mData.add("")
        mData.add("")



        val mSuggestData = ArrayList<String>()
        mSuggestData.add("A")
        mSuggestData.add("B")
        mSuggestData.add("C")
        mSuggestData.add("D")
        mSuggestData.add("E")

        mSuggestData.add("A")
        mSuggestData.add("B")
        mSuggestData.add("C")
        mSuggestData.add("D")
        mSuggestData.add("E")
        mSuggestData.add("A")
        mSuggestData.add("B")


        lnSoloWithImageChooseView.setDataForAnswerList(mData)
        lnSoloWithImageChooseView.setDataForSuggestList(mSuggestData)
    }

    override fun onFinishCountDown(listDemo: Boolean) {

    }

    private fun updateUI(){
        if(mQuestion != null){
//            txt_coins.text ="mQuestion!!.coins"
            txt_point.text = mQuestion!!.point
            txt_question.text = mQuestion!!.question
        }
    }
}
