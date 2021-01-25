package com.ssafy.homfit.model.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.Challenge_day;


public interface ChallengeDAO {
	
	/**챌린지 리스트  */
	public List<Challenge> selectAllChallenge();
	
	public Challenge selectChallengeByid(int challenge_id);
	
	
	public int insertChallenge(Challenge challenge);
	
	public int updateChallenge(Challenge challenge);
	
	public int deleteChallenge(int challenge_id);

	/** 챌린지 인증요일 */
	public int insertChallengeDay(Challenge_day c_day);
}
