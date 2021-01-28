package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Feed;

public interface FeedService {

    public boolean create(Feed feed) throws Exception;

	public boolean updateImg(Feed feed) throws Exception;
    
    public List<Feed> searchByTag(String keyword) throws Exception;

    public Feed searchByFeedId(int feed_id) throws Exception;

    public List<Feed> searchByUser(String uid) throws Exception;

    public List<Feed> searchByChallenge(int challenge_id) throws Exception;
}
