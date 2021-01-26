package com.ssafy.homfit.model.service;

import com.ssafy.homfit.model.Feed;
import com.ssafy.homfit.model.dao.FeedDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public boolean create(Feed feed) throws Exception {
        try {
            sqlSession.getMapper(FeedDAO.class).create(feed);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
