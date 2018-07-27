package com.elcom.com.quizupapp.ui.activity.presenter

import com.elcom.com.quizupapp.ui.activity.model.entity.ChallengeQuestion
import com.elcom.com.quizupapp.ui.activity.model.entity.LiveQuestionData
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.view.LiveChallengeInviteAndPlayGameView
import com.elcom.com.quizupapp.utils.LogUtils
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hailpt on 5/25/2018.
 */
class LiveChallengeInviteAndPlayGamePresenter(pLiveChallengeInviteAndPlayGameView: LiveChallengeInviteAndPlayGameView) {


    private val mLiveChallengeInviteAndPlayGameView = pLiveChallengeInviteAndPlayGameView


    fun getData( showId: String){

        RestClient().getInstance().getRestService().getLiveChallengeQuestionList(showId).enqueue(object : Callback<RestData<List<ChallengeQuestion>>> {
            override fun onFailure(call: Call<RestData<List<ChallengeQuestion>>>?, t: Throwable?) {
                mLiveChallengeInviteAndPlayGameView.getChallengeQuestionListFault()
            }

            override fun onResponse(call: Call<RestData<List<ChallengeQuestion>>>?, response: Response<RestData<List<ChallengeQuestion>>>?) {
                if (response?.body() != null){
                    mLiveChallengeInviteAndPlayGameView.getChallengeQuestionListSuccessfully(response.body().data!!)
                }
            }
        })
    }

    fun answerLiveChallengeQuestion(showId:String, answerId:String, questionId:String, timeAnswer:String){
        RestClient().getInstance().getRestService().answerLiveChallengeQuestion(showId,answerId,questionId,timeAnswer).enqueue(object : Callback<RestData<JsonElement>> {

            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                mLiveChallengeInviteAndPlayGameView.getChallengeQuestionFault()
            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                if (response?.body() != null){
                    mLiveChallengeInviteAndPlayGameView.getResultAfterAnsweringTheQuestion(response.body().data!!)
                }
            }
        })
    }

    fun breakLiveChallengeQuestion(showId:String, answerId:String, questionId:String, timeAnswer:String){
        RestClient().getInstance().getRestService().breakLiveChallengeQuestion(showId,answerId,questionId,timeAnswer).enqueue(object : Callback<RestData<JsonElement>> {

            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                mLiveChallengeInviteAndPlayGameView.getChallengeQuestionListFault()
            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                if (response?.body() != null){
                    mLiveChallengeInviteAndPlayGameView.breakLiveChallengeQuestion(response.body().data!!)
                }
            }

        })
    }

    fun endLiveChallengeGame(showId:String){
        RestClient().getInstance().getRestService().endLiveChallengeGame(showId).enqueue(object : Callback<RestData<JsonElement>> {

            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                mLiveChallengeInviteAndPlayGameView.endLiveChallengeGameFault()
            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                if (response?.body() != null){
                    mLiveChallengeInviteAndPlayGameView.endLiveChallengeGameSuccessfully(response.body().data!!)
                }
            }

        })
    }

    fun getIndivitualQuestion( showId: String, numberOfTheQuestion: String){

        RestClient().getInstance().getRestService().getLiveQuestion(showId,numberOfTheQuestion).enqueue(object : Callback<RestData<LiveQuestionData>> {
            override fun onFailure(call: Call<RestData<LiveQuestionData>>?, t: Throwable?) {
                mLiveChallengeInviteAndPlayGameView.getChallengeQuestionListFault()
            }

            override fun onResponse(call: Call<RestData<LiveQuestionData>>?, response: Response<RestData<LiveQuestionData>>?) {
                if (response?.body() != null){
                    mLiveChallengeInviteAndPlayGameView.getChallengeQuestionSuccessfully(response.body().data!!)
                }
            }
        })
    }

    fun getTimeCountDownLiveQuestion(showId: String){
        RestClient().getInstance().getRestService().getTimeCountDownLiveQuestion(showId).enqueue(object : Callback<RestData<JsonElement>> {
            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                mLiveChallengeInviteAndPlayGameView.getChallengeQuestionListFault()
            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                if (response?.body() != null){
                    mLiveChallengeInviteAndPlayGameView.getTimeCountDownQuestionSuccessfully(10)
                }
            }
        })
    }

}