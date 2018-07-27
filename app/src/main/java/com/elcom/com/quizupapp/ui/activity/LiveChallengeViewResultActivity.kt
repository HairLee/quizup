package com.elcom.com.quizupapp.ui.activity

import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.LiveChallengeResult
import com.elcom.com.quizupapp.ui.activity.presenter.LiveChallengeViewResultPresenter
import com.elcom.com.quizupapp.ui.adapter.LiveChallengeCongratulationAdapter
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener
import com.elcom.com.quizupapp.ui.view.LiveChallengeViewResultView
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_live_challenge_view_result.*

class LiveChallengeViewResultActivity : BaseActivityQuiz(), LiveChallengeViewResultView{


    private var showId = "0"
    private var mTotal = 0
    private var mLiveChallengeViewResultPresenter:LiveChallengeViewResultPresenter = LiveChallengeViewResultPresenter(this)
    override fun getLayout(): Int {
        return R.layout.activity_live_challenge_view_result
    }

    override fun initView() {

    }

    override fun initData() {
        if (intent.hasExtra(ConstantsApp.KEY_LIVECHALLENGE_SHOWID)){
            showId = intent.getStringExtra(ConstantsApp.KEY_LIVECHALLENGE_SHOWID)
            mTotal = intent.getIntExtra(ConstantsApp.KEY_LIVECHALLENGE_TOTAL,0)
            tvTotal.text = mTotal.toString() +"/" + mTotal.toString()
            mLiveChallengeViewResultPresenter.getTimeCountDownChallengeShowResult(showId)
        }
    }

    override fun getTimeCountDownSuccessfully(data: JsonElement) {
        ProgressDialogUtils.showProgressDialog(this, 0, 0)
        val mTimeOb = data.asString
        Handler().postDelayed({
            mLiveChallengeViewResultPresenter.getData(showId)
        },   mTimeOb.toLong()*1000)
    }

    override fun getDataSuccessfully(data:List<LiveChallengeResult>) {
        ProgressDialogUtils.dismissProgressDialog()
        setupRecyclerView(data)
    }

    override fun getDatafault() {
        ProgressDialogUtils.dismissProgressDialog()
    }

    private fun setupRecyclerView(data:List<LiveChallengeResult>) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = LiveChallengeCongratulationAdapter(data, object  : OnItemClickListener {
            override fun onItemClicked(position: Int) {

            }
        })
    }

    override fun onBackPressed() {
        setResult(ConstantsApp.START_ACTIVITY_TO_MOVE_FROM_LIVECHALLENGE_EXIT)
        finish()
    }



}
