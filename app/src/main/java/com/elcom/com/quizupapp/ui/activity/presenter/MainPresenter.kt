package com.elcom.com.quizupapp.ui.activity.presenter

import com.elcom.com.quizupapp.ui.activity.model.MainIteractor
import com.elcom.com.quizupapp.ui.activity.model.MainListener
import com.elcom.com.quizupapp.ui.view.MainView

/**
 * Created by admin on 3/8/2018.
 */
     class MainPresenter(pMainView: MainView) : MainListener {

    var mMainIterator: MainIteractor? = MainIteractor(this)
    var mMainView: MainView? = pMainView

    fun getData() {
        mMainIterator?.getData()
    }


    override fun onPushDataListener(mData: String) {
        mMainView?.showContentForText(mData)
    }


}