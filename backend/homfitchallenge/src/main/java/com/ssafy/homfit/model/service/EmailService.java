package com.ssafy.homfit.model.service;

public interface EmailService {
    public void sendSimpleMessage(String to) throws Exception;
    public String getVerifyCode(String email) throws Exception;
}
