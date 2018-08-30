package com.elcom.eonline.quizupapp.ui.activity.model.entity.admod

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hailpt on 8/24/2018.
 */
class Admob_ {

    @SerializedName("banner")
    @Expose
    var banner: List<Banner_>? = null
    @SerializedName("video")
    @Expose
    var video: List<Video_>? = null

}