package com.elcom.eonline.quizupapp.ui.activity

import android.Manifest
import android.app.AlertDialog
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import android.widget.ImageView
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.adapter.SettingEditAccountAdapter
import com.elcom.eonline.quizupapp.ui.listener.OnItemClickListener
import com.elcom.eonline.quizupapp.utils.PreferUtils
import kotlinx.android.synthetic.main.activity_setting_edit_account.*
import android.widget.Toast
import android.graphics.Bitmap
import android.provider.MediaStore
import java.io.IOException
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Environment
import java.nio.file.Files.exists
import android.os.Environment.getExternalStorageDirectory
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.CompoundButton
import com.elcom.eonline.quizupapp.ui.activity.model.entity.AnswerQuestion
import com.elcom.eonline.quizupapp.ui.activity.model.entity.profile.Profile
import com.elcom.eonline.quizupapp.ui.activity.presenter.SettingProfilePresenter
import com.elcom.eonline.quizupapp.ui.network.RestClient
import com.elcom.eonline.quizupapp.ui.network.RestData
import com.elcom.eonline.quizupapp.ui.view.SettingProfileView
import com.elcom.eonline.quizupapp.utils.ConstantsApp
import com.elcom.eonline.quizupapp.utils.LogUtils
import com.google.gson.JsonElement
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*


class SettingEditAccountActivity : BaseActivityQuiz(), OnItemClickListener, SettingProfileView {

    private val mSettingProfilePresenter = SettingProfilePresenter(this)


    private val IMAGE_DIRECTORY = "/demonuts"
    private val GALLERY = 1
    private val CAMERA = 2
    private var isGender = true // Man
    private var isGenderStr = "1"
    var ON_DO_NOT_DISTURB_CALLBACK_CODE = 12345
    var adapter:SettingEditAccountAdapter? = null
    override fun getLayout(): Int {
        return R.layout.activity_setting_edit_account
    }

    override fun initView() {

        swSound.isChecked = PreferUtils().getSoundSetting(this)


        swSound.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    PreferUtils().setSoundSetting(this@SettingEditAccountActivity,true)
                } else {
                    PreferUtils().setSoundSetting(this@SettingEditAccountActivity,false)
                }
            }

        })

    }

    override fun initData() {
        setupView()

    }

    private fun setupView(){
        val myResArray = resources.getStringArray(R.array.setting_edit_title)
        adapter = SettingEditAccountAdapter(myResArray);
        adapter!!.setListener(this)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
        showProgessDialog()
        mSettingProfilePresenter.getData(PreferUtils().getUserId(this))
        imvBack.setOnClickListener { onBackPressed() }
        tvLogout.setOnClickListener {
            logoutDialog()
        }

        tvSave.setOnClickListener {
            updateProfile()
        }
    }

    override fun getDataSuccessfully(profile: Profile) {
        dismisProgressDialog()
        edtName.hint = profile.name
        isGender = profile.gender != "0"
        adapter!!.setData(profile)

    }

    override fun getDataFault() {

    }

    override fun onItemClicked(position: Int) {
        when(position){

            0 -> {

            }

            2->{
                isGenderStr = if (isGender){
                    "0"
                } else {
                    "1"
                }
                isGender = !isGender
                adapter!!.setGender(isGender)
            }

            3-> {

            }

            4-> {

            }
        }
    }

    private fun logoutDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setMessage("Bạn có muốn đăng xuất không?")
        builder.setPositiveButton("Có", DialogInterface.OnClickListener { dialog, which ->
            PreferUtils().setToken(this,"")
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.putExtra("FROM_LOGOUT","FROM_LOGOUT")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            dialog.dismiss()
        })

        builder.setNegativeButton("Không", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        val alert = builder.create()
        alert.show()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ON_DO_NOT_DISTURB_CALLBACK_CODE) {
            this.requestDoNotDisturbPermissionOrSetDoNotDisturbApi23AndUp()
        }
    }


    private fun updateProfile(){

//        if (image == null){
//            return
//        }

//        val avatarImage = RequestBody.create(MediaType.parse("image/*"), image);
//        val coverImage = RequestBody.create(MediaType.parse("image/*"), imageCover);
//
//        val bodyAvatarImage = MultipartBody.Part.createFormData("avatar", image!!.name, avatarImage);
//        val bodyCoverImage = MultipartBody.Part.createFormData("cover", imageCover!!.name, coverImage);

        val nameBody =  RequestBody.create(MediaType.parse("text/plain"), edtName.text.toString())
        val genderBody =  RequestBody.create(MediaType.parse("text/plain"), isGenderStr)


        showProgessDialog()
        RestClient().getInstance().getRestService().updateProfile(nameBody,genderBody,nameBody,nameBody).enqueue(object : Callback<RestData<JsonElement>> {
            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                if (response?.body() != null){
                    dismisProgressDialog()
                    sendImageBroadcast()
                    mSettingProfilePresenter.getData(PreferUtils().getUserId(baseContext))
                    Toast.makeText(this@SettingEditAccountActivity, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                dismisProgressDialog()
            }
        })
    }


    private fun sendImageBroadcast() {
        val intent = Intent(ConstantsApp.KEY_LIVE_CHALLENGE_VALUE)
        sendBroadcast(intent)
    }


    private fun requestMutePhonePermsAndMutePhone() {
        try {
            if (Build.VERSION.SDK_INT < 23) {
                val audioManager = applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
            } else if (Build.VERSION.SDK_INT >= 23) {
                this.requestDoNotDisturbPermissionOrSetDoNotDisturbApi23AndUp()
            }
        } catch (e: SecurityException) {

        }

    }

    private fun requestDoNotDisturbPermissionOrSetDoNotDisturbApi23AndUp() {
        //TO SUPPRESS API ERROR MESSAGES IN THIS FUNCTION, since Ive no time to figrure our Android SDK suppress stuff
        if (Build.VERSION.SDK_INT < 23) {
            return
        }

        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (notificationManager.isNotificationPolicyAccessGranted) {
            val audioManager = applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
        } else {
            // Ask the user to grant access
            val intent = Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            startActivityForResult(intent, ON_DO_NOT_DISTURB_CALLBACK_CODE)
        }
    }




}
