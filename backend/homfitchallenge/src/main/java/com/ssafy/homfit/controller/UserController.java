package com.ssafy.homfit.controller;

import java.util.HashMap;
import java.util.Map;

import com.ssafy.homfit.model.User;
import com.ssafy.homfit.model.service.JwtServiceImpl;
import com.ssafy.homfit.model.service.UserService;
import com.ssafy.homfit.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("UserController V1")
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "회원 등록", notes = "새로운 회원을 등록한다. 그리고 가입 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PostMapping
    public ResponseEntity<String> signup(
            @RequestBody @ApiParam(value = "회원 등록시 필요한 회원정보()", required = true) User user) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        String uidToken = null;
        String msg = null;
        try {
            while(true){
                uidToken = Util.generateToken();
                if(userService.getUid(uidToken) == null) break;
            }
            userService.signup(user);

            msg = SUCCESS;
            status = HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            msg = FAIL;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(msg, status);
    }
}
