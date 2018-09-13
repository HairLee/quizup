package com.elcom.eonline.quizupapp.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.model.entity.ChallengeMatching
import com.elcom.eonline.quizupapp.ui.custom.SocketManage
import com.elcom.eonline.quizupapp.utils.ConstantsApp
import com.elcom.eonline.quizupapp.utils.PreferUtils
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.widget.ShareDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_challenge_result.*
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.util.*
import android.R.attr.bitmap
import android.net.Uri
import android.provider.MediaStore.Images
import com.elcom.eonline.quizupapp.ApplicationQuzup
import com.elcom.eonline.quizupapp.ui.listener.OnFinishActivityListener
import com.elcom.eonline.quizupapp.utils.ApplicationLifecycleHandler


class ChallengeResultActivity : BaseActivityQuiz(), SocketManage.OnGetResultQuestion, OnFinishActivityListener {


    private var isFromOpoonentOrYou = false // true = you -- false = opponent
    private var opAvatar = ""
    private var opName = ""
    private var topicId = ""
    private var matchId = ""
    private var sendId = ""
    private var toId = ""
    override fun getLayout(): Int {
        return R.layout.activity_challenge_result
    }

    override fun initView() {
        btn_stop_playing.setOnClickListener {
            setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY )
            finish()
        }

        imvShare.setOnClickListener {
            takeScreenshot()
        }

