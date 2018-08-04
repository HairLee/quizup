package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.db.MangerDB
import com.elcom.com.quizupapp.db.model.Invention
import com.elcom.com.quizupapp.ui.activity.model.entity.User
import com.elcom.com.quizupapp.ui.activity.model.entity.response.ContinueMatch
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.TopicDetail
import com.elcom.com.quizupapp.ui.custom.SocketManage
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.network.RestService
import com.elcom.com.quizupapp.ui.view.LoginView
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.LogUtils
import com.elcom.com.quizupapp.utils.PreferUtils
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(PreferUtils().getFirstTimeGoToApp(this)){
            val mainIntent = Intent(this, IntroductionActivity::class.java)
            startActivity(mainIntent)
            finish()
        } else {
            val tokenId = PreferUtils().getToken(this)
            if(tokenId != ""){
                loginWithFacebook()
            } else {
                val mainIntent = Intent(this, LoginActivity::class.java)
                startActivity(mainIntent)
                finish()
            }
        }

    }



    private fun getPauseGame(){

        val pauseGame =  MangerDB.getInstance().getGamePause(this)
        if (pauseGame != null){
            LogUtils.e("hailpt", "SplashActivity pauseGame "+pauseGame.matchId)
        }

    }

    override fun onStart() {
        super.onStart()
    }

    public fun getMatchWhenComeBackSplash(){
        RestClient().getInstance().getRestService().getMatchWhenComeBackSplash().enqueue(object : Callback<RestData<ContinueMatch>> {
            override fun onResponse(call: Call<RestData<ContinueMatch>>?, response: Response<RestData<ContinueMatch>>?) {

                Handler().postDelayed(Runnable {
                    if(response?.body() != null && response.body().data != null && (response.body().message == getString(R.string.get_last_question_success))){

                        val match = response!!.body().data
                        val intent = Intent(baseContext, HomeActivity::class.java)
                        ConstantsApp.BASE64_HEADER = PreferUtils().getToken(baseContext)
                        intent.putExtra(ConstantsApp.KEY_QUESTION_ID,match!!.topic_id)
                        intent.putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,match.match_id)
                        intent.putExtra(ConstantsApp.KEY_QUESTION_NUMBER,match.question_number+1)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                    } else {
                        val loginIntent = Intent(baseContext, HomeActivity::class.java)
                        startActivity(loginIntent)
                        finish()
                    }

                }, 2000)

            }

            override fun onFailure(call: Call<RestData<ContinueMatch>>?, t: Throwable?) {
                LogUtils.e("hailpt", "SplashActivity pauseGame "+t!!.message)
            }

        })
    }


    fun loginWithFacebook(){

        RestClient().getInstance().getRestService().loginWithFacebook( PreferUtils().getFacebookId(this),"").enqueue(object : Callback<RestData<User>>{
            override fun onResponse(call: Call<RestData<User>>?, response: Response<RestData<User>>?) {
                if (response?.body() != null){
                    ConstantsApp.USER_AVATAR_ME = PreferUtils().getAvatar(this@SplashActivity)
                    ConstantsApp.BASE64_HEADER = response.body().data!!.token
                    getMatchWhenComeBackSplash()
                } else{

                }
            }
            override fun onFailure(call: Call<RestData<User>>?, t: Throwable?) {


            }
        })
    }


}
