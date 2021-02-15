package com.ssafy.homfit.model;

public class UserRate {

	private String uid;
	private int challenge_id;
	private int achievement_rate;
	private String end_date;
	private String challenge_title;
	

	public UserRate(String uid, int challenge_id, int achievement_rate, String end_date, String challenge_title) {
		super();
		this.uid = uid;
		this.challenge_id = challenge_id;
		this.achievement_rate = achievement_rate;
		this.end_date = end_date;
		this.challenge_title = challenge_title;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getChallenge_id() {
		return challenge_id;
	}
	public void setChallenge_id(int challenge_id) {
		this.challenge_id = challenge_id;
	}
	public int getAchievement_rate() {
		return achievement_rate;
	}
	public void setAchievement_rate(int achievement_rate) {
		this.achievement_rate = achievement_rate;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getChallenge_title() {
		return challenge_title;
	}
	public void setChallenge_title(String challenge_title) {
		this.challenge_title = challenge_title;
	}
	@Override
	public String toString() {
		return "UserRate [uid=" + uid + ", challenge_id=" + challenge_id + ", achievement_rate=" + achievement_rate
				+ ", end_date=" + end_date + ", challenge_title=" + challenge_title + "]";
	}
	
	
	
	
}
