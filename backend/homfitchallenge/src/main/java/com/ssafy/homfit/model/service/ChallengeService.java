package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Challenge;

public interface ChallengeService {

	
	public List<Challenge> AllChallengeList();
	public Challenge detailChallenge(int challenge_id);
	public boolean writeChallenge(Challenge challenge);
	public boolean updateChallenge(Challenge challenge);
	public boolean deleteChallenge(int challenge_id);
}
