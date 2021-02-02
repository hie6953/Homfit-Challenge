package com.ssafy.homfit.controller;

import com.ssafy.homfit.model.service.EmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    
    @Autowired
    EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @PostMapping("/verify")
    public ResponseEntity<String> emailConfirm(String email){
        String msg = null;
        HttpStatus status = null;
        try {
            emailService.sendSimpleMessage(email);
            msg = SUCCESS;
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("이메일 인증전송 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }

    @PostMapping("/verifyCode")
    public ResponseEntity<Boolean> verifyCode(@RequestBody String email, @RequestBody String code){
        boolean check = false;
        HttpStatus status = null;

        try {
            if(emailService.getVerifyCode(email).equals(code)){
                check = true;
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("이메일 인증 확인 실패 : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Boolean>(check, status);
    }
}
