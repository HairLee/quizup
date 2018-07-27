package com.elcom.com.quizupapp.ui.activity.model.entity.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Hailpt on 7/18/2018.
 */
class StatisticalRes {

    @SerializedName("month_statistical")
    @Expose
    var monthStatistical: List<Statistical>? = null

    @SerializedName("world_statistical")
    @Expose
    var world_statistical: List<Statistical>? = null

    @SerializedName("friends_statistical")
    @Expose
    var friends_statistical: List<Statistical>? = null
}