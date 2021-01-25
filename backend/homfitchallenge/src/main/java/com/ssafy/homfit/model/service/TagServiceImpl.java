package com.ssafy.homfit.model.service;

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
	public boolean writeTag(String tag) {
		return tagDao.insertTag(tag) == 1;
	}

	@Override
	public String[] AllTagList() {
		return tagDao.selectAllTag();
	}


	@Override
	public String selectTag(String tag_name) {
		return tagDao.selectTag(tag_name);
	}

}
