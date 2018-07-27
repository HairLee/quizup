package com.elcom.com.quizupapp.ui.activity.model.entity.profile

/**
 * Created by Hailpt on 6/25/2018.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null

}