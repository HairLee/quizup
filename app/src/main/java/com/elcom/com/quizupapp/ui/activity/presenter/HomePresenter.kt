package com.elcom.com.quizupapp.ui.activity.presenter

import com.elcom.com.quizupapp.ui.activity.model.HomeInteractor
import com.elcom.com.quizupapp.ui.activity.model.HomeListener
import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.com.quizupapp.ui.view.HomeView

/**
 * Created by Hailpt on 3/21/2018.
 */
class HomePresenter(pHomeView: HomeView) : HomeListener {

    private val mHomeView =  pHomeView
    private val mHomeInteracter = HomeInteractor(this)
    override fun getListTopicSuccess(pData: List<Caterogy>) {
        mHomeView.getListTopicSuccess(pData)
    }

    override fun getListTopicFail() {
        mHomeView.getListTopicFail()
    }

    fun getLisTopic(){
        mHomeInteracter.getTopicList()
    }

}