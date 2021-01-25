package com.ssafy.homfit.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.Challenge_day;
import com.ssafy.homfit.model.Tag;
import com.ssafy.homfit.model.service.ChallengeService;
import com.ssafy.homfit.model.service.TagService;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

	private static final Logger logger = LoggerFactory.getLogger(ChallengeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	ChallengeService challengeService;
	
	@Autowired
	TagService tagService;

	
	// 이미지 따로 빼야함

	/** 테스트 */
	@PostMapping("/test")
	public String testChallenge(@RequestBody Challenge challenge) throws JsonMappingException, JsonProcessingException {

		System.out.println(challenge);
		return null;
	}

	/** 챌린지 등록 */
	@PostMapping
	@Transactional
	public ResponseEntity<String> intsertChallenge(@RequestBody Challenge challenge) {

		HttpStatus status = HttpStatus.OK;
		String result = SUCCESS;
		try {
			if (challengeService.writeChallenge(challenge)) {
				
				// 요일처리
				int c_id = challenge.getChallenge_id();
				int day_list[] = challenge.getDayList();
				if (day_list == null || day_list.length == 0) {
					result = "day_list is null or length == 0";
					throw new Exception();
				} else {
					for (int i = 0; i < day_list.length; i++) {
						challengeService.writeChallengeDay(new Challenge_day(c_id, day_list[i]));
					}
				}
				
				//태그처리
				String tag_list[] = challenge.getTagList();
				//태그 입력 받았다면 태그 테이블과 비교해서 넣음 
				if(tag_list != null || tag_list.length != 0) { 
					String[] db_tag_list = tagService.AllTagList();
					 for (int i = 0; i < tag_list.length; i++) {
						for (int j = 0; j < db_tag_list.length; j++) {
							if(!tag_list[i].equals(db_tag_list[j])) {
								tagService.writeTag(tag_list[i]);
								break;
							} 
						}
					}
				}

//				//태그 다 긁어와. 입력된 태그가 있는지 봐. 없다면 태그에 인서트. 챌린지별 태그에 인서트.둘중에 하나 오류나면 exception

			}
		} catch (Exception e) {
			logger.error("챌린지 등록 실패 : {}", e);
			result = e.getMessage();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<String>(result, status);
	}

	/** 챌린지 수정 */
	@PutMapping("{no}")
	public ResponseEntity<String> updateChallenge(@RequestBody Challenge challenge) {
		if (challengeService.updateChallenge(challenge)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	//

	/** 진행상태 처리 필요 */
	// 12시 지나면 업데이트 시켜서 진행중, 완료중으로 바꿔야함.

	
	/** 챌린지 삭제 */
	// delete매핑
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteChallenge(@PathVariable int no) {
		if (challengeService.deleteChallenge(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	/** 챌린지 상세보기 */
	@GetMapping("{no}")
	public ResponseEntity<Challenge> detailChallenge(@PathVariable int no) {
		// 요일, 운동종류해서 같이담아서 줘야해
		return new ResponseEntity<Challenge>(challengeService.detailChallenge(no), HttpStatus.OK);
	}

	/** 챌린지 전체리스트 반환 */
	@GetMapping("/all")
	public ResponseEntity<List<Challenge>> AllChallengeList() {
		// 요일, 운동종류해서 같이담아서 줘야해
		return new ResponseEntity<List<Challenge>>(challengeService.AllChallengeList(), HttpStatus.OK);
	}

}
