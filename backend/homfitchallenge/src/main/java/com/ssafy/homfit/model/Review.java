package com.ssafy.homfit.model;

import java.sql.Timestamp;

public class Review {



	private int challenge_id;
	private String uid;
	private int star_point;
	private String review_contents;
	private Timestamp regist_time;
	

	public int getChallenge_id() {
		return challenge_id;
	}
	public void setChallenge_id(int challenge_id) {
		this.challenge_id = challenge_id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getStar_point() {
		return star_point;
	}
	public void setStar_point(int star_point) {
		this.star_point = star_point;
	}
	public String getReview_contents() {
		return review_contents;
	}
	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}
	public Timestamp getRegist_time() {
		return regist_time;
	}
	public void setRegist_time(Timestamp regist_time) {
		this.regist_time = regist_time;
	}
	@Override
	public String toString() {
		return "Review [challenge_id=" + challenge_id + ", uid=" + uid + ", star_point=" + star_point
				+ ", review_contents=" + review_contents + ", regist_time=" + regist_time + "]";
	}
	
	
	
	
}
