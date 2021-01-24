package com.ssafy.homfit.model.dao;

import java.sql.SQLException;

import com.ssafy.homfit.model.User;

public interface UserDAO {
    public User getUid(String uid) throws SQLException;

    public void signup(User user) throws SQLException;
}
