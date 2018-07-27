package com.elcom.com.quizupapp.ui.custom

import android.content.Context
import android.util.Log
import com.elcom.com.quizupapp.ui.listener.OnSocketInviteOpponentListener
import com.elcom.com.quizupapp.ui.listener.OnSocketGetOnlineListener
import com.elcom.com.quizupapp.ui.listener.OnSocketListener
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.LogUtils
import com.elcom.com.quizupapp.utils.PreferUtils
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URISyntaxException

/**
 * Created by Hailpt on 5/3/2018.
 */
class SocketManage {

    private var mSocket: Socket? = null
    private var mOnSocketListener: OnSocketListener? = null
    private var mOnSocketGetOnlineListListener:OnSocketGetOnlineListener? = null
    private var mOnInviteOpponentListener: OnSocketInviteOpponentListener? = null
    fun init(pOnSocketListener:OnSocketListener ){
        mOnSocketListener = pOnSocketListener
    }

    fun initToGetListOnline(pOnSocketGetOnlineListListener:OnSocketGetOnlineListener ){
        mOnSocketGetOnlineListListener = pOnSocketGetOnlineListListener
    }

    fun initToInventionFromFriend(pOnInviteOpponentListener:OnSocketInviteOpponentListener){
        mOnInviteOpponentListener = pOnInviteOpponentListener
    }

    fun connectSocket(){
        Log.e("SocketManage", " Connecting... ")

        try {
            mSocket = IO.socket("http://192.168.86.91:3000")
        } catch (e: URISyntaxException) {

        }

        mSocket!!.connect()
        mSocket!!.on("connect", onNewMessage)
        mSocket!!.on("message", onNewMessage)
        mSocket!!.on("authentication", authentication)
        mSocket!!.on("countDown", countDown)
        mSocket!!.on("resultQuestion", resultQuestion)
        mSocket!!.on("getUserOnlineByTopic", getUserOnlineByTopic)
        mSocket!!.on("sendChallengeInformation", sendChallengeInformation)
    }

    fun disconnect(){
        mSocket!!.disconnect()
        mSocket!!.off("new message", onNewMessage)
        mSocket!!.off("message", onNewMessage)
        mSocket!!.off("authentication", authentication)
        mSocket!!.off("resultQuestion", resultQuestion)
        LogUtils.e("SocketManage", " SocketManage onNewMessage Disconnected" )
    }

    private val onNewMessage = Emitter.Listener { args ->
        LogUtils.e("SocketManage", " SocketManage onNewMessage Connected" )

        if (mOnSocketListener != null){
            mOnSocketListener!!.onSocketConnected()
        }
    }

    /*authentication*/
    private val authentication = Emitter.Listener { args ->
        mOnSocketListener!!.onAuthentication()
    }

    fun sendUserInformationBySocket(context:Context){
        val myInfo = JSONObject()
        try {
            myInfo.put("token", PreferUtils().getToken(context))
        } catch (e: JSONException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        mSocket!!.emit("authentication", myInfo)
    }

   /* set Online Myself*/
   fun sendOnlineTopicMyself(context:Context, topicId:String){
       val myInfo = JSONObject()
       try {
           myInfo.put("topicId", topicId)
           myInfo.put("sendId", PreferUtils().getUserId(context))
       } catch (e: JSONException) {
           // TODO Auto-generated catch block
           e.printStackTrace()
       }

       mSocket!!.emit("setUserOnlineByTopic", myInfo)
   }

    fun sendOfflineTopicMyself(context:Context, topicId:String){
        val myInfo = JSONObject()
        try {
            myInfo.put("topicId", topicId)
            myInfo.put("sendId", PreferUtils().getUserId(context))
        } catch (e: JSONException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        mSocket!!.emit("disconnectUserByTopic", myInfo)
    }


    /*getUserOnlineByTopic*/
    private val getUserOnlineByTopic = Emitter.Listener { args ->
        val data = args[0]  as JSONObject
        val jsonArray = data["data"] as JSONArray
        if(mOnSocketGetOnlineListListener != null){
            mOnSocketGetOnlineListListener!!.onUserOnlineByTopic(jsonArray)
        }
    }


    fun getUserOnlineByTopic(mInfo:JSONObject) {
        mSocket!!.emit("getUserOnlineByTopic", mInfo)
    }
    /*getUserOnlineByTopic*/

    /*sendChallengeInformation*/
    private val sendChallengeInformation = Emitter.Listener { args ->
        val data = args[0]  as JSONObject

        if(mOnInviteOpponentListener != null){
            mOnInviteOpponentListener!!.onSomeoneInviteYouToPlayGame(data)
        }

    }


    fun sendChallengeInformation(mInfo:JSONObject) {
        mSocket!!.emit("sendChallengeInformation", mInfo)
    }
    /*authentication*/


    /*CountDown*/
    private val countDown = Emitter.Listener { args ->
        val data = args[0] as JSONObject
        mOnSocketListener!!.onCountDown(getIntValueObjectFromKey(data,"CountDownTime"))
    }

    fun countDownBySocket(mInfo:JSONObject) {
        mSocket!!.emit("countDown", mInfo)
    }
    /*CountDown*/



    /*resultQuestion*/
    private val resultQuestion = Emitter.Listener { args ->
        val data = args[0] as JSONObject
        mOnSocketListener!!.onResultQuestion(data)
    }

    fun sendMyAnswerBySocket(data:JSONObject){
        mSocket!!.emit("resultQuestion", data)
    }
    /*resultQuestion*/



    private fun getStringValueObjectFromKey(pData:JSONObject,pKey:String): String{
        var  countDown = ""
        try {
            countDown = pData.getString(pKey)
        } catch (e: JSONException) {

        }
        return countDown
    }

    private fun getIntValueObjectFromKey(pData:JSONObject,pKey:String): Int{
        var  countDown = 0
        try {
            countDown = pData.getInt(pKey)
        } catch (e: JSONException) {

        }
        return countDown
    }



}