package com.ssafy.homfit.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.homfit.model.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    public User getUid(String uid) throws SQLException;

    public void signup(User user) throws SQLException;

    public User login(User user) throws SQLException;

    public String findPassword(String email) throws SQLException;

	public User duplicateEmailCheck(String email) throws SQLException;

	public void updatePassword(User user) throws SQLException;

	public void updateNickName(User user) throws SQLException;

    public void updateUserImg(User user) throws SQLException;
    
    public List<User> test() throws SQLException;

	public void updateGrade(User user) throws SQLException;
}
