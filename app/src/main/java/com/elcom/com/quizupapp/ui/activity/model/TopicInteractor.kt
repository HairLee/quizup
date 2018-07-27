package com.elcom.com.quizupapp.ui.activity.model

import android.content.Context
import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.utils.LogUtils
import com.elcom.com.quizupapp.utils.PreferUtils
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hailpt on 3/21/2018.
 */
class TopicInteractor(pTopicListener: TopicListener, pContext:Context) {

    private val mTopicListener = pTopicListener
    private val mContext = pContext
    fun getTopicList(){

        RestClient().getInstance().getRestService().getListHomeTopic(PreferUtils().getUserId(mContext)).enqueue(object : Callback<RestData<List<Caterogy>>>{
            override fun onResponse(call: Call<RestData<List<Caterogy>>>?, response: Response<RestData<List<Caterogy>>>?) {
                if (response?.body() != null){
                    mTopicListener.getListTopicSuccess(response.body().data!!)
                } else {
                    mTopicListener.getListTopicFail()
                }
            }

            override fun onFailure(call: Call<RestData<List<Caterogy>>>?, t: Throwable?) {
                     mTopicListener.getListTopicFail()
            }
        })
    }


    fun removeHistory(matchId:Int){

        RestClient().getInstance().getRestService().removeHistory(matchId).enqueue(object : Callback<RestData<JsonElement>>{
            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                if (response?.body() != null){
                    mTopicListener.removeHistorySuccess()
                } else {
                    mTopicListener.removeHistoryFault()
                }
            }

            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                mTopicListener.getListTopicFail()
            }
        })
    }




}