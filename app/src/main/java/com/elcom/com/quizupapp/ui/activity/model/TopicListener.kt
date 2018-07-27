package com.elcom.com.quizupapp.ui.activity.model

import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.com.quizupapp.ui.activity.model.entity.User
import com.elcom.com.quizupapp.ui.network.RestData

/**
 * Created by Hailpt on 3/21/2018.
 */
interface TopicListener {
    fun getListTopicSuccess(pData: List<Caterogy>)
    fun getListTopicFail()

    fun removeHistorySuccess()
    fun removeHistoryFault()

}