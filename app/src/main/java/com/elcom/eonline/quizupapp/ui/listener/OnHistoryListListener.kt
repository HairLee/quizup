package com.elcom.eonline.quizupapp.ui.listener

import com.elcom.eonline.quizupapp.ui.activity.model.entity.response.topicdetail.Topic

/**
 * Created by Hailpt on 4/12/2018.
 */
interface OnHistoryListListener {
    fun onRemoveHistory(topic:Topic)
    fun onPlayTopicAgain(topic:Topic)
}