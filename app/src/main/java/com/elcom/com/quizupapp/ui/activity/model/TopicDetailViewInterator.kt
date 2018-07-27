package com.elcom.com.quizupapp.ui.activity.model

import android.content.Context
import android.util.Log
import com.elcom.com.quizupapp.ui.activity.model.entity.SoloMatch
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.TopicDetail
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.utils.PreferUtils
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hailpt on 3/26/2018.
 */
class TopicDetailViewInterator(pTopicDetailViewListener:TopicDetailViewListener) {

    val mTopicDetailViewListener = pTopicDetailViewListener

    fun getTopicViewDetail(pTopicId:String){

        RestClient().getInstance().getRestService().getTopicDetail(pTopicId).enqueue(object : Callback<RestData<TopicDetail>> {
            override fun onFailure(call: Call<RestData<TopicDetail>>?, t: Throwable?) {
                    Log.e("hailpt"," getTopicDetail onFailure")
            }

            override fun onResponse(call: Call<RestData<TopicDetail>>?, response: Response<RestData<TopicDetail>>?) {
                if(response?.body() != null){
                    mTopicDetailViewListener.getTopicDetailSuccess(response?.body().data!!)
                }
            }
        })
    }

    fun getMatchId(pContext:Context, pTopicId:String){


            RestClient().getInstance().getRestService().getTopicMatchId(PreferUtils().getUserId(pContext), pTopicId).enqueue(object : Callback<RestData<SoloMatch>>{
                override fun onResponse(call: Call<RestData<SoloMatch>>?, response: Response<RestData<SoloMatch>>?) {
                    ProgressDialogUtils.dismissProgressDialog()
                    if (response?.body() != null){
                        mTopicDetailViewListener.getMatchIdSuccess(response.body().data!!)
                    }
                }

                override fun onFailure(call: Call<RestData<SoloMatch>>?, t: Throwable?) {

                }

            })

    }

    fun followAndUnfollowTopic(pTopicId:String,pStatus:String){

        RestClient().getInstance().getRestService().followAndUnfollowTopic(pTopicId,pStatus).enqueue(object: Callback<JsonElement>{
            override fun onFailure(call: Call<JsonElement>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<JsonElement>?, response: Response<JsonElement>?) {
                mTopicDetailViewListener.followAndUnfollowSuccess()
            }
        })

    }








}