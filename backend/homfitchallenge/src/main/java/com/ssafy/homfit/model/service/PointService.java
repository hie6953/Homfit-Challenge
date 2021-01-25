package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Point;

public interface PointService {

	public List<Point> inquiry(String uid) throws Exception;

	public boolean earn(Point point) throws Exception;
    
}
