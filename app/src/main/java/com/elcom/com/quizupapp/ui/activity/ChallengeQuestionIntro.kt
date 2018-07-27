package com.elcom.com.quizupapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.ChallengeMatching
import com.elcom.com.quizupapp.ui.activity.model.entity.Introduction
import com.elcom.com.quizupapp.ui.custom.SocketManage
import com.elcom.com.quizupapp.ui.dialog.StopGameDialog
import com.elcom.com.quizupapp.ui.listener.OnDialogYesNoListener
import com.elcom.com.quizupapp.ui.listener.OnSocketListener
import com.elcom.com.quizupapp.utils.ConstantsApp
import com.elcom.com.quizupapp.utils.LogUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_solo_question_intro.*
import kotlinx.android.synthetic.main.coin_and_ex_layout.*


class ChallengeQuestionIntro : BaseActivityQuiz() {



    private var mTopicId = ""
    private var mMatchId = ""
    private var mQuestionNumber = 0
    private var mType = 1
    private var mChallengeMatching:ChallengeMatching? = null
    private var numberOfRightAnswerFromMe = 0
    override fun getLayout(): Int {
        return R.layout.activity_solo_question_intro
    }

    override fun initView() {

    }

    override fun initData() {
        if (intent.hasExtra(ConstantsApp.KEY_QUESTION_ID)){
            mTopicId = intent.getStringExtra(ConstantsApp.KEY_QUESTION_ID)
            mMatchId = intent.getStringExtra(ConstantsApp.KEY_SOLO_MATCH_ID)
            mChallengeMatching = intent.getSerializableExtra("value") as ChallengeMatching
            moveToPlayingGame()
        }
    }

    private fun moveToPlayingGame(){

        if ( mChallengeMatching != null){
            val bundle = Bundle()
            bundle.putInt(ConstantsApp.KEY_CHALLENGE_TOTAL_RIGHT_ANSWER_ME,numberOfRightAnswerFromMe)
            bundle.putString(ConstantsApp.KEY_SOLO_MATCH_ID,mMatchId)
            bundle.putString(ConstantsApp.KEY_QUESTION_ID,mTopicId)
            bundle.putInt(ConstantsApp.KEY_QUESTION_NUMBER,mQuestionNumber)
            bundle.putSerializable(ConstantsApp.KEY_INTRODUCTION_VALUE, mChallengeMatching)
            startActivityForResultQuiz(ChallengeMatchWithTextActivity::class.java,ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY,bundle)
        }
    }

    private fun updateLayout(){
        txt_coins.text = mChallengeMatching!!.coins
        txt_point.text = mChallengeMatching!!.point
        txt_topic.text = mChallengeMatching!!.nameTopic


        Picasso.get()
                .load(mChallengeMatching!!.topicImageUrl)
                .into(imv_topic)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

        // user has given the right answer and go to the next question ( or -10 coins )
            ConstantsApp.REQUEST_CODE_FROM_QUESTION_INTRO_ACTIVITY -> LogUtils.e("hailpt", "SoloQuestionIntro resultCode " + resultCode) // Get the new Question id ( mQuestionId )

        // User does not want to play game any more
            ConstantsApp.START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY -> {
                when (resultCode) {
                    ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY -> {
                        setResult(ConstantsApp.RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY)
                        finish()
                    }

                    ConstantsApp.RESULT_CODE_FROM_RIGHT_ANSWER -> {
                        mQuestionNumber++
                        numberOfRightAnswerFromMe++
                        mType = 1
                        btn_next.text = "START QUESTION "+ mQuestionNumber
                        moveToPlayingGame()
                    }

                }
            }
        }

    }
}
