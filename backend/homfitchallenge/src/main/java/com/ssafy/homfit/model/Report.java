package com.ssafy.homfit.model;

import java.sql.Timestamp;

public class Report {
    private int report_id;
    private Timestamp report_date;
    private String uid;
    private int feed_id;
    private int comment_id;

    public int getReport_id() {
        return this.report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public Timestamp getReport_date() {
        return this.report_date;
    }

    public void setReport_date(Timestamp report_date) {
        this.report_date = report_date;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getFeed_id() {
        return this.feed_id;
    }

    public void setFeed_id(int feed_id) {
        this.feed_id = feed_id;
    }

    public int getComment_id() {
        return this.comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    @Override
    public String toString() {
        return "Report {" +
            " report_id='" + getReport_id() + "'" +
            ", report_date='" + getReport_date() + "'" +
            ", uid='" + getUid() + "'" +
            ", feed_id='" + getFeed_id() + "'" +
            ", comment_id='" + getComment_id() + "'" +
            "}";
    }


}
