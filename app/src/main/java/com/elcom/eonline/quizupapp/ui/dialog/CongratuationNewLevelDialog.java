package com.elcom.eonline.quizupapp.ui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.elcom.eonline.quizupapp.R;
import com.elcom.eonline.quizupapp.ui.listener.OnDialogYesNoListener;

/**
 * Created by Hailpt on 4/12/2018.
 */
public class CongratuationNewLevelDialog extends AlertDialog {

    private OnDialogYesNoListener mOnDialogYesNoListener;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimationRightLeft;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.congratulation_new_level_dialog_layout);




        final Button btnShare = (Button)findViewById(R.id.btnShare);
        Button btnResult = (Button)findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mOnDialogYesNoListener.clickYesAction();
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mOnDialogYesNoListener.clickNoAction();
            }
        });



    }

    public CongratuationNewLevelDialog(@NonNull Context context, int numberOfQuestion, String nameOfTopic, OnDialogYesNoListener pOnDialogYesNoListener ) {
        super(context);
        mOnDialogYesNoListener = pOnDialogYesNoListener;
        mContext = context;
    }

    protected CongratuationNewLevelDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CongratuationNewLevelDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
