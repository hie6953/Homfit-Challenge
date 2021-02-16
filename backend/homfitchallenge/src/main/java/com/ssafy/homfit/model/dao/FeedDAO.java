package com.ssafy.homfit.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.homfit.model.Feed;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedDAO {

	public int searchLikeCnt(int feed_id) throws SQLException;

	public void create(Feed feed) throws SQLException;

	public List<Feed> searchByUser(String uid) throws SQLException;

	public List<Feed> searchByChallenge(Map<String, Object> map) throws SQLException;

	public List<Feed> searchByChallengeNotFeedId(Map<String, Object> map) throws SQLException;

	public Feed searchByFeedId(Map<String, Object> map) throws SQLException;

	public void updateImg(Feed feed) throws SQLException;

	public List<Integer> searchCidKeyword(String keyword) throws SQLException;

	public List<Feed> searchByTag(List<Integer> item) throws SQLException;

	public void createLikes(String uid, int feed_id) throws SQLException;

	public void deleteLikes(String uid, int feed_id) throws SQLException;

	public void increaseLikes(int feed_id) throws SQLException;

	public void decreaseLikes(int feed_id) throws SQLException;

	public List<Feed> searchAll() throws SQLException;

	public List<Feed> searchCategory(int category) throws SQLException;

	public List<Feed> searchByChallengeTitle(String keyword) throws SQLException;

	/** 챌린지에서 사용함 */
	// 지금까지 찍은 피드 갯수
	public int[] selectUserFeed(@Param("uid") String uid, @Param("challenge_id") int challenge_id);

	// 오늘 찍은 피드 img url
	public List<Feed> selectFeedImg(@Param("uid") String uid, @Param("challenge_id") int challenge_id);

	public List<Feed> searchByUserAll(String uid) throws SQLException;

	public Feed searchByFeedIdForUseAlarm(int feed_id) throws SQLException;

	public void deleteFeed(int feed_id) throws SQLException;
}
