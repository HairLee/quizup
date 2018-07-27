package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Hailpt on 5/24/2018.
 */
class LiveChallenge : Serializable {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("max_user")
    @Expose
    var maxUser: Int? = null
    @SerializedName("number_register")
    @Expose
    var numberRegister: Int? = null
    @SerializedName("register_coins")
    @Expose
    var registerCoins: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("show_picture")
    @Expose
    var showPicture: String? = null
    @SerializedName("show_poster")
    @Expose
    var showPoster: Any? = null
    @SerializedName("award_text")
    @Expose
    var awardText: String? = null
    @SerializedName("publish_at")
    @Expose
    var publishAt: Int? = null
    @SerializedName("start_time")
    @Expose
    var startTime: Int? = null
    @SerializedName("end_time")
    @Expose
    var endTime: Int? = null
    @SerializedName("comment")
    @Expose
    var comment: String? = null
    @SerializedName("award_structure")
    @Expose
    var awardStructure: List<AwardStructure>? = null

    @SerializedName("registerOrNot")
    @Expose
    var registerOrNot: Int = 0





}