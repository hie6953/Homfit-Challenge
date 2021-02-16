package com.ssafy.homfit.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.homfit.model.Alarm;

public interface AlarmDAO {
    public void create(Alarm alarm) throws SQLException;

    public List<Alarm> getAlarm(String uid) throws SQLException;

    public void updateAlarmCheck(int alarm_id) throws SQLException;
}
