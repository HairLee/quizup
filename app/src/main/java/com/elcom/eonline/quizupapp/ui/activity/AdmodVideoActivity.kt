package com.elcom.eonline.quizupapp.ui.activity

import com.elcom.eonline.quizupapp.R
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
//        finish()
    }

    override fun onRewardedVideoAdLeftApplication() {
    }

    override fun onRewardedVideoAdLoaded() {
        mRewardedVideoAd.show()
    }

    override fun onRewardedVideoAdOpened() {
    }

    override fun onRewardedVideoCompleted() {
        finish()
    }

    override fun onRewarded(p0: RewardItem?) {
        finish()
    }

    override fun onRewardedVideoStarted() {
    }

    override fun onRewardedVideoAdFailedToLoad(p0: Int) {
    }

}
