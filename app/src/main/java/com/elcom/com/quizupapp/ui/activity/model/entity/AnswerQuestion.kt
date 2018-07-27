package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Hailpt on 3/26/2018.
 */
class AnswerQuestion {

    @SerializedName("correct")
    @Expose
    var correct: String? = null

}