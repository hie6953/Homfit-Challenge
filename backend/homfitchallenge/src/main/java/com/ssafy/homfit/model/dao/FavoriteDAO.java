package com.ssafy.homfit.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.homfit.model.Favorite;
@Mapper
public interface FavoriteDAO {
    public void init(String uid) throws SQLException;
    public void updateFavorite(Favorite favorite) throws SQLException;
    public Boolean isSetting(String uid) throws SQLException;
    
    /**추천 챌린지*/
    public Favorite selectUserInfo (String uid);
    
    /**통계*/
    public List<Favorite> selectUserByAgeSex (@Param("age") int age , @Param("sex") char sex );
}
