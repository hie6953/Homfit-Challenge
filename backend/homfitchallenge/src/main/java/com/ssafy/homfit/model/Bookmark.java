package com.ssafy.homfit.model;

public class Bookmark {
    private String uid;
    private int challenge_id;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getChallenge_id() {
        return this.challenge_id;
    }

    public void setChallenge_id(int challenge_id) {
        this.challenge_id = challenge_id;
    }

	@Override
	public String toString() {
		return "Bookmark [uid=" + uid + ", challenge_id=" + challenge_id + "]";
	}
    
    
    
}
