package com.elcom.com.quizupapp;

import android.app.Application;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;

import com.elcom.com.quizupapp.ui.custom.SocketManage;
import com.elcom.com.quizupapp.ui.listener.OnInvitationTimeCountDownListener;
import com.elcom.com.quizupapp.ui.listener.OnSocketInviteOpponentListener;
import com.elcom.com.quizupapp.ui.listener.OnSocketListener;
import com.elcom.com.quizupapp.utils.ConstantsApp;
import com.elcom.com.quizupapp.utils.FontsOverride;
import com.elcom.com.quizupapp.utils.LogUtils;
import com.elcom.com.quizupapp.utils.PreferUtils;
import com.onesignal.OneSignal;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

/**
 * Created by Hailpt on 5/7/2018.
 */
public class QuizUpApplication extends Application implements OnSocketInviteOpponentListener,OnSocketListener {

    SocketManage mSocketManage = new SocketManage();
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/RobotoLight.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/RobotoLight.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/RobotoLight.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/RobotoLight.ttf");
        LogUtils.e("SocketManage","onCreate ");
        new PreferUtils().setChallengeTimeToInviteFriend(this, "0");
        mSocketManage.init(this);
        mSocketManage.connectSocket();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    public void regisSocket(){
        ConstantsApp.socketManage.initToInventionFromFriend(this);
    }

    @Override
    public void onSomeoneInviteYouToPlayGame(@NotNull JSONObject resultQuestion) {
        LogUtils.e("SocketManage","QuizUpApplication "+resultQuestion.toString());

    }

    private static final Handler mHandler = new Handler();

    public static final void runOnUiThread(Runnable runnable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            mHandler.post(runnable);
        }
    }

    private CountDownTimer countDownTimer;
    public void startCountDownTimer() {

        long timeCountInMilliSeconds = 40 * 1000;
        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                LogUtils.e("SocketManage", " startCountDownTimer "+hmsTimeFormatter(millisUntilFinished));
                mOnCountDown.onTimeCountDown(hmsTimeFormatter(millisUntilFinished));

                ConstantsApp.CHALLENGE_TIME_COUNT_DOWN = hmsTimeFormatter(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                ConstantsApp.CHALLENGE_TIME_COUNT_DOWN = "0";
                mOnCountDown.onTimeCountDown("0");
                countDownTimer.cancel();
            }

        }.start();
    }

    public void stopCountDownTimer() {
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    private String hmsTimeFormatter(long milliSeconds) {
        String hms = String.format("%d", TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
        return hms;
    }

    private OnInvitationTimeCountDownListener mOnCountDown;
    public void setListener(OnInvitationTimeCountDownListener pOnCountDown){
        mOnCountDown = pOnCountDown;
    }


    @Override
    public void onResultQuestion(@NotNull JSONObject resultQuestion) {

    }

    @Override
    public void onCountDown(int timeToCountDown) {

    }

    @Override
    public void onAuthentication() {
        LogUtils.e("SocketManage", " QuizUpApplication onAuthentication ");
    }

    @Override
    public void onSocketConnected() {
        LogUtils.e("SocketManage", " QuizUpApplication onSocketConnected ");
        ConstantsApp.socketManage = mSocketManage;
        mSocketManage.sendUserInformationBySocket(this);
    }
}