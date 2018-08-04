package com.elcom.eonline.quizupapp.ui.view

import com.elcom.eonline.quizupapp.ui.activity.model.entity.SoloMatch
import com.elcom.eonline.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.eonline.quizupapp.ui.activity.model.entity.response.topicdetail.TopicDetail

/**
 * Created by Hailpt on 3/26/2018.
 */
interface TopicDetailView {
    fun getTopicDetailSuccess(pTopic: TopicDetail)
    fun getMatchIdSuccess(pMatchId: SoloMatch)

    fun followAndUnfollowSuccess()
    fun getTopicDetailFault()
}