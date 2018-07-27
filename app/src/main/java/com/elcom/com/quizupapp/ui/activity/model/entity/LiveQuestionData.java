package com.elcom.com.quizupapp.ui.activity.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hailpt on 6/18/2018.
 */
public class LiveQuestionData{

    @SerializedName("time_count_down")
    @Expose
    private Integer timeCountDown;
    @SerializedName("total_question")
    @Expose
    private Integer totalQuestion;
    @SerializedName("question")
    @Expose
    private LiveQuestion question;

    public Integer getTimeCountDown() {
        return timeCountDown;
    }

    public void setTimeCountDown(Integer timeCountDown) {
        this.timeCountDown = timeCountDown;
    }

    public Integer getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(Integer totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public LiveQuestion getQuestion() {
        return question;
    }

    public void setQuestion(LiveQuestion question) {
        this.question = question;
    }

}