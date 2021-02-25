package com.ssafy.homfit.model.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.TodayChallenge;
import com.ssafy.homfit.model.UserRate;



public interface ChallengeDAO {
	
	/**챌린지 리스트  */
	public List<Challenge> selectAllChallenge();
	public Challenge selectChallengeByid(int challenge_id);
	public int insertChallenge(Challenge challenge);
	public int updateChallenge(Challenge challenge);
	public int deleteChallenge(int challenge_id);
	public String selectUserNickname (int challenge_id);
	
	/** 챌린지 인증요일 */
	public int insertChallengeDay(Map<String,Object> map);

	
	/** 챌린지 부위 */
	public int insertChallengeBody(HashMap<String, Integer> map);
	public int[] selectBodyPart(int ChallengeId);
	
	/** 챌린지 참여 */
	public int joinChallenge(@Param("challenge_id") int challenge_id, @Param("uid") String uid );
	public int quitChallenge(@Param("challenge_id") int challenge_id, @Param("uid") String uid);
	public String selectParticipant(@Param("challenge_id") int challenge_id, @Param("uid") String uid);
	public String[] selectUidByChallenge (int challenge_id);
	
	/** 챌린지 관리*/
	public List<TodayChallenge> selectTodayChallenge(int day); //오늘할 챌린지 -> cache에 올림
	public int[] selectEndChallenge(String uid); //완료
	public int[] selectPreChallenge(String uid); //진행전
	public int[] selectIngChallenge(String uid); //진행중

	
	/**batch 작업*/
	public String selectDate();
	public String selectYesterday();
	public int [] selectBefoToIng(); //시작전 -> 진행중으로 갈 c_id
	public int [] selectIngToComp(); //진행중 -> 완료중으로 갈 c_id
	public int updateChallengeStatus(Map<String, Object> map); //c_id를 받아 챌린지 상태 업데이트
	public int updateAverageRate(@Param("challenge_id")int challenge_id, @Param("average_rate") int average_rate); //챌린지 id별 평균달성률 업데이트
	
	/** 통계 */
	public int[] selectFitId(String uid); //완료된 참여한 모든 운동 카테고리
	public int[] selectBodyId(String uid); //완료된 참여한 모든 부위 카테고리
	public List<UserRate> selectMonthChallenge ( @Param("uid") String uid, @Param("month")int month);
	public int insertUserRate (UserRate userRate); //완료된 챌린지 개인달성률

}
