package com.ssafy.homfit.model.service;

import com.ssafy.homfit.model.User;

public interface UserService {
    public User getUid(String uid) throws Exception;
    public void signup(User user) throws Exception;
}
