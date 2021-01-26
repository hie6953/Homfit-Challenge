package com.ssafy.homfit.model.service;

import com.ssafy.homfit.model.dao.FavoriteDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class FavoriteServiceImpl implements FavoriteService{

    @Autowired
    SqlSession sqlSession;

    @Override
    public boolean init(String uid) throws Exception {
        try {
            sqlSession.getMapper(FavoriteDAO.class).init(uid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
