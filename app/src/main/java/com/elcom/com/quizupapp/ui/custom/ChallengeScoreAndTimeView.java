package com.elcom.com.quizupapp.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.elcom.com.quizupapp.R;

import java.util.ArrayList;

/**
 * Created by admin on 3/5/2018.
 */

public class ChallengeScoreAndTimeView extends LinearLayout {


    private ArrayList<View> mViewList = new ArrayList<>();

    public ChallengeScoreAndTimeView(Context context) {
        super(context);
        init(context);
    }

    public ChallengeScoreAndTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChallengeScoreAndTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public ChallengeScoreAndTimeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(final Context context) {
        View rootView = inflate(context, R.layout.challenge_score_and_timer_item_layout, this);
        ProgressTimerView ProgressTimerView1 = (ProgressTimerView)rootView.findViewById(R.id.ProgressTimerView1);

    }

    public void visibleAndHideProgessview(int pos){

    }

}


