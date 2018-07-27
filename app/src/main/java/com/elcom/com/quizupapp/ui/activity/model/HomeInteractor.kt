package com.elcom.com.quizupapp.ui.activity.model

import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hailpt on 3/21/2018.
 */
class HomeInteractor(pTopicListener: HomeListener) {

    val mTopicListener = pTopicListener
    fun getTopicList(){

        RestClient().getInstance().getRestService().getListTopic().enqueue(object : Callback<RestData<List<Caterogy>>> {
            override fun onResponse(call: Call<RestData<List<Caterogy>>>?, response: Response<RestData<List<Caterogy>>>?) {

                if (response?.body() != null){
                    mTopicListener.getListTopicSuccess(response.body().data!!)
                }else{
                    mTopicListener.getListTopicFail()
                }
            }

            override fun onFailure(call: Call<RestData<List<Caterogy>>>?, t: Throwable?) {
                mTopicListener.getListTopicFail()
            }
        })
    }

}