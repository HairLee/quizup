package com.elcom.eonline.quizupapp.ui.fragment


import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.*
import com.elcom.eonline.quizupapp.ui.activity.model.entity.profile.Profile
import com.elcom.eonline.quizupapp.ui.activity.presenter.SettingProfilePresenter
import com.elcom.eonline.quizupapp.ui.adapter.SettingAdapter
import com.elcom.eonline.quizupapp.ui.dialog.ChallengeInventationDialog
import com.elcom.eonline.quizupapp.ui.dialog.SettingDialog
import com.elcom.eonline.quizupapp.ui.listener.OnDialogInvitationListener
import com.elcom.eonline.quizupapp.ui.listener.OnItemClickListener
import com.elcom.eonline.quizupapp.ui.network.RestClient
import com.elcom.eonline.quizupapp.ui.network.RestData
import com.elcom.eonline.quizupapp.ui.view.SettingProfileView
import com.elcom.eonline.quizupapp.utils.ConstantsApp
import com.elcom.eonline.quizupapp.utils.LogUtils
import com.elcom.eonline.quizupapp.utils.PreferUtils
import com.google.gson.JsonElement
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE
import com.theartofdev.edmodo.cropper.CropImage.getPickImageChooserIntent
import kotlinx.android.synthetic.main.fragment_setting.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : BaseFragment(), OnItemClickListener, SettingProfileView {

    private val mSettingProfilePresenter = SettingProfilePresenter(this)
    private val IMAGE_DIRECTORY = "/GIAIDOVN"
    private val GALLERY = 1
    private val CAMERA = 2
    public  var mImage: Bitmap? = null


    override fun onItemClicked(position: Int) {
        when(position){
            0->{
                startActivity(Intent(context, SettingProfileActivity::class.java))
            }

            1->{
                startActivity(Intent(context, SettingFriendActivity::class.java))
            }

           2->{
               startActivity(Intent(context, FavouriteTopicActivity::class.java))
            }

            4-> {
//                val   mChallengeGameDialog = SettingDialog(context!!,R.drawable.setting_dialog_wrong_email, "THỬ LẠI","QUAY LẠI", object : OnDialogInvitationListener {
//
//                    override fun onInviteFriendToPlayGame() {
//                        Toast.makeText(context!!, "Accept", Toast.LENGTH_SHORT).show()
//                    }
//
//                    override fun onCancelInviteFriendToPlayGame() {
//                        Toast.makeText(context!!, "Reject", Toast.LENGTH_SHORT).show()
//                    }
//
//                })
//                mChallengeGameDialog!!.show()
            }

            5->{
//                startActivity(Intent(context, SettingProfileActivity::class.java))
            }
        }
    }

    private var mView:ViewGroup? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if(mView == null){
            mView = inflater!!.inflate(R.layout.fragment_setting, container, false) as ViewGroup?

            showProgessDialog()
            mSettingProfilePresenter.getData(PreferUtils().getUserId(activity!!))

        }


        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView(){
        val myResArray = resources.getStringArray(R.array.setting_title)
        val  adapter = SettingAdapter(myResArray);
        adapter.setListener(this)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter

        imvSetting.setOnClickListener {
            startActivity(Intent(context, SettingEditAccountActivity::class.java))
        }

        registerReceiver()

        imvAva.setOnClickListener {
            isAvatarChanged = false
            showPictureDialog2()
        }

        imvCover.setOnClickListener {
            isAvatarChanged = true
            showPictureDialog2()
        }
    }

    override fun getDataSuccessfully(profile: Profile) {
        dismisProgressDialog()
        if( profile.cover != null){

            Picasso.get()
                    .load(profile.cover)
                    .error(R.drawable.ic_ava_default)
                    .into(imvBg);

        }

        if( profile.avatar != null){
            Picasso.get()
                    .load(profile.avatar)
                    .placeholder(R.drawable.picasso_progress_animation)
                    .error(R.drawable.ic_ava_default)
                    .into(imvAva)
        }

        tvName.text = profile.name
    }

    override fun getDataFault() {

    }

    private fun registerReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConstantsApp.KEY_LIVE_CHALLENGE_VALUE)
        activity!!.registerReceiver(receiver, intentFilter)
    }

    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (ConstantsApp.KEY_LIVE_CHALLENGE_VALUE.equals(action)) {
                mSettingProfilePresenter.getData(PreferUtils().getUserId(context))
            }
        }
    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(activity)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems,
                { _, which ->
                    when (which) {
                        0 ->  ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
                        1 ->   ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.CAMERA),2)
                    }
                })

        pictureDialog.show()
    }

    private fun showPictureDialog2() {
        if (CropImage.isExplicitCameraPermissionRequired(context!!)) {
            requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE)
        } else {
//            CropImage.startPickImageActivity(activity!!,this)

            startActivityForResult(
                    getPickImageChooserIntent(context!!), PICK_IMAGE_CHOOSER_REQUEST_CODE);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) run {
            val imageUri = CropImage.getPickImageResultUri(context!!, data)

            Log.e("hailpt", " CropImageView $imageUri")

            val fileLocation = File(imageUri.path)
            //            uploadAvatar(fileLocation);

            //            binding.profileImage.setImageBitmap();
            // For API >= 23 we need to check specifically that we have permissions to read external
            // storage,
            // but we don't know if we need to for the URI so the simplest is to try open the stream and
            // see if we get error.
            var requirePermissions = false
            if (CropImage.isReadExternalStoragePermissionsRequired(context!!, imageUri)) {

                // request permissions and handle the result in onRequestPermissionsResult()
                requirePermissions = true
                mCropImageUri = imageUri
                requestPermissions(
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE)
            } else {
                CropImageActivity.uri = imageUri
                goToCropActivity(imageUri.path)
            }
        } else if (requestCode == 10) run {
            if (resultCode == ConstantsApp.RESULT_CODE_CROP_IMAGE) {
                mImage = ConstantsApp.mImage
                saveImage(mImage!!)
            }
        }
    }

    private var avatar: File? = null
    fun saveImage(myBitmap: Bitmap): String {
        if(isAvatarChanged){
            imvBg.setImageBitmap(myBitmap)
        } else {
            imvAva.setImageBitmap(myBitmap)
        }
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
            MediaScannerConnection.scanFile(context,
                    arrayOf(f.path),
                    arrayOf("image/jpeg"), null)
            avatar = f
            uploadAvarOrBackGround(avatar!!)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.absolutePath)

            return f.absolutePath
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }



    private var mCropImageUri: Uri? = null
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if ((activity as HomeActivity).isStoragePermissionGranted()) {
            if (requestCode == CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE) {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    CropImage.startPickImageActivity(activity!!)
                } else {
                    Toast.makeText(context, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG)
                            .show()
                }
            }

            if (requestCode == CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
                if (mCropImageUri != null
                        && grantResults.size > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goToCropActivity(mCropImageUri!!.getPath())


                } else {
                    Toast.makeText(context, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG)
                            .show()
                }
            }
        }
    }

    fun goToCropActivity(path: String) {
        val intent = Intent(context, CropImageActivity::class.java)
        intent.putExtra(ConstantsApp.EXTRA_URI_STR, path)
        startActivityForResult(intent, 10)
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        activity!!.startActivityForResult(galleryIntent, GALLERY)
    }

    fun takePhotoFromCamera() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        activity!!.startActivityForResult(intent, CAMERA)
    }


    private var isAvatarChanged = false


    fun uploadAvarOrBackGround(file:File){
        val avatarImage = RequestBody.create(MediaType.parse("image/*"), file);
        var nameOfType = "avatar"
        if(isAvatarChanged){
            nameOfType = "cover"
        }

        val bodyAvatarImage = MultipartBody.Part.createFormData(nameOfType, file.name, avatarImage);
        showProgessDialog()
        RestClient().getRestService().updateAvatar(bodyAvatarImage).enqueue(object :Callback<RestData<Profile>>{
            override fun onFailure(call: Call<RestData<Profile>>?, t: Throwable?) {
                dismisProgressDialog()
                sendImageBroadcast()
            }

            override fun onResponse(call: Call<RestData<Profile>>?, response: Response<RestData<Profile>>?) {
                if (response?.body() != null){
                    dismisProgressDialog()
                    PreferUtils().setAvatar(context!!,response.body().data!!.avatar!!)
                    ConstantsApp.USER_AVATAR_ME = response.body().data!!.avatar

                        if (isAvatarChanged) {
//                            if( response.body().data!!.cover != null) {
//                                Picasso.get()
//                                        .load(response.body().data!!.cover)
//                                        .into(imvBg)
//                            }
                        } else {
//                            if( response.body().data!!.avatar != null) {
//                                Picasso.get()
//                                        .load(response.body().data!!.avatar)
//                                        .into(imvAva)
//                            }
                        }
                    }
                }
        })
    }

    private fun sendImageBroadcast() {
        val intent = Intent(ConstantsApp.KEY_LIVE_CHALLENGE_VALUE)
        activity!!.sendBroadcast(intent)
    }


}
