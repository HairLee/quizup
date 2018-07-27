package com.elcom.com.quizupapp.ui.activity.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Hailpt on 5/24/2018.
 */
class AwardStructure : Serializable {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("show_id")
    @Expose
    var showId: Int? = null
    @SerializedName("award_text")
    @Expose
    var awardText: String? = null
    @SerializedName("award_picture")
    @Expose
    var awardPicture: String? = null
    @SerializedName("award_value")
    @Expose
    var awardValue: Any? = null
    @SerializedName("ranks")
    @Expose
    var ranks: Int? = null

}