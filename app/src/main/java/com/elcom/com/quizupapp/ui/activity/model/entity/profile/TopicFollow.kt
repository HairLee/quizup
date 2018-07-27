package com.elcom.com.quizupapp.ui.activity.model.entity.profile

/**
 * Created by Hailpt on 6/25/2018.
 */
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TopicFollow {

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null

}