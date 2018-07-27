package com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail

/**
 * Created by Hailpt on 7/4/2018.
 */


import com.elcom.com.quizupapp.ui.activity.model.entity.response.ListDetail
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Achievements {

    @SerializedName("list")
    @Expose
    var listDetail: List<ListDetail>? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null

}