package com.elcom.com.quizupapp.ui.activity.model

import com.elcom.com.quizupapp.ui.activity.model.entity.Caterogy
import com.elcom.com.quizupapp.ui.activity.model.entity.User

/**
 * Created by Hailpt on 3/21/2018.
 */
interface HomeListener {
    fun getListTopicSuccess(pData: List<Caterogy>)
    fun getListTopicFail()
}