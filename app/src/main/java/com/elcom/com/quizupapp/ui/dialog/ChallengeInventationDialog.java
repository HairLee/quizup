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

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.listener.OnDialogInvitationListener;
import com.elcom.com.quizupapp.ui.listener.OnDialogYesNoListener;
import com.elcom.com.quizupapp.utils.Utils;

/**
 * Created by Hailpt on 4/12/2018.
 */
public class ChallengeInventationDialog extends AlertDialog {

    private OnDialogInvitationListener mOnDialogInvitationListener;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimationRightLeft;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.challenge_invented_by_friend_dialog);
        setCancelable(true);



        final Button btnAccept = (Button)findViewById(R.id.btnAccept);
        new Utils().setFontForButton(mContext,btnAccept);
        final Button btnReject = (Button)findViewById(R.id.btnReject);
        ImageView imvClose = (ImageView)findViewById(R.id.imvClose);

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnDialogInvitationListener.onCancelInviteFriendToPlayGame();
                dismiss();
//                final Animation myAnim = AnimationUtils.loadAnimation(mContext, R.anim.scale_in);
//                double animationDuration = 5 * 10;
//                myAnim.setDuration((long)animationDuration);
//                btnOk.startAnimation(myAnim);
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnDialogInvitationListener.onInviteFriendToPlayGame();
                dismiss();
            }
        });

        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



    }

    public ChallengeInventationDialog(@NonNull Context context, OnDialogInvitationListener pOnDialogInvitationListener) {
        super(context);
        mOnDialogInvitationListener = pOnDialogInvitationListener;
        mContext = context;
    }

    protected ChallengeInventationDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ChallengeInventationDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

}
