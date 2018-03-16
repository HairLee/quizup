package com.elcom.com.quizupapp.ui.listener;

import android.view.DragEvent;
import android.view.View;

/**
 * Created by hoanv on 9/15/2015.
 */
public class MyDragListener implements View.OnDragListener {
    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        View dragView = (View) event.getLocalState();

        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                System.out.println("Drag started");
                return true;
            case DragEvent.ACTION_DRAG_ENTERED:
                System.out.println("Drag entered");
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                System.out.println("Drag ended");
                dragView.setVisibility(View.VISIBLE);
                return true;
            case DragEvent.ACTION_DROP:
                System.out.println("Dop");
                return true;
            default:
                break;
        }
        return false;
    }
}
