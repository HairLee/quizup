package com.elcom.eonline.quizupapp.ui.custom;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Hailpt on 8/28/2018.
 */
@SuppressLint("AppCompatCustomView")
public class GameButton extends Button {
    private int parentId;
    private int cellId;


    public GameButton(Context context) {
        super(context);
    }
    public GameButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public GameButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GameButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setParentId(int parentId){
        this.parentId=parentId;
    }

    public void setCellId(int cellId){
        this.cellId=cellId;
    }

    public int getParentId(){
        return this.parentId;
    }

    public int getCellId(){
        return this.cellId;
    }

}
