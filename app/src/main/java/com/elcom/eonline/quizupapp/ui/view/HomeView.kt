package com.elcom.eonline.quizupapp.ui.view

import com.elcom.eonline.quizupapp.ui.activity.model.entity.Caterogy

/**
 * Created by Hailpt on 3/21/2018.
 */
interface HomeView {
    fun getListTopicSuccess(pData: List<Caterogy>)
    fun getListTopicFail()
}