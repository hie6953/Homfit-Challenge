package com.ssafy.homfit.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.homfit.model.Tag;
import com.ssafy.homfit.model.dao.TagDAO;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	TagDAO tagDao;
	
	@Override
	@Transactional
	public boolean writeTag(Tag tag) {
		return tagDao.insertTag(tag) == 1;
	}

	@Override
	public String[] AllTagList() {
		return tagDao.selectAllTag();
	}


	@Override
	public Tag selectTag(String tag_name) {
		return tagDao.selectTag(tag_name);
	}

	@Override
	@Transactional
	public boolean writeTagInChallenge(HashMap<String, Integer> map) {
		return tagDao.insertTagInChallenge(map) == 1;
	}

	//챌린지에 있는 태그리스트
	@Override
	public Tag[] selectTagInChallenge(int challengeID) {
		return tagDao.selectTagInChallenge(challengeID);
	}

	//해당 태그를 가지고 있는 챌린지
	@Override
	public Tag[] selectChallengeInTag(int tagID) {
		return tagDao.selectChallengeInTag(tagID);
	}

	@Override
	public boolean deleteTagInChallenge(int challengeID) {
		return tagDao.deleteTagInChallenge(challengeID) >= 1;
	}

	@Override
	public List<Tag> selectPopularTag() {
		return tagDao.selectPopularTag();
	}

}
