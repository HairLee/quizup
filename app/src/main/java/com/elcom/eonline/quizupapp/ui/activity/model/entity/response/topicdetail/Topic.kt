package com.elcom.eonline.quizupapp.ui.activity.model.entity.response.topicdetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Hailpt on 3/21/2018.
 */
class Topic {

    @SerializedName("topic_id")
    @Expose
    var topic_id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("published")
    @Expose
    var published: String? = null
    @SerializedName("picture")
    @Expose
    var picture: String? = null
    @SerializedName("totalFollower")
    @Expose
    var totalFollower: String? = null
    @SerializedName("statusFollow")
    @Expose
    var statusFollow: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("jump_wins")
    @Expose
    var jump_wins: String? = null

    @SerializedName("start_at")
    @Expose
    var start_at: String? = null

    @SerializedName("match_id")
    @Expose
    var match_id: String? = null

    @SerializedName("number_played")
    @Expose
    var number_played: Int? = null

    @SerializedName("level")
    @Expose
    var level: String? = null

    @SerializedName("bonus")
    @Expose
    var bonus: String? = null

    @SerializedName("time_ago")
    @Expose
    var time_ago: String? = null


    @SerializedName("play_with_avatar")
    @Expose
    var play_with_avatar: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("result")
    @Expose
    var result: String? = null

}