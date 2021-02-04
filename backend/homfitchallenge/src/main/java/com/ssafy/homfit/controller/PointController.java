package com.ssafy.homfit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.homfit.model.Point;
import com.ssafy.homfit.model.service.PointService;

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

@Api("PointController V1")
@RestController
@RequestMapping("/point")
public class PointController {
    private static final Logger logger = LoggerFactory.getLogger(PointController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private PointService pointService;

    @ApiOperation(value = "포인트 조회", notes = "해당 회원의 포인트를 조회한다")
    @PostMapping("/inquiry")
    public ResponseEntity<Map<String, Object>> inquery(@RequestBody String uid){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        String msg = null;
        List<Point> list = null;
        try {
            list = pointService.inquiry(uid);
            if(list != null){
                msg = SUCCESS;
                status = HttpStatus.OK;
            } else{
                msg = "empty";
                status = HttpStatus.OK;
            }
            resultMap.put("list", list);
            resultMap.put("msg", msg);
            resultMap.put("sum", pointService.sumPoint(list));
        } catch (Exception e) {
            logger.error("포인트 조회 실패 : {}", e);
            msg = e.getMessage();
            resultMap.put("msg", msg);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String,Object>>(resultMap, status);
    }

    @PostMapping("/earned")
    public ResponseEntity<String> earn(@RequestBody Point point){
        HttpStatus status = null;
        String msg = null;

        try {
            if(pointService.earn(point)){
                msg = SUCCESS;
                status = HttpStatus.OK;
            } else{
                msg = FAIL;
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            logger.error("포인트 정보 삽입 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }
}
