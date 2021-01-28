package com.ssafy.homfit.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.homfit.model.Feed;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedDAO {

	public void create(Feed feed) throws SQLException;
    public List<Feed> searchByUser(String uid) throws SQLException;
	public List<Feed> searchByChallenge(int challenge_id) throws SQLException;
	public Feed searchByFeedId(int feed_id) throws SQLException;
	public void updateImg(Feed feed) throws SQLException;
	public List<Integer> searchCidKeyword(String keyword) throws SQLException;
	public List<Feed> searchByTag(List<Integer> item) throws SQLException;
}