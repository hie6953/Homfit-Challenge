package com.ssafy.homfit.controller;

import java.util.LinkedList;
import java.util.List;

import com.ssafy.homfit.model.Feed;
import com.ssafy.homfit.model.LikeVO;
import com.ssafy.homfit.model.service.FeedService;
import com.ssafy.homfit.model.service.S3Service;
import com.ssafy.homfit.util.UploadImg;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Api("FeedController V1")
@RestController
@RequestMapping("/feed")
public class FeedController {
    private static final Logger logger = LoggerFactory.getLogger(FeedController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    S3Service s3service;

    @Autowired
    private FeedService feedService;

    @ApiOperation(value = "피드 생성", notes = "입력된 데이터(maked_uid = 작성자 uid, challenge_id = 챌린지 식별번호, feed_contents = 피드 내용, imgFile = 이미지 파일)를 토대로 피드를 생성")
    @PostMapping(value = "/create")
    public ResponseEntity<String> create(@ModelAttribute Feed feed) {
        HttpStatus status = null;
        String msg = null;

        try {
            feed.setFeed_picture(s3service.uploadImg(feed.getImgFile()));
            if (feedService.create(feed)) {
                msg = SUCCESS;
                status = HttpStatus.ACCEPTED;
            } else {
                msg = FAIL;
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("피드 생성 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }

    @ApiOperation(value = "인증샷 수정", notes = "기존 인증샷을 지우고, 새로운 인증샷을 등록한다")
    @PutMapping(value = "/update/img")
    public ResponseEntity<String> putMethodName(@RequestPart int feed_id, @RequestPart MultipartFile imgFile) {
        HttpStatus status = null;
        String msg = null;

        try {
            if (feedService.updateImg(feed_id, s3service.uploadImg(imgFile))) {
                msg = SUCCESS;
            } else {
                msg = FAIL;
            }
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("피드 이미지 수정 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }

    @ApiOperation(value = "좋아요 수정", notes = "회원이 피드를 좋아할 경우 좋아요 삭제, 아닐경우 좋아요 생성")
    @PutMapping(value = "/like")
    @Transactional
    public ResponseEntity<Integer> updateLikes(@RequestBody LikeVO like) {
        int count = -1;
        HttpStatus status = null;

        try {
            if (like.getUser_liked()) {
                feedService.deleteLikes(like.getUid(), like.getFeed_id());
            } else {
                feedService.createLikes(like.getUid(), like.getFeed_id());
            }
            count = feedService.searchLikeCnt(like.getFeed_id());
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("좋아요 수정 실패 :{}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Integer>(count, status);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Feed>> searchAllFeed() {
        List<Feed> result = null;
        HttpStatus status = null;

        try {
            result = feedService.searchAll();
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("피드 검색 실패 : {}", e);
            result = null;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<Feed>>(result, status);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Feed>> searchCategoryFeed(@RequestParam int category) {
        List<Feed> result = null;
        HttpStatus status = null;

        try {
            result = feedService.searchCategoryFeed(category);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("피드 검색 실패 : {}", e);
            result = null;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<Feed>>(result, status);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Feed>> search(@RequestParam int kind, @RequestParam String keyword) {
        List<Feed> result = null;
        HttpStatus status = null;

        try {
            result = feedService.searchByKeyword(kind, keyword);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("피드 검색 실패 : {}", e);
            result = null;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<Feed>>(result, status);
    }

    @GetMapping("/all/focus/{feed_id}")
    public ResponseEntity<List<Feed>> focusfeed(@PathVariable int feed_id, @RequestParam int challenge_id,
            @RequestParam String uid) {
        List<Feed> result = new LinkedList<Feed>();
        HttpStatus status = null;

        try {
            result.add(feedService.searchByFeedId(feed_id, uid));
            result.addAll(feedService.searchByChallengeNotFeedId(challenge_id, feed_id, uid));
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("특정 피드 목록 검색 실패 : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<Feed>>(result, status);
    }

    @GetMapping("/mypage")
    public ResponseEntity<List<Feed>> mypageFeed(@RequestParam String uid) {
        List<Feed> result = null;
        HttpStatus status = null;

        try {
            result = feedService.searchByUser(uid);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("나의 피드 목록 검색 실패 : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<Feed>>(result, status);
    }

    @GetMapping("/mypage/allFeed")
    public ResponseEntity<List<Feed>> mypageAllFeed(@RequestParam String uid) {
        List<Feed> result = null;
        HttpStatus status = null;

        try {
            result = feedService.searchByUserAll(uid);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("나의 피드 목록 검색 실패 : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<Feed>>(result, status);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFeed(@RequestParam int feed_id){
        String msg = null;
        HttpStatus status = null;

        try {
            feedService.deleteFeed(feed_id);
            msg = SUCCESS;
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("피드 삭제 실패 : {}", e);
            msg = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(msg, status);
    }

    @GetMapping("/search/challenge")
    public ResponseEntity<List<Feed>> searchByChallenge(@RequestParam int challenge_id, @RequestParam String uid) {
        List<Feed> result = null;
        HttpStatus status = null;

        try {
            result = feedService.searchByChallenge(challenge_id, uid);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("나의 피드 목록 검색 실패 : {}", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<Feed>>(result, status);
    }
}
