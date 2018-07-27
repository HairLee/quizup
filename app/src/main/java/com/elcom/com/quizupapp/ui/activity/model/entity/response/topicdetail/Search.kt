package com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail

/**
 * Created by Hailpt on 7/5/2018.
 */
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Search {

    @SerializedName("user_id")
    @Expose
    var userId: String? = null
    @SerializedName("topic_id")
    @Expose
    var topicId: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("level")
    @Expose
    var level: String? = null
    @SerializedName("statusFollow")
    @Expose
    var statusFollow: Int? = null

}