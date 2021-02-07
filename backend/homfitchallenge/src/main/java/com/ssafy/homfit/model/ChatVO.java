package com.ssafy.homfit.model;

import java.security.Timestamp;

public class ChatVO {
    private String uid;
    private String userNickName;
    private String content;
    private Timestamp regist_date;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserNickName() {
        return this.userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
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



}
