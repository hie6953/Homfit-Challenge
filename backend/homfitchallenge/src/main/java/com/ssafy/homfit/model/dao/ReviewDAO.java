package com.ssafy.homfit.model.dao;

import java.util.List;

import com.ssafy.homfit.model.Review;
import org.apache.ibatis.annotations.Param;

public interface ReviewDAO {

	public int insertReview(Review review);
	public List<Review> selectAllReview(int challenge_id);
	public List<Review> selectThreeReview(int challenge_id);
}
