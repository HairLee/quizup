package com.elcom.com.quizupapp.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.custom.ProgressTimerView
import com.elcom.com.quizupapp.utils.AnimationUtil
import com.elcom.com.quizupapp.utils.Utils
import kotlinx.android.synthetic.main.activity_quiz.*
import java.nio.file.Files.find
import java.util.*
import android.databinding.DataBindingUtil
import com.elcom.com.quizupapp.databinding.ActivityQuizBinding
import com.elcom.com.quizupapp.ui.activity.model.entity.Person
import com.elcom.com.quizupapp.ui.activity.model.entity.QuizDao
import kotlinx.android.synthetic.main.activity_quiz.view.*


class QuizActivity : AppCompatActivity(), View.OnClickListener,ProgressTimerView.onFinishCountDown {

    private var mCurrentScore = 20
    private var mResult: Boolean? = false
    var person:Person? = null
    lateinit var mActivityQuizBinding:ActivityQuizBinding

    private val mButtonList = ArrayList<Button>()

    var mCustomButtom: Utils.CustomButtom? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityQuizBinding = DataBindingUtil.setContentView(this,R.layout.activity_quiz )

        person = Person("Ambition", "Male")
        val pQuiz = QuizDao("AuDyyyyyyy")
        mActivityQuizBinding.person = person
//        mActivityQuizBinding.quizdao =  pQuiz


        mActivityQuizBinding.apply {
            txtScore.text = "Medium Android Repository Article"
        }

        initLayoutId()
    }


    private fun initLayoutId() {

        answer_1.setOnClickListener(this)
        answer_2.setOnClickListener(this)
        answer_3.setOnClickListener(this)
        answer_4.setOnClickListener(this)
        rl_progress.setListener(this)
        rl_progress.startStop()

        mButtonList.add(answer_1)
        mButtonList.add(answer_2)
        mButtonList.add(answer_3)
        mButtonList.add(answer_4)

        mCustomButtom =  Utils.CustomButtom(mButtonList)

        beginToShowAnswerLayout()
    }

    @SuppressLint("NewApi")
    private fun beginToShowAnswerLayout() {

        AnimationUtil.setAnimationSlideLeft(ln_answer_top, true)
        AnimationUtil.setAnimationSlideRight(ln_answer_bottom,true)

        val mRandom = Random()
        txt_question?.setText("How many people in my family?" + mRandom.nextInt(10))
        txt_question?.setVisibility(View.VISIBLE)
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.answer_1 -> {
                    mCurrentScore++
                    mResult = true
                    updateScore()
                }
                R.id.answer_2 -> {
                    mCurrentScore--
                    mResult = false
                    updateScore()
                }
                R.id.answer_3 -> {
                    mCurrentScore--
                    mResult = false
                    updateScore()
                }
                R.id.answer_4 -> {
                    mCurrentScore--
                    mResult = false
                    updateScore()
                }

                else -> {

                }
            }
        }

    }

    private fun updateScore() {
        txt_score?.text = "Score : " + mCurrentScore
        runAnimationAfterUpdatingResult()
    }

    private fun runAnimationAfterUpdatingResult() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                if (this!!.mResult!!) {

                    AnimationUtil.setAnimationSlideLeft(ln_answer_bottom,false)
                    AnimationUtil.setAnimationSlideRight(ln_answer_top,false)

                    mCustomButtom?.unableButtonClick()
                    val handler = Handler()
                    handler.postDelayed({
                        mCustomButtom?.enableButtonClick()
                        beginToShowAnswerLayout()
                    }, 500)
                }
            }
        }
    }

    override fun onFinishCountDown(listDemo: Boolean) {
//        mResult = true
//        rl_progress.reset()
//        runAnimationAfterUpdatingResult()
        mActivityQuizBinding.person = Person("haha", "kakaka")
    }


}
