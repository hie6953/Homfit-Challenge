package com.ssafy.homfit.model;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Comment : 댓글", description = "특정 피드의 댓글의 정보를 가지고 있다")
public class Comment {
    private int comment_id;
    private String contents;
    private int feed_id;
    private String uid;
    private Timestamp comment_regist_date;

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


    @Override
    public String toString() {
        return "Comment {" +
            " comment_id='" + getComment_id() + "'" +
            ", contents='" + getContents() + "'" +
            ", feed_id='" + getFeed_id() + "'" +
            ", uid='" + getUid() + "'" +
            ", comment_regist_date='" + getComment_regist_date() + "'" +
            "}";
    }

}
