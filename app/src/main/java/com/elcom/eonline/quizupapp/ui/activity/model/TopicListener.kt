package com.elcom.eonline.quizupapp.ui.activity.model

import com.elcom.eonline.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.eonline.quizupapp.ui.activity.model.entity.User
import com.elcom.eonline.quizupapp.ui.network.RestData

/**
 * Created by Hailpt on 3/21/2018.
 */
interface TopicListener {
    fun getListTopicSuccess(pData: List<Caterogy>)
    fun getListTopicFail()

    fun removeHistorySuccess()
    fun removeHistoryFault()

}