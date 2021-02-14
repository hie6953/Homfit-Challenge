package com.ssafy.homfit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.homfit.model.Report;
import com.ssafy.homfit.model.service.ReportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/report")
public class ReportController {
    
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    ReportService reportService;

    @ApiOperation(value = "모든 신고기록 가져오기")
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> searchAll(){
        Map<String, Object> resultMap = new HashMap<>();
        String msg = null;
        HttpStatus status = null;
        List<Report> list = null;

        try {
            list = reportService.searchAll();
            if(list.size() > 0){
                msg = SUCCESS;
            } else {
                msg = "EMPTY";
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("신고 전체 검색 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        resultMap.put("msg", msg);
        resultMap.put("list", list);

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
