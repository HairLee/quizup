package com.elcom.eonline.quizupapp.ui.view

import com.elcom.eonline.quizupapp.ui.activity.model.entity.User

/**
 * Created by Hailpt on 3/9/2018.
 */
interface LoginView {
    fun doUpdateAfterLoginSuccessfully(mSignal:String)
    fun loginWithFacebookSuccess(mContent:User)
    fun loginWithGoogleSuccess(mContent:String)
    fun loginWithPhoneSuccess(mContent:String)

    fun loginFail(status:String)
}