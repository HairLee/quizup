package com.elcom.com.quizupapp.ui.fragment


import android.Manifest
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.SettingEditAccountActivity
import com.elcom.com.quizupapp.ui.activity.SettingFriendActivity
import com.elcom.com.quizupapp.ui.activity.SettingProfileActivity
import com.elcom.com.quizupapp.ui.activity.model.entity.profile.Profile
import com.elcom.com.quizupapp.ui.activity.presenter.SettingProfilePresenter
import com.elcom.com.quizupapp.ui.adapter.SettingAdapter
import com.elcom.com.quizupapp.ui.dialog.ChallengeInventationDialog
import com.elcom.com.quizupapp.ui.dialog.SettingDialog
import com.elcom.com.quizupapp.ui.listener.OnDialogInvitationListener
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener
import com.elcom.com.quizupapp.ui.network.RestClient
import com.elcom.com.quizupapp.ui.network.RestData
import com.elcom.com.quizupapp.ui.view.SettingProfileView
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.LogUtils
import com.elcom.com.quizupapp.utils.PreferUtils
import com.google.gson.JsonElement
import com.squareup.picasso.Picasso
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
    private val IMAGE_DIRECTORY = "/demonuts"
    private val GALLERY = 1
    private val CAMERA = 2

    override fun onItemClicked(position: Int) {
        when(position){
            0->{
                startActivity(Intent(context, SettingProfileActivity::class.java))
            }

            1->{
                startActivity(Intent(context, SettingFriendActivity::class.java))
            }

            4-> {
                val   mChallengeGameDialog = SettingDialog(context!!,R.drawable.setting_dialog_wrong_email, "THỬ LẠI","QUAY LẠI", object : OnDialogInvitationListener {

                    override fun onInviteFriendToPlayGame() {
                        Toast.makeText(context!!, "Accept", Toast.LENGTH_SHORT).show()
                    }

                    override fun onCancelInviteFriendToPlayGame() {
                        Toast.makeText(context!!, "Reject", Toast.LENGTH_SHORT).show()
                    }

                })
                mChallengeGameDialog!!.show()
            }

            5->{
                startActivity(Intent(context, SettingProfileActivity::class.java))
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
            showPictureDialog()
        }

        imvCover.setOnClickListener {
            isAvatarChanged = true
            showPictureDialog()
        }
    }

    override fun getDataSuccessfully(profile: Profile) {
        dismisProgressDialog()
        if( profile.cover != null){

            Picasso.get()
                    .load(profile.cover)
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

                    if(  response.body().data!!.avatar != null) {
                        if (isAvatarChanged) {
                            Picasso.get()
                                    .load(response.body().data!!.cover)
                                    .placeholder(R.drawable.picasso_progress_animation)
                                    .error(R.drawable.ic_ava_default).into(imvBg)
                        } else {
                            Picasso.get()
                                    .load(response.body().data!!.avatar)
                                    .placeholder(R.drawable.picasso_progress_animation)
                                    .error(R.drawable.ic_ava_default).into(imvAva)

                        }
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
