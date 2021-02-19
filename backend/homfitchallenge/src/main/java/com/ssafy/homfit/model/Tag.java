package com.ssafy.homfit.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash("tag")
public class Tag implements Serializable{

	@Id
	private int tag_id;
	private String tag_name;
	private int challenge_id;
	private int tag_count;
	
	
	public int getTag_count() {
		return tag_count;
	}

	public void setTag_count(int tag_count) {
		this.tag_count = tag_count;
	}

	public int getChallenge_id() {
		return challenge_id;
	}

	public void setChallenge_id(int challenge_id) {
		this.challenge_id = challenge_id;
	}

	public Tag() {
		
	}

	public Tag(String tag_name) {
		this.tag_name = tag_name;
	}
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	@Override
	public String toString() {
		return "Tag [tag_id=" + tag_id + ", tag_name=" + tag_name + ", challenge_id=" + challenge_id + ", tag_count="
				+ tag_count + "]";
	}
	

	
	
}
