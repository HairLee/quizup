package com.elcom.eonline.quizupapp.ui.activity.singleplay;

import android.widget.TextView;

/**
 * Created by Hailpt on 8/30/2018.
 */
public class ChooseAnswer {

    private TextView textView;
    private int position;
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
