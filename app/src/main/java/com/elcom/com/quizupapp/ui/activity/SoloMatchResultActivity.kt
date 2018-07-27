package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.Result
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.PreferUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_solo_match_result.*
import kotlinx.android.synthetic.main.coin_and_ex_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SoloMatchResultActivity : BaseActivityQuiz() {


    private var mMatchId = "1"
    private var mTopicId = "1"
    override fun getLayout(): Int {
        return R.layout.activity_solo_match_result
    }

    override fun initView() {
        btnStop.setOnClickListener {
            setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
            finish()
        }

        lnStatistic.setOnClickListener {
            startActivityForResult(Intent(this,SoloMatchStatisticActivity::class.java).putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId).putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId), ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
        }
    }

    override fun initData() {
        if(intent.hasExtra(ConstantsApp.KEY_SOLO_MATCH_ID)){
            mMatchId = intent.getStringExtra(ConstantsApp.KEY_SOLO_MATCH_ID)
            mTopicId = intent.getStringExtra(ConstantsApp.KEY_QUESTION_ID)
            getResult()
        }
    }

    private fun getResult(){

        RestClient().getInstance().getRestService().getResultAfterPlayingGame( PreferUtils().getUserId(this),mTopicId,mMatchId).enqueue(object: Callback<RestData<Result>> {
            override fun onResponse(call: Call<RestData<Result>>?, response: Response<RestData<Result>>?) {
                if (response?.body() != null){
                    updateLayout(response.body().data!!)
                }
            }

            override fun onFailure(call: Call<RestData<Result>>?, t: Throwable?) {

            }
        })
    }

    private fun updateLayout(result:Result){
        tvTitle.text = result.nameTopic
        txt_topic_title.text = result.nameTopic
        Picasso.get().load(result.topicImageUrl).into(imvTopic)
        txt_coins.text = result.coins
        txt_point.text = result.pointTotal
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
