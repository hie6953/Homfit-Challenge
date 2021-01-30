package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.User;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    public User getUid(String uid) throws Exception;
    public boolean signup(User user) throws Exception;
	public User login(User user) throws Exception;
	public String findPassword(String email) throws Exception;
    public boolean duplicateEmailCheck(String email) throws Exception;
    public boolean duplicateNickNameCheck(String nick_name) throws Exception;
    public void updateGrade(User user) throws Exception;
    public boolean updateDetail(User user, MultipartFile imgFile) throws Exception;
    public List<User> test() throws Exception;
}
