package com.ssafy.homfit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.homfit.model.Alarm;
import com.ssafy.homfit.model.Bookmark;
import com.ssafy.homfit.model.Favorite;

import javax.servlet.http.HttpServletRequest;

import com.ssafy.homfit.model.User;
import com.ssafy.homfit.model.service.AlarmService;
import com.ssafy.homfit.model.service.BookmarkService;
import com.ssafy.homfit.model.service.FavoriteService;
import com.ssafy.homfit.model.service.JwtServiceImpl;
import com.ssafy.homfit.model.service.S3Service;
import com.ssafy.homfit.model.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    S3Service s3service;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private AlarmService alarmService;

    @ApiOperation(value = "?????? ??????", notes = "????????? ????????? ????????????. ????????? ?????? ?????? ????????? ?????? 'success' ?????? 'fail' ???????????? ????????????.", response = String.class)
    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestBody @ApiParam(value = "?????? ????????? ????????? ????????????()", required = true) User user) {
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

    @ApiOperation(value = "?????????", notes = "access-token??? ????????? ?????? ???????????? ??????", response = Map.class)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody @ApiParam(value = "????????? ??? ????????? ????????????(?????????, ????????????)", required = true) User user) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            User loginUser = userService.login(user);
            System.out.println(loginUser);
            if (loginUser != null) {
                String token = jwtService.create("uid", loginUser.getUid(), "access-token");
                logger.debug("????????? ???????????? : {}", token);
                resultMap.put("access-token", token);
                resultMap.put("message", SUCCESS);
                resultMap.put("uid", loginUser.getUid());
                resultMap.put("nickName", loginUser.getNick_name());
                resultMap.put("userImg", loginUser.getUser_img());
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("????????? ?????? : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "???????????? ?????? ??? ??????", notes = "?????? ???????????? ?????? ???????????? ?????? ??? ??????", response = String.class)
    @PostMapping("/findPw")
    public ResponseEntity<String> findPassword(@RequestBody @ApiParam(value = "???????????? ????????? ????????? ?????? ??????????????? ??? ????????? ????????????") User user) {
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
            logger.error("???????????? ?????? ?????? : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }

    @ApiOperation(value = "?????? ?????? ??????", notes = "????????? ????????? ???????????? ?????? ????????? ????????????(????????? ?????? ?????? : ????????????, ?????????, ????????? ?????????")
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
            logger.error("?????? ???????????? ?????? : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(msg, status);
    }

    @ApiOperation(value = "?????? ????????? ?????? ??????")
    @PutMapping("/updateImg")
    public ResponseEntity<Map<String, Object>> updateImg(@RequestPart("imgFile") MultipartFile imgFile,
            @RequestPart("uid") String uid) {
        HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap();
        try {
            String imgURL = s3service.uploadImg(imgFile);
            if (userService.updateImg(uid, imgURL)) {
                resultMap.put("msg", SUCCESS);
                resultMap.put("imgurl", imgURL);
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("msg", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("?????? ????????? ???????????? ?????? : {}", e);
            resultMap.put("msg", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "????????? ???????????? ??????", notes = "?????? ???????????? ???????????? ???????????? ????????? true ??????")
    @GetMapping(value = "/check/{nickName}")
    public ResponseEntity<Boolean> checkNickName(@PathVariable String nickName) {
        boolean check = true;
        HttpStatus status = null;

        try {
            check = userService.duplicateNickNameCheck(nickName);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("???????????? ?????? : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Boolean>(check, status);
    }

    @ApiOperation(value = "????????? ???????????? ??????", notes = "?????? ???????????? ???????????? ???????????? ????????? true ??????")
    @GetMapping(value = "/checkemail/{email}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable String email) {
        boolean check = true;
        HttpStatus status = null;

        try {
            check = userService.duplicateEmailCheck(email);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("???????????? ?????? : {}", e);
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

    @ApiOperation(value = "????????? ????????? ??????")
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
            logger.error("????????? ?????? ?????? : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(msg, status);
    }

    @ApiOperation(value = "????????? ?????? ????????? ??????")
    @DeleteMapping(value = "/bookmark/{challenge_id}")
    public ResponseEntity<String> deleteBookMark(@RequestParam String uid, @PathVariable int challenge_id) {
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
            logger.error("????????? ?????? ?????? : {}", e);
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
            logger.error("?????? ?????? ?????? ?????? : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        System.out.println(user);

        return new ResponseEntity<User>(user, status);
    }

    @ApiOperation(value = "?????? ???????????? ??????")
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
            logger.error("???????????? ?????? ?????? : {}", e);
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
            logger.error("???????????? ?????? : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "?????? ????????? ?????? ??????")
    @PutMapping("/updateFavorite")
    public ResponseEntity<String> updateFavorite(@RequestBody Favorite favorite){
        String msg = null;
        HttpStatus status = null;

        try {
            if(favoriteService.updateFavorite(favorite)){
                msg = SUCCESS;
            } else{
                msg = FAIL;
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("?????? ????????? ?????? ?????? : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }

    @ApiOperation(value = "????????? ?????? ????????? ?????? ????????? ?????? ??????")
    @GetMapping("/favorite/isSetting")
    public ResponseEntity<Boolean> isFavoriteSetting(@RequestParam String uid){
        HttpStatus status = null;
        boolean isSetting = false;
        
        try {
            isSetting = favoriteService.isSetting(uid);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("????????? ?????? ?????? ?????? ?????? : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(isSetting ,status);
    }

    @GetMapping(value="/alarm")
    public ResponseEntity<List<Alarm>> getAlarm(@RequestParam String uid) {
        List<Alarm> list = null;
        HttpStatus status = null;

        try {
            list = alarmService.getAlarm(uid);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<Alarm>>(list, status);
    }
    
    @PutMapping(value="/alarm")
    public ResponseEntity<String> updateAlarmCheck(@RequestBody Alarm alarm_id) {
        String msg = null;
        HttpStatus status = null;

        try {
            alarmService.updateAlarmCheck(alarm_id.getAlarm_id());
            msg = SUCCESS;
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }
    
}
