package com.elcom.com.quizupapp.ui.custom

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.ChallengeQuestion
import com.elcom.com.quizupapp.ui.activity.presenter.LiveChallengeInviteAndPlayGamePresenter
import com.elcom.com.quizupapp.ui.dialog.LiveChallengeWrongAnswerDialog
import com.elcom.com.quizupapp.ui.listener.OnLiveChallengeWinnerDialogListener
import com.elcom.com.quizupapp.utils.AnimationUtil
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.live_challenge_play_game_main_layout.view.*
import java.util.concurrent.TimeUnit
import android.content.DialogInterface
import android.os.Handler
import com.elcom.com.quizupapp.ui.activity.model.entity.LiveQuestionData


/**
 * Created by Hailpt on 5/24/2018.
 */
class LiveChallengePlayGameView : RelativeLayout {


    private var PERCENT_OF_ANSWER_A = 0.3
    private var PERCENT_OF_ANSWER_B = 0.7
    private var PERCENT_OF_ANSWER_C = 0.5
    private var PERCENT_OF_ANSWER_ZEZO = 0.0
    private var mLiveChallengeInviteAndPlayGamePresenter: LiveChallengeInviteAndPlayGamePresenter? = null
    private var mChallengeQuestion:LiveQuestionData? = null
    private var mShowId:String = ""
    private var mRightAnswer = 0
    private var mTvList = ArrayList<ViewGroup>()

    private var countDownTimer: CountDownTimer? = null
    private var mContext:Context? = null
    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @SuppressLint("NewApi")
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {

    }

