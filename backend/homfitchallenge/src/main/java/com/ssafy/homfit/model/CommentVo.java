package com.ssafy.homfit.model;

import java.sql.Timestamp;

public class CommentVo {
    private int comment_id;
    private String contents;
    private int feed_id;
    private String uid;
    private Timestamp comment_regist_date;
    private String user_img;
    private String nick_name;

    public int getComment_id() {
        return this.comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getFeed_id() {
        return this.feed_id;
    }

    public void setFeed_id(int feed_id) {
        this.feed_id = feed_id;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Timestamp getComment_regist_date() {
        return this.comment_regist_date;
    }

    public void setComment_regist_date(Timestamp comment_regist_date) {
        this.comment_regist_date = comment_regist_date;
    }

    public String getUser_img() {
        return this.user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getNick_name() {
        return this.nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}
