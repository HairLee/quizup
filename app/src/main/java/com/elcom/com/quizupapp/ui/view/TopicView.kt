package com.elcom.com.quizupapp.ui.view

import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.com.quizupapp.ui.network.RestData

/**
 * Created by Hailpt on 3/21/2018.
 */
interface TopicView {
    fun getListTopicSuccess(pData: List<Caterogy>)
    fun getListTopicFail()

    fun removeHistorySuccess()
    fun removeHistoryFault()
}