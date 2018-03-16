package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.presenter.LoginPresenter
import com.elcom.com.quizupapp.ui.view.LoginView
import com.google.android.gms.common.SignInButton
import kotlinx.android.synthetic.main.activity_login.*
import android.support.v4.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import android.R.attr.data
import android.util.Log
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.LogUtils
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException


class LoginActivity : BaseActivity(), LoginView, View.OnClickListener {



    private val mLoginPresenter = LoginPresenter(this, this)

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        btn_sign_in.setOnClickListener(this)
        imv_login_facebook.setOnClickListener(this)
        imv_login_goolge.setOnClickListener(this)
        imv_login_phone.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ConstantsApp.REQUEST_CODE_START_ACTIVITY) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
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

        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }

    override fun initData() {

    }

    override fun resumeData() {

    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.btn_sign_in-> {
                    mLoginPresenter.getData()
                }

                R.id.imv_login_facebook-> {
                    mLoginPresenter.loginWithFacebook()
                }

                R.id.imv_login_goolge-> {
//                    avi.show()
                    showProgessDialog()
                    mLoginPresenter.loginWithGoogle()
                }

                R.id.imv_login_phone-> {

                }
            }
        }
    }

    /*Result after clicking login button*/
    override fun doUpdateAfterLoginSuccessfully(mSignal: String) {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun loginWithFacebookSuccess(mContent: String) {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun loginWithGoogleSuccess(mContent: String) {
        // dont need this function
    }

    override fun loginWithPhoneSuccess(mContent: String) {

    }




}
