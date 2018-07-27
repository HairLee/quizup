package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.adapter.ChallengeFromFriendsAdapter
import com.elcom.com.quizupapp.ui.dialog.ChallengeInventedFriendDialog
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.LogUtils
import com.elcom.com.quizupapp.utils.PreferUtils
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.activity_challenge_from_friends.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import com.elcom.com.quizupapp.QuizUpApplication
import com.elcom.com.quizupapp.ui.listener.*
import com.elcom.com.quizupapp.utils.WrapContentLinearLayoutManager


class ChallengeFromFriendsActivity : BaseActivityQuiz(), OnSocketGetOnlineListener, OnItemClickListener, OnSocketInviteOpponentListener, OnInvitationTimeCountDownListener {



    private var mTopicId = ""
    private var listdata = ArrayList<String>()
    val mAdapter =  ChallengeFromFriendsAdapter(listdata,this)
    override fun getLayout(): Int {
        return R.layout.activity_challenge_from_friends
    }

    override fun initData() {
        if (intent.hasExtra(ConstantsApp.KEY_QUESTION_ID)){
            mTopicId = intent.getStringExtra(ConstantsApp.KEY_QUESTION_ID)

            if( ConstantsApp.socketManage != null){
                ConstantsApp.socketManage.initToGetListOnline(this)
                ConstantsApp.socketManage.initToInventionFromFriend(this)
                getUserOnlineByTopic()
            }
        }
    }

    private fun getUserOnlineByTopic(){
        val myInfo = JSONObject()
        try {
            myInfo.put("topicId",mTopicId)
            myInfo.put("sendId",PreferUtils().getUserId(this))
        } catch (e : JSONException){

        }

        ConstantsApp.socketManage.getUserOnlineByTopic(myInfo)
        ProgressDialogUtils.showProgressDialog(this, 0, 0)
    }

    override fun onSomeoneInviteYouToPlayGame(resultQuestion: JSONObject) {
        LogUtils.e("SocketManage","ChallengeFromFriendsActivity Someone invite you to play a game " + resultQuestion.toString())

        // When your friend accept your invitation, begin to play a game
        if(resultQuestion["challenge"] == "true" && resultQuestion["userSendId"] == PreferUtils().getUserId(this)){
            startActivity(Intent(this,ChallengeWaitingToPlayGameActivity::class.java).putExtra("data",resultQuestion.toString()))
        } else if (resultQuestion["challenge"] == "true" && resultQuestion["userSendId"] != PreferUtils().getUserId(this)) {
        // When you wanna accept your friend to play Game
            runOnUiThread {
                Log.e("hailpt"," resultQuestion "+resultQuestion.toString())
                val snack = Snackbar.make(lnRoot, "Someone invite you to play a game", Snackbar.LENGTH_SHORT) .setAction("DISMISS", object : View.OnClickListener {
                    override fun onClick(view: View) {
                        // Accept the invitation
                        sendInviteOrAcceptInvite(resultQuestion["userSendId"] as String, resultQuestion["sendId"] as String )
                        startActivity(Intent(applicationContext,ChallengeWaitingToPlayGameActivity::class.java).putExtra("data",resultQuestion.toString()))
                    }
                })
                val view = snack.getView()
                val params = view.layoutParams as FrameLayout.LayoutParams
                params.gravity = Gravity.BOTTOM
                view.layoutParams = params
                snack.show()
            }
        }
    }

    override fun initView(){
        recyclerView.layoutManager = WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter


        btnRandomToPlay.setOnClickListener {
            if(mTopicId != ""){
                val  intent = Intent(applicationContext, ChallengeFindingRandomOpponentActivity::class.java)
                intent.putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId)
                startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }

        svMember.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                mAdapter.filter.filter(newText)

                return false
            }
        })

        imvBack.setOnClickListener {
            onBackPressed()
        }
    }

    var mChallengeGameDialog: ChallengeInventedFriendDialog? = null
    override fun onItemClicked(position: Int) {

        if( ConstantsApp.CHALLENGE_TIME_COUNT_DOWN  != "0"){
            Toast.makeText(this, "Đang Mời Người Khác Rồi Nhé !", Toast.LENGTH_SHORT).show()
            return
        }


        val mApp = application as QuizUpApplication
        mApp.setListener(this)

        mChallengeGameDialog = ChallengeInventedFriendDialog(this, object : OnDialogInvitationListener {
            override fun onCancelInviteFriendToPlayGame() {
                mApp.stopCountDownTimer()
                ConstantsApp.CHALLENGE_TIME_COUNT_DOWN = "0"
            }

            override fun onInviteFriendToPlayGame() {

                mApp.stopCountDownTimer()
                mApp.startCountDownTimer()

                val mObject = mOnlineList[position] as JSONObject
                sendInviteOrAcceptInvite(PreferUtils().getUserId(applicationContext), mObject["id"].toString())


            }
        })
        mChallengeGameDialog!!.show()
    }

    fun sendInviteOrAcceptInvite(userSendId: String, toId: String ){
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
    }

    override fun onTimeCountDown(currentTime: String) {
        mChallengeGameDialog!!.setTime(currentTime)
    }

    private  var mOnlineList = JSONArray()
    override fun onUserOnlineByTopic(onlineList: JSONArray) {
        LogUtils.e("SocketManage",onlineList.toString())
        listdata.clear()

        for (i in 0 until onlineList.length()) {
            listdata.add(onlineList.getString(i))
        }

        if(mOnlineList.toString() != onlineList.toString()){
            runOnUiThread {
                ProgressDialogUtils.dismissProgressDialog()
                recyclerView.adapter.notifyDataSetChanged()
            }
        }

        mOnlineList = onlineList
    }

    override fun onDestroy() {
        super.onDestroy()
    }





}
