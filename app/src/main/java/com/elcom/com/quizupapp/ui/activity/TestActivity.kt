package com.elcom.com.quizupapp.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.listener.OnSocketListener
import com.elcom.com.quizupapp.utils.LogUtils
import org.json.JSONObject
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.widget.Toast
import kotlinx.android.synthetic.main.aaaatest_result_layout.*
import android.content.IntentFilter






class TestActivity : BaseActivity(), OnSocketListener {

    var myReceiver: MyReceiver? = null
    var intentFilter: IntentFilter? = null
    override fun onSocketConnected() {
        LogUtils.e("hailpt", "TestActivity onSocketConnected ")
    }

    override fun onAuthentication() {
    }

    override fun onCountDown(timeToCountDown: Int) {
    }

    override fun onResultQuestion(resultQuestion: JSONObject) {
    }

    override fun getLayout(): Int {
    return R.layout.aaaatestconstraint
    }

    override fun initView() {
//        tvNext.setOnClickListener {
//            val intent = Intent(this, TestSocketActivity::class.java);
//            startActivity(intent)
//        }
    }

    override fun initData() {
        myReceiver = MyReceiver()
        intentFilter = IntentFilter("com.example.SendBroadcast")
    }

    override fun resumeData() {
    }

    inner class MyReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val name = intent.getStringExtra("name")

            Toast.makeText(context, "Broadcast Intent Detected. $name",
                    Toast.LENGTH_LONG).show();
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(myReceiver, intentFilter)

    }

    override fun onPause() {
        super.onPause()
//        unregisterReceiver(myReceiver)
    }





}


















