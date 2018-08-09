package com.elcom.eonline.quizupapp.ui.activity

import android.util.Log
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.custom.SocketManage
import com.elcom.eonline.quizupapp.utils.ConstantsApp
import com.elcom.eonline.quizupapp.utils.PreferUtils
import kotlinx.android.synthetic.main.activity_challenge_result.*
import org.json.JSONException
import org.json.JSONObject

class ChallengeResultActivity : BaseActivityQuiz(), SocketManage.OnGetResultQuestion {


    override fun getLayout(): Int {
        return R.layout.activity_challenge_result
    }

    override fun initView() {

    }

    override fun initData() {

        if(intent.hasExtra(ConstantsApp.KEY_CHALLENGE_USER_ID)){

            val sendId = intent.extras.getString(ConstantsApp.KEY_CHALLENGE_USER_ID)
            val toId = intent.extras.getString(ConstantsApp.KEY_CHALLENGE_TO_ID)
            val statusUserBot = intent.extras.getString(ConstantsApp.KEY_CHALLENGE_USER_BOT)
            val topicId = intent.extras.getString(ConstantsApp.KEY_QUESTION_ID)
            val matchId = intent.extras.getString(ConstantsApp.KEY_SOLO_MATCH_ID)

            ConstantsApp.socketManage.setOnGetResultQuestion(this)

            getResultMatchDuel(sendId,toId,matchId,topicId,statusUserBot)
        }

    }

    override fun onBackPressed() {
        setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
        finish()
    }

    override fun onGetResultQuestion(resultQuestion: JSONObject) {
        Log.e("hailpt"," onGetResultQuestion "+ resultQuestion.toString())




        try {

            if(PreferUtils().getUserId(this).equals(resultQuestion["sendUserPoint"])){
                runOnUiThread {
                    tvMyScore.text = resultQuestion["sendUserPoint"].toString()
                    tvOpScore.text = resultQuestion["toUserPoint"].toString()

                    if (resultQuestion["sendUserPoint"] as Int > resultQuestion["sendUserPoint"] as Int) {
                        tvConfirm.text = "BẠN ĐÃ CHIẾN THẮNG"
                    } else {
                        tvConfirm.text = "BẠN ĐÃ THUA"
                    }
                }

            } else {

                runOnUiThread {
                    tvMyScore.text = resultQuestion["toUserPoint"].toString()
                    tvOpScore.text = resultQuestion["sendUserPoint"].toString()

                    if(resultQuestion["sendUserPoint"] as Int > resultQuestion["sendUserPoint"] as Int){
                        tvConfirm.text = "BẠN ĐÃ THUA"
                    } else {
                        tvConfirm.text = "BẠN ĐÃ CHIẾN THẮNG"
                    }
                }


            }



        } catch (e: JSONException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

    }

    fun getResultMatchDuel(sendId:String, toId:String,matchId:String, topicId:String, statusUserBot:String){
        val data = JSONObject()
        try {
            data.put("sendId", sendId)
            data.put("toId", toId)
            data.put("matchId", matchId)
            data.put("topicId", topicId)
            data.put("statusUserBot", statusUserBot)
        } catch (e: JSONException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        if(ConstantsApp.socketManage != null){
            ConstantsApp.socketManage.getResultMatchDuel(data)
        }

    }


}
