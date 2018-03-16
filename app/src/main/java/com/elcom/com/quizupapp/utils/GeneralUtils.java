package com.elcom.com.quizupapp.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by hoanv on 08/09/2015.
 */
public class GeneralUtils {
    public static Point getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point out = new Point();
        display.getSize(out);
        return out;
    }
}
