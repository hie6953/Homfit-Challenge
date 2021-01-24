package com.ssafy.homfit.controller;


import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.service.ChallengeServiceImpl;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

	private static final Logger logger = LoggerFactory.getLogger(ChallengeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	ChallengeServiceImpl challengeService;
	
	/** 테스트*/
	@PostMapping("/test")
	public String testChallenge(@RequestBody HashMap<String, Object> test) {

		return null; 
	}
	
	
	/** 챌린지 등록*/
	@PostMapping
	public ResponseEntity<String> intsertChallenge(@RequestBody Challenge challenge){
		if(challengeService.writeChallenge(challenge)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	//태그처리, 요일처리 필요
	
	
	/** 챌린지 수정*/
	@PutMapping("{no}")
	public ResponseEntity<String> updateChallenge(@RequestBody Challenge challenge){
		if(challengeService.updateChallenge(challenge)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	//
	
	/**진행상태 처리 필요 */
	//12시 지나면 업데이트 시켜서 진행중, 완료중으로 바꿔야함. 
	
	
	
	/** 챌린지 삭제*/
	//delete매핑
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteChallenge(@PathVariable int no){
		if(challengeService.deleteChallenge(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	/** 챌린지  상세보기 */
	@GetMapping("{no}")
	public ResponseEntity<Challenge> detailChallenge(@PathVariable int no){
		//요일, 운동종류해서 같이담아서 줘야해
		return new ResponseEntity<Challenge>(challengeService.detailChallenge(no), HttpStatus.OK);
	}
	
	
	/** 챌린지 전체리스트 반환 */
	@GetMapping("/all")
	public ResponseEntity<List<Challenge>> AllChallengeList(){
		//요일, 운동종류해서 같이담아서 줘야해
		return new ResponseEntity<List<Challenge>>(challengeService.AllChallengeList(), HttpStatus.OK);
	}
	 
}
