package com.ssafy.homfit.model.dao;

import java.sql.SQLException;

import com.ssafy.homfit.model.Feed;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedDAO {

	public void create(Feed feed) throws SQLException;
    
}
