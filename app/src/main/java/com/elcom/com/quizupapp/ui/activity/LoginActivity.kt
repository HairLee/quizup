package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.db.MangerDB
import com.elcom.com.quizupapp.db.model.Invention
import com.elcom.com.quizupapp.ui.activity.model.entity.User
import com.elcom.com.quizupapp.ui.activity.presenter.LoginPresenter
import com.elcom.com.quizupapp.ui.view.LoginView
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.LogUtils
import com.elcom.com.quizupapp.utils.PreferUtils
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(), LoginView, View.OnClickListener {

    private val mLoginPresenter = LoginPresenter(this, this)
    private val mCallbackManager =  CallbackManager.Factory.create()

    override fun getLayout(): Int {
        FacebookSdk.sdkInitialize(applicationContext)
        return R.layout.activity_login
    }

    override fun initView() {
        imv_login_facebook.setOnClickListener(this)
        setupCallBackForLoginFacebookButton()



//        val mData = Invention()
//        mData.id = 1
//        mData.myLevel = "15"
//        mData.myName = "Ambition"
//        mData.opLevel = "20"
//        mData.timeCountDown = "5"
//
//        MangerDB.getInstance().insertInvention(this,mData)
//
//
//        val pData =  MangerDB.getInstance().getInvention(this)
//
//        LogUtils.e("hailpt", "LoginActivity "+ pData.myName)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            ConstantsApp.REQUEST_CODE_START_ACTIVITY->{
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
            }
            ConstantsApp.REQUEST_CODE_START_FACEBOOK_LOGIN -> {
                mCallbackManager.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
//            avi.hide()
            dismisProgressDialog()
            val account = completedTask.getResult(ApiException::class.java)
            val intent = Intent(this,HomeActivity::class.java)
            intent.putExtra("EMAIL", account.displayName)
            startActivity(intent)
            finish()
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
        }


    }

    private fun setupCallBackForLoginFacebookButton(){

        btn_login_facebook.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                showProgessDialog()
                mLoginPresenter.loginWithFacebook(loginResult.accessToken.userId,loginResult.accessToken.token)
            }

            override fun onCancel() {

            }

            override fun onError(e: FacebookException) {
            }
        })
    }

    override fun initData() {
        if (intent.hasExtra("FROM_LOGOUT")){
            LoginManager.getInstance().logOut();
        }
    }

    override fun resumeData() {

    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.imv_login_facebook-> {
                    btn_login_facebook.performClick()
                }
            }
        }
    }

    /*Result after clicking login button*/
    override fun doUpdateAfterLoginSuccessfully(mSignal: String) {
        PreferUtils().setToken(this,"LoginWithGoogle")
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun loginWithFacebookSuccess(mContent: User) {
        dismisProgressDialog()
        PreferUtils().setToken(this,mContent.token)
        PreferUtils().setUserId(this,mContent.id.toString())
        PreferUtils().setAvatar(this,mContent.avatar.toString())
        ConstantsApp.USER_AVATAR_ME = mContent.avatar.toString()
        ConstantsApp.BASE64_HEADER = mContent.token
        startActivity(Intent(application, HomeActivity::class.java))
        finish()
    }

    override fun loginWithGoogleSuccess(mContent: String) {
        // dont need this function
    }

    override fun loginWithPhoneSuccess(mContent: String) {

    }

    override fun loginFail(status: String) {
        dismisProgressDialog()
        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
    }

}
