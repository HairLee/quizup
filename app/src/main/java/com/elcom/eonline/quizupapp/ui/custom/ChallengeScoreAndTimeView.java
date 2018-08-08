package com.elcom.eonline.quizupapp.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.elcom.eonline.quizupapp.R;

import java.util.ArrayList;

/**
 * Created by admin on 3/5/2018.
 */

public class ChallengeScoreAndTimeView extends LinearLayout implements ProgressSmallTimerView.onFinishCountDown {


    private ArrayList<View> mViewList = new ArrayList<>();
    private  ArrayList<ProgressSmallTimerView> mProgressTimerViews = new ArrayList<>();
    private  ArrayList<RelativeLayout> mTimeSeconds = new ArrayList<>();
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
        ProgressSmallTimerView progressTimerView0 = rootView.findViewById(R.id.ProgressTimerView1);
        ProgressSmallTimerView progressTimerView1 = rootView.findViewById(R.id.ProgressTimerView2);
        ProgressSmallTimerView progressTimerView2 = rootView.findViewById(R.id.ProgressTimerView3);
        ProgressSmallTimerView progressTimerView3 = rootView.findViewById(R.id.ProgressTimerView4);
        ProgressSmallTimerView progressTimerView4 = rootView.findViewById(R.id.ProgressTimerView5);
        ProgressSmallTimerView progressTimerView5 = rootView.findViewById(R.id.ProgressTimerView6);
        ProgressSmallTimerView progressTimerView6 = rootView.findViewById(R.id.ProgressTimerView7);

        RelativeLayout rlTime0 = rootView.findViewById(R.id.rl1);
        RelativeLayout rlTime1 = rootView.findViewById(R.id.rl2);
        RelativeLayout rlTime2 = rootView.findViewById(R.id.rl3);
        RelativeLayout rlTime3 = rootView.findViewById(R.id.rl4);
        RelativeLayout rlTime4 = rootView.findViewById(R.id.rl5);
        RelativeLayout rlTime5 = rootView.findViewById(R.id.rl6);
        RelativeLayout rlTime6 = rootView.findViewById(R.id.rl7);

        mProgressTimerViews.add(progressTimerView0);
        mProgressTimerViews.add(progressTimerView1);
        mProgressTimerViews.add(progressTimerView2);
        mProgressTimerViews.add(progressTimerView3);
        mProgressTimerViews.add(progressTimerView4);
        mProgressTimerViews.add(progressTimerView5);
        mProgressTimerViews.add(progressTimerView6);

        mTimeSeconds.add(rlTime0);
        mTimeSeconds.add(rlTime1);
        mTimeSeconds.add(rlTime2);
        mTimeSeconds.add(rlTime3);
        mTimeSeconds.add(rlTime4);
        mTimeSeconds.add(rlTime5);
        mTimeSeconds.add(rlTime6);


        for (int i = 0; i < mProgressTimerViews.size(); i++) {
            mProgressTimerViews.get(i).setListener(this);
        }
    }

    public void setShowCountDown(int pos){
        for (int i = 0; i < mProgressTimerViews.size(); i++) {
            ProgressSmallTimerView progressTimerView = mProgressTimerViews.get(i);
            RelativeLayout relativeLayout = mTimeSeconds.get(i);
            if(i == pos){
                progressTimerView.setVisibility(VISIBLE);
                relativeLayout.setVisibility(GONE);
                progressTimerView.startStop();
            } else {
                relativeLayout.setVisibility(VISIBLE);
                progressTimerView.setVisibility(GONE);
                progressTimerView.stopCountDownTimer();
            }
        }

    }

    @Override
    public void onFinishCountDown(boolean listDemo) {

    }


}


