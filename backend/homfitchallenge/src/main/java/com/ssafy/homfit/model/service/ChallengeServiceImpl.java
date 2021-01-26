package com.ssafy.homfit.model.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.Challenge_day;
import com.ssafy.homfit.model.dao.ChallengeDAO;

@Service
public class ChallengeServiceImpl implements ChallengeService{

	@Autowired
	ChallengeDAO challengeDAO;
	
	//상세보기
	@Override
	public Challenge detailChallenge(int challenge_id) {
		return challengeDAO.selectChallengeByid(challenge_id);
	}

	
	//챌린지 등록
	@Override
	@Transactional
	public boolean writeChallenge(Challenge challenge) {
		return challengeDAO.insertChallenge(challenge) == 1;
	}

	//수정
	@Override
	@Transactional
	public boolean updateChallenge(Challenge challenge) {
		return challengeDAO.updateChallenge(challenge) == 1;
	}

	//삭제
	@Override
	@Transactional
	public boolean deleteChallenge(int challenge_id) {
		return challengeDAO.deleteChallenge(challenge_id) == 1;
	}

	//전체리스트 반환
	@Override
	public List<Challenge> AllChallengeList() {
		return challengeDAO.selectAllChallenge();
	}


	//챌린지 인증요일 등록
	@Override
	@Transactional
	public boolean writeChallengeDay(Challenge_day c_day) {
		return challengeDAO.insertChallengeDay(c_day) == 1;
	}


	//챌린지 부위 등록
	@Override
	@Transactional
	public boolean writeChallengeBody(HashMap<String, Integer> map) {
		return challengeDAO.insertChallengeBody(map) ==1;
	}


	//챌린지 참여 등록
	@Override
	@Transactional
	public boolean joinChallenge(int challenge_id, String uid) {
		return challengeDAO.joinChallenge(challenge_id, uid ) == 1;
	}

	//챌린지 참여 삭제
	@Override
	@Transactional
	public boolean quitChallenge(int challenge_id, String uid) {
		return challengeDAO.quitChallenge(challenge_id, uid) == 1;
	}




}
