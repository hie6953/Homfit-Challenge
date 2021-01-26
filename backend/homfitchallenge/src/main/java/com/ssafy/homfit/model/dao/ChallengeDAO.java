package com.ssafy.homfit.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	/** 챌린지 부위 */
	public int insertChallengeBody(HashMap<String, Integer> map);
	
	/** 챌린지 참여 */
	public int joinChallenge(@Param("challenge_id") int challenge_id, @Param("uid") String uid );
	public int quitChallenge(@Param("challenge_id") int challenge_id, @Param("uid") String uid);
	
	
	
}
