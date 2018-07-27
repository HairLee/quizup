package com.elcom.com.quizupapp.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.ChallengeQuestion
import com.elcom.com.quizupapp.ui.activity.presenter.LiveChallengeInviteAndPlayGamePresenter
import com.elcom.com.quizupapp.utils.AnimationUtil
import com.elcom.com.quizupapp.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.live_challenge_play_game_main_layout.view.*
import java.util.concurrent.TimeUnit

/**
 * Created by Hailpt on 5/24/2018.
 */
class LiveChallengeWaitingGameView : LinearLayout {



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
        val rootView = View.inflate(context, R.layout.live_challenge_play_wating_game_main_layout, this)



    }


}