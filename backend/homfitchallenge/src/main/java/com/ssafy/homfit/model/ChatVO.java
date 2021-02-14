package com.ssafy.homfit.model;

import java.sql.Date;

public class ChatVO {
    private String uid;
    private String nick_name;
    private Date regist_date;

    public Date getRegist_date() {
        return this.regist_date;
    }

    public void setRegist_date(Date regist_date) {
        this.regist_date = regist_date;
    }

    private int challenge_id;
    private String content;
    private String user_img;

    public String getUser_img() {
        return this.user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public ChatVO() {
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNick_name() {
        return this.nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getChallenge_id() {
        return this.challenge_id;
    }

    public void setChallenge_id(int challenge_id) {
        this.challenge_id = challenge_id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ChatVO {" + " uid='" + getUid() + "'" + ", nick_name='" + getNick_name() + "'" + ", challenge_id='"
                + getChallenge_id() + "'" + ", content='" + getContent() + "'" + ", regist_date='" + getRegist_date()
                + "'" + "}";
    }

}
