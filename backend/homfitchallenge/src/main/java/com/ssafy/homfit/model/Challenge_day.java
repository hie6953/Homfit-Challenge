package com.ssafy.homfit.model;

/** 챌린지 인증 요일 */
public class Challenge_day {

	private int challenge_id;
	private int day_id;
	
	
	public Challenge_day(int challenge_id, int day_id) {
		super();
		this.challenge_id = challenge_id;
		this.day_id = day_id;
	}
	
	public int getChallenge_id() {
		return challenge_id;
	}
	public void setChallenge_id(int challenge_id) {
		this.challenge_id = challenge_id;
	}
	public int getDay_id() {
		return day_id;
	}
	public void setDay_id(int day_id) {
		this.day_id = day_id;
	}
	
	@Override
	public String toString() {
		return "Challenge_day [challenge_id=" + challenge_id + ", day_id=" + day_id + "]";
	}
	
	
}
