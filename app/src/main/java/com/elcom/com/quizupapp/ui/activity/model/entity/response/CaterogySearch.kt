package com.elcom.com.quizupapp.ui.activity.model.entity.response

/**
 * Created by Hailpt on 3/21/2018.
 */

import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Search
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.TopicDetail
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CaterogySearch {

    @SerializedName("keyValue")
    @Expose
    var keyValue: Int? = null

    @SerializedName("category_id")
    @Expose
    var category_id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("total")
    @Expose
    var total: Int? = null


    @SerializedName("list")
    @Expose
    var list: List<Search>? = null

}