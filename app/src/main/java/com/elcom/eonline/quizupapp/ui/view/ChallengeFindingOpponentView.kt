package com.elcom.eonline.quizupapp.ui.view

import com.elcom.eonline.quizupapp.ui.activity.model.entity.ChallengeMatching
import com.google.gson.JsonElement

/**
 * Created by Hailpt on 4/27/2018.
 */
interface ChallengeFindingOpponentView {
    fun findingOpponentSuccess(pChallengeMatching: ChallengeMatching)
    fun getMatchIdChallengeSuccess(pMatch: JsonElement)
}