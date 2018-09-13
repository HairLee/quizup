package com.elcom.eonline.quizupapp.ui.activity

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
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.adapter.ChallengeFromFriendsAdapter
import com.elcom.eonline.quizupapp.ui.dialog.ChallengeInventedFriendDialog
import com.elcom.eonline.quizupapp.utils.ConstantsApp
import com.elcom.eonline.quizupapp.utils.LogUtils
import com.elcom.eonline.quizupapp.utils.PreferUtils
import kotlinx.android.synthetic.main.activity_challenge_from_friends.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import com.elcom.eonline.quizupapp.ui.listener.*
import com.elcom.eonline.quizupapp.utils.WrapContentLinearLayoutManager
import android.os.CountDownTimer
import com.elcom.eonline.quizupapp.ApplicationQuzup
import java.util.concurrent.TimeUnit


class ChallengeFromFriendsActivity : BaseActivityQuiz(), OnSocketGetOnlineListener, OnItemClickListener, OnSocketInviteOpponentListener, OnInvitationTimeCountDownListener,OnSocketSendChallengeInformationListener,OnRejectInvitationListener {



    private var mTopicId = ""
    private var listdata = ArrayList<String>()
    val mAdapter =  ChallengeFromFriendsAdapter(listdata,this, this)
    override fun getLayout(): Int {
        return R.layout.activity_challenge_from_friends
    }

