package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Hailpt on 5/28/2018.
 */
class LiveChallengeBig {

    @SerializedName("listShow")
    @Expose
    var listShow: ArrayList<LiveChallenge>? = null

    @SerializedName("listShowRegistered")
    @Expose
    var listShowRegistered: ArrayList<Int>? = null


}