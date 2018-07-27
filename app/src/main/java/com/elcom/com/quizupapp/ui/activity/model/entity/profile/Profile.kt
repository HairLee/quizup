package com.elcom.com.quizupapp.ui.activity.model.entity.profile

/**
 * Created by Hailpt on 6/25/2018.
 */
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Profile {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
    @SerializedName("cover")
    @Expose
    var cover: String? = null
    @SerializedName("coins")
    @Expose
    var coins: Int? = null
    @SerializedName("my_profile")
    @Expose
    var myProfile: Int? = null
    @SerializedName("total_friend")
    @Expose
    var totalFriend: Int? = null
    @SerializedName("total_follow")
    @Expose
    var totalFollow: Int? = null
    @SerializedName("total_match_solo")
    @Expose
    var totalMatchSolo: Int? = null
    @SerializedName("total_match_duel")
    @Expose
    var totalMatchDuel: Int? = null
    @SerializedName("match_duel")
    @Expose
    var matchDuel: List<Any>? = null
    @SerializedName("winning_rate")
    @Expose
    var winningRate: Int? = null
    @SerializedName("topic_follow")
    @Expose
    var topicFollow: TopicFollow? = null
    @SerializedName("achievement")
    @Expose
    var achievement: List<Any>? = null
    @SerializedName("checkFollow")
    @Expose
    var checkFollow: Int? = null

}