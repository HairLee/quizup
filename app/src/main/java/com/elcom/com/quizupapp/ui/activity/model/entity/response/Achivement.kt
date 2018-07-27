package com.elcom.com.quizupapp.ui.activity.model.entity.response

/**
 * Created by Hailpt on 7/6/2018.
 */
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Achivement {

    @SerializedName("list")
    @Expose
    var listDetail: List<ListDetail>? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null

}