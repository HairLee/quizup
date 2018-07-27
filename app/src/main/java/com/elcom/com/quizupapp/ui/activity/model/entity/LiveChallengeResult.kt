package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hailpt on 6/4/2018.
 */
class LiveChallengeResult {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("show_id")
    @Expose
    var showId: Int? = null
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null
    @SerializedName("answer_time")
    @Expose
    var answerTime: Int? = null
    @SerializedName("answer_correct")
    @Expose
    var answerCorrect: Int? = null
    @SerializedName("win_award")
    @Expose
    var winAward: String? = null
    @SerializedName("win_coins")
    @Expose
    var winCoins: Int? = null
    @SerializedName("rank")
    @Expose
    var rank: Int? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: Int? = null
    @SerializedName("break_at")
    @Expose
    var breakAt: Int? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: Int? = null
    @SerializedName("same_grade")
    @Expose
    var sameGrade: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null

}