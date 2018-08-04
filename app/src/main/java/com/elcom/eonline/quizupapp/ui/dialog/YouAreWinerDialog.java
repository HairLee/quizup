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
import android.widget.ImageView;
import android.widget.TextView;

import com.elcom.eonline.quizupapp.R;
import com.elcom.eonline.quizupapp.ui.listener.OnDialogInvitationListener;
import com.elcom.eonline.quizupapp.ui.listener.OnLiveChallengeWinnerDialogListener;
import com.elcom.eonline.quizupapp.utils.ConstantsApp;
import com.elcom.eonline.quizupapp.utils.Utils;

/**
 * Created by Hailpt on 5/24/2018.
 */
public class YouAreWinerDialog extends AlertDialog {

    private OnLiveChallengeWinnerDialogListener mOnLiveChallengeWinnerDialogListener;
    private Context mContext;
    private TextView tvTime;
    private Button btnInvite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimationRightLeft;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.live_challenge_you_are_winner_dialog_layout);
        setCancelable(true);

        TextView tvResult = (TextView)findViewById(R.id.tvResult);
        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnLiveChallengeWinnerDialogListener.onViewTheResult(mContext);
            }
        });
    }


    public YouAreWinerDialog(@NonNull Context context, OnLiveChallengeWinnerDialogListener pOnLiveChallengeWinnerDialogListener ) {
        super(context);
        mOnLiveChallengeWinnerDialogListener = pOnLiveChallengeWinnerDialogListener;
        mContext = context;
    }

    protected YouAreWinerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected YouAreWinerDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}