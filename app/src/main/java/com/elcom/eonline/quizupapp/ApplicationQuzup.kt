package com.elcom.eonline.quizupapp

import android.app.Application
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.elcom.eonline.quizupapp.ui.activity.ChallengeInvitationDialogActivity
import com.elcom.eonline.quizupapp.ui.activity.ChallengeResultActivity
import com.elcom.eonline.quizupapp.ui.activity.ChallengeWaitingToPlayGameActivity
import com.elcom.eonline.quizupapp.ui.custom.SocketManage
import com.elcom.eonline.quizupapp.ui.dialog.ChallengeInventedFriendDialog
import com.elcom.eonline.quizupapp.ui.listener.*
import com.elcom.eonline.quizupapp.utils.*
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.onesignal.OneSignal
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Hailpt on 8/22/2018.
 */
class ApplicationQuzup : Application(), OnSocketInviteOpponentListener, OnSocketListener, OnSocketSendChallengeInformationListener {


    var mSocketManage = SocketManage()
    var handler = ApplicationLifecycleHandler()
    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/RobotoLight.ttf")
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/RobotoLight.ttf")
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/RobotoLight.ttf")
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/RobotoLight.ttf")
        LogUtils.e("SocketManage", "onCreate ")
        PreferUtils().setChallengeTimeToInviteFriend(this, "0")
        mSocketManage.init(this)
        mSocketManage.connectSocket()

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init()

        registerActivityLifecycleCallbacks(handler)
        registerComponentCallbacks(handler)
    }

    override fun onSocketConnected() {
        ConstantsApp.socketManage = mSocketManage
        regisSocket()
        mSocketManage.sendUserInformationBySocket(this)
    }

    override fun onAuthentication(content: String) {

    }

    override fun onCountDown(timeToCountDown: Int) {

    }

    override fun onResultQuestion(resultQuestion: JSONObject) {

    }
    
    


    var mChallengeGameDialog: ChallengeInventedFriendDialog? = null
    var mORejectInvitationListener: OnRejectInvitationListener? = null
    var mOnFinishActivityListener: OnFinishActivityListener? = null
    override fun onSomeoneInviteYouToPlayGame(resultQuestion: JSONObject) {
        Log.e("SocketManage", "QuizUpApplication " + resultQuestion.toString())
//        runOnUiThreadA(Runnable { Toast.makeText(baseContext, resultQuestion.toString(), Toast.LENGTH_SHORT).show() })


        if(resultQuestion["challenge"] == "true" && resultQuestion["userSendId"] == PreferUtils().getUserId(this)){

            if(ApplicationLifecycleHandler.getCurrentActivityName() == "ui.activity.ChallengeResultActivity" && mOnFinishActivityListener != null ){
                mOnFinishActivityListener!!.onFinishActivityListener()
            }

            val intent = Intent(this,ChallengeWaitingToPlayGameActivity::class.java)
            intent.putExtra("data",resultQuestion.toString())
            intent.putExtra("accept","")
            startActivity(intent)
            // Call Api to get question info and then send data from socket
        } else if (resultQuestion["challenge"] == "true" ){
            val intent = Intent(baseContext, ChallengeInvitationDialogActivity::class.java)
            intent.putExtra("resultQuestion",resultQuestion.toString())
            startActivity(intent)
        } else {
            if(mORejectInvitationListener != null){
                mORejectInvitationListener!!.onRejectInvitationListener()
                runOnUiThreadA(Runnable { Toast.makeText(baseContext, "Đối phương từ chối lời mời", Toast.LENGTH_SHORT).show() })
            }
        }



//        runOnUiThreadA(Runnable {
//            mChallengeGameDialog = ChallengeInventedFriendDialog(baseContext, object : OnDialogInvitationListener {
//                override fun onCancelInviteFriendToPlayGame() {
//
//                }
//
//                override fun onInviteFriendToPlayGame() {
//                    onSomeoneInviteYou(resultQuestion)
//                }
//            })
//            mChallengeGameDialog!!.show()
//
//        })


    }

    override fun OnSocketSendChallengeInformationListener(resultQuestion: JSONObject) {
        val intent = Intent(this,ChallengeWaitingToPlayGameActivity::class.java)
        intent.putExtra("data_op",resultQuestion.toString())
        startActivity(intent)
    }

    fun regisSocket() {
        mSocketManage.initToInventionFromFriend(this)
        mSocketManage.initOnSocketSendChallengeInformationListener(this)
    }

    private val mHandler = Handler()
    fun runOnUiThreadA(runnable: Runnable) {
        if (Thread.currentThread() === Looper.getMainLooper().thread) {
            runnable.run()
        } else {
            mHandler.post(runnable)
        }
    }


     fun onSomeoneInviteYou(resultQuestion: JSONObject) {
        LogUtils.e("SocketManage","ChallengeFromFriendsActivity Someone invite you to play a game " + resultQuestion.toString())
        LogUtils.e("SocketManage","ChallengeFromFriendsActivity Someone invite you to play a game Id " + PreferUtils().getUserId(this))

        // When your friend accept your invitation, begin to play a game
        if(resultQuestion["challenge"] == "true" && resultQuestion["userSendId"] == PreferUtils().getUserId(this)){
            val intent = Intent(this, ChallengeWaitingToPlayGameActivity::class.java)
            intent.putExtra("data",resultQuestion.toString())
            intent.putExtra("accept","")
            startActivity(intent)
            // Call Api to get question info and then send data from socket

        } else if (resultQuestion["challenge"] == "true" && resultQuestion["userSendId"] != PreferUtils().getUserId(this)) {
            // When you wanna accept your friend to play Game
            sendInviteOrAcceptInvite(resultQuestion["topicId"] as String,resultQuestion["userSendId"] as String, resultQuestion["sendId"] as String )
        }
    }

    fun sendInviteOrAcceptInvite(mTopicId: String,userSendId: String, toId: String ){
        val myInfo = JSONObject()
        try {
            myInfo.put("topicId", mTopicId)
            myInfo.put("sendId", PreferUtils().getUserId(this))
            myInfo.put("toId", toId )
            myInfo.put("challenge", "true")
            myInfo.put("url", "url")
            myInfo.put("name", "Ambitionnnn")
            myInfo.put("topicName", "topicName")
            myInfo.put("urlTopic", "urlTopic")
            myInfo.put("userSendId", userSendId)
        } catch (e: JSONException) {

        }
        ConstantsApp.socketManage.sendChallengeInformation(myInfo)

        Log.e("hailpt", " ChallengeFromFriendsActivity sendInviteOrAcceptInvite "+ myInfo.toString())
    }


    fun setOnRejectInvitationListener(pOnRejectInvitationListener:OnRejectInvitationListener){
        mORejectInvitationListener = pOnRejectInvitationListener
    }

    fun setOnFinishActivityListener(pOnFinishActivityListener:OnFinishActivityListener){
        mOnFinishActivityListener = pOnFinishActivityListener
    }


}