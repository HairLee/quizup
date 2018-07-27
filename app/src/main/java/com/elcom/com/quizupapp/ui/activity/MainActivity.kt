package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.view.View
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.presenter.MainPresenter
import com.elcom.com.quizupapp.ui.view.MainView
import com.elcom.com.quizupapp.utils.AnimationUtil
import com.elcom.com.quizupapp.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView, View.OnClickListener, AnimationUtil.onAnimationFinished {

    private var mMainPresenter:MainPresenter = MainPresenter(this)

    override fun dofinish() {
        dismisProgressDialog()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        btnShow.setOnClickListener(this)
        AnimationUtil.setAnimationfinished(this)
//        mMainPresenter.getData()
    }

    override fun initData() {

    }

    override fun resumeData() {
        Utils().printKeyHash(this)
    }

    override fun showContentForText(mContent: String) {
        tvShow.setText(mContent)
//        dismisProgressDialog()
        avi.hide()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(p0: View?) {

        when(p0){
            btnShow -> requestServerAndGetData()
        }

    }

    private fun requestServerAndGetData(){
//        showProgessDialog()
        avi.show()
        mMainPresenter.getData()
    }

    fun doAnimation(){
        showProgessDialog()

        AnimationUtil.flipInvisToVis(tvShow)
    }

    fun doMoveAnotherActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}
