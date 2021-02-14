package com.ssafy.homfit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.homfit.model.Review;
import com.ssafy.homfit.model.dao.ReviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDAO reviewDao;
	
	
	@Override
	public List<Review> selectAllReview(int challenge_id) {
		return reviewDao.selectAllReview(challenge_id);
	}

	@Override
	public boolean writeReview(Review review) {
		return reviewDao.insertReview(review) == 1;
	}

	@Override
	public List<Review> selectThreeReview(int challenge_id) {
		return reviewDao.selectThreeReview(challenge_id);
	}

	@Override
	public Review selectReview(String uid, int challenge_id) {
		return reviewDao.selectReview(uid, challenge_id);
	}

	
	
}
