package com.ssafy.homfit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.homfit.model.Challenge;
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

	
	//쓰기
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

}
