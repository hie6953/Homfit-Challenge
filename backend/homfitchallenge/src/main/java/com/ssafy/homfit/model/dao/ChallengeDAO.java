package com.ssafy.homfit.model.dao;

import java.util.List;

import com.ssafy.homfit.model.Challenge;


public interface ChallengeDAO {
	
	public List<Challenge> selectAllChallenge();
	
	public Challenge selectChallengeByid(int challenge_id);
	
	public int insertChallenge(Challenge challenge);
	
	public int updateChallenge(Challenge challenge);
	
	public int deleteChallenge(int challenge_id);
}
