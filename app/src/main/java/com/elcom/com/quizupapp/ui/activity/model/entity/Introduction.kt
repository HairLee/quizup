package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Hailpt on 3/22/2018.
 */
class Introduction : Serializable {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("coins")
    @Expose
    var coins: String? = null
    @SerializedName("point")
    @Expose
    var point: String? = null
    @SerializedName("name_topic")
    @Expose
    var nameTopic: String? = null
    @SerializedName("topic_image_url")
    @Expose
    var topicImageUrl: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("user_image_url")
    @Expose
    var userImageUrl: String? = null
    @SerializedName("question")
    @Expose
    var question: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("question_image_url")
    @Expose
    var questionImageUrl: String = ""
    @SerializedName("answer")
    @Expose
    var answer: List<Answer>? = null

    @SerializedName("question_number")
    @Expose
    var question_number: String? = null

    @SerializedName("last_question")
    @Expose
    var last_question: String? = null


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

    @SerializedName("jump_wins")
    @Expose
    var jump_wins: Int? = null

}