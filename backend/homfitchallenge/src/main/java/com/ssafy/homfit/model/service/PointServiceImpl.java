package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Point;
import com.ssafy.homfit.model.User;
import com.ssafy.homfit.model.dao.PointDAO;
import com.ssafy.homfit.model.dao.UserDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Point> inquiry(String uid) throws Exception {
        List<Point> list = sqlSession.getMapper(PointDAO.class).inquiry(uid);
        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    @Override    
    public boolean earn(Point point) throws Exception {
        if(point.getPoint() > 0 && sqlSession.getMapper(UserDAO.class).getUid(point.getUid()) != null){
            sqlSession.getMapper(PointDAO.class).earn(point);
            long sum = 0;
            String grade = null;
            String uid = point.getUid();
            User user = new User();
            // 회원의 모든 포인트의 합계를 가지고온다
            sum = this.sumPoint(uid);
            // 회원의 포인트가 어느 등급에 해당하는지 확인한다
            if(sum <= 99) grade = "bronze";
            else if(100 <= sum && sum <= 499) grade = "silver";
            else if(500 <= sum && sum <= 1999) grade = "gold";
            else if(2000 <= sum && sum <= 9999) grade = "diamond";
            else if(10000 <= sum) grade = "challenger";
            // 회원 등급 업데이트 요청
            user.setUid(uid);
            user.setGrade(grade);
            sqlSession.getMapper(UserDAO.class).updateGrade(user);
            return true;
        }    
        return false;
    }

    @Override
    public long sumPoint(String uid) throws Exception {
        long sum = 0;
        List<Point> list = sqlSession.getMapper(PointDAO.class).inquiry(uid);
        for (Point point : list) {
            sum += point.getPoint();
        }
        return sum;
    }

}
