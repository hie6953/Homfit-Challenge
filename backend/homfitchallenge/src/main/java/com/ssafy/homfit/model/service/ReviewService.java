package com.ssafy.homfit.model.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.homfit.model.Review;

public interface ReviewService {

	
	public boolean writeReview(Review review);
	public List<Review> selectAllReview(int challenge_id);
	public List<Review> selectThreeReview(int challenge_id);
	public Review selectReview(String uid, int challenge_id);
}
