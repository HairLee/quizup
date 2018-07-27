package com.elcom.com.quizupapp.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.elcom.com.quizupapp.R

class TopicRankActivity : BaseActivityQuiz() {
    override fun getLayout(): Int {
        return R.layout.activity_topic_rank
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_rank)
    }
}
