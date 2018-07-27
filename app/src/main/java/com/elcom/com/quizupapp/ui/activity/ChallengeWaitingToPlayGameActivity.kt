package com.elcom.com.quizupapp.ui.activity

import com.elcom.com.quizupapp.R
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_challenge_waiting_to_playgame.*
import org.json.JSONObject

class ChallengeWaitingToPlayGameActivity : BaseActivityQuiz() {

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
        }
    }

}
