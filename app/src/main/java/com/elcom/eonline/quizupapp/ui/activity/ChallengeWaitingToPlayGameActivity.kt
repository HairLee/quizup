package com.elcom.eonline.quizupapp.ui.activity

import android.util.Log
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.model.entity.ChallengeMatching
import com.elcom.eonline.quizupapp.ui.network.RestClient
import com.elcom.eonline.quizupapp.ui.network.RestData
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_challenge_waiting_to_playgame.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChallengeWaitingToPlayGameActivity : BaseActivityQuiz() {


    private var topicId = ""
    private var opponentId = ""

    override fun getLayout(): Int {
        return R.layout.activity_challenge_waiting_to_playgame
    }

    override fun initView() {

    }

    override fun initData() {

        if(intent.hasExtra("data")){
            val data = intent.extras.getString("data")

            val mObject = JSONObject(data)

            tvName.text = mObject["name"] as String

            topicId = mObject["topicId"] as String
            opponentId = mObject["toId"] as String
            getData(topicId,opponentId)
            Log.e("hailpt"," ChallengeWaitingToPlayGameActivity "+mObject)
        }
    }


    fun getData(topicId:String, opponentId:String) {

        RestClient().getInstance().getRestService().getOpponentChallengeInfo(topicId,opponentId).enqueue(object : Callback<RestData<ChallengeMatching>> {

            override fun onFailure(call: Call<RestData<ChallengeMatching>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<RestData<ChallengeMatching>>?, response: Response<RestData<ChallengeMatching>>?) {
                if(response?.body() != null){
                    Log.e("hailpt"," ChallengeWaitingToPlayGameActivity "+(response?.body().message))
                }
            }

        })
    }

}
