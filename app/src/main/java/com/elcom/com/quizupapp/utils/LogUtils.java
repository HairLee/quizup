package com.elcom.com.quizupapp.utils;

import android.util.Log;

/**
 * Created by hoanv23 on 5/12/15.
 */
public class LogUtils {

    public static void v(String tag, String msg) {
        if (ConstantsApp.DEBUG) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (ConstantsApp.DEBUG) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (ConstantsApp.DEBUG) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (ConstantsApp.DEBUG) {
            android.util.Log.e(tag, msg);
        }
    }
}
