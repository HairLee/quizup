package com.elcom.com.quizupapp.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.utils.AnimationUtil;
import com.elcom.com.quizupapp.utils.LogUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 3/5/2018.
 */

public class ProgressTimerView extends RelativeLayout {

    View rootView;
    TextView valueTextView;
    private ProgressBar progressBarCircle;
    private CountDownTimer countDownTimer;
    private TextView textViewTime;

    private long timeCountInMilliSeconds = 15 * 60000;

    private enum TimerStatus {
        STARTED,
        STOPPED
    }
    private TimerStatus timerStatus = TimerStatus.STOPPED;

    public ProgressTimerView(Context context) {
        super(context);
        init(context);
    }

    public ProgressTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressTimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public ProgressTimerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.progress_timer_relative_layout, this);
        textViewTime = (TextView)rootView.findViewById(R.id.textViewTime);
        progressBarCircle = (ProgressBar)rootView.findViewById(R.id.progressBarCircle);

    }

    public void reset() {
        stopCountDownTimer();
        startCountDownTimer();
    }

    public void startStop() {
        if (timerStatus == ProgressTimerView.TimerStatus.STOPPED) {
            setTimerValues();
            setProgressBarValues();
            timerStatus = ProgressTimerView.TimerStatus.STARTED;
            startCountDownTimer();
        } else {
            timerStatus = ProgressTimerView.TimerStatus.STOPPED;
            stopCountDownTimer();
        }
    }

    private void setTimerValues() {
        int time = 11;

        timeCountInMilliSeconds = time * 1000;
    }

    private void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                AnimationUtil.flipTextView(textViewTime);
                textViewTime.setText(hmsTimeFormatter(millisUntilFinished));
                progressBarCircle.setProgress((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textViewTime.setTextColor(getResources().getColor(R.color.colorAccent));
                textViewTime.setText("0");
                setProgressBarValues();
                timerStatus = ProgressTimerView.TimerStatus.STOPPED;
                mOnFinishCountDown.onFinishCountDown(true);
            }

        }.start();
    }

    public void stopCountDownTimer() {
        countDownTimer.cancel();
    }

    private void setProgressBarValues() {
        progressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
        progressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);
    }

    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%d", TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

        return hms;

    }
    private onFinishCountDown mOnFinishCountDown;
    public void setListener(onFinishCountDown pOnFinishCountDown){
        mOnFinishCountDown = pOnFinishCountDown;
    }

    public interface onFinishCountDown {
        void onFinishCountDown(boolean listDemo);
    }

}


