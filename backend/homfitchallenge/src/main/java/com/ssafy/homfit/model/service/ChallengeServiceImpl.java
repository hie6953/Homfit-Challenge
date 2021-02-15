package com.ssafy.homfit.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.TodayChallenge;
import com.ssafy.homfit.model.UserRate;
import com.ssafy.homfit.model.dao.ChallengeDAO;

@Service
public class ChallengeServiceImpl implements ChallengeService {

	@Autowired
	ChallengeDAO challengeDAO;

	// 상세보기
	@Override
	public Challenge detailChallenge(int challenge_id) {
		return challengeDAO.selectChallengeByid(challenge_id);
	}

	// 챌린지 등록
	@Override
	@Transactional
	public boolean writeChallenge(Challenge challenge) {
		return challengeDAO.insertChallenge(challenge) == 1;
	}

	// 수정
	@Override
	@Transactional
	public boolean updateChallenge(Challenge challenge) {
		return challengeDAO.updateChallenge(challenge) == 1;
	}

	// 삭제
	@Override
	@Transactional
	public boolean deleteChallenge(int challenge_id) {
		return challengeDAO.deleteChallenge(challenge_id) == 1;
	}

	
	// 전체리스트 반환
	@Override
	public List<Challenge> AllChallengeList() {
		return challengeDAO.selectAllChallenge();
	}


	
	// 챌린지 인증요일 등록
	@Override
	@Transactional
	public boolean writeChallengeDay(Map<String, Object> map) {
		return challengeDAO.insertChallengeDay(map) == 1;
	}

	// 챌린지 부위 등록
	@Override
	@Transactional
	public boolean writeChallengeBody(HashMap<String, Integer> map) {
		return challengeDAO.insertChallengeBody(map) == 1;
	}

	// 챌린지 참여 등록
	@Override
	@Transactional
	public boolean joinChallenge(int challenge_id, String uid) {
		return challengeDAO.joinChallenge(challenge_id, uid) == 1;
	}

	// 챌린지 참여 삭제
	@Override
	@Transactional
	public boolean quitChallenge(int challenge_id, String uid) {
		return challengeDAO.quitChallenge(challenge_id, uid) == 1;
	}

	// 해당 챌린지 부위리스트
	@Override
	public int[] selectBodyPart(int ChallengeId) {
		return challengeDAO.selectBodyPart(ChallengeId);
	}

	// 챌린지의 참여중인 user
	@Override
	public String selectParticipant(int challenge_id, String uid) {
		return challengeDAO.selectParticipant(challenge_id, uid);
	}

	//챌린지 개설자 닉네임 select
	@Override
	public String selectUserNickname(int challenge_id) {
		return challengeDAO.selectUserNickname(challenge_id);
	}

	//회원의 완료된 챌린지
	@Override
	public int[]  selectEndChallenge(String uid) {
		return challengeDAO.selectEndChallenge(uid);
	}


	//회원의 진행전 챌린지
	@Override
	public int[]  selectPreChallenge(String uid) {
		return challengeDAO.selectPreChallenge(uid);
	}

	//회원의 진행중 챌린지
	@Override
	public int[]  selectIngChallenge(String uid) {
		return challengeDAO.selectIngChallenge(uid);
	}

	//오늘 진행하는 챌린지
	@Override
	public List<TodayChallenge> selectTodayChallenge(int day) {
		return challengeDAO.selectTodayChallenge(day);
	}

	//완료된 참여한 운동 카테고리 리스트
	@Override
	public int[] selectFitId(String uid) {
		return challengeDAO.selectFitId(uid);
	}

	//완료된 참여한 부위 카테고리 리스트
	@Override
	public int[] selectBodyId(String uid) {
		return challengeDAO.selectBodyId(uid);
	}

	//해당 월 챌린지 데이터
	@Override
	public List<UserRate> selectMonthChallenge(String uid, int month) {
		return challengeDAO.selectMonthChallenge(uid, month);
	}

	//진행중 -> 완료로 갈 챌린지 id
	@Override
	public int[] selectIngToComp() {
		return challengeDAO.selectIngToComp();
	}

	//시작전 -> 진행중으로 갈 챌린지 id
	@Override
	public int[] selectBefoToIng() {
		return challengeDAO.selectBefoToIng();
	}

	//챌린지 id 받아 상태 업데이트
	@Override
	public boolean updateChallengeStatus(Map<String, Object> map) {
		return challengeDAO.updateChallengeStatus(map) > 0;
	}


	//챌린지별 평균 달성률 업데이트
	@Override
	public boolean updateAverageRate(int challenge_id, int average_rate) {
		return challengeDAO.updateAverageRate(challenge_id, average_rate) == 1;
	}

	//완료된 챌린지 개인달성률 insert
	@Override
	public boolean insertUserRate(UserRate userRate) {
		return challengeDAO.insertUserRate(userRate) == 1;
	}

	//챌린지 ID에 참여중인 userID목록
	@Override
	public String[] selectUidByChallenge(int challenge_id) {
		return challengeDAO.selectUidByChallenge(challenge_id);
	}


	



}
