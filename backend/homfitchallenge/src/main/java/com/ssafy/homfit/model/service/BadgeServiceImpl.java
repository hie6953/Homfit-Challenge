package com.ssafy.homfit.model.service;

import com.ssafy.homfit.model.dao.BadgeDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeServiceImpl implements BadgeService{

    @Autowired
    SqlSession sqlSession;

    @Override
    public boolean init(String uid) throws Exception {
        try {
            sqlSession.getMapper(BadgeDAO.class).init(uid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
