package com.elcom.com.quizupapp.ui.activity.model.entity.response

/**
 * Created by Hailpt on 6/27/2018.
 */
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StatisticalData {

    @SerializedName("category_name")
    @Expose
    var categoryName: String? = null
    @SerializedName("topic_name")
    @Expose
    var topicName: String? = null
    @SerializedName("user_avarta")
    @Expose
    var userAvarta: String? = null
    @SerializedName("comment")
    @Expose
    var comment: String? = null
    @SerializedName("win_string")
    @Expose
    var winString: Int? = null
    @SerializedName("level")
    @Expose
    var level: String? = null
    @SerializedName("win_points")
    @Expose
    var winPoints: Int? = null
    @SerializedName("bonus_points")
    @Expose
    var bonusPoints: Int? = null
    @SerializedName("k_multiple")
    @Expose
    var kMultiple: Int? = null
    @SerializedName("total_xp")
    @Expose
    var totalXp: Int? = null
    @SerializedName("month_statistical")
    @Expose
    var monthStatistical: List<Statistical>? = null

    @SerializedName("world_statistical")
    @Expose
    var world_statistical: List<Statistical>? = null

    @SerializedName("friends_statistical")
    @Expose
    var friends_statistical: List<Statistical>? = null

}