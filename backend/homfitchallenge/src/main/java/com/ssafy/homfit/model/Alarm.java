package com.ssafy.homfit.model;

import java.sql.Date;

public class Alarm {
    private int alarm_id;
    private String alarm_title;
    private String alarm_content;
    private Date alarm_regist_date;
    private String uid;
    private boolean alarm_check;

    public int getAlarm_id() {
        return this.alarm_id;
    }

    public void setAlarm_id(int alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getAlarm_title() {
        return this.alarm_title;
    }

    public void setAlarm_title(String alarm_title) {
        this.alarm_title = alarm_title;
    }

    public String getAlarm_content() {
        return this.alarm_content;
    }

    public void setAlarm_content(String alarm_content) {
        this.alarm_content = alarm_content;
    }

    public Date getAlarm_regist_date() {
        return this.alarm_regist_date;
    }

    public void setAlarm_regist_date(Date alarm_regist_date) {
        this.alarm_regist_date = alarm_regist_date;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isAlarm_check() {
        return this.alarm_check;
    }

    public boolean getAlarm_check() {
        return this.alarm_check;
    }

    public void setAlarm_check(boolean alarm_check) {
        this.alarm_check = alarm_check;
    }


    @Override
    public String toString() {
        return "Alarm{" +
            " alarm_id='" + getAlarm_id() + "'" +
            ", alarm_title='" + getAlarm_title() + "'" +
            ", alarm_content='" + getAlarm_content() + "'" +
            ", alarm_regist_date='" + getAlarm_regist_date() + "'" +
            ", uid='" + getUid() + "'" +
            ", alarm_check='" + isAlarm_check() + "'" +
            "}";
    }

}
