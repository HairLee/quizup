package com.elcom.com.quizupapp.ui.activity.presenter

import com.elcom.com.quizupapp.ui.activity.model.entity.Followed.Followed
import com.elcom.com.quizupapp.ui.activity.model.entity.LiveChallenge
import com.elcom.com.quizupapp.ui.activity.model.entity.profile.Profile
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.view.LiveChallengeDetailView
import com.elcom.com.quizupapp.ui.view.LiveChallengeGameListView
import com.elcom.com.quizupapp.ui.view.SettingFollowedView
import com.elcom.com.quizupapp.ui.view.SettingProfileView
import com.elcom.com.quizupapp.utils.LogUtils
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hailpt on 5/24/2018.
 */
class SettingFollowedPresenter(pSettingFollowedView : SettingFollowedView) {


    private val mSettingFollowedView = pSettingFollowedView
    fun getData(showId: String){

        RestClient().getInstance().getRestService().getFollowed(showId).enqueue(object : Callback<RestData<Followed>> {
            override fun onFailure(call: Call<RestData<Followed>>?, t: Throwable?) {
                mSettingFollowedView.getDataFault()
            }

            override fun onResponse(call: Call<RestData<Followed>>?, response: Response<RestData<Followed>>?) {
               if (response?.body() != null){
                   mSettingFollowedView.getDataSuccessfully(response.body().data!!)
               }

            }
        })
    }

    fun getFriends(showId: String){

        RestClient().getInstance().getRestService().getFriends(showId,50,0).enqueue(object : Callback<RestData<Followed>> {
            override fun onFailure(call: Call<RestData<Followed>>?, t: Throwable?) {
                mSettingFollowedView.getDataFault()
            }

            override fun onResponse(call: Call<RestData<Followed>>?, response: Response<RestData<Followed>>?) {
                if (response?.body() != null){
                    mSettingFollowedView.getDataSuccessfully(response.body().data!!)
                }

            }
        })
    }

}