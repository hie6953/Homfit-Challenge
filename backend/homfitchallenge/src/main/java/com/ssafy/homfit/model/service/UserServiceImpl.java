package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Favorite;
import com.ssafy.homfit.model.User;
import com.ssafy.homfit.model.dao.BadgeDAO;
import com.ssafy.homfit.model.dao.FavoriteDAO;
import com.ssafy.homfit.model.dao.UserDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    FavoriteService favoriteService;
    
    @Autowired
    BadgeService badgeService;

    @Autowired
    PointService pointService;

    @Override
    public User getUid(String uid) throws Exception {
        return sqlSession.getMapper(UserDAO.class).getUid(uid);
    }

    @Override
    @Transactional
    public boolean signup(User user) throws Exception {
        user.setGrade("bronze");
        user.setAdmin_check(false);
        if (user.getKakao_key() == "" || user.getKakao_key() == null) {
            user.setProvider_id(false);

            // 이메일 중복확인
            if (!this.duplicateEmailCheck(user.getEmail()))
                return false;
        } else {
            user.setProvider_id(true);
        }

        sqlSession.getMapper(UserDAO.class).signup(user);
        try {
            if(!favoriteService.init(user.getUid())) new Exception();
            if(!badgeService.init(user.getUid())) new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
    public void updateGrade(User user) throws Exception {
        User userData = this.getUid(user.getUid());
        // 회원 등급이 이전과 다르다면 회원을 해당 등급으로 업데이트 한다
        if(userData.getGrade() != user.getGrade()){
            sqlSession.getMapper(UserDAO.class).updateGrade(user);
        }
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
