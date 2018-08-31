package com.elcom.eonline.quizupapp.ui.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elcom.eonline.quizupapp.R;
import com.elcom.eonline.quizupapp.ui.activity.singleplay.ChooseAnswer;
import com.elcom.eonline.quizupapp.ui.activity.singleplay.OnAnswerTheQuestionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 3/5/2018.
 */

public class SoloWithImageChooseView extends LinearLayout implements View.OnClickListener {



    private TextView   tvAnswer1,tvAnswer2,tvAnswer3,tvAnswer4,tvAnswer5,tvAnswer6,tvAnswer7,tvAnswer8;
    private TextView   tvAnswer11,tvAnswer21,tvAnswer31,tvAnswer41,tvAnswer51,tvAnswer61,tvAnswer71,tvAnswer81;
    private TextView   tvAnswer12,tvAnswer22,tvAnswer32,tvAnswer42,tvAnswer52,tvAnswer62,tvAnswer72,tvAnswer82;
    private TextView tvSuggest1,tvSuggest2,tvSuggest3,tvSuggest4,tvSuggest5,tvSuggest6,tvSuggest7,tvSuggest8;
    private TextView tvSuggest11,tvSuggest21,tvSuggest31,tvSuggest41,tvSuggest51,tvSuggest61,tvSuggest71,tvSuggest81;
    private List<TextView> answerTextViewList = new ArrayList<>();
    private List<TextView> suggestTextViewList = new ArrayList<>();
    private List<ChooseAnswer> chooseAnswerList = new ArrayList<>();
    private List<ChooseAnswer> chooseSuggestList = new ArrayList<>();



    public SoloWithImageChooseView(Context context) {
        super(context);
        init(context);
    }

    public SoloWithImageChooseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SoloWithImageChooseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public SoloWithImageChooseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(final Context context) {
        View rootView = inflate(context, R.layout.solo_with_image_choose_item, this);

        /* Answer Layout*/
        tvAnswer1 = rootView.findViewById(R.id.tvAnswer1);
        tvAnswer2 = rootView.findViewById(R.id.tvAnswer2);
        tvAnswer3 = rootView.findViewById(R.id.tvAnswer3);
        tvAnswer4 = rootView.findViewById(R.id.tvAnswer4);
        tvAnswer5 = rootView.findViewById(R.id.tvAnswer5);
        tvAnswer6 = rootView.findViewById(R.id.tvAnswer6);
        tvAnswer7 = rootView.findViewById(R.id.tvAnswer7);
        tvAnswer8 = rootView.findViewById(R.id.tvAnswer8);

        tvAnswer11 = rootView.findViewById(R.id.tvAnswer11);
        tvAnswer21 = rootView.findViewById(R.id.tvAnswer21);
        tvAnswer31 = rootView.findViewById(R.id.tvAnswer31);
        tvAnswer41 = rootView.findViewById(R.id.tvAnswer41);
        tvAnswer51 = rootView.findViewById(R.id.tvAnswer51);
        tvAnswer61 = rootView.findViewById(R.id.tvAnswer61);
        tvAnswer71 = rootView.findViewById(R.id.tvAnswer71);
        tvAnswer81 = rootView.findViewById(R.id.tvAnswer81);

        tvAnswer12 = rootView.findViewById(R.id.tvAnswer12);
        tvAnswer22 = rootView.findViewById(R.id.tvAnswer22);
        tvAnswer32 = rootView.findViewById(R.id.tvAnswer32);
        tvAnswer42 = rootView.findViewById(R.id.tvAnswer42);
        tvAnswer52 = rootView.findViewById(R.id.tvAnswer52);
        tvAnswer62 = rootView.findViewById(R.id.tvAnswer62);
        tvAnswer72 = rootView.findViewById(R.id.tvAnswer72);
        tvAnswer82 = rootView.findViewById(R.id.tvAnswer82);
        /* Answer Layout*/

        /* Suggest Layout*/
        tvSuggest1 = rootView.findViewById(R.id.tvSuggest1);
        tvSuggest2 = rootView.findViewById(R.id.tvSuggest2);
        tvSuggest3 = rootView.findViewById(R.id.tvSuggest3);
        tvSuggest4 = rootView.findViewById(R.id.tvSuggest4);
        tvSuggest5 = rootView.findViewById(R.id.tvSuggest5);
        tvSuggest6 = rootView.findViewById(R.id.tvSuggest6);
        tvSuggest7 = rootView.findViewById(R.id.tvSuggest7);
        tvSuggest8 = rootView.findViewById(R.id.tvSuggest8);

        tvSuggest11 = rootView.findViewById(R.id.tvSuggest11);
        tvSuggest21 = rootView.findViewById(R.id.tvSuggest21);
        tvSuggest31 = rootView.findViewById(R.id.tvSuggest31);
        tvSuggest41 = rootView.findViewById(R.id.tvSuggest41);
        tvSuggest51 = rootView.findViewById(R.id.tvSuggest51);
        tvSuggest61 = rootView.findViewById(R.id.tvSuggest61);
        tvSuggest71 = rootView.findViewById(R.id.tvSuggest71);
        tvSuggest81 = rootView.findViewById(R.id.tvSuggest81);



        suggestTextViewList.add(tvSuggest1);
        suggestTextViewList.add(tvSuggest2);
        suggestTextViewList.add(tvSuggest3);
        suggestTextViewList.add(tvSuggest4);
        suggestTextViewList.add(tvSuggest5);
        suggestTextViewList.add(tvSuggest6);
        suggestTextViewList.add(tvSuggest7);
        suggestTextViewList.add(tvSuggest8);

        suggestTextViewList.add(tvSuggest11);
        suggestTextViewList.add(tvSuggest21);
        suggestTextViewList.add(tvSuggest31);
        suggestTextViewList.add(tvSuggest41);
        suggestTextViewList.add(tvSuggest51);
        suggestTextViewList.add(tvSuggest61);
        suggestTextViewList.add(tvSuggest71);
        suggestTextViewList.add(tvSuggest81);



        answerTextViewList.add(tvAnswer1);
        answerTextViewList.add(tvAnswer2);
        answerTextViewList.add(tvAnswer3);
        answerTextViewList.add(tvAnswer4);
        answerTextViewList.add(tvAnswer5);
        answerTextViewList.add(tvAnswer6);
        answerTextViewList.add(tvAnswer7);
        answerTextViewList.add(tvAnswer8);

        answerTextViewList.add(tvAnswer11);
        answerTextViewList.add(tvAnswer21);
        answerTextViewList.add(tvAnswer31);
        answerTextViewList.add(tvAnswer41);
        answerTextViewList.add(tvAnswer51);
        answerTextViewList.add(tvAnswer61);
        answerTextViewList.add(tvAnswer71);
        answerTextViewList.add(tvAnswer81);

        answerTextViewList.add(tvAnswer12);
        answerTextViewList.add(tvAnswer22);
        answerTextViewList.add(tvAnswer32);
        answerTextViewList.add(tvAnswer42);
        answerTextViewList.add(tvAnswer52);
        answerTextViewList.add(tvAnswer62);
        answerTextViewList.add(tvAnswer72);
        answerTextViewList.add(tvAnswer82);

    }

