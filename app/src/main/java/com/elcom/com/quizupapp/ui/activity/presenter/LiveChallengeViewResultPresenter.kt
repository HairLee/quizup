package com.elcom.com.quizupapp.ui.activity.presenter

import com.elcom.com.quizupapp.ui.activity.model.entity.LiveChallengeResult
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.view.LiveChallengeViewResultView
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hailpt on 6/4/2018.
 */
class LiveChallengeViewResultPresenter(pLiveChallengeViewResultView: LiveChallengeViewResultView) {

    private val mLiveChallengeViewResultView = pLiveChallengeViewResultView


    fun getData(showId:String){
        RestClient().getInstance().getRestService().getChallengeShowResult(showId).enqueue(object : Callback<RestData<List<LiveChallengeResult>>> {

            override fun onFailure(call: Call<RestData<List<LiveChallengeResult>>>?, t: Throwable?) {
                mLiveChallengeViewResultView.getDatafault()
            }

            override fun onResponse(call: Call<RestData<List<LiveChallengeResult>>>?, response: Response<RestData<List<LiveChallengeResult>>>?) {
                if (response?.body() != null){
                    mLiveChallengeViewResultView.getDataSuccessfully(response.body().data!!)
                }
            }

        })
    }

    fun getTimeCountDownChallengeShowResult(showId:String){
        RestClient().getInstance().getRestService().getTimeCountDownChallengeShowResult(showId).enqueue(object : Callback<RestData<JsonElement>> {

            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                mLiveChallengeViewResultView.getDatafault()
            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                if (response?.body() != null){
                    mLiveChallengeViewResultView.getTimeCountDownSuccessfully(response.body().data!!)
                }
            }

        })
    }

}