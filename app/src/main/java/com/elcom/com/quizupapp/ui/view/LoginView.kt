package com.elcom.com.quizupapp.ui.view

/**
 * Created by Hailpt on 3/9/2018.
 */
interface LoginView {
    fun doUpdateAfterLoginSuccessfully(mSignal:String)
    fun loginWithFacebookSuccess(mContent:String)
    fun loginWithGoogleSuccess(mContent:String)
    fun loginWithPhoneSuccess(mContent:String)
}