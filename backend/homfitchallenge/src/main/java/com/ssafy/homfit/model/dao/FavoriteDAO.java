package com.ssafy.homfit.model.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface FavoriteDAO {
    public void init(String uid) throws SQLException;
}
