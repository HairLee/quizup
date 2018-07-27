package com.elcom.com.quizupapp.ui.activity.presenter

import android.content.Context
import com.elcom.com.quizupapp.ui.activity.model.LoginListener
import com.elcom.com.quizupapp.ui.activity.model.LoginInteractor
import com.elcom.com.quizupapp.ui.activity.model.entity.User
import com.elcom.com.quizupapp.ui.view.LoginView
import com.facebook.CallbackManager
import com.facebook.login.widget.LoginButton

/**
 * Created by Hailpt on 3/9/2018.
 */
class LoginPresenter(pLoginView:LoginView,pContext: Context): LoginListener {



    private val mLoginView= pLoginView
    private val mLoginInterator = LoginInteractor(this,pContext)

    override fun doReturnResultAfterLogin(mContent: String) {
        mLoginView.doUpdateAfterLoginSuccessfully(mContent)
    }

    override fun loginWithFacebookSuccess(mContent: User) {
        mLoginView.loginWithFacebookSuccess(mContent)
    }

    override fun loginWithGoogleSuccess(mContent: String) {

    }

    override fun loginWithPhoneSuccess(mContent: String) {

    }

    override fun loginFail(status: String) {
        mLoginView.loginFail(status)
    }


    fun getData(){
        mLoginInterator.getData()
    }

    fun loginWithFacebook(userId:String,tokenId:String){
        mLoginInterator.loginWithFacebook(userId,tokenId)
    }

    fun loginWithGoogle(){
        mLoginInterator.loginWithGoogle()
    }

    fun loginWithPhone(){

    }

}