package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hailpt on 3/27/2018.
 */
class Result {

    @SerializedName("coins")
    @Expose
    var coins: String? = null
    @SerializedName("point_answer")
    @Expose
    var pointAnswer: String? = null
    @SerializedName("point_total")
    @Expose
    var pointTotal: String? = null
    @SerializedName("name_topic")
    @Expose
    var nameTopic: String? = null
    @SerializedName("topic_image_url")
    @Expose
    var topicImageUrl: String? = null
    @SerializedName("level")
    @Expose
    var level: String? = null

    @SerializedName("point_next_level")
    @Expose
    var point_next_level: String? = null

    @SerializedName("next_level")
    @Expose
    var next_level: String? = null

    @SerializedName("current_win_string")
    @Expose
    var current_win_string: String? = null

}