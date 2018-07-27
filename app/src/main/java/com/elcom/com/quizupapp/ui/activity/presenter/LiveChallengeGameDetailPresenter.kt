package com.elcom.com.quizupapp.ui.activity.presenter

import com.elcom.com.quizupapp.ui.activity.model.entity.LiveChallenge
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.view.LiveChallengeDetailView
import com.elcom.com.quizupapp.ui.view.LiveChallengeGameListView
import com.elcom.com.quizupapp.utils.LogUtils
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hailpt on 5/24/2018.
 */
class LiveChallengeGameDetailPresenter(pLiveChallengeDetailView : LiveChallengeDetailView) {


    private val mLiveChallengeGameListView = pLiveChallengeDetailView
    fun getData(coins: String, showId: String){

        RestClient().getInstance().getRestService().joinLiveChallengeGame(coins,showId).enqueue(object : Callback<RestData<JsonElement>> {
            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                mLiveChallengeGameListView.registerFault()
            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
               if (response?.body() != null){
                   mLiveChallengeGameListView.registerSuccessfully()
               }

            }
        })
    }

}