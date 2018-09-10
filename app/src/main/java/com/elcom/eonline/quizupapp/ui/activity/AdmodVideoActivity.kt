package com.elcom.eonline.quizupapp.ui.activity

import android.hardware.fingerprint.FingerprintManager
import android.util.Log
import android.widget.Toast
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.utils.ConstantsApp
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener

class AdmodVideoActivity : BaseActivityQuiz(), RewardedVideoAdListener {


    private lateinit var mRewardedVideoAd: RewardedVideoAd
    override fun getLayout(): Int {
        return R.layout.activity_admod_video
    }

    override fun initView() {

    }

    override fun initData() {
        MobileAds.initialize(this, "ca-app-pub-7842886552548626/2863752478")

        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.rewardedVideoAdListener = this

        mRewardedVideoAd.loadAd("ca-app-pub-7842886552548626/2863752478",
                AdRequest.Builder().build())

    }

    override fun onRewardedVideoAdClosed() {
        Log.e("hailpt","onRewardedVideoAdClosed")
    }

    override fun onRewardedVideoAdLeftApplication() {
        Log.e("hailpt","onRewardedVideoAdLeftApplication")
    }

    override fun onRewardedVideoAdLoaded() {
        mRewardedVideoAd.show()
    }

    override fun onRewardedVideoAdOpened() {
        Log.e("hailpt","onRewardedVideoAdOpened")
    }

    override fun onRewardedVideoCompleted() {
        Log.e("hailpt","onRewardedVideoCompleted")

    }

    override fun onRewarded(p0: RewardItem?) {
        Toast.makeText(this, " onRewarded ", Toast.LENGTH_SHORT).show()
        setResult(ConstantsApp.RESULT_CODE_FROM_ADMODS_VIDEO_OK)
        finish()
    }

    override fun onRewardedVideoStarted() {
        Log.e("hailpt","onRewardedVideoStarted")
    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
        Log.e("hailpt","onRewardedVideoAdFailedToLoad")
        setResult(ConstantsApp.RESULT_CODE_FROM_ADMODS_VIDEO_CANCEL)
        finish()
    }

}
