package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Point;

public interface PointService {

	public List<Point> inquiry(String uid) throws Exception;

	public boolean earn(Point point) throws Exception;
    
    public long sumPoint(List<Point> list) throws Exception;

	//완료시 지급해야할 포인트 계산
	public int calcPoint(int numberOfParticipants, int numberOfDays, boolean everyoneDoPerfect) throws Exception;
}
