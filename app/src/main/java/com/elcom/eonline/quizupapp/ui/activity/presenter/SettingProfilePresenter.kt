package com.elcom.eonline.quizupapp.ui.activity.presenter

import com.elcom.eonline.quizupapp.ui.activity.model.entity.LiveChallenge
import com.elcom.eonline.quizupapp.ui.activity.model.entity.profile.Profile
import com.elcom.eonline.quizupapp.ui.network.RestClient
import com.elcom.eonline.quizupapp.ui.network.RestData
import com.elcom.eonline.quizupapp.ui.view.LiveChallengeDetailView
import com.elcom.eonline.quizupapp.ui.view.LiveChallengeGameListView
import com.elcom.eonline.quizupapp.ui.view.SettingProfileView
import com.elcom.eonline.quizupapp.utils.LogUtils
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hailpt on 5/24/2018.
 */
class SettingProfilePresenter(pSettingProfileView : SettingProfileView) {


    private val mSettingProfileView = pSettingProfileView
    fun getData(showId: String){

        RestClient().getInstance().getRestService().getProfileData(showId).enqueue(object : Callback<RestData<Profile>> {
            override fun onFailure(call: Call<RestData<Profile>>?, t: Throwable?) {
                mSettingProfileView.getDataFault()
            }

            override fun onResponse(call: Call<RestData<Profile>>?, response: Response<RestData<Profile>>?) {
               if (response?.body() != null){
                   mSettingProfileView.getDataSuccessfully(response.body().data!!)
               }

            }
        })
    }

}