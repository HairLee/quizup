package com.elcom.com.quizupapp.ui.activity.presenter

import android.util.Log
import com.elcom.com.quizupapp.ui.activity.model.entity.LiveChallengeBig
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.view.LiveChallengeGameListView
import com.elcom.com.quizupapp.utils.LogUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hailpt on 5/24/2018.
 */
class LiveChallengeGameListPresenter(pLiveChallengeGameListView : LiveChallengeGameListView) {


    private val mLiveChallengeGameListView = pLiveChallengeGameListView


    fun getData(){

        RestClient().getInstance().getRestService().getLiveChallengeList().enqueue(object : Callback<RestData<LiveChallengeBig>> {
            override fun onFailure(call: Call<RestData<LiveChallengeBig>>?, t: Throwable?) {
                mLiveChallengeGameListView.getListLiveGameSuccessFail()
            }

            override fun onResponse(call: Call<RestData<LiveChallengeBig>>?, response: Response<RestData<LiveChallengeBig>>?) {

                if (response?.body() != null){
                    val list = response.body().data!!.listShow
                    val resList = response.body().data!!.listShowRegistered


                    if(resList!!.size > 0 ){
                        for (j in (list!!.size -1) downTo 0){
                            for (i in 0.. (resList.size-1)) {
                                if (list[j].id == resList[i]){
                                    list[j].registerOrNot = -1
                                }
                            }
                        }
                    }
                    mLiveChallengeGameListView.getListLiveGameSuccessfully(list!!)
                }
            }
        })
    }

}