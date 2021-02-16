package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Alarm;

public interface AlarmService {
    public void create(Alarm alarm) throws Exception;
    public List<Alarm> getAlarm(String uid) throws Exception;
    public void updateAlarmCheck(int alarm_id) throws Exception;

}
