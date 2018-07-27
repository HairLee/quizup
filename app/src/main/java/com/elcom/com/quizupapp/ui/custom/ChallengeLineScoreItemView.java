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

public class ChallengeLineScoreItemView extends LinearLayout {


    private ArrayList<View> mViewList = new ArrayList<>();

    public ChallengeLineScoreItemView(Context context) {
        super(context);
        init(context);
    }

    public ChallengeLineScoreItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChallengeLineScoreItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public ChallengeLineScoreItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(final Context context) {
        View rootView = inflate(context, R.layout.challenge_line_score_item_layout, this);

        View view1 = rootView.findViewById(R.id.view1);
        View view2 = rootView.findViewById(R.id.view2);
        View view3 = rootView.findViewById(R.id.view3);
        View view4 = rootView.findViewById(R.id.view4);
        View view5 = rootView.findViewById(R.id.view5);
        View view6 = rootView.findViewById(R.id.view6);
        View view7 = rootView.findViewById(R.id.view7);

        mViewList.add(view7);
        mViewList.add(view6);
        mViewList.add(view5);
        mViewList.add(view4);
        mViewList.add(view3);
        mViewList.add(view2);
        mViewList.add(view1);

//        changeColorOfItemWithRightAnswer(3);

//        changeColorOfItemWithRightAnswerByPos(0);
    }

    public void changeColorOfItemWithRightAnswer(int pNumberOfRightAnswer){
        for (int i = 0; i < pNumberOfRightAnswer; i++) {
            mViewList.get(i).setVisibility(VISIBLE);
            mViewList.get(i).setBackgroundColor(getResources().getColor(R.color.green_2));
        }
    }

    public void changeColorOfItemWithRightAnswerOp(int pNumberOfRightAnswer){
        for (int i = 0; i < pNumberOfRightAnswer; i++) {
            mViewList.get(i).setVisibility(VISIBLE);
            mViewList.get(i).setBackgroundColor(getResources().getColor(R.color.colorWrongAnswer));
        }
    }



    public void changeColorOfItemWithRightAnswerByPos(int pRightAnswerPos){
            mViewList.get(pRightAnswerPos).setVisibility(VISIBLE);
            mViewList.get(pRightAnswerPos).setBackgroundColor(getResources().getColor(R.color.green_2));
    }

}


