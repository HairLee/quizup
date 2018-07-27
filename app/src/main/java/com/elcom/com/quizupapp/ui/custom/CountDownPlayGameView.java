package com.elcom.com.quizupapp.ui.custom;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.listener.OnTimeCountDownListener;
import com.squareup.picasso.Picasso;

/**
 * Created by Hailpt on 4/5/2018.
 */
public class CountDownPlayGameView extends RelativeLayout {
    public CountDownPlayGameView(Context context) {
        super(context);
        init(context);
    }
    CountDownTimer countDownTimer;
    public CountDownPlayGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CountDownPlayGameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public CountDownPlayGameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private ObjectAnimator progressAnimator;
    private void init(final Context context) {
        View rootView = inflate(context, R.layout.time_count_down_layout, this);

        final ProgressBar mProgressBar = (ProgressBar)rootView. findViewById(R.id.progress_bar);
        ImageView imvAva = (ImageView)rootView.findViewById(R.id.imv_ava);
        ImageView imvTime = (ImageView)rootView.findViewById(R.id.imv_time);
        final TextView txtSecond = (TextView)rootView.findViewById(R.id.txt_second);
         setCircleForImageView(imvAva);



        mProgressBar.getProgressDrawable().setColorFilter(
                Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);

//        mProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar_bg));

        progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", 100, 0);
//        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", mProgressBar.getProgress(), 0 * 100);
        progressAnimator.setDuration(11000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();

         countDownTimer = new CountDownTimer(11000, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) ((millisUntilFinished / 1000));
                txtSecond.setText(seconds+"");
            }

            public void onFinish() {
                txtSecond.setText("0");
                mOnFinishCountDown.onTheTimeCountDownIsOver();
            }

        };

        countDownTimer.start();


    }

    public void stopCountDownTimer() {
        countDownTimer.cancel();
        progressAnimator.cancel();
    }

    private OnTimeCountDownListener mOnFinishCountDown;
    public void setListener(OnTimeCountDownListener pOnFinishCountDown){
        mOnFinishCountDown = pOnFinishCountDown;
    }

    public void setCircleForImageView(ImageView imageView){
        Picasso.get().load("https://i.pinimg.com/564x/45/80/05/458005878776edef9f30bc83b06e5abf.jpg")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
//                .transform(new PicassoCircleTransformation())
                .into(imageView);
    }
}
