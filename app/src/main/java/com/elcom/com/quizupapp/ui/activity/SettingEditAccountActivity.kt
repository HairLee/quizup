package com.elcom.com.quizupapp.ui.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import android.widget.ImageView
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.adapter.SettingEditAccountAdapter
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener
import com.elcom.com.quizupapp.utils.PreferUtils
import kotlinx.android.synthetic.main.activity_setting_edit_account.*
import android.widget.Toast
import android.graphics.Bitmap
import android.provider.MediaStore
import java.io.IOException
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.media.MediaScannerConnection
import android.os.Environment
import java.nio.file.Files.exists
import android.os.Environment.getExternalStorageDirectory
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.elcom.com.quizupapp.ui.activity.model.entity.AnswerQuestion
import com.elcom.com.quizupapp.ui.activity.model.entity.profile.Profile
import com.elcom.com.quizupapp.ui.activity.presenter.SettingProfilePresenter
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.view.SettingProfileView
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.LogUtils
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
    var adapter:SettingEditAccountAdapter? = null
    override fun getLayout(): Int {
        return R.layout.activity_setting_edit_account
    }

    override fun initView() {

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
            PreferUtils().setToken(this,"")
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.putExtra("FROM_LOGOUT","FROM_LOGOUT")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        tvSave.setOnClickListener {
            updateProfile()
        }
    }

    override fun getDataSuccessfully(profile: Profile) {
        dismisProgressDialog()
        edtName.hint = profile.name
        adapter!!.setData(profile)

    }

    override fun getDataFault() {

    }

    override fun onItemClicked(position: Int) {
        when(position){

            0 -> {

            }

            2->{

            }

            3-> {
                isAvatarChanged = true
                showPictureDialog()
            }

            4-> {
                isAvatarChanged = false
                showPictureDialog()
            }
        }
    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems,
                { _, which ->
                    when (which) {
                        0 ->  ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
                        1 ->   ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),2)
                    }
                })

        pictureDialog.show()
    }

    private fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }


    private var image:File? = null
    private var imageCover:File? = null
    private var isAvatarChanged = false
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
            if(isAvatarChanged){
                image = f
            } else {
                imageCover = f
            }

            fo.close()
            Log.e("TAG", " SettingEditAccountActivity File Saved::--->" + f.path)

            return f
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return null
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CANCELED) {
            return
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show()
                    adapter!!.setAvaBitmap(bitmap)

                    saveImage(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        } else if (requestCode == CAMERA) {
            val bitmap = data!!.extras!!.get("data") as Bitmap
            adapter!!.setAvaBitmap(bitmap)
            saveImage(bitmap)
            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun updateProfile(){

        if (image == null){
            return
        }

        val avatarImage = RequestBody.create(MediaType.parse("image/*"), image);
        val coverImage = RequestBody.create(MediaType.parse("image/*"), imageCover);

        val bodyAvatarImage = MultipartBody.Part.createFormData("avatar", image!!.name, avatarImage);
        val bodyCoverImage = MultipartBody.Part.createFormData("cover", imageCover!!.name, coverImage);

        val nameBody =  RequestBody.create(MediaType.parse("text/plain"), edtName.text.toString())
        val genderBody =  RequestBody.create(MediaType.parse("text/plain"), "1")


        showProgessDialog()
        RestClient().getInstance().getRestService().updateProfile(nameBody,bodyAvatarImage,bodyCoverImage,genderBody,nameBody,nameBody).enqueue(object : Callback<RestData<JsonElement>> {
            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                if (response?.body() != null){
                    dismisProgressDialog()
                    sendImageBroadcast()
                    LogUtils.e("hailpt"," updateProfile onResponse "+ response.body().data.toString())
                }
            }

            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {
                dismisProgressDialog()
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){

            1->{
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    choosePhotoFromGallary()
                } else {
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
            }

            2-> {
                takePhotoFromCamera()
            }
        }

    }





    private fun sendImageBroadcast() {
        val intent = Intent(ConstantsApp.KEY_LIVE_CHALLENGE_VALUE)
        sendBroadcast(intent)
    }


}