    private fun init(context: Context) {
        val rootView = View.inflate(context, R.layout.live_challenge_play_game_main_layout, this)

        mTvList.add(rlViewPercentA)
        mTvList.add(rlViewPercentB)
        mTvList.add(rlViewPercentC)

        btnA.setOnClickListener {
            ProgressDialogUtils.showProgressDialog(context, 0, 0)
            checkTheAnswerIsRightOrNot(rlViewPercentA,0)
            stopCountDownTimer()
            mLiveChallengeInviteAndPlayGamePresenter!!.answerLiveChallengeQuestion(mShowId, mChallengeQuestion!!.question.answer!![0].id.toString(),mChallengeQuestion!!.question.id.toString(),"6")
        }

        btnB.setOnClickListener {
            checkTheAnswerIsRightOrNot(rlViewPercentB,1)
            stopCountDownTimer()
            mLiveChallengeInviteAndPlayGamePresenter!!.answerLiveChallengeQuestion(mShowId, mChallengeQuestion!!.question.answer!![1].id.toString(),mChallengeQuestion!!.question.id.toString(),"6")
        }

        btnC.setOnClickListener {
            checkTheAnswerIsRightOrNot(rlViewPercentC,2)
            stopCountDownTimer()
            mLiveChallengeInviteAndPlayGamePresenter!!.answerLiveChallengeQuestion(mShowId, mChallengeQuestion!!.question.answer!![2].id.toString(),mChallengeQuestion!!.question.id.toString(),"6")
        }

        imvClose.setOnClickListener {

            val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        stopCountDownTimer()
                        mLiveChallengeInviteAndPlayGamePresenter!!.breakLiveChallengeQuestion(mShowId, mChallengeQuestion!!.question.answer!![mLocationOfCurrentQuestion].id.toString(),mChallengeQuestion!!.question.id.toString(),"6")
                    }

                    DialogInterface.BUTTON_NEGATIVE -> {
                    }
                }//Yes button clicked
                //No button clicked
            }

            val builder = AlertDialog.Builder(context)
            builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show()

        }

    }

    fun updateData() {

//        Handler().postDelayed(Runnable {
//
//            rlViewPercentA.setBackgroundResource(R.drawable.radius_bg_purple_just_on_left_layout)
//
//            setLayoutWidth(rlViewPercentA,PERCENT_OF_ANSWER_A)
//            setLayoutWidth(rlViewPercentB,PERCENT_OF_ANSWER_B)
//            setLayoutWidth(rlViewPercentC,PERCENT_OF_ANSWER_C)
//
//            AnimationUtil.setAnimationSlideLeftWithTime(rlViewPercentA,true)
//            AnimationUtil.setAnimationSlideLeftWithTime(rlViewPercentB,true)
//            AnimationUtil.setAnimationSlideLeftWithTime(rlViewPercentC,true)
//        }, 0)
    }

    private fun setLayoutWidth(tv: View, percent: Double ){
        val lp = tv.layoutParams as RelativeLayout.LayoutParams
        lp.width = (btnB.width*percent).toInt()
        tv.layoutParams = lp
    }

    fun resetLayOutWidthOfAnswerBar(){
        setLayoutWidth(rlViewPercentA,PERCENT_OF_ANSWER_ZEZO)
        setLayoutWidth(rlViewPercentB,PERCENT_OF_ANSWER_ZEZO)
        setLayoutWidth(rlViewPercentC,PERCENT_OF_ANSWER_ZEZO)
        AnimationUtil.setAnimationSlideLeftWithNoTime(rlViewPercentA)
        AnimationUtil.setAnimationSlideLeftWithNoTime(rlViewPercentB)
        AnimationUtil.setAnimationSlideLeftWithNoTime(rlViewPercentC)

    }

    private var mLocationOfCurrentQuestion = 0
    fun updateLayoutAfterGettingQuestionList(challengeQuestion: LiveQuestionData, locationOfCurrentQuestion:Int, toTalQuestion:Int){
        mLocationOfCurrentQuestion = locationOfCurrentQuestion
        mChallengeQuestion = challengeQuestion
        tvNumberOfQuestion.text =(locationOfCurrentQuestion).toString()+ "/"+toTalQuestion.toString()
        tvQuestion.text = challengeQuestion.question.question
        btnA.text = challengeQuestion.question.answer!![0].text
        btnB.text = challengeQuestion.question.answer!![1].text
        btnC.text = challengeQuestion.question.answer!![2].text


        for (i in 0..challengeQuestion.question.answer!!.size) {
            if (challengeQuestion.question.answer!![i].correct == 1){
                mRightAnswer = i
                return
            }
        }
    }

    private fun checkTheAnswerIsRightOrNot(view: ViewGroup, pos:Int){

        if(pos == mRightAnswer){
            view.setBackgroundResource(R.drawable.radius_bg_green_just_on_left_layout)
        } else {
            view.setBackgroundResource(R.drawable.radius_bg_purple_just_on_left_layout)
            for (i in 0..mTvList.size){
                if (i == mRightAnswer){
                    setLayoutWidth( mTvList[i],PERCENT_OF_ANSWER_A)
                    mTvList[i].setBackgroundResource(R.drawable.radius_bg_green_just_on_left_layout)
                    AnimationUtil.setAnimationSlideLeftWithTime( mTvList[i],true)
                }
            }
        }

        setLayoutWidth(view,PERCENT_OF_ANSWER_B)
        AnimationUtil.setAnimationSlideLeftWithTime(view,true)

    }

    private var timeCountInMilliSeconds = (30 * 1000).toLong()
    fun startCountDownTimer() {

        countDownTimer = object : CountDownTimer(timeCountInMilliSeconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = hmsTimeFormatter(millisUntilFinished)
            }

            override fun onFinish() {
                tvTimer.setTextColor(resources.getColor(R.color.colorAccent))
                tvTimer.text = "0"
                tvTimer.visibility = View.INVISIBLE
                imvClock.visibility = View.INVISIBLE
                tvWarn.visibility = View.VISIBLE
                tvWarn.text = "Hết Giờ"
                Handler().postDelayed({
                    mOnFinishCountDown!!.onFinishCountDown(true)
                },2000)
            }

        }.start()
    }

    fun reset() {
        stopCountDownTimer()
        startCountDownTimer()
    }

    private fun stopCountDownTimer() {
        countDownTimer!!.cancel()
    }

    private fun hmsTimeFormatter(milliSeconds: Long): String {

        val hms = String.format("%d", TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)))

        return hms

    }

    private var mOnFinishCountDown: ProgressTimerView.onFinishCountDown? = null
    fun setListener(pOnFinishCountDown: ProgressTimerView.onFinishCountDown) {
        mOnFinishCountDown = pOnFinishCountDown
    }

    fun setLiveChallengeInviteAndPlayGamePresenter(pLiveChallengeInviteAndPlayGamePresenter:LiveChallengeInviteAndPlayGamePresenter, showId:String){
        mLiveChallengeInviteAndPlayGamePresenter = pLiveChallengeInviteAndPlayGamePresenter
        mShowId = showId
    }

}