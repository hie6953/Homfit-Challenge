package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Alarm;
import com.ssafy.homfit.model.dao.AlarmDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public void create(Alarm alarm) throws Exception {
        sqlSession.getMapper(AlarmDAO.class).create(alarm);
    }
    
    @Override
    public List<Alarm> getAlarm(String uid) throws Exception {
        return sqlSession.getMapper(AlarmDAO.class).getAlarm(uid);
    }
    
    @Override
    public void updateAlarmCheck(int alarm_id) throws Exception {
        sqlSession.getMapper(AlarmDAO.class).updateAlarmCheck(alarm_id);
    }
    
}
