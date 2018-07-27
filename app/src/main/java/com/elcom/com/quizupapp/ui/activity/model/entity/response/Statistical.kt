package com.elcom.com.quizupapp.ui.activity.model.entity.response

/**
 * Created by Hailpt on 6/27/2018.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Statistical {

    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("points")
    @Expose
    var points: String? = null

}