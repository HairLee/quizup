package com.elcom.com.quizupapp.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.custom.SocketManage
import com.elcom.com.quizupapp.ui.listener.OnSocketListener
import com.elcom.com.quizupapp.utils.LogUtils
import com.elcom.com.quizupapp.utils.ProgressDialogUtils

/**
 * Created by Hailpt on 3/23/2018.
 */
abstract class BaseActivityQuiz : FragmentActivity() {

    protected abstract fun getLayout(): Int

    protected abstract fun initView()

    protected abstract fun initData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initView()
        initData()

    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e("SocketManage", "BaseActivityQuiz onDestroy")
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun startActivityForResultQuiz(cls: Class<*>, requestCode: Int, bundle: Bundle) {
        val intent = Intent(this, cls)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    fun showProgessDialog() {
        ProgressDialogUtils.showProgressDialog(this, 0, 0)
    }

    fun dismisProgressDialog() {
        ProgressDialogUtils.dismissProgressDialog()
    }

}