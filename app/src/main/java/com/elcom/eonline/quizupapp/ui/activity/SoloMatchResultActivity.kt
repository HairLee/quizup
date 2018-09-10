package com.elcom.eonline.quizupapp.ui.activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.model.entity.Result
import com.elcom.eonline.quizupapp.ui.network.RestClient
import com.elcom.eonline.quizupapp.ui.network.RestData
import com.elcom.eonline.quizupapp.utils.ConstantsApp
import com.elcom.eonline.quizupapp.utils.PreferUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_challenge_result.*
import kotlinx.android.synthetic.main.activity_solo_match_result.*
import kotlinx.android.synthetic.main.coin_and_ex_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.model.SharePhoto




class SoloMatchResultActivity : BaseActivityQuiz() {


    private var mMatchId = "1"
    private var mTopicId = "1"
    override fun getLayout(): Int {
        return R.layout.activity_solo_match_result
    }

    override fun initView() {
        btnStop.setOnClickListener {
            setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
            finish()
        }

        lnStatistic.setOnClickListener {
            startActivityForResult(Intent(this,SoloMatchStatisticActivity::class.java).putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId).putExtra(ConstantsApp.KEY_QUESTION_ID,mTopicId), ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
        }

//        btn_stop_playing.setOnClickListener {
//            setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY )
//            finish()
//        }

        lnShare.setOnClickListener {
            takeScreenshot()
        }
    }

    override fun initData() {
        if(intent.hasExtra(ConstantsApp.KEY_SOLO_MATCH_ID)){
            mMatchId = intent.getStringExtra(ConstantsApp.KEY_SOLO_MATCH_ID)
            mTopicId = intent.getStringExtra(ConstantsApp.KEY_QUESTION_ID)
            getResult()
        }
    }

    private fun getResult(){

        RestClient().getInstance().getRestService().getResultAfterPlayingGame( PreferUtils().getUserId(this),mTopicId,mMatchId).enqueue(object: Callback<RestData<Result>> {
            override fun onResponse(call: Call<RestData<Result>>?, response: Response<RestData<Result>>?) {
                if (response?.body() != null){
                    updateLayout(response.body().data!!)
                }
            }

            override fun onFailure(call: Call<RestData<Result>>?, t: Throwable?) {

            }
        })
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

            if( bitmap != null){
                val photo = SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .setCaption("Share Facebook")
                        .build()
                val content = SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build()
                ShareDialog.show(this,content)
            }

        } catch (e: Throwable) {
            // Several error may come out with file handling or DOM
            e.printStackTrace()
        }

    }

    private fun updateLayout(result:Result){
        tvTitle.text = result.nameTopic
        txt_topic_title.text = result.nameTopic
        Picasso.get().load(PreferUtils().getAvatar(this)).into(imvTopic)
        txt_coins.text = result.coins
        txt_point.text = result.point
        tvLevelConfirm.text = "Chuỗi thắng: "+result.current_win_string
        tvEx.text = "+"+result.pointAnswer
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY){
            when(resultCode){
                ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY -> {
                    setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY )
                    finish()
                }
            }
        }


    }


}
