package com.elcom.com.quizupapp.ui.activity.model

import android.app.Activity
import android.content.Context

import com.elcom.com.quizupapp.utils.ConstantsApp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

/**
 * Created by Hailpt on 3/9/2018.
 */
class LoginInteractor(pLoginListener: LoginListener, pContext: Context) {

    private val mLoginListener = pLoginListener
    private var mContext: Context? = pContext
    fun getData(){
        mLoginListener.doReturnResultAfterLogin("Ambition Is Good ~~")
    }

    private fun signInWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        val signInIntent = mContext?.let { GoogleSignIn.getClient(it, gso).getSignInIntent() }
        if(mContext is Activity){
            (mContext as Activity).startActivityForResult(signInIntent, ConstantsApp.REQUEST_CODE_START_ACTIVITY)
        }
    }

    fun loginWithFacebook(){

        mLoginListener.loginWithFacebookSuccess("Ambition Is Good ~~")
    }

    fun loginWithGoogle(){

        signInWithGoogle()
    }

    fun loginWithPhone(){

    }

}