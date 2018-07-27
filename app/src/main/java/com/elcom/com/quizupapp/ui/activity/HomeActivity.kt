package com.elcom.com.quizupapp.ui.activity

import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import com.elcom.com.quizupapp.QuizUpApplication
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.fragment.*
import com.elcom.com.quizupapp.ui.listener.OnSocketInviteOpponentListener
import com.elcom.com.quizupapp.utils.BottomNavigationViewHelper
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.LogUtils
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_solo_question_intro.*
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class HomeActivity : BaseActivityQuiz(), OnSocketInviteOpponentListener {

    override fun onSomeoneInviteYouToPlayGame(resultQuestion: JSONObject) {
//        runOnUiThread {
//            val snack = Snackbar.make(lnRoot, "Someone invite you to play a game", Snackbar.LENGTH_SHORT)
//            val view = snack.getView()
//            val params = view.layoutParams as FrameLayout.LayoutParams
//            params.gravity = Gravity.BOTTOM
//            view.layoutParams = params
//            snack.show()
//        }
    }


    var mainFragment = ListTopicGroupTotalFragment()
    var liveChallengeFragment = LiveChallengeFragment()
    var homeFragment = HomeFragment()
    var settingFragment = SettingFragment()
    var mEmail:String = ""
    var selectedFragment: Fragment? = mainFragment
    var mTagOfFragment = "homeFragment"
    override fun getLayout(): Int {
        return R.layout.activity_home
    }

    override fun initView() {

    }

    override fun initData() {




    }

    private fun moveToGameIntroduction(topic_id:String, match_id:String, question_number:Int){
        val intent = Intent(applicationContext, SoloQuestionIntro::class.java)
        intent.putExtra(ConstantsApp.KEY_QUESTION_ID,topic_id)
        intent.putExtra(ConstantsApp.KEY_SOLO_MATCH_ID,match_id)
        intent.putExtra(ConstantsApp.KEY_QUESTION_NUMBER,question_number)
        startActivityForResult(intent, ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }


    override fun onResume() {
        super.onResume()


        navigation.selectedItemId = R.id.action_item1
        BottomNavigationViewHelper.removeShiftMode(navigation)
        navigation.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.action_item2 -> {
                    selectedFragment = mainFragment
                    mTagOfFragment = "mainFragment"
                }

                R.id.action_item1 -> {
                    selectedFragment = homeFragment
                    mTagOfFragment = "homeFragment"
                }

                R.id.action_item3 -> {
                    selectedFragment = liveChallengeFragment
                    mTagOfFragment = "liveChallengeFragment"
                }


                R.id.action_item4 -> {
                    selectedFragment = settingFragment
                    mTagOfFragment = "settingFragment"
                }
//                R.id.action_item4 -> selectedFragment = SettingFragment()
//                R.id.action_item5 -> selectedFragment = SettingFragment()
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, selectedFragment, mTagOfFragment)
            transaction.commit()
            true

        }

        //Manually displaying the first fragment - one time only
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, homeFragment,mTagOfFragment)
        transaction.commit()

        if(intent.hasExtra(ConstantsApp.KEY_QUESTION_ID)){
            val  topicId = intent.getStringExtra(ConstantsApp.KEY_QUESTION_ID)
            val  matchId = intent.getStringExtra(ConstantsApp.KEY_SOLO_MATCH_ID)
            val  questionNumber = intent.getIntExtra(ConstantsApp.KEY_QUESTION_NUMBER,1)
            moveToGameIntroduction(topicId,matchId,questionNumber)
        }



        if( ConstantsApp.socketManage != null){
            ConstantsApp.socketManage.initToInventionFromFriend(this)
        }
    }


    fun addHeader(pView:View){
        lnHeader.addView(pView)
    }

    fun sendTest(){
        val intent = Intent()
        intent.action = "com.example.SendBroadcast"
        intent.putExtra("name", "Ambition")
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        sendBroadcast(intent)
    }

    override fun onBackPressed() {

        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_layout)
        LogUtils.e("hailpt","onSomeoneInviteYouToPlayGame onBackPressed "+currentFragment.tag)
        if (currentFragment.tag == "mainFragment" ){
            LogUtils.e("hailpt","onSomeoneInviteYouToPlayGame onBackPressed ")
        }

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish();
        } else {
            supportFragmentManager.popBackStackImmediate()
        }
    }

    private val IMAGE_DIRECTORY = "/demonuts"
    fun saveImage(myBitmap: Bitmap): File? {

        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
                Environment.getExternalStorageDirectory().toString() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs()
        }

        try {
            val f = File(wallpaperDirectory, Calendar.getInstance()
                    .timeInMillis.toString() + ".jpg")
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                    arrayOf(f.path),
                    arrayOf("image/jpeg"), null)


            settingFragment.uploadAvarOrBackGround(f)



            fo.close()
            Log.e("TAG", " SettingEditAccountActivity File Saved::--->" + f.path)

            return f
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return null
    }

    private val GALLERY = 1
    private val CAMERA = 2
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == FragmentActivity.RESULT_CANCELED) {
            return
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show()

                    saveImage(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        } else if (requestCode == CAMERA) {
            val bitmap = data!!.extras!!.get("data") as Bitmap
            saveImage(bitmap)
            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Toast.makeText(this, "onRequestPermissionsResult", Toast.LENGTH_SHORT).show();
        when(requestCode){

            1->{
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    settingFragment.choosePhotoFromGallary()

                } else {
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
            }

            2-> {
                settingFragment.takePhotoFromCamera()
            }
        }

    }

}
