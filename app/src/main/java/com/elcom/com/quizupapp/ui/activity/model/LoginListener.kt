package com.elcom.com.quizupapp.ui.activity.model

/**
 * Created by Hailpt on 3/9/2018.
 */
interface LoginListener {
    fun doReturnResultAfterLogin(mContent:String)
    fun loginWithFacebookSuccess(mContent:String)
    fun loginWithGoogleSuccess(mContent:String)
    fun loginWithPhoneSuccess(mContent:String)
}