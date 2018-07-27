package com.elcom.com.quizupapp.ui.activity.model

import com.elcom.com.quizupapp.ui.activity.model.entity.AnswerQuestion
import com.elcom.com.quizupapp.ui.activity.model.entity.Question
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.utils.LogUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hailpt on 3/22/2018.
 */
class SoloMatchWithTextInteractor(pSoloMatchWithTextListener:SoloMatchWithTextListener) {



    val mSoloMatchWithTextListener = pSoloMatchWithTextListener


    fun answerTheQuestion(user_id:String,topic_id:String,answer_id:String,question_id:String,match_id:String, last_question:String){

        RestClient().getInstance().getRestService().answerTheQuestion(user_id,topic_id,answer_id,question_id,match_id,last_question).enqueue(object : Callback<RestData<AnswerQuestion>>{
            override fun onResponse(call: Call<RestData<AnswerQuestion>>?, response: Response<RestData<AnswerQuestion>>?) {
                if (response?.body() != null){
                    mSoloMatchWithTextListener.answerTheQuestionSuccess(response.body().data!!)
                }
            }

            override fun onFailure(call: Call<RestData<AnswerQuestion>>?, t: Throwable?) {

            }
        })
    }



}