package com.elcom.eonline.quizupapp.ui.activity.model.entity.response.topicdetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Hailpt on 3/21/2018.
 */


class TopicDetail {

    @SerializedName("topic_id")
    @Expose
    var topicId: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("number_played")
    @Expose
    var numberPlayed: Int? = null
    @SerializedName("totalFollower")
    @Expose
    var totalFollower: Int? = null
    @SerializedName("statusFollow")
    @Expose
    var statusFollow: Int? = null

    @SerializedName("result_question_true")
    @Expose
    var result_question_true: Int? = null

    @SerializedName("coins")
    @Expose
    var coins: Int? = null


    @SerializedName("jump_wins")
    @Expose
    var jumpWins: Int? = null
    @SerializedName("promotion_process")
    @Expose
    var promotionProcess: Float? = null
    @SerializedName("number_question")
    @Expose
    var numberQuestion: Int? = null
    @SerializedName("xp")
    @Expose
    var xp: String? = null
    @SerializedName("ratings")
    @Expose
    var ratings: Int? = null
    @SerializedName("achievements")
    @Expose
    var achievements: Achievements? = null
    @SerializedName("level")
    @Expose
    var level: String? = null


    @SerializedName("number_challegen")
    @Expose
    var number_challegen: String? = null


    @SerializedName("number_view_video_get_challegen")
    @Expose
    var number_view_video_get_challegen: String? = null

}