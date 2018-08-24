package com.elcom.eonline.quizupapp.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
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
    private  ArrayList<ImageView> imvScores = new ArrayList<>();
    private  onFinishSmallCountDown mOnFinishSmmallCountDown;
    private ImageView imvScore0,imvScore1,imvScore2,imvScore3,imvScore4,imvScore5,imvScore6;
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

        imvScore0 = rootView.findViewById(R.id.imvScore0);
        imvScore1 = rootView.findViewById(R.id.imvScore1);
        imvScore2 = rootView.findViewById(R.id.imvScore2);
        imvScore3 = rootView.findViewById(R.id.imvScore3);
        imvScore4 = rootView.findViewById(R.id.imvScore4);
        imvScore5 = rootView.findViewById(R.id.imvScore5);
        imvScore6 = rootView.findViewById(R.id.imvScore6);

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

        imvScores.add(imvScore0);
        imvScores.add(imvScore1);
        imvScores.add(imvScore2);
        imvScores.add(imvScore3);
        imvScores.add(imvScore4);
        imvScores.add(imvScore5);
        imvScores.add(imvScore6);


        for (int i = 0; i < mProgressTimerViews.size(); i++) {
            mProgressTimerViews.get(i).setListener(this);
        }
    }

    public void changeScoreIcon(int pos, boolean answer){

        imvScores.get(pos).setVisibility(View.VISIBLE);
        if(answer){
            imvScores.get(pos).setImageResource(R.drawable.ic_challenge_v);
        } else {
            imvScores.get(pos).setImageResource(R.drawable.ic_challenge_x);
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
        mOnFinishSmmallCountDown.onFinishSmallCountDown(0);
    }

    public interface onFinishSmallCountDown{
        void onFinishSmallCountDown(int positionOfTheQuestion);
    }

    public void setOnFinishSmmallCountDown(onFinishSmallCountDown pOnFinishSmmallCountDown){
        mOnFinishSmmallCountDown = pOnFinishSmmallCountDown;
    }
}


