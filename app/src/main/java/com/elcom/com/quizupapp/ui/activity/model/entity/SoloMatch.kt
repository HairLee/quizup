package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hailpt on 3/26/2018.
 */
class SoloMatch {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("topic_id")
    @Expose
    var topicId: String? = null
    @SerializedName("points")
    @Expose
    var points: String? = null
    @SerializedName("results")
    @Expose
    var results: String? = null
    @SerializedName("start_at")
    @Expose
    var startAt: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("end_at")
    @Expose
    var endAt: String? = null

}