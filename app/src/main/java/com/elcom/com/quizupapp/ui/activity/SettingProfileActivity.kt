package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.util.Log
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.profile.Profile
import com.elcom.com.quizupapp.ui.activity.presenter.SettingProfilePresenter
import com.elcom.com.quizupapp.ui.view.SettingProfileView
import com.elcom.com.quizupapp.utils.PreferUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*

class SettingProfileActivity : BaseActivityQuiz(), SettingProfileView {


    private val mSettingProfilePresenter = SettingProfilePresenter(this)
    override fun getLayout(): Int {
        return R.layout.activity_profile
    }

    override fun initView() {
        imvBack.setOnClickListener { onBackPressed() }
        tvFavorTopic.setOnClickListener {   startActivity(Intent(this, FavouriteTopicActivity::class.java)) }
    }

    override fun initData() {
        showProgessDialog()
        mSettingProfilePresenter.getData(PreferUtils().getUserId(this))
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

        tvNumberOfChallenge.text = profile.totalFollow.toString()
        tvFollowed.text = profile.totalFollow.toString()
        tvFriend.text = profile.totalFriend.toString()
        tvName.text = profile.name
        tvNameTop.text = profile.name
        tvNumberOfTopic.text = "Chủ đề yêu thích ( "+profile.topicFollow!!.data!!.size + " )"

        tvRanking.text = "Tỉ lệ thắng "+profile.winningRate + "%"
    }

}
