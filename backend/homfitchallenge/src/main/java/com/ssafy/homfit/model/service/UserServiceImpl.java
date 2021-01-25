package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.User;
import com.ssafy.homfit.model.dao.UserDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public User getUid(String uid) throws Exception {
        return sqlSession.getMapper(UserDAO.class).getUid(uid);
    }

    @Override
    public boolean signup(User user) throws Exception {
        user.setGrade("bronze");
        user.setAdmin_check(false);
        if (user.getKakao_key() == "" || user.getKakao_key() == null) {
            user.setProvider_id(false);

            // 이메일 중복확인
            if (this.duplicateEmailCheck(user.getEmail()))
                return false;
        } else {
            user.setProvider_id(true);
        }

        sqlSession.getMapper(UserDAO.class).signup(user);
        return true;
    }

    @Override
    public User login(User user) throws Exception {
        if (user.getEmail() == null || user.getPassword() == null)
            return null;
        return sqlSession.getMapper(UserDAO.class).login(user);
    }

    @Override
    public String findPassword(String email) throws Exception {
        if (email == null)
            return null;

        // 회원 휴대전화로 무언가 하려면 여기에

        return sqlSession.getMapper(UserDAO.class).findPassword(email);
    }

    @Override
    public boolean duplicateEmailCheck(String email) throws Exception {
        User user = null;
        user = sqlSession.getMapper(UserDAO.class).duplicateEmailCheck(email);

        if (user == null)
            return true;
        return false;
    }

    @Override
    public void updateGrade(String uid) throws Exception {
        // 회원의 모든 포인트의 합계를 가지고온다

        // 회원의 포인트가 어느 등급에 해당하는지 확인한다

        // 회원을 해당 등급으로 업데이트 한다
    }

    @Override
    @Transactional
    public boolean updateDetail(User user) throws Exception {
        try {
            sqlSession.getMapper(UserDAO.class).updatePassword(user);
            sqlSession.getMapper(UserDAO.class).updateNickName(user);
            sqlSession.getMapper(UserDAO.class).updateUserImg(user);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public List<User> test() throws Exception{
        List<User> list = null;
        try {
            list = sqlSession.getMapper(UserDAO.class).test();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
