package com.elcom.eonline.quizupapp.ui.activity.model.entity.admod

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hailpt on 8/24/2018.
 */
class Banner {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("active")
    @Expose
    var active: String? = null

}