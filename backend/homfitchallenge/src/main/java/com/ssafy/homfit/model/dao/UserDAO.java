package com.ssafy.homfit.model.dao;

import java.sql.SQLException;

import com.ssafy.homfit.model.User;

public interface UserDAO {
    public User getUid(String uid) throws SQLException;

    public void signup(User user) throws SQLException;

    public User login(User user) throws SQLException;

    public String findPassword(String email) throws SQLException;

	public User duplicateEmailCheck(String email) throws SQLException;

	public void updatePassword(User user) throws SQLException;

	public void updateNickName(User user) throws SQLException;

	public void updateUserImg(User user) throws SQLException;
}
