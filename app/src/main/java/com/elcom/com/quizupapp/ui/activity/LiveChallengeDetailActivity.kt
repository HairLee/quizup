package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.LiveChallenge
import com.elcom.com.quizupapp.ui.activity.presenter.LiveChallengeGameDetailPresenter
import com.elcom.com.quizupapp.ui.view.LiveChallengeDetailView
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.LogUtils
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_live_challenge_detail.*

class LiveChallengeDetailActivity : BaseActivityQuiz(), LiveChallengeDetailView {


    private val mLiveChallengeGameDetailPresenter = LiveChallengeGameDetailPresenter(this)
    private  var  liveChallenge:LiveChallenge? = null
    override fun getLayout(): Int {
        return R.layout.activity_live_challenge_detail
    }

    override fun initView() {
        btnJoinLiveChallenge.setOnClickListener {
            ProgressDialogUtils.showProgressDialog(this, 0, 0)
            if(liveChallenge != null){
                mLiveChallengeGameDetailPresenter.getData("10",liveChallenge!!.id.toString())
            }
        }
    }

    override fun initData() {
        if (intent.hasExtra(ConstantsApp.KEY_LIVE_CHALLENGE_VALUE)){
            liveChallenge = intent.extras.getSerializable(ConstantsApp.KEY_LIVE_CHALLENGE_VALUE) as LiveChallenge
            updateLayout(liveChallenge!!)
        }
    }

    private fun updateLayout(liveChallenge: LiveChallenge){
        tvInformationOfChallenge.text = liveChallenge.description
        tvRegulation.text = liveChallenge.comment
        tvReward.text = liveChallenge.awardText

        Picasso.get()
                .load(liveChallenge.awardStructure!![0].awardPicture)
                .into(imvTop1)

        Picasso.get()
                .load(liveChallenge.awardStructure!![1].awardPicture)
                .into(imvTop2)

        Picasso.get()
                .load(liveChallenge.awardStructure!![2].awardPicture)
                .into(imvTop3)
    }

    override fun registerSuccessfully() {
        ProgressDialogUtils.dismissProgressDialog()
        startActivityForResult(Intent(this,LiveChallengeInviteAndPlayGameActivity::class.java).putExtra(ConstantsApp.KEY_LIVE_CHALLENGE_ID_VALUE,liveChallenge!!.id.toString()),ConstantsApp.START_ACTIVITY_TO_MOVE_FROM_LIVECHALLENGE)
    }

    override fun registerFault() {
        ProgressDialogUtils.dismissProgressDialog()
        LogUtils.e("hailpt", " registerFault")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ConstantsApp.START_ACTIVITY_TO_MOVE_FROM_LIVECHALLENGE ){
            when(resultCode){
                0 -> {
                    finish()
                }
            }

        }
    }


}
