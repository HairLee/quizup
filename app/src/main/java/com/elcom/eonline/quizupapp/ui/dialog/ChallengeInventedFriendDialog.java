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
import com.elcom.eonline.quizupapp.ui.listener.OnDialogYesNoListener;
import com.elcom.eonline.quizupapp.utils.ConstantsApp;
import com.elcom.eonline.quizupapp.utils.PreferUtils;
import com.elcom.eonline.quizupapp.utils.Utils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Hailpt on 4/12/2018.
 */
public class ChallengeInventedFriendDialog extends AlertDialog {

    private OnDialogInvitationListener mOnDialogInvitationListener;
    private Context mContext;
    private TextView tvTime, tvOpName;
    private Button btnInvite;
    private JSONObject mData;
    private CircleImageView  imvObAva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimationRightLeft;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.challenge_invent_friend_dialog);
        setCancelable(false);


       btnInvite = (Button)findViewById(R.id.btnInvite);
        new Utils().setFontForButton(mContext,btnInvite);
        final ImageView imvClose = (ImageView)findViewById(R.id.imvClose);
        tvTime = (TextView)findViewById(R.id.tvTimeCountDown);
        tvOpName = (TextView)findViewById(R.id.tvOpName);
        imvObAva = findViewById(R.id.imvAva);

        try {
            tvOpName.setText(mData.getString("name"));
            Picasso.get().load(mData.getString("avatar")).into(imvObAva);


            if (mData.has("avatar")){
                if(mData.has("avatar")){
                    Picasso.get().load(mData.getString("avatar")).into( imvObAva);
                }
            } else {
                if(mData.has("url")) {
                    Picasso.get().load(mData.getString("url")).into(imvObAva);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


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

    public ChallengeInventedFriendDialog(@NonNull Context context, JSONObject data, OnDialogInvitationListener pOnDialogYesNoListener ) {
        super(context);
        mOnDialogInvitationListener = pOnDialogYesNoListener;
        mContext = context;
        mData = data;
    }

    protected ChallengeInventedFriendDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ChallengeInventedFriendDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
