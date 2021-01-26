package com.ssafy.homfit.model.dao;

import java.sql.SQLException;

public interface FavoriteDAO {
    public void init(String uid) throws SQLException;
}
