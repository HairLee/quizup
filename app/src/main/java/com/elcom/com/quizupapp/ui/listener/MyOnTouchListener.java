package com.elcom.com.quizupapp.ui.listener;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hoanv on 9/15/2015.
 */
public final class MyOnTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            return  true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            view.setVisibility(View.VISIBLE);
            return  true;
        }
        return false;
    }
}