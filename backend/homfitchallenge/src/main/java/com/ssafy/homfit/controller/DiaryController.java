package com.ssafy.homfit.controller;

import com.ssafy.homfit.model.Diary;
import com.ssafy.homfit.model.service.DiaryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@Api("DiaryController V1")
@RestController
@RequestMapping("/diary")
public class DiaryController {
    private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private DiaryService diaryService;

    @ApiOperation(value = "기록 생성", notes = "해당 일자와 사용자 식별번호를 이용해 데이터를 저장한다")
    @PostMapping(value = "/create")
    public ResponseEntity<String> create(@RequestBody Diary diary) {
        String msg = null;
        HttpStatus status = null;

        try {
            if (diaryService.create(diary)) {
                msg = SUCCESS;
                status = HttpStatus.ACCEPTED;
            } else {
                msg = FAIL;
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("기록 생성 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }

    @ApiOperation(value = "일기 내용 수정", notes = "해당 사용자의 일기 데이터 수정")
    @PutMapping(value = "/update")
    public ResponseEntity<String> update(@RequestBody Diary diary) {
        String msg = null;
        HttpStatus status = null;
        
        try {
            msg = diaryService.update(diary);
        } catch (Exception e) {
            logger.error("기록 수정 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }

    @ApiOperation(value = "기록 내용 검색", notes = "사용자의 해당일자 기록 데이터를 반환")
    @PostMapping(value="/search")
    public ResponseEntity<Diary> search(@RequestBody Diary diary) {
        Diary data = null;
        HttpStatus status = null;
        
        try {
            data = diaryService.search(diary);
        } catch (Exception e) {
            logger.error("기록 검색 실패 : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Diary>(data, status);
    }
    
}
