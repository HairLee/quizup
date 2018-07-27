package com.elcom.com.quizupapp.ui.activity.model.entity

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("mobile")
    @Expose
    var mobile: String? = null
    @SerializedName("facebook_id")
    @Expose
    var facebookId: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("coins")
    @Expose
    var coins: String? = null
    @SerializedName("verified")
    @Expose
    var verified: String? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
    @SerializedName("token")
    @Expose
    var token: String = ""

}