    public void setUpAnswerLayout( List<String> mData ){
        int remove = chooseAnswerList.size() - mData.size(); // 5

        for (int i = 0; i < remove; i++) {
            chooseAnswerList.get(chooseAnswerList.size()-1).getTextView().setVisibility(View.GONE);
            chooseAnswerList.remove(chooseAnswerList.size() -1 );
        }

        for (int i = 0; i < mData.size(); i++) {
            chooseAnswerList.get(i).getTextView().setText(mData.get(i));
        }

    }

    public void setUpSuggestLayout( List<String> mData ){
        int remove = chooseSuggestList.size() - mData.size(); // 5

        for (int i = 0; i < remove; i++) {
            chooseSuggestList.get(chooseSuggestList.size()-1).getTextView().setVisibility(View.GONE);
            chooseSuggestList.remove(chooseSuggestList.size() -1 );
        }

        for (int i = 0; i < mData.size(); i++) {
            chooseSuggestList.get(i).getTextView().setText(mData.get(i));
        }

    }

    private OnAnswerTheQuestionListener mOnAnswerTheQuestionListener;
    public void setOnSoloChooseTextListener(OnAnswerTheQuestionListener pOnAnswerTheQuestionListener){
        mOnAnswerTheQuestionListener = pOnAnswerTheQuestionListener;
    }

