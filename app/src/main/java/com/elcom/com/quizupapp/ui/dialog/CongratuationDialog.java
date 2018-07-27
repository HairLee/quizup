package com.elcom.com.quizupapp.ui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.listener.OnDialogYesNoListener;

/**
 * Created by Hailpt on 4/12/2018.
 */
public class CongratuationDialog extends AlertDialog {

    private OnDialogYesNoListener mOnDialogYesNoListener;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimationRightLeft;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.congratulation_dialog_layout);
        setCancelable(true);



        final Button btnShare = (Button)findViewById(R.id.btnShare);
        Button btnResult = (Button)findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnDialogYesNoListener.clickYesAction();
//                final Animation myAnim = AnimationUtils.loadAnimation(mContext, R.anim.scale_in);
//                double animationDuration = 5 * 10;
//                myAnim.setDuration((long)animationDuration);
//                btnOk.startAnimation(myAnim);
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnDialogYesNoListener.clickNoAction();
            }
        });



    }

    public CongratuationDialog(@NonNull Context context, int numberOfQuestion, String nameOfTopic, OnDialogYesNoListener pOnDialogYesNoListener ) {
        super(context);
        mOnDialogYesNoListener = pOnDialogYesNoListener;
        mContext = context;
    }

    protected CongratuationDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CongratuationDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
