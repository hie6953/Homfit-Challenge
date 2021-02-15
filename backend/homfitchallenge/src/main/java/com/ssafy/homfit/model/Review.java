package com.ssafy.homfit.model;

import java.sql.Timestamp;

public class Review {



	private int challenge_id;
	private String uid;
	private String nick_name;
	private int star_point;
	private String review_contents;
	private Timestamp regist_time;
	private String user_img; //userImg

	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
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
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	@Override
	public String toString() {
		return "Review [challenge_id=" + challenge_id + ", uid=" + uid + ", nick_name=" + nick_name + ", star_point="
				+ star_point + ", review_contents=" + review_contents + ", regist_time=" + regist_time + ", user_img="
				+ user_img + "]";
	}
	
	
	
}