    @Override
    public void onClick(View view) {

        Log.e("hailp","updateAfterGivingTheAnswer ~ "+ (Integer) view.getTag());
        switch (view.getId()){

            case R.id.tvAnswer1:
                answerTextViewList.get(0).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer2:
                answerTextViewList.get(1).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer3:
                answerTextViewList.get(2).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer4:
                answerTextViewList.get(3).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer5:
                answerTextViewList.get(4).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer6:
                answerTextViewList.get(5).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer7:
                answerTextViewList.get(6).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer8:
                answerTextViewList.get(7).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;




            case R.id.tvAnswer11:
                answerTextViewList.get(8).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer21:
                answerTextViewList.get(9).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer31:
                answerTextViewList.get(10).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer41:
                answerTextViewList.get(11).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer51:
                answerTextViewList.get(12).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer61:
                answerTextViewList.get(13).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer71:
                answerTextViewList.get(14).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

            case R.id.tvAnswer81:
                answerTextViewList.get(15).setText("");
                hideSuggestButtonAfterChooseAnswer((Integer) view.getTag(),true);
                break;

        }

        if (isEnoughAnswerOrNot()){
            return;
        }

        switch (view.getId()){
            case R.id.tvSuggest1:
                updateAfterGivingTheAnswer(0);
                break;
            case R.id.tvSuggest2:
                updateAfterGivingTheAnswer(1);
                break;
            case R.id.tvSuggest3:
                updateAfterGivingTheAnswer(2);
                break;
            case R.id.tvSuggest4:
                updateAfterGivingTheAnswer(3);
                break;
            case R.id.tvSuggest5:
                updateAfterGivingTheAnswer(4);
                break;
            case R.id.tvSuggest6:
                updateAfterGivingTheAnswer(5);
                break;
            case R.id.tvSuggest7:
                updateAfterGivingTheAnswer(6);
                break;
            case R.id.tvSuggest8:
                updateAfterGivingTheAnswer(7);
                break;



            case R.id.tvSuggest11:
                updateAfterGivingTheAnswer(8);
                break;
            case R.id.tvSuggest21:
                updateAfterGivingTheAnswer(9);
                break;
            case R.id.tvSuggest31:
                updateAfterGivingTheAnswer(10);
                break;
            case R.id.tvSuggest41:
                updateAfterGivingTheAnswer(11);
                break;
            case R.id.tvSuggest51:
                updateAfterGivingTheAnswer(12);
                break;
            case R.id.tvSuggest61:
                updateAfterGivingTheAnswer(13);
                break;
            case R.id.tvSuggest71:
                updateAfterGivingTheAnswer(14);
                break;
            case R.id.tvSuggest81:
                updateAfterGivingTheAnswer(15);
                break;
        }


    }

    public void setDataForSuggestList(List<String> suggestList){
        for (int i = 0; i < suggestTextViewList.size(); i++) {
            ChooseAnswer chooseAnswer = new ChooseAnswer();
            chooseAnswer.setPosition(i);
            chooseAnswer.setTextView(suggestTextViewList.get(i));
            if(suggestList.size() > i){
                chooseAnswer.setAnswer(suggestList.get(i));
            }

            chooseSuggestList.add(chooseAnswer);
            suggestTextViewList.get(i).setTag(i);
            suggestTextViewList.get(i).setOnClickListener(this);
        }

        setUpSuggestLayout(suggestList);

    }

    public void setDataForAnswerList(List<String> answerList){
        for (int i = 0; i < answerTextViewList.size(); i++) {
            ChooseAnswer chooseAnswer = new ChooseAnswer();
            chooseAnswer.setPosition(i);
            chooseAnswer.setTextView(answerTextViewList.get(i));
            chooseAnswerList.add(chooseAnswer);

            answerTextViewList.get(i).setOnClickListener(this);
        }

        setUpAnswerLayout(answerList);
    }


    public void updateAfterGivingTheAnswer( int location){

        hideSuggestButtonAfterChooseAnswer(location,false);
        for (int i = 0; i < answerTextViewList.size(); i++) {
            if(answerTextViewList.get(i).getText().equals("")){
                answerTextViewList.get(i).setText(chooseSuggestList.get(location).getAnswer());
                answerTextViewList.get(i).setTag(location);
                Log.e("hailpt"," updateAfterGivingTheAnswer final "+checkFinalAnswer());
                mOnAnswerTheQuestionListener.onAnswerTheQuestionListener(checkFinalAnswer());
                return;
            }
        }


    }

    public void hideSuggestButtonAfterChooseAnswer(int location, boolean isChoose){

        if(isChoose){

            for (int i = 0; i < suggestTextViewList.size(); i++) {
                if((Integer)suggestTextViewList.get(i).getTag() == location){
                    suggestTextViewList.get(i).setVisibility(VISIBLE);
                }
            }

        } else  {
            for (int i = 0; i < suggestTextViewList.size(); i++) {
                if((Integer)suggestTextViewList.get(i).getTag() == location){
                    suggestTextViewList.get(i).setVisibility(INVISIBLE);
                }
            }
        }
    }

    private boolean isEnoughAnswerOrNot(){
        for (int i = 0; i < chooseAnswerList.size(); i++) {
            if (chooseAnswerList.get(i).getTextView().getText().equals("")){
                return false;
            }
        }
        return true;
    }

    public String checkFinalAnswer(){
       if(isEnoughAnswerOrNot()){
           String finalAnswer = "";
           for (int i = 0; i < chooseAnswerList.size(); i++) {
               finalAnswer = finalAnswer + chooseAnswerList.get(i).getTextView().getText();
           }
           return finalAnswer;
       }
        return "";
    }
}


