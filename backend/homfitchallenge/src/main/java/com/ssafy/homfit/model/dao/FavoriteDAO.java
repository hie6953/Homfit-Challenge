package com.ssafy.homfit.model.dao;

import java.sql.SQLException;

import com.ssafy.homfit.model.Favorite;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface FavoriteDAO {
    public void init(String uid) throws SQLException;
    public void updateFavorite(Favorite favorite) throws SQLException;
}
