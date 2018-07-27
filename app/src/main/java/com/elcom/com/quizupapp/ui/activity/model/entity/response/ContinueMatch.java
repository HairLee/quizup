package com.elcom.com.quizupapp.ui.activity.model.entity.response;

/**
 * Created by Hailpt on 7/3/2018.
 */
public class ContinueMatch {

    private String match_id;
    private String topic_id;
    private int question_number;

    public int getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(int question_number) {
        this.question_number = question_number;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }


}
