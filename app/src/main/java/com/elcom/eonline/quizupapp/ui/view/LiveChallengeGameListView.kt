package com.elcom.eonline.quizupapp.ui.view

import com.elcom.eonline.quizupapp.ui.activity.model.entity.ChallengeMatching
import com.elcom.eonline.quizupapp.ui.activity.model.entity.LiveChallenge
import com.google.gson.JsonElement

/**
 * Created by Hailpt on 4/27/2018.
 */
interface LiveChallengeGameListView {
    fun getListLiveGameSuccessfully(liveChallenge: List<LiveChallenge>)
    fun getListLiveGameSuccessFail()
}