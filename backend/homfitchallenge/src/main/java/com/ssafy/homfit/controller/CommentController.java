package com.ssafy.homfit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.homfit.model.Comment;
import com.ssafy.homfit.model.CommentVo;
import com.ssafy.homfit.model.service.CommentService;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "CommentController V1")
@RestController
@RequestMapping("/comment")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    CommentService commentService;

    @GetMapping("/search/{feed_id}")
    public ResponseEntity<Map<String, Object>> search(@PathVariable int feed_id){
        Map<String, Object> map = new HashMap<String, Object>();
        HttpStatus status = null;
        String msg = null;
        List<CommentVo> list = null;
        
        try {
            list = commentService.searchByFeedId(feed_id);
            if(list.size() > 0){
                msg = SUCCESS;
            } else{
                msg = FAIL;
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("댓글 검색 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        map.put("msg", msg);
        map.put("list", list);

        return new ResponseEntity<>(map ,status);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Comment comment) {
        String msg = null;
        HttpStatus status = null;

        try {
            if (commentService.create(comment)) {
                msg = SUCCESS;
            } else {
                msg = FAIL;
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("댓글 생성 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }
    
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Comment comment) {
        String msg = null;
        HttpStatus status = null;

        try {
            if (commentService.update(comment)) {
                msg = SUCCESS;
            } else {
                msg = FAIL;
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("댓글 수정 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }
    
    @DeleteMapping("/delete/{feed_id}")
    public ResponseEntity<String> delete(@PathVariable int feed_id) {
        String msg = null;
        HttpStatus status = null;

        try {
            if (commentService.delete(feed_id)) {
                msg = SUCCESS;
            } else {
                msg = FAIL;
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("댓글 수정 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }
    
}
