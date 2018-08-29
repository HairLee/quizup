package com.elcom.eonline.quizupapp.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.elcom.eonline.quizupapp.ApplicationQuzup
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.model.entity.response.ChallengeInfo
import com.elcom.eonline.quizupapp.ui.listener.OnRejectInvitationListener
import com.elcom.eonline.quizupapp.utils.ConstantsApp
import com.elcom.eonline.quizupapp.utils.LogUtils
import com.elcom.eonline.quizupapp.utils.PreferUtils
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_challenge_invitation_dialog.*
import org.json.JSONException
import org.json.JSONObject

class ChallengeInvitationDialogActivity : AppCompatActivity(), OnRejectInvitationListener {


    var mChallengeMatching:ChallengeInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_invitation_dialog)
        (application as ApplicationQuzup).setOnRejectInvitationListener(this)
        val data =   intent.getStringExtra("resultQuestion")

        val mObject = JSONObject(data)

        mChallengeMatching = Gson().fromJson(mObject.toString() , ChallengeInfo::class.java)
        updateLayout(mChallengeMatching!!)
        btnAccept.setOnClickListener {
            onSomeoneInviteYou(mObject)
            ChallengeInvitationDialogActivity@this.finish()
        }

        btnReject.setOnClickListener {
            sendRejectInvite(mObject["topicId"] as String,mObject["userSendId"] as String, mObject["sendId"] as String)
            ChallengeInvitationDialogActivity@this.finish()
        }

        imvClose.setOnClickListener {
            sendRejectInvite(mObject["topicId"] as String,mObject["userSendId"] as String, mObject["sendId"] as String)
            ChallengeInvitationDialogActivity@this.finish()
        }
    }

    fun onSomeoneInviteYou(resultQuestion: JSONObject) {
        LogUtils.e("SocketManage","ChallengeFromFriendsActivity Someone invite you to play a game " + resultQuestion.toString())
        LogUtils.e("SocketManage","ChallengeFromFriendsActivity Someone invite you to play a game Id " + PreferUtils().getUserId(this))

        if (resultQuestion["challenge"] == "false"){
            sendRejectInvite(resultQuestion["topicId"] as String,resultQuestion["userSendId"] as String, resultQuestion["sendId"] as String)
            return
        }

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

    fun sendRejectInvite(mTopicId: String,userSendId: String, toId: String ){
        val myInfo = JSONObject()
        try {
            myInfo.put("topicId", mTopicId)
            myInfo.put("sendId", PreferUtils().getUserId(this))
            myInfo.put("toId", toId )
            myInfo.put("challenge", "false")
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

    override fun onRejectInvitationListener() {
        Toast.makeText(this, "Hủy lời mời", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun updateLayout(data:ChallengeInfo){
        if(data != null){
            Picasso.get().load(data.getUrl()).into(imvAva)
            tvTopicName.text = data.getTopicName()
            tvMyName.text = data.getName()
        }


    }
}
