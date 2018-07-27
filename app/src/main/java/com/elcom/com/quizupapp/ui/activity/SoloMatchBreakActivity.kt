package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.AnswerQuestion
import com.elcom.com.quizupapp.ui.activity.model.entity.Introduction
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.PreferUtils
import com.google.gson.JsonElement
import retrofit2.Callback
import retrofit2.Response
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_solo_match_break.*
import kotlinx.android.synthetic.main.coin_and_ex_layout.*
import retrofit2.Call

class SoloMatchBreakActivity : FragmentActivity() {


    var mQuestion:Introduction? = null
    private var mMatchId = ""
    private var mTopicId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportActionBar!!.hide()
        setContentView(R.layout.activity_solo_match_break)

        if(intent.hasExtra(ConstantsApp.KEY_INTRODUCTION_VALUE)){
            val bundle = intent.extras
            mQuestion = bundle.getSerializable(ConstantsApp.KEY_INTRODUCTION_VALUE) as Introduction
            mMatchId = bundle.getString(ConstantsApp.KEY_SOLO_MATCH_ID)
            mTopicId = bundle.getString(ConstantsApp.KEY_TOPIC_ID)
            updateUI()
        }

        btn_stop_playing.setOnClickListener {
            endGame()
            startActivityForResult(Intent(this,SoloMatchResultActivity::class.java).putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId).putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId), ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        btn_continue_to_play.setOnClickListener {
            setResult(ConstantsApp.RESULT_CODE_TO_CONTINUE_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        lnStatistic.setOnClickListener {
            startActivityForResult(Intent(this,SoloMatchStatisticActivity::class.java).putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId).putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId), ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)

        }

    }

    private fun endGame(){

        RestClient().getInstance().getRestService().endGame(PreferUtils().getUserId(this),mTopicId,"3",mMatchId).enqueue(object : Callback<RestData<JsonElement>>{
            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                Log.e("hailpt","SoloMatchBreakActivity endGame onFailure")
            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                Log.e("hailpt","SoloMatchBreakActivity endGame")
                if (response?.body() != null){
                }
            }
        })
    }

    private fun updateUI(){
        if (mQuestion != null){
            txt_topic_title.text = mQuestion!!.nameTopic
            txt_coins.text = mQuestion!!.coins
            txt_point.text = mQuestion!!.point
            txt_stop_at.text = "Chuỗi thắng : "+mQuestion!!.coins
            tvNuberOfWin.text = "+ " +mQuestion!!.question_number

            if(!mQuestion!!.topicImageUrl!!.isEmpty()){
                Picasso.get()
                        .load(mQuestion!!.topicImageUrl)
                        .into(imv_topic)
            }
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY){
            when(resultCode){
                ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY -> {
                    setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY )
                    finish()
                }
            }
        }
    }

}
