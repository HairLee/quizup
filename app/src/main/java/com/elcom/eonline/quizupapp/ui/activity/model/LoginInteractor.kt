package com.elcom.eonline.quizupapp.ui.activity.model

import android.app.Activity
import android.content.Context
import com.elcom.eonline.quizupapp.ui.activity.model.entity.User
import com.elcom.eonline.quizupapp.ui.network.RestClient
import com.elcom.eonline.quizupapp.ui.network.RestData

import com.elcom.eonline.quizupapp.utils.ConstantsApp
import com.elcom.eonline.quizupapp.utils.LogUtils
import com.elcom.eonline.quizupapp.utils.PreferUtils
import com.facebook.CallbackManager
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
//                .requestIdToken("idToken")
                .requestEmail()
                .build()
        val signInIntent = mContext?.let { GoogleSignIn.getClient(it, gso).getSignInIntent() }
        if(mContext is Activity){
            (mContext as Activity).startActivityForResult(signInIntent, ConstantsApp.REQUEST_CODE_START_ACTIVITY)
        }
    }

    fun loginWithFacebook(userId:String,tokenId:String){

        RestClient().getInstance().getRestService().loginWithFacebook(userId,tokenId).enqueue(object : Callback<RestData<User>>{
            override fun onResponse(call: Call<RestData<User>>?, response: Response<RestData<User>>?) {
                if (response?.body() != null){
                    mLoginListener.loginWithFacebookSuccess(response.body().data!!)
                } else{
                    mLoginListener.loginFail("Fail")
                }
            }
            override fun onFailure(call: Call<RestData<User>>?, t: Throwable?) {
                mLoginListener.loginFail("Fail")
            }

        })
    }

    fun loginWithGoogle(){
        signInWithGoogle()
    }

    fun loginWithPhone(){

    }

}