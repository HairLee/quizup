package com.elcom.com.quizupapp.ui.activity.presenter

import android.content.Context
import com.elcom.com.quizupapp.ui.activity.model.TopicInteractor
import com.elcom.com.quizupapp.ui.activity.model.TopicListener
import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.com.quizupapp.ui.view.MainView
import com.elcom.com.quizupapp.ui.view.TopicView

/**
 * Created by Hailpt on 3/21/2018.
 */
class TopicPresenter(pTopicView: TopicView, pContext: Context) : TopicListener {



    val mTopicView = pTopicView
    val mContext = pContext
    val mTopicInteractor = TopicInteractor(this,mContext)
    override fun getListTopicSuccess(pData: List<Caterogy>) {
        mTopicView.getListTopicSuccess(pData)
    }

    override fun getListTopicFail() {
        mTopicView.getListTopicFail()
    }

    fun getLisTopic(){
        mTopicInteractor.getTopicList()
    }

    fun removeHistory(matchid:Int){
        mTopicInteractor.removeHistory(matchid)
    }

    override fun removeHistorySuccess() {
        mTopicView.removeHistorySuccess()
    }

    override fun removeHistoryFault() {
        mTopicView.removeHistoryFault()
    }


}