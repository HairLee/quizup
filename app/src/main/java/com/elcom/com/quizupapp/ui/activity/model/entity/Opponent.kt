package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Hailpt on 4/27/2018.
 */
class Opponent : Serializable {

    @SerializedName("userIdOpponent")
    @Expose
    var userIdOpponent: String? = null
    @SerializedName("level")
    @Expose
    var level: String? = null
    @SerializedName("statusBotUser")
    @Expose
    var statusBotUser: String? = null
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null


}