package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable



/**
 * Created by Hailpt on 4/27/2018.
 */
class ChallengeQuestion : Serializable {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("question")
    @Expose
    var question: String? = null
    @SerializedName("type")
    @Expose
    var type: Int? = null
    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null
    @SerializedName("answer")
    @Expose
    var answer: List<ChallengeAnswer>? = null

}