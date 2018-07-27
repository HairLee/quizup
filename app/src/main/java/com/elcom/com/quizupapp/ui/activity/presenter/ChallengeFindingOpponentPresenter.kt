package com.elcom.com.quizupapp.ui.activity.presenter

import android.content.Context
import com.elcom.com.quizupapp.ui.activity.model.ChallengeFindingOpponentInteractor
import com.elcom.com.quizupapp.ui.activity.model.ChallengeFindingOpponentListener
import com.elcom.com.quizupapp.ui.activity.model.entity.ChallengeMatching
import com.elcom.com.quizupapp.ui.view.ChallengeFindingOpponentView
import com.google.gson.JsonElement

/**
 * Created by Hailpt on 4/27/2018.
 */
class ChallengeFindingOpponentPresenter(pChallengeFindingOpponentView:ChallengeFindingOpponentView):ChallengeFindingOpponentListener {


    override fun getMatchIdChallengeSuccess(pMatch: JsonElement) {
        mChallengeFindingOpponentView.getMatchIdChallengeSuccess(pMatch)
    }

    private val mChallengeFindingOpponentView = pChallengeFindingOpponentView
    private val mChallengeFindingOpponentIntegrator = ChallengeFindingOpponentInteractor(this)

    override fun findingOpponentSuccess(pChallengeMatching: ChallengeMatching) {
        mChallengeFindingOpponentView.findingOpponentSuccess(pChallengeMatching)
    }

    fun getData(topicId:String, matchId:String) {
        mChallengeFindingOpponentIntegrator.getData(topicId,matchId)
    }

    fun getMatchIdChallenge(pContext: Context, pTopicId:String){
        mChallengeFindingOpponentIntegrator.getMatchIdChallenge(pContext,pTopicId)
    }





}