package com.elcom.eonline.quizupapp.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;

public class ApplicationLifecycleHandler implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    private static final String TAG = ApplicationLifecycleHandler.class.getSimpleName();
    public static boolean isInBackground = false;
    private Activity mActivity;
    public static String mActivityName;

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

        LogUtils.e("hailpt", TAG + activity.getClass().getName());

    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        mActivity = activity;
        mActivityName = mActivity.getLocalClassName();

        if (isInBackground) {
            isInBackground = false;
        }
//        NotificationManager mNotificationManager =
//                (NotificationManager) AppController.getAppContext().getSystemService(NOTIFICATION_SERVICE);
//        mNotificationManager.cancel(10000000);

    }

    public String getCurrentActivityName(){
        return mActivityName;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
//        PreferUtils.setIsHomePressedCode(activity, false);
//        NotificationManager mNotificationManager =
//                (NotificationManager) AppController.getAppContext().getSystemService(NOTIFICATION_SERVICE);
//        mNotificationManager.cancel(10000000);
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override
    public void onLowMemory() {
    }

    @Override
    public void onTrimMemory(int i) {
        if (i == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            isInBackground = true;
            if (mActivity != null) {
//                if (!mActivityName.equals("com.trams.let.activity.chat.ActivityPhotoSchedule")) {
//                    PreferUtils.setIsFromHomePressed(mActivity, true);
//                }
//
//                if (PreferUtils.getPassCodeAndSetup(mActivity)) {
//                    PreferUtils.setIsHomePressedCode(mActivity, true);
//                }
            }

        }
    }
}