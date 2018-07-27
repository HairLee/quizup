package com.elcom.com.quizupapp.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.adapter.NotificationAdapter
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : BaseActivityQuiz() {
    override fun getLayout(): Int {
        return R.layout.activity_notification
    }

    override fun initView() {
        imvBack.setOnClickListener { onBackPressed() }
        tvNameTop.text = "NOTIFICATIONS"
    }

    override fun initData() {

        val mList = ArrayList<Int>(15)

        val adapter = NotificationAdapter(mList)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

  }
