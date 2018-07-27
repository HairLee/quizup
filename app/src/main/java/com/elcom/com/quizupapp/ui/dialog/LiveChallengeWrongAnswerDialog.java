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
import android.widget.TextView;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.listener.OnLiveChallengeWinnerDialogListener;

/**
 * Created by Hailpt on 5/24/2018.
 */
public class LiveChallengeWrongAnswerDialog extends AlertDialog {

    private OnLiveChallengeWinnerDialogListener mOnLiveChallengeWinnerDialogListener;
    private Context mContext;
    private TextView tvTime;
    private Button btnInvite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.live_challenge_you_wrong_answer_dialog_layout);
        setCancelable(false);

        Button btnClose = (Button)findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnLiveChallengeWinnerDialogListener.onViewTheResult(mContext);
            }
        });

        Button btnShare = (Button)findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnLiveChallengeWinnerDialogListener.onShareTheResult();
            }
        });


    }


    public LiveChallengeWrongAnswerDialog(@NonNull Context context, OnLiveChallengeWinnerDialogListener pOnLiveChallengeWinnerDialogListener ) {
        super(context);
        mOnLiveChallengeWinnerDialogListener = pOnLiveChallengeWinnerDialogListener;
        mContext = context;
    }

    protected LiveChallengeWrongAnswerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LiveChallengeWrongAnswerDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}