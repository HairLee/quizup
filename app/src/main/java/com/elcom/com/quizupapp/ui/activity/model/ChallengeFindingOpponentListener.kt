package com.elcom.com.quizupapp.ui.activity.model

import com.elcom.com.quizupapp.ui.activity.model.entity.ChallengeMatching
import com.google.gson.JsonElement

/**
 * Created by Hailpt on 4/27/2018.
 */
interface ChallengeFindingOpponentListener {
    fun findingOpponentSuccess(pChallengeMatching:ChallengeMatching)
    fun getMatchIdChallengeSuccess(pMatch: JsonElement)
}