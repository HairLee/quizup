package com.elcom.eonline.quizupapp.ui.listener

import com.elcom.eonline.quizupapp.ui.activity.model.entity.response.topicdetail.Topic

/**
 * Created by Hailpt on 8/2/2018.
 */
interface OnFavouriteListener {
    fun onFavourtie(topic:Topic,favourite:String)
}