package com.ssafy.homfit.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("todayChallenge")
public class TodayChallenge implements Serializable{

	@Id
	private int challenge_id;

	public int getChallenge_id() {
		return challenge_id;
	}

	public void setChallenge_id(int challenge_id) {
		this.challenge_id = challenge_id;
	}

	@Override
	public String toString() {
		return "TodayChallenge [challenge_id=" + challenge_id + "]";
	}
	
	
}
