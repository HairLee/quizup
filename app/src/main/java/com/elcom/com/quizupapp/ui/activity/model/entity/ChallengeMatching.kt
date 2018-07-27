package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Hailpt on 4/27/2018.
 */
class ChallengeMatching : Serializable {

    @SerializedName("match_id")
    @Expose
    var matchId: String? = null




    @SerializedName("topic_id")
    @Expose
    var topicId: String? = null
    @SerializedName("user_id")
    @Expose
    var userId: String? = null
    @SerializedName("opponent")
    @Expose
    var opponent: Opponent? = null
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

    @SerializedName("question")
    @Expose
    var question: List<ChallengeQuestion>? = null

}