package com.elcom.com.quizupapp.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.adapter.AdvertisingAdapter;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 3/5/2018.
 */

public class AdvertisingView extends RelativeLayout {

    private enum TimerStatus {
        STARTED,
        STOPPED
    }
    private TimerStatus timerStatus = TimerStatus.STOPPED;

    public AdvertisingView(Context context) {
        super(context);
        init(context);
    }

    public AdvertisingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AdvertisingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public AdvertisingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(final Context context) {
        View rootView = inflate(context, R.layout.main_fragment_advertising_layout, this);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        AdvertisingAdapter viewPagerAdapter = new AdvertisingAdapter(context);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());

    }

    public void updateData(){

    }

}


