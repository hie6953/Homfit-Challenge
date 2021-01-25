package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.Challenge_day;

public interface ChallengeService {

	/** 챌린지 리스트 */
	public List<Challenge> AllChallengeList();
	public Challenge detailChallenge(int challenge_id);
	public boolean writeChallenge(Challenge challenge);
	public boolean updateChallenge(Challenge challenge);
	public boolean deleteChallenge(int challenge_id);
	
	/** 챌린지 인증요일 */
	public boolean writeChallengeDay(Challenge_day c_day);
}
