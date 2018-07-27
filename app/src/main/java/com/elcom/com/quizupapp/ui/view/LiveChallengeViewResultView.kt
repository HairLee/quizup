package com.elcom.com.quizupapp.ui.view

import com.elcom.com.quizupapp.ui.activity.model.entity.LiveChallengeResult
import com.google.gson.JsonElement

/**
 * Created by Hailpt on 6/4/2018.
 */
interface LiveChallengeViewResultView {

    fun getDataSuccessfully(data: List<LiveChallengeResult>)
    fun getDatafault()

    fun getTimeCountDownSuccessfully(data: JsonElement)

}