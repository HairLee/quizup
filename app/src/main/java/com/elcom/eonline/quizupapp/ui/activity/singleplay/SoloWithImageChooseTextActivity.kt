package com.elcom.eonline.quizupapp.ui.activity.singleplay

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.ui.activity.BaseActivityQuiz

class SoloWithImageChooseTextActivity : BaseActivityQuiz() {
    override fun getLayout(): Int {
        return R.layout.activity_solo_with_image_choose_text
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solo_with_image_choose_text)
    }
}
