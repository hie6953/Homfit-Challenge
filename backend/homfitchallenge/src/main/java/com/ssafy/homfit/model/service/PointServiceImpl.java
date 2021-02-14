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

    //특정 회원의 포인트 정보 가져오기
    @Override
    public List<Point> inquiry(String uid) throws Exception {
        List<Point> list = sqlSession.getMapper(PointDAO.class).inquiry(uid);
        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    //포인트 지급 및 포인트에 따른 등급 수정
    @Override    
    public boolean earn(Point point) throws Exception {
        if(point.getPoint() > 0 && sqlSession.getMapper(UserDAO.class).getUid(point.getUid()) != null){
            
            sqlSession.getMapper(PointDAO.class).earn(point);
            long sum = 0;
            String grade = null;
            String uid = point.getUid();
            User user = new User();
            // 회원의 모든 포인트의 합계를 가지고온다
            sum = this.sumPoint(this.inquiry(uid));
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

    //가져온 포인트의 합계 계산
    @Override
    public long sumPoint(List<Point> list) throws Exception {
        long sum = 0;
        for (Point point : list) {
            sum += point.getPoint();
        }
        return sum;
    }

    //포인트 지급을 위한 계산
    //numberOfParticipants : 챌린지 총 참여자수, numberOfDays : 챌린지 총 일수, everyoneDoPerfect : 모든 인원 달성 여부
    @Override
    public int calcPoint(int numberOfParticipants, int numberOfDays, boolean everyoneDoPerfect) throws Exception {
        int point = Math.round(numberOfDays * numberOfDays * 5 / 9);
        if(everyoneDoPerfect){
            if(numberOfParticipants >= 10 && numberOfParticipants < 50) point *= 1.5;
            else if(numberOfParticipants >= 50 && numberOfParticipants < 100) point *= 2;
            else if(numberOfParticipants >= 100 && numberOfParticipants < 500) point *= 5;
            else if(numberOfParticipants >= 505) point *= 10;
        }
        
        return point;
    }
}
