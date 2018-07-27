package com.elcom.com.quizupapp.ui.activity.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hailpt on 6/18/2018.
 */
public class LiveAnswer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("note")
    @Expose
    private Object note;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("correct")
    @Expose
    private Integer correct;
    @SerializedName("count_selected")
    @Expose
    private Integer countSelected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public Integer getCountSelected() {
        return countSelected;
    }

    public void setCountSelected(Integer countSelected) {
        this.countSelected = countSelected;
    }

}
