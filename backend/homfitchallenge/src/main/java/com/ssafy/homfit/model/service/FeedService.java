package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Feed;

import io.lettuce.core.dynamic.annotation.Param;

public interface FeedService {
    public int searchLikeCnt(int feed_id) throws Exception;

    public boolean create(Feed feed) throws Exception;

    public boolean updateImg(int feed_id, String feed_picture) throws Exception;

    public List<Feed> searchByTag(String keyword) throws Exception;

    public Feed searchByFeedId(int feed_id, String uid) throws Exception;

    public List<Feed> searchByUser(String uid) throws Exception;

    public List<Feed> searchByChallenge(int challenge_id) throws Exception;

    public List<Feed> searchByChallengeNotFeedId(int challenge_id, int feed_id, String uid) throws Exception;

    public boolean createLikes(String uid, int feed_id) throws Exception;

    public boolean deleteLikes(String uid, int feed_id) throws Exception;

    public List<Feed> searchAll() throws Exception;

    public List<Feed> searchCategoryFeed(int category) throws Exception;

    public List<Feed> searchByKeyword(int type, String keyword) throws Exception;

    public List<Feed> searchByChallengeTitle(String keyword) throws Exception;

    /** 챌린지에서 사용함 */
    public int[] selectUserFeed(String uid, int challenge_id);

    public List<Feed> selectFeedImg(String uid, int challenge_id);

	public List<Feed> searchByUserAll(String uid) throws Exception;

}
