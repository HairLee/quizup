package com.elcom.com.quizupapp.ui.activity.model

import android.content.Context
import com.elcom.com.quizupapp.ui.activity.model.entity.ChallengeMatching
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/**
 * Created by Hailpt on 4/27/2018.
 */
class ChallengeFindingOpponentInteractor(pChallengeFindingOpponentListener:ChallengeFindingOpponentListener) {

   private val mChallengeFindingOpponentListener = pChallengeFindingOpponentListener

    fun getData(topicId:String, matchId:String) {

        RestClient().getInstance().getRestService().getOpponentChallenge(topicId,matchId).enqueue(object : Callback<RestData<ChallengeMatching>>{

            override fun onFailure(call: Call<RestData<ChallengeMatching>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<RestData<ChallengeMatching>>?, response: Response<RestData<ChallengeMatching>>?) {
                if(response?.body() != null){
                    mChallengeFindingOpponentListener.findingOpponentSuccess(response.body().data!!)
                }
            }

        })
    }

    fun getMatchIdChallenge(pContext: Context, pTopicId:String){

        RestClient().getInstance().getRestService().getTopicMatchIdChallenge(pTopicId).enqueue(object : Callback<RestData<JsonElement>>{
            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                ProgressDialogUtils.dismissProgressDialog()
                if (response?.body() != null){
                    mChallengeFindingOpponentListener.getMatchIdChallengeSuccess(response.body().data!!)
                }
            }

            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {

            }

        })

    }
}