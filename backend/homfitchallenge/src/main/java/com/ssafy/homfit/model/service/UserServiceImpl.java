package com.ssafy.homfit.model.service;

import com.ssafy.homfit.model.User;
import com.ssafy.homfit.model.dao.UserDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public User getUid(String uid) throws Exception {
        return sqlSession.getMapper(UserDAO.class).getUid(uid);
    }

    @Override
    public void signup(User user) throws Exception {
        sqlSession.getMapper(UserDAO.class).signup(user);
    }
    
}
