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
import com.elcom.com.quizupapp.ui.listener.OnDialogYesNoListener;
import com.elcom.com.quizupapp.utils.ConstantsApp;
import com.elcom.com.quizupapp.utils.PreferUtils;
import com.elcom.com.quizupapp.utils.Utils;

/**
 * Created by Hailpt on 4/12/2018.
 */
public class ChallengeInventedFriendDialog extends AlertDialog {

    private OnDialogInvitationListener mOnDialogInvitationListener;
    private Context mContext;
    private TextView tvTime;
    private Button btnInvite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimationRightLeft;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.challenge_invent_friend_dialog);
        setCancelable(true);



       btnInvite = (Button)findViewById(R.id.btnInvite);
        new Utils().setFontForButton(mContext,btnInvite);
        final ImageView imvClose = (ImageView)findViewById(R.id.imvClose);
        tvTime = (TextView)findViewById(R.id.tvTimeCountDown);

        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ConstantsApp.CHALLENGE_TIME_COUNT_DOWN.equals("0")){
                    mOnDialogInvitationListener.onInviteFriendToPlayGame();
                    setupLayout();
                } else {
                    mOnDialogInvitationListener.onCancelInviteFriendToPlayGame();
                    tvTime.setVisibility(View.INVISIBLE);
                    btnInvite.setText("MỜI CHƠI");
                    dismiss();
                }
            }
        });

        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    public void setupLayout(){
        tvTime.setVisibility(View.VISIBLE);
        btnInvite.setText("HỦY LỜI MỜI");

    }

    public void setTime(String time){
        tvTime.setText(time);
    }

    public ChallengeInventedFriendDialog(@NonNull Context context, OnDialogInvitationListener pOnDialogYesNoListener ) {
        super(context);
        mOnDialogInvitationListener = pOnDialogYesNoListener;
        mContext = context;
    }

    protected ChallengeInventedFriendDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ChallengeInventedFriendDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
