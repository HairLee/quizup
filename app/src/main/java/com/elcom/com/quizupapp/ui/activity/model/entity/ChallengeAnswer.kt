package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Hailpt on 3/22/2018.
 */
class ChallengeAnswer : Serializable {


    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("note")
    @Expose
    var note: String? = null
    @SerializedName("status")
    @Expose
    var status: Int? = null
    @SerializedName("correct")
    @Expose
    var correct: Int? = null

}