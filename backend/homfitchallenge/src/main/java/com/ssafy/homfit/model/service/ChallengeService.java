package com.ssafy.homfit.model.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	/** 챌린지 부위 */
	public boolean writeChallengeBody(HashMap<String, Integer> map);
	
	/** 챌린지 참여 */
	public boolean joinChallenge(int challenge_id, String uid);
	public boolean quitChallenge(int challenge_id, String uid);
	
	
}
