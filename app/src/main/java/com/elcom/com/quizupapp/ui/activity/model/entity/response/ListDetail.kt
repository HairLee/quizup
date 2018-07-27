package com.elcom.com.quizupapp.ui.activity.model.entity.response

/**
 * Created by Hailpt on 7/6/2018.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListDetail {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("trigger")
    @Expose
    var trigger: String? = null
    @SerializedName("note")
    @Expose
    var note: String? = null
    @SerializedName("image_unlock")
    @Expose
    var imageUnlock: String? = null
    @SerializedName("image_not_unlock")
    @Expose
    var imageNotUnlock: String? = null
    @SerializedName("coin")
    @Expose
    var coin: Int? = null
    @SerializedName("level")
    @Expose
    var level: String? = null

}