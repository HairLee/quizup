package com.elcom.com.quizupapp.ui.view

import com.elcom.com.quizupapp.ui.activity.model.entity.Followed.Followed
import com.elcom.com.quizupapp.ui.activity.model.entity.profile.Profile

/**
 * Created by Hailpt on 6/25/2018.
 */
interface SettingFollowedView {
    fun getDataSuccessfully(profile:Followed)
    fun getDataFault()
}