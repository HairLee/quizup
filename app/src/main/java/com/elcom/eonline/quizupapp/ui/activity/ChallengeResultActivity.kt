package com.elcom.eonline.quizupapp.ui.activity

import com.elcom.eonline.quizupapp.R
import com.elcom.eonline.quizupapp.utils.ConstantsApp

class ChallengeResultActivity : BaseActivityQuiz() {

    override fun getLayout(): Int {
        return R.layout.activity_challenge_result
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun onBackPressed() {
        setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
        finish()
    }


}
