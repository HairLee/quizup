package com.elcom.com.quizupapp.ui.activity.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Hailpt on 6/18/2018.
 */
public class LiveQuestion {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("image_url")
    @Expose
    private Object imageUrl;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("answer")
    @Expose
    private List<LiveAnswer> answer = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<LiveAnswer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<LiveAnswer> answer) {
        this.answer = answer;
    }

}