        imvStalistic.setOnClickListener {
            if(topicId != ""){
                startActivityForResult(Intent(this,SoloMatchStatisticActivity::class.java).putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,matchId).putExtra(ConstantsApp.KEY_QUESTION_ID,topicId), ConstantsApp.RESULT_CODE_FROM_CHALLENGE_RESULT)
            }
        }

        btnPlayAgain.setOnClickListener {
            Log.e("hailpt", " Current Activity == " +  ApplicationLifecycleHandler.getCurrentActivityName());
            sendInviteOrAcceptInvite()
        }
    }

    private fun takeScreenshot() {
        val now = Date()
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now)

        try {
            // image naming and path  to include sd card  appending name you choose for file
            val mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg"

            // create bitmap screen capture
            val v1 = window.decorView.rootView
            v1.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(v1.drawingCache)
            v1.isDrawingCacheEnabled = false

            val imageFile = File(mPath)

            val outputStream = FileOutputStream(imageFile)
            val quality = 100
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputStream.flush()
            outputStream.close()


            val bitmapPath = Images.Media.insertImage(contentResolver, bitmap, "title", null)
            val bitmapUri = Uri.parse(bitmapPath)

            if( bitmapUri != null){
                shareResult(bitmapUri)
            }

        } catch (e: Throwable) {
            // Several error may come out with file handling or DOM
            e.printStackTrace()
        }

    }

    private fun shareResult(uriToImage:Uri){
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uriToImage)
            type = "image/jpeg"
        }
        startActivity(Intent.createChooser(shareIntent, "Share the result"))
    }

    private fun shareResult2(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
        }
        startActivity(sendIntent)
    }

    override fun initData() {

        if(intent.hasExtra(ConstantsApp.KEY_CHALLENGE_USER_ID)){

            sendId = intent.extras.getString(ConstantsApp.KEY_CHALLENGE_USER_ID)
            toId = intent.extras.getString(ConstantsApp.KEY_CHALLENGE_TO_ID)
            val statusUserBot = intent.extras.getString(ConstantsApp.KEY_CHALLENGE_USER_BOT)
            topicId = intent.extras.getString(ConstantsApp.KEY_QUESTION_ID)
            matchId = intent.extras.getString(ConstantsApp.KEY_SOLO_MATCH_ID)
            opAvatar = intent.extras.getString(ConstantsApp.KEY_AVATAR_OPPONENT)
            opName = intent.extras.getString(ConstantsApp.KEY_NAME_OPPONENT)

            isFromOpoonentOrYou = intent.getBooleanExtra(ConstantsApp.KEY_CHALLENGE_IS_OPPONENT,false)

            updateLayout()
            ConstantsApp.socketManage.setOnGetResultQuestion(this)

            showProgessDialog()
            Handler().postDelayed(Runnable {
                dismisProgressDialog()
                getResultMatchDuel(sendId,toId,matchId,topicId,statusUserBot)
            }, 4000)


            (application as ApplicationQuzup).setOnFinishActivityListener(this)

        }

    }

    fun sendInviteOrAcceptInvite(){
        val myInfo = JSONObject()
        try {
            myInfo.put("topicId", topicId)
            myInfo.put("sendId", PreferUtils().getUserId(this))
            myInfo.put("toId", toId )
            myInfo.put("challenge", "true")
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


    private fun updateLayout(){
        Picasso.get().load(opAvatar).into(imvOpAva)
        Picasso.get().load(PreferUtils().getAvatar(this)).into(imvMyAva)
        tvObName.text = opName
    }

    override fun onBackPressed() {
        setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
        finish()
    }

    override fun onGetResultQuestion(resultQuestion: JSONObject) {
        Log.e("hailpt"," onGetResultQuestion "+ resultQuestion.toString())

        try {

            if(PreferUtils().getUserId(this) == resultQuestion["sendUserPoint"]){

                runOnUiThread {
                    tvNameOfTopic.text = resultQuestion["topicName"].toString()
                    tvMyScore.text = resultQuestion["sendUserPoint"].toString()
                    tvOpScore.text = resultQuestion["toUserPoint"].toString()

                    tvMyLevel.text = "Level : "+ resultQuestion["levelSendId"].toString()
                    tvObLevel.text = "Level : "+ resultQuestion["levelToId"].toString()


                    if (resultQuestion["result"] == 1) {
                        tvConfirm.text = "BẠN ĐÃ CHIẾN THẮNG"
                    } else if(resultQuestion["result"] == 2){
                        tvConfirm.text = "BẠN ĐÃ THUA"
                    } else {
                        tvConfirm.text = "BẠN ĐÃ HÒA"
                    }

                    tvScore.text = "+"+ resultQuestion["toUserPoint"].toString()
                }

            } else {

                runOnUiThread {
                    tvNameOfTopic.text = resultQuestion["topicName"].toString()
                    tvOpScore.text = resultQuestion["toUserPoint"].toString()
                    tvMyScore.text = resultQuestion["sendUserPoint"].toString()


                    tvMyLevel.text = "Level : "+  resultQuestion["levelToId"].toString()
                    tvObLevel.text = "Level : "+  resultQuestion["levelSendId"].toString()

                    if (resultQuestion["result"] == 1) {
                        tvConfirm.text = "BẠN ĐÃ CHIẾN THẮNG"
                    } else if(resultQuestion["result"] == 2){
                        tvConfirm.text = "BẠN ĐÃ THUA"
                    } else {
                        tvConfirm.text = "BẠN ĐÃ HÒA"
                    }

                    tvScore.text = "+"+  resultQuestion["sendUserPoint"]
                }


            }



        } catch (e: JSONException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

    }

    fun getResultMatchDuel(sendId:String, toId:String,matchId:String, topicId:String, statusUserBot:String){
        val data = JSONObject()
        try {


            data.put("sendId", sendId)
            data.put("toId", toId)
            data.put("matchId", matchId)
            data.put("topicId", topicId)
            data.put("statusUserBot", statusUserBot)
        } catch (e: JSONException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        if(ConstantsApp.socketManage != null){
            ConstantsApp.socketManage.getResultMatchDuel(data)
        }

        Log.e("hailpt"," getResultMatchDuel ~~> "+ data.toString())

    }

    override fun onFinishActivityListener() {
        Log.e("hailpt"," onFinishActivityListener ~~> ")
        setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY){
            when(resultCode){

                ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY -> {
                    setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
                    finish()
                }

                ConstantsApp.RESULT_CODE_TO_CONTINUE_TO_PLAY_GAME_FROM_QUIZUPACTIVITY -> {
                    setResult(ConstantsApp.RESULT_CODE_FROM_RIGHT_ANSWER_USING_COINS)
                    finish()
                }
            }
        }
    }


}
