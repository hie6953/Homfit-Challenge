package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Point;
import com.ssafy.homfit.model.User;
import com.ssafy.homfit.model.dao.PointDAO;
import com.ssafy.homfit.model.dao.UserDAO;
import com.ssafy.homfit.util.Util;

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

    @Autowired
    S3Service s3service;

    @Override
    public User getUid(String uid) throws Exception {
        return sqlSession.getMapper(UserDAO.class).getUid(uid);
    }

    @Override
    public boolean signup(User user) throws Exception {
        String uidToken = null;
        // while (true) {
            uidToken = Util.generateToken(user.getEmail());
        //     if (this.getUid(uidToken) == null)
        //         break;
        // }
        user.setUid(uidToken);
        // user.setUid(user.getEmail());
        user.setUser_img("https://homfitimage.s3.ap-northeast-2.amazonaws.com/a50148c1b3f70141c7969e9c00d50af4");
        user.setGrade("bronze");
        user.setAdmin_check(false);
        if (user.getKakao_key() == "" || user.getKakao_key() == null) {
            user.setProvider_id(false);
        } else {
            user.setProvider_id(true);
        }
        // // 이메일 중복확인
        // if (user.getEmail()!= null && this.duplicateEmailCheck(user.getEmail()))
        // return false;
        try {
            sqlSession.getMapper(UserDAO.class).signup(user);
            if (!favoriteService.init(user.getUid()))
                new Exception();
            if (!badgeService.init(user.getUid()))
                new Exception();
            Point point = new Point();
            point.setPoint(100);
            point.setUid(user.getUid());
            point.setTitle("뱃지");
            point.setContent("첫 회원 가입");
            sqlSession.getMapper(PointDAO.class).earn(point);
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
            return false;
        return true;
    }

    @Override
    public boolean duplicateNickNameCheck(String nick_name) throws Exception {
        User user = null;
        user = sqlSession.getMapper(UserDAO.class).duplicateNickNameCheck(nick_name);

        if (user == null)
            return false;
        return true;
    }

    @Override
    public void updateGrade(User user) throws Exception {
        User userData = this.getUid(user.getUid());
        // 회원 등급이 이전과 다르다면 회원을 해당 등급으로 업데이트 한다
        if (userData.getGrade() != user.getGrade()) {
            sqlSession.getMapper(UserDAO.class).updateGrade(user);
        }
    }

    @Override
    @Transactional
    public boolean updateDetail(User user) throws Exception {
        try {
            if (user.getPassword() != null)
                sqlSession.getMapper(UserDAO.class).updatePassword(user);

            if (user.getNick_name() != null)
                sqlSession.getMapper(UserDAO.class).updateNickName(user);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public List<User> test() throws Exception {
        List<User> list = null;
        try {
            list = sqlSession.getMapper(UserDAO.class).test();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateImg(String uid, String imgURL) throws Exception {
        try {
            if (uid != null && imgURL != null) {
                sqlSession.getMapper(UserDAO.class).updateUserImg(uid, imgURL);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Boolean checkPassword(User user) throws Exception {
        User tmp = null;
        try {
            tmp = sqlSession.getMapper(UserDAO.class).checkPassword(user);
        } catch (Exception e) {
            return false;
        }
        if (tmp == null)
            return false;
        return true;
    }

    @Override
    public String getByEmail(String email) throws Exception {
        return sqlSession.getMapper(UserDAO.class).getByEmail(email);
    }
}
