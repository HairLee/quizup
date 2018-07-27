package com.elcom.com.quizupapp.ui.activity.model.entity.Followed

/**
 * Created by Hailpt on 6/25/2018.
 */
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FollowedList {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null

}