package com.ssafy.homfit.controller;

import java.util.HashMap;
import java.util.Map;

import com.ssafy.homfit.model.Bookmark;
import javax.servlet.http.HttpServletRequest;

import com.ssafy.homfit.model.User;
import com.ssafy.homfit.model.service.BookmarkService;
import com.ssafy.homfit.model.service.JwtServiceImpl;
import com.ssafy.homfit.model.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private BookmarkService bookmarkService;

    @ApiOperation(value = "회원 등록", notes = "새로운 회원을 등록한다. 그리고 가입 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestBody @ApiParam(value = "회원 등록시 필요한 회원정보()", required = true) User user) {
        HttpStatus status = null;
        String msg = null;
        try {
            if (userService.signup(user)) {
                msg = SUCCESS;
                status = HttpStatus.OK;
            } else {
                msg = FAIL;
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = FAIL;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(msg, status);
    }

    @ApiOperation(value = "로그인", notes = "access-token과 로그인 결과 메세지를 반환", response = Map.class)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호)", required = true) User user) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            User loginUser = userService.login(user);
            System.out.println(loginUser);
            if (loginUser != null) {
                String token = jwtService.create("uid", loginUser.getUid(), "access-token");
                logger.debug("로그인 토큰정보 : {}", token);
                resultMap.put("access-token", token);
                resultMap.put("message", SUCCESS);
                resultMap.put("uid", loginUser.getUid());
                resultMap.put("nickName", loginUser.getNick_name());
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "비밀번호 찾기 및 변경", notes = "회원 이메일을 통한 비밀번호 찾기 및 변경", response = String.class)
    @PostMapping("/findPw")
    public ResponseEntity<String> findPassword(@RequestBody @ApiParam(value = "비밀번호 찾기에 필요한 회원 이메일정보 및 수정할 비밀번호") User user) {
        HttpStatus status = null;
        String msg = null;
        String uid = null;
        try {
            uid = userService.getByEmail(user.getEmail());
            user.setUid(uid);
            if (userService.updateDetail(user)) {
                msg = SUCCESS;
                status = HttpStatus.ACCEPTED;
            } else {
                msg = FAIL;
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("비밀번호 찾기 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "회원이 입력한 정보대로 회원 정보를 수정한다(바꿀수 있는 정보 : 비밀번호, 닉네임, 사용자 이미지")
    @PutMapping("/updateDetail")
    public ResponseEntity<String> updateDetail(@RequestBody User user) {
        HttpStatus status = null;
        String msg = null;
        try {
            if (userService.updateDetail(user)) {
                msg = SUCCESS;
                status = HttpStatus.ACCEPTED;
            } else {
                msg = FAIL;
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("회원 정보수정 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(msg, status);
    }

    @PutMapping("/updateImg")
    public ResponseEntity<String> updateImg(@RequestPart("imgFile") MultipartFile imgFile,
            @RequestPart("uid") String uid) {
        HttpStatus status = null;
        String msg = null;
        try {
            if (userService.updateImg(uid, imgFile)) {
                msg = SUCCESS;
                status = HttpStatus.ACCEPTED;
            } else {
                msg = FAIL;
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("회원 프로필 사진수정 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(msg, status);
    }

    @ApiOperation(value = "닉네임 중복확인 체크", notes = "해당 닉네임이 중복인지 체크한다 중복시 true 반환")
    @GetMapping(value = "/check/{nickName}")
    public ResponseEntity<Boolean> checkNickName(@PathVariable String nickName) {
        boolean check = true;
        HttpStatus status = null;

        try {
            check = userService.duplicateNickNameCheck(nickName);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("중복확인 실패 : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Boolean>(check, status);
    }

    @ApiOperation(value = "이메일 중복확인 체크", notes = "해당 이메일이 중복인지 체크한다 중복시 true 반환")
    @GetMapping(value = "/checkemail/{email}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable String email) {
        boolean check = true;
        HttpStatus status = null;

        try {
            check = userService.duplicateEmailCheck(email);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("중복확인 실패 : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Boolean>(check, status);
    }

    // @GetMapping("/test")
    // public ResponseEntity<Map<String,Object>> test(@RequestBody User uid){
    // HttpStatus status = HttpStatus.OK;
    // Map<String, Object> resultMap = new HashMap<>();
    // List<User> list = null;
    // User user = null;
    // System.out.println(uid.getUid());
    // try {
    // list = userService.test();
    // user = userService.getUid(uid.getUid());
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // resultMap.put("uid", user.getUid());
    // resultMap.put("list", list);
    // return new ResponseEntity<Map<String,Object>>(resultMap, status);
    // }

    @PostMapping(value = "/bookmark")
    public ResponseEntity<String> addBookMark(@RequestBody Bookmark bookmark) {
        String msg = null;
        HttpStatus status = null;

        try {
            if (bookmarkService.create(bookmark)) {
                msg = SUCCESS;
            } else {
                msg = FAIL;
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("북마크 추가 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(msg, status);
    }

    @DeleteMapping(value = "/bookmark/{uid}/{challenge_id}")
    public ResponseEntity<String> deleteBookMark(@PathVariable String uid, @PathVariable int challenge_id) {
        String msg = null;
        HttpStatus status = null;
        Bookmark bookmark = new Bookmark();
        bookmark.setChallenge_id(challenge_id);
        bookmark.setUid(uid);
        try {
            if (bookmarkService.delete(bookmark)) {
                msg = SUCCESS;
            } else {
                msg = FAIL;
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("북마크 삭제 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }

    @PostMapping
    public ResponseEntity<User> getUserInfo(@RequestBody User uid) {
        User user = null;
        HttpStatus status = null;

        System.out.println(uid);
        try {
            user = userService.getUid(uid.getUid());
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("회원 정보 찾기 실패 : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        System.out.println(user);

        return new ResponseEntity<User>(user, status);
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<Boolean> checkPassword(@RequestBody User user) {
        boolean isRight = false;
        HttpStatus status = null;

        try {
            if (userService.checkPassword(user)) {
                isRight = true;
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("비밀번호 확인 실패 : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Boolean>(isRight, status);
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        System.out.println(">>>>>> " + jwtService.get(req.getHeader("access-token")));
        try {
            resultMap.putAll(jwtService.get(req.getHeader("access-token")));
            status = HttpStatus.ACCEPTED;
        } catch (RuntimeException e) {
            logger.error("정보조회 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
