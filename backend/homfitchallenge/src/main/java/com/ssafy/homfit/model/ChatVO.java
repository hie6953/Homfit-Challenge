package com.ssafy.homfit.model;

import java.security.Timestamp;

public class ChatVO {
    private String uid;
    private String nick_name;
    private int challenge_id;
    private String content;
    private Timestamp regist_date;

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

    public Timestamp getRegist_date() {
        return this.regist_date;
    }

    public void setRegist_date(Timestamp regist_date) {
        this.regist_date = regist_date;
    }

    @Override
    public String toString() {
        return "ChatVO {" +
            " uid='" + getUid() + "'" +
            ", nick_name='" + getNick_name() + "'" +
            ", challenge_id='" + getChallenge_id() + "'" +
            ", content='" + getContent() + "'" +
            ", regist_date='" + getRegist_date() + "'" +
            "}";
    }
    
}
