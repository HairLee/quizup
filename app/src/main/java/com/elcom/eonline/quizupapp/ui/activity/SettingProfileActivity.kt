package com.elcom.eonline.quizupapp.ui.activity

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.model.entity.profile.Profile
import com.elcom.eonline.quizupapp.ui.activity.presenter.SettingProfilePresenter
import com.elcom.eonline.quizupapp.ui.network.RestClient
import com.elcom.eonline.quizupapp.ui.network.RestData
import com.elcom.eonline.quizupapp.ui.view.SettingProfileView
import com.elcom.eonline.quizupapp.utils.PreferUtils
import com.google.gson.JsonElement
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingProfileActivity : BaseActivityQuiz(), SettingProfileView {

    private var userId = ""
    private val mSettingProfilePresenter = SettingProfilePresenter(this)
    override fun getLayout(): Int {
        return R.layout.activity_profile
    }

    override fun initView() {
        imvBack.setOnClickListener { onBackPressed() }
        tvFavorTopic.setOnClickListener {   startActivity(Intent(this, FavouriteTopicActivity::class.java)) }

        rlFollow.setOnClickListener {
            followFriend(userId)
        }
    }

    override fun initData() {

        if(intent.hasExtra("USER_ID")){
            showProgessDialog()
            userId = intent.getStringExtra("USER_ID")
            mSettingProfilePresenter.getData(userId)
        } else {
            showProgessDialog()
            tlFollowFriend.visibility = View.GONE
            mSettingProfilePresenter.getData(PreferUtils().getUserId(this))
        }


    }

    override fun getDataSuccessfully(profile: Profile) {
        dismisProgressDialog()
        updateView(profile)
    }

    override fun getDataFault() {

    }

    private fun updateView(profile: Profile){

        if( profile.cover != null){
            Picasso.get()
                    .load(profile.cover)
                    .into(imvBg)
        }

        if( profile.avatar != null){
            Picasso.get()

                    .load(profile.avatar)
                    .into(imvAva)
        }

        tvNumberOfChallenge.text = profile.totalMatchSolo.toString()
        tvFollowed.text = profile.totalFollow.toString()
        tvFriend.text = profile.totalFriend.toString()
        tvName.text = profile.name
        tvNameTop.text = profile.name
        tvNumberOfTopic.text = "Chủ đề yêu thích ( "+profile.topicFollow!!.data!!.size + " )"

        tvRanking.text = "Tỉ lệ thắng "+profile.winningRate + "%"
    }

    fun followFriend(userId:String){

        RestClient().getInstance().getRestService().followFriend(userId).enqueue(object : Callback<RestData<JsonElement>> {
            override fun onFailure(call: Call<RestData<JsonElement>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<RestData<JsonElement>>?, response: Response<RestData<JsonElement>>?) {
                    if(response?.body() != null){
                    Toast.makeText(this@SettingProfileActivity, "Theo dõi thành công", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}
