package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Point;
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
            return true;
        }    
        return false;
    }

}
