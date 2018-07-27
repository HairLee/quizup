package com.elcom.com.quizupapp.ui.activity.model.entity.Followed

/**
 * Created by Hailpt on 6/25/2018.
 */
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Followed {

    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("list")
    @Expose
    var list: List<FollowedList>? = null

}