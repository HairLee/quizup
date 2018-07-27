package com.elcom.com.quizupapp.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Hailpt on 4/13/2018.
 */
public class Invention extends RealmObject {

    private String myName;
    private String myLevel;
    private String opLevel;
    private String timeCountDown;

    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getMyLevel() {
        return myLevel;
    }

    public void setMyLevel(String myLevel) {
        this.myLevel = myLevel;
    }

    public String getOpLevel() {
        return opLevel;
    }

    public void setOpLevel(String opLevel) {
        this.opLevel = opLevel;
    }

    public String getTimeCountDown() {
        return timeCountDown;
    }

    public void setTimeCountDown(String timeCountDown) {
        this.timeCountDown = timeCountDown;
    }
}