    override fun initData() {
        if (intent.hasExtra(ConstantsApp.KEY_QUESTION_ID)){
            mTopicId = intent.getStringExtra(ConstantsApp.KEY_QUESTION_ID)
            (application as ApplicationQuzup).setOnRejectInvitationListener(this)
            if( ConstantsApp.socketManage != null){
                ConstantsApp.socketManage.initToGetListOnline(this)
//                ConstantsApp.socketManage.initToInventionFromFriend(this)
//                ConstantsApp.socketManage.initOnSocketSendChallengeInformationListener(this)

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
    }

    override fun onSomeoneInviteYouToPlayGame(resultQuestion: JSONObject) {
        LogUtils.e("SocketManage","ChallengeFromFriendsActivity Someone invite you to play a game " + resultQuestion.toString())
        LogUtils.e("SocketManage","ChallengeFromFriendsActivity Someone invite you to play a game Id " + PreferUtils().getUserId(this))

        if (resultQuestion["challenge"] == "false"){
           if(mChallengeGameDialog != null && mChallengeGameDialog!!.isShowing){
               mChallengeGameDialog!!.dismiss()
               Toast.makeText(this,"Lời mời bị từ chối", Toast.LENGTH_SHORT).show()
           }
            return
        }

        // When your friend accept your invitation, begin to play a game
        if(resultQuestion["challenge"] == "true" && resultQuestion["userSendId"] == PreferUtils().getUserId(this)){
           val intent = Intent(this,ChallengeWaitingToPlayGameActivity::class.java)
            intent.putExtra("data",resultQuestion.toString())
            intent.putExtra("accept","")
            startActivity(intent)
            // Call Api to get question info and then send data from socket

        } else if (resultQuestion["challenge"] == "true" && resultQuestion["userSendId"] != PreferUtils().getUserId(this)) {
        // When you wanna accept your friend to play Game
//            runOnUiThread {
//                Log.e("hailpt"," resultQuestion "+resultQuestion.toString())
//                val snack = Snackbar.make(lnRoot, "Bạn có một lời mời chơi game", Snackbar.LENGTH_SHORT) .setAction("Đồng ý", object : View.OnClickListener {
//                    override fun onClick(view: View) {
//                        // Accept the invitation
//                        sendInviteOrAcceptInvite(resultQuestion["userSendId"] as String, resultQuestion["sendId"] as String )
//
//
////                        startActivity(Intent(applicationContext,ChallengeWaitingToPlayGameActivity::class.java).putExtra("data",resultQuestion.toString()))
//                    }
//                })
//                val view = snack.getView()
//                val params = view.layoutParams as FrameLayout.LayoutParams
//                params.gravity = Gravity.BOTTOM
//                view.layoutParams = params
//                snack.show()
//            }
        }
    }


    override fun OnSocketSendChallengeInformationListener(resultQuestion: JSONObject) {
        val intent = Intent(this,ChallengeWaitingToPlayGameActivity::class.java)
        intent.putExtra("data_op",resultQuestion.toString())
        startActivity(intent)
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

//        if( ConstantsApp.CHALLENGE_TIME_COUNT_DOWN  != "0"){
//            Toast.makeText(this, "Đang Mời Người Khác Rồi Nhé !", Toast.LENGTH_SHORT).show()
//            return
//        }

        mChallengeGameDialog = ChallengeInventedFriendDialog(this, mOnlineList[position] as JSONObject, object : OnDialogInvitationListener {

            val mObject = mOnlineList[position] as JSONObject
            override fun onCancelInviteFriendToPlayGame() {
                stopCountDownTimer()
                ConstantsApp.CHALLENGE_TIME_COUNT_DOWN = "0"
                sendInviteOrAcceptInvite(mObject,"false")
            }

            override fun onInviteFriendToPlayGame() {

                stopCountDownTimer()
                startCountDownTimer()


                sendInviteOrAcceptInvite(mObject,"true")


            }
        })
        mChallengeGameDialog!!.show()
    }

    override fun onRejectInvitationListener() {
        if(mChallengeGameDialog != null && mChallengeGameDialog!!.isShowing){
            mChallengeGameDialog!!.dismiss()
        }
    }

    fun sendInviteOrAcceptInvite(mObject:JSONObject,challenge:String ){
        val myInfo = JSONObject()
        try {
            myInfo.put("topicId", mTopicId)
            myInfo.put("sendId", PreferUtils().getUserId(this))
            myInfo.put("toId", mObject["id"].toString() )
            myInfo.put("challenge", challenge)
            myInfo.put("url", "url")
            myInfo.put("name", PreferUtils().getName(this))
            myInfo.put("topicName", "Thể Thao")
            myInfo.put("urlTopic", "urlTopic")
            myInfo.put("userSendId", PreferUtils().getUserId(this))
        } catch (e: JSONException) {

        }
        ConstantsApp.socketManage.sendChallengeInformation(myInfo)

        Log.e("hailpt", " ChallengeFromFriendsActivity sendInviteOrAcceptInvite "+ myInfo.toString())
    }

    override fun onTimeCountDown(currentTime: String) {
        mChallengeGameDialog!!.setTime(currentTime)
    }

    private  var mOnlineList = JSONArray()
    override fun onUserOnlineByTopic(onlineList: JSONArray) {
//        if(topicId == mTopicId){


            LogUtils.e("SocketManage ~~~~~~~~~~",onlineList.toString())
            listdata.clear()


            for (i in 0 until onlineList.length()) {
                listdata.add(onlineList.getString(i))
            }

            if(mOnlineList.toString() != onlineList.toString()){
                runOnUiThread {
                    recyclerView.adapter.notifyDataSetChanged()
                }
            }

            mOnlineList = onlineList
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        if (mChallengeGameDialog != null){
            mChallengeGameDialog!!.dismiss()
        }
    }




    private var countDownTimer: CountDownTimer? = null
    fun startCountDownTimer() {

        val timeCountInMilliSeconds = (10 * 1000).toLong()
        countDownTimer = object : CountDownTimer(timeCountInMilliSeconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                LogUtils.e("SocketManage", " startCountDownTimer " + hmsTimeFormatter(millisUntilFinished))
//                mOnCountDown!!.onTimeCountDown(hmsTimeFormatter(millisUntilFinished))

                ConstantsApp.CHALLENGE_TIME_COUNT_DOWN = hmsTimeFormatter(millisUntilFinished)
            }

            override fun onFinish() {
                ConstantsApp.CHALLENGE_TIME_COUNT_DOWN = "0"
//                mOnCountDown!!.onTimeCountDown("0")
                countDownTimer!!.cancel()
            }

        }.start()
    }

    fun stopCountDownTimer() {
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
        }
    }

    private fun hmsTimeFormatter(milliSeconds: Long): String {
        return String.format("%d", TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)))
    }

    private var mOnCountDown: OnInvitationTimeCountDownListener? = null
    fun setListener(pOnCountDown: OnInvitationTimeCountDownListener) {
        mOnCountDown = pOnCountDown
    }



}
