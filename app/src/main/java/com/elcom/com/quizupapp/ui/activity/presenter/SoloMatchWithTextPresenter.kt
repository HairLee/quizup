package com.elcom.com.quizupapp.ui.activity.presenter

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.elcom.com.quizupapp.ui.activity.model.SoloMatchWithTextInteractor
import com.elcom.com.quizupapp.ui.activity.model.SoloMatchWithTextListener
import com.elcom.com.quizupapp.ui.activity.model.entity.AnswerQuestion
import com.elcom.com.quizupapp.ui.activity.model.entity.Question
import com.elcom.com.quizupapp.ui.custom.SocketManage
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.view.LoginView
import com.elcom.com.quizupapp.ui.view.SoloMatchWithTextView
import com.elcom.com.quizupapp.utils.PreferUtils
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hailpt on 3/22/2018.
 */
class SoloMatchWithTextPresenter(pSoloMatchWithTextView: SoloMatchWithTextView):SoloMatchWithTextListener {


    private val soloMatchWithTextInteractor = SoloMatchWithTextInteractor(this)
    private val mSoloMatchWithTextView = pSoloMatchWithTextView

    fun answerTheQuestion(user_id:String,topic_id:String,answer_id:String,question_id:String,match_id:String, last_question:String){
        soloMatchWithTextInteractor.answerTheQuestion(user_id,topic_id,answer_id,question_id,match_id,last_question)
    }


    override fun answerTheQuestionSuccess(mData: AnswerQuestion) {
        mSoloMatchWithTextView.answerTheQuestionSuccess(mData)
    }

    override fun answerTheQuestionFault() {

    }

    fun sendUserInformationBySocket(pContext: Context, pSocketManage: SocketManage, pTopicId:String){
//        val myInfo = JSONObject()
//        try {
//            myInfo.put("topicId", PreferUtils().getUserId(pContext))
//            myInfo.put("token", PreferUtils().getToken(pContext))
//               } catch (e: JSONException) {
//            // TODO Auto-generated catch block
//            e.printStackTrace()
//        }
//
//        pSocketManage.sendUserInformationBySocket(myInfo)
    }


    fun countDownBySocket(pSocketManage: SocketManage, sendId:String, toId:String,statusUserBot:String) {

        val myInfo = JSONObject()
        try {
            myInfo.put("sendId", sendId)
            myInfo.put("toId", toId)
            myInfo.put("statusUserBot", statusUserBot)
        } catch (e: JSONException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        pSocketManage.countDownBySocket(myInfo)
    }

    fun sendMyAnswerBySocket(pSocketManage: SocketManage, sendId:String, toId:String,matchId:String, topicId:String, numberQuestion:String, resultQuestion:String, statusUserBot:String){
        val data = JSONObject()
        try {
            data.put("sendId", sendId)
            data.put("toId", toId)
            data.put("matchId", matchId)
            data.put("topicId", topicId)
            data.put("numberQuestion", numberQuestion)
            data.put("resultQuestion", resultQuestion)
            data.put("statusUserBot", statusUserBot)
        } catch (e: JSONException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        pSocketManage.sendMyAnswerBySocket(data)

    }

     fun endGame(context:Context, topicId:String, matchId:String){

        RestClient().getInstance().getRestService().endGame(PreferUtils().getUserId(context),topicId,"3",matchId).enqueue(object : Callback<RestData<JsonElement>> {
            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                Log.e("hailpt","SoloMatchBreakActivity endGame")
                if (response?.body() != null){
                }
            }
        })
    }



}