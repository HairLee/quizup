package com.elcom.com.quizupapp.ui.activity.presenter

import android.content.Context
import com.elcom.com.quizupapp.ui.activity.model.LoginListener
import com.elcom.com.quizupapp.ui.activity.model.LoginInteractor
import com.elcom.com.quizupapp.ui.view.LoginView

/**
 * Created by Hailpt on 3/9/2018.
 */
class LoginPresenter(pLoginView:LoginView,pContext: Context): LoginListener {


    private val mLoginView= pLoginView
    private val mLoginInterator = LoginInteractor(this,pContext)

    override fun doReturnResultAfterLogin(mContent: String) {
        mLoginView.doUpdateAfterLoginSuccessfully(mContent)
    }

    override fun loginWithFacebookSuccess(mContent: String) {
        mLoginView.loginWithFacebookSuccess("loginWithFacebookSuccess")
    }

    override fun loginWithGoogleSuccess(mContent: String) {

    }

    override fun loginWithPhoneSuccess(mContent: String) {

    }


    fun getData(){
        mLoginInterator.getData()
    }

    fun loginWithFacebook(){
        mLoginInterator.loginWithFacebook()
    }

    fun loginWithGoogle(){
        mLoginInterator.loginWithGoogle()
    }

    fun loginWithPhone(){

    }

}