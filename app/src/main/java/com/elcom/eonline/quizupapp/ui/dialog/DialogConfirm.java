package com.elcom.eonline.quizupapp.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.elcom.eonline.quizupapp.R;


/**
 * Created by admin on 10/25/2016.
 */

public class DialogConfirm extends Dialog   {

    private TextView tvTitle;
    private TextView deleteTextView;
    private TextView cancelTextView;


    private String mConfirmContent;
    private String mConfirmButtonContent;
    private Context mContext;
    public DialogConfirm(Context context, int themeResId) {
        super(context, themeResId);
    }

    public DialogConfirm(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.challenge_invented_by_friend_dialog);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
    }

    public void changeText(String mTime){
        tvTitle.setText(mTime);
    }

}
