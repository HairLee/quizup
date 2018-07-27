package com.elcom.com.quizupapp.ui.activity.model.entity

/**
 * Created by Hailpt on 3/21/2018.
 */

import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Caterogy {


    @SerializedName("category_id")
    @Expose
    var category_id: String? = null

    @SerializedName("keyValue")
    @Expose
    var keyValue: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("topics")
    @Expose
    var topics: List<Topic>? = null

}