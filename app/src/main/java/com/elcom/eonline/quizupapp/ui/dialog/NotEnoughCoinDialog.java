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
import com.elcom.eonline.quizupapp.utils.Utils;

/**
 * Created by Hailpt on 4/12/2018.
 */
public class NotEnoughCoinDialog extends AlertDialog {

    private OnDialogInvitationListener mOnDialogInvitationListener;
    private Context mContext;
    private Boolean mIsNotEnoughCoinAndFreeVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimationRightLeft;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.not_enough_coin_dialog);
        setCancelable(true);



        final Button btnAccept = (Button)findViewById(R.id.btnAccept);
        new Utils().setFontForButton(mContext,btnAccept);
        final Button btnReject = (Button)findViewById(R.id.btnReject);
        ImageView imvClose = (ImageView)findViewById(R.id.imvClose);
        TextView tvTitle =  findViewById(R.id.tvTitle);


        if (mIsNotEnoughCoinAndFreeVideo) {
            imvClose.setVisibility(View.GONE);
            tvTitle.setText("Nạp Coin để tiếp tục?");
        }


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

    public NotEnoughCoinDialog(@NonNull Context context, Boolean isNotEnoughCoinAndFreeVideo, OnDialogInvitationListener pOnDialogInvitationListener) {
        super(context);
        mOnDialogInvitationListener = pOnDialogInvitationListener;
        mContext = context;
        mIsNotEnoughCoinAndFreeVideo = isNotEnoughCoinAndFreeVideo;
    }

    protected NotEnoughCoinDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NotEnoughCoinDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

}
