package com.ssafy.homfit.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.TodayChallenge;
import com.ssafy.homfit.model.UserRate;



public interface ChallengeService {

	/** 챌린지 리스트 */
	public List<Challenge> AllChallengeList();
	public Challenge detailChallenge(int challenge_id);
	public boolean writeChallenge(Challenge challenge);
	public boolean updateChallenge(Challenge challenge);
	public boolean deleteChallenge(int challenge_id);
	public String selectUserNickname (int challenge_id);
	
	/** 챌린지 인증요일 */
	public boolean writeChallengeDay(Map<String,Object> map);

	/** 챌린지 부위 */
	public boolean writeChallengeBody(HashMap<String, Integer> map);
	public int[] selectBodyPart(int ChallengeId);
	
	/** 챌린지 참여 */
	public boolean joinChallenge(int challenge_id, String uid);
	public boolean quitChallenge(int challenge_id, String uid);
	public String selectParticipant(int challenge_id, String uid);
	
	/** 챌린지 관리*/
	public List<TodayChallenge> selectTodayChallenge(int day); //오늘할 챌린지 -> cache에 올림
	public int[] selectEndChallenge(String uid); //완료
	public int[] selectPreChallenge(String uid); //진행전
	public int[] selectIngChallenge(String uid); //진행중
	
	
	/**batch 작업*/
	public int [] selectBefoToIng(); //시작전 -> 진행중으로 갈 c_id
	public int [] selectIngToComp(); //진행중 -> 완료중으로 갈 c_id 
	public boolean updateChallengeStatus(Map<String, Object> map); //c_id를 받아 챌린지 상태 업데이트
	
	/** 통계 */
	public int[] selectFitId(String uid);
	public int[] selectBodyId(String uid);
	public List<UserRate> selectMonthChallenge(String uid, int month);

}
