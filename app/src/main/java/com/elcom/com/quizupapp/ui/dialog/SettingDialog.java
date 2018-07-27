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
import android.widget.ImageView;
import android.widget.TextView;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.listener.OnDialogInvitationListener;
import com.elcom.com.quizupapp.utils.Utils;

/**
 * Created by Hailpt on 4/12/2018.
 */
public class SettingDialog extends AlertDialog {

    private OnDialogInvitationListener mOnDialogInvitationListener;
    private Context mContext;
    private String titleLeft, titleRight;
    private int imageId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimationRightLeft;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.setting_dialog);
        setCancelable(true);


        final TextView btnAccept = findViewById(R.id.btnAccept);
        final TextView btnReject = findViewById(R.id.btnReject);
        final ImageView imvAva = findViewById(R.id.imvAva);

        btnAccept.setText(titleLeft);
        btnReject.setText(titleRight);

        imvAva.setImageResource(imageId);

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnDialogInvitationListener.onCancelInviteFriendToPlayGame();
                dismiss();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnDialogInvitationListener.onInviteFriendToPlayGame();
                dismiss();
            }
        });



    }

    public SettingDialog(@NonNull Context context,int resId,String leftTitle, String rightTitle, OnDialogInvitationListener pOnDialogInvitationListener) {
        super(context);
        mOnDialogInvitationListener = pOnDialogInvitationListener;
        mContext = context;
        titleLeft = leftTitle;
        titleRight = rightTitle;
        imageId = resId;
    }

    protected SettingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SettingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

}
