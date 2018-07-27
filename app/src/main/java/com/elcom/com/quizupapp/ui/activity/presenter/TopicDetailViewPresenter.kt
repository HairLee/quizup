package com.elcom.com.quizupapp.ui.activity.presenter

import android.content.Context
import com.elcom.com.quizupapp.ui.activity.model.TopicDetailViewInterator
import com.elcom.com.quizupapp.ui.activity.model.TopicDetailViewListener
import com.elcom.com.quizupapp.ui.activity.model.entity.SoloMatch
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.TopicDetail
import com.elcom.com.quizupapp.ui.view.TopicDetailView

/**
 * Created by Hailpt on 3/26/2018.
 */
class TopicDetailViewPresenter(pTopicDetailView:TopicDetailView) : TopicDetailViewListener {



    var TopicDetailViewInterator = TopicDetailViewInterator(this)
    var mTopicDetailView  = pTopicDetailView
    override fun getTopicDetailSuccess(pTopic: TopicDetail) {
        mTopicDetailView.getTopicDetailSuccess(pTopic)
    }

    override fun getMatchIdSuccess(mMatchId: SoloMatch) {
        mTopicDetailView.getMatchIdSuccess(mMatchId)
    }

    override fun getTopicDetailFault() {

    }

    override fun followAndUnfollowSuccess() {
        mTopicDetailView.followAndUnfollowSuccess()
    }

    fun getTopicViewDetail(pTopicId:String){
        TopicDetailViewInterator.getTopicViewDetail(pTopicId)
    }

    fun getMatchId(pContext: Context, pTopicId:String){
        TopicDetailViewInterator.getMatchId(pContext,pTopicId)
    }

    fun followAndUnfollowTopic(pTopicId:String,pStatus:String){
        TopicDetailViewInterator.followAndUnfollowTopic(pTopicId,pStatus)
    }






}