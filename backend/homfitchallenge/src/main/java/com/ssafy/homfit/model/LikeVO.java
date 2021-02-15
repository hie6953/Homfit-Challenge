package com.ssafy.homfit.model;

public class LikeVO {
    private String uid;
    private int feed_id;
    private boolean user_liked;

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

    public boolean isUser_liked() {
        return this.user_liked;
    }

    public boolean getUser_liked() {
        return this.user_liked;
    }

    public void setUser_liked(boolean user_liked) {
        this.user_liked = user_liked;
    }
}
