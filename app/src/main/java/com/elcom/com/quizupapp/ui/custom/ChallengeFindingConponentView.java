package com.elcom.com.quizupapp.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.adapter.AdvertisingAdapter;

/**
 * Created by admin on 3/5/2018.
 */

public class ChallengeFindingConponentView extends LinearLayout {


    public ChallengeFindingConponentView(Context context) {
        super(context);
        init(context);
    }

    public ChallengeFindingConponentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChallengeFindingConponentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public ChallengeFindingConponentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(final Context context) {
        View rootView = inflate(context, R.layout.challenge_finding_opponent_layout, this);


    }

    public void updateData(){

    }

}


