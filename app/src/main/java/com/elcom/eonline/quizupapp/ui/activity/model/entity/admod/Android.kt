package com.elcom.eonline.quizupapp.ui.activity.model.entity.admod

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hailpt on 8/24/2018.
 */
class Android {

    @SerializedName("admob")
    @Expose
    var admob: Admob_? = null
    @SerializedName("cms")
    @Expose
    var cms: List<Any>? = null

}