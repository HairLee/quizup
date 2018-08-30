package com.elcom.eonline.quizupapp.ui.activity.model.entity.admod

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hailpt on 8/24/2018.
 */
class AdmodAds {

    @SerializedName("display_number")
    @Expose
    var displayNumber: Int? = null
    @SerializedName("ios")
    @Expose
    var ios: Ios? = null
    @SerializedName("android")
    @Expose
    var android: Android? = null

}