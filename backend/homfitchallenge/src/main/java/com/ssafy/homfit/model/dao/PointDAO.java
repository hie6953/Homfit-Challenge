package com.ssafy.homfit.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.homfit.model.Point;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointDAO {

	public List<Point> inquiry(String uid) throws SQLException;

	public void earn(Point point) throws SQLException;
    
}
