package com.ssafy.homfit.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.Tag;
import com.ssafy.homfit.model.service.ChallengeService;
import com.ssafy.homfit.model.service.TagService;

/**
 * @author 황다희
 *
 */
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

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

//	@Resource(name = "redisTemplate")
//	private ListOperations<String, Challenge> listOperation;

	/** 테스트 - redis */
	@GetMapping("/test")
	public ResponseEntity<String> testChallenge() {
		ValueOperations<String, Object> vop = redisTemplate.opsForValue();

//		Challenge ch = new Challenge();
//		ch.setChallenge_title("테스트해보자");
//		ch.setChallenge_img("이미지");
//		ch.setChallenge_contents("내용");

//		
//		String key = "challengeList::SimpleKey";
//		System.out.println(vop.range(key, 0, 10));
//		//Set<String> keys = redisTemplate.keys("*");

		// System.out.println(keys.toString());
		// System.out.println(vop.get("challengeList::SimpleKey []"));
//		Iterator<byte[]> it = keys.iterator();
//
//		while(it.hasNext()){
//
//		    byte[] data = (byte[])it.next();
//
//		    System.out.println(new String(data, 0, data.length));
//		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	/** 챌린지 참여 */
	@PostMapping("/join/{challengeId}")
	@Transactional
	public ResponseEntity<String> joinChallenge(@PathVariable int challengeId, @RequestBody String uid) {
		if (challengeService.joinChallenge(challengeId, uid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	/** 챌린지 참여 삭제 -> 참여자 일때만, 개설자는 챌린지 삭제로 가야함 */
	@DeleteMapping("/join/{challengeId}")
	@Transactional
	public ResponseEntity<String> quitChallenge(@PathVariable int challengeId, @RequestBody String uid) {

		if (challengeService.quitChallenge(challengeId, uid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	/** 챌린지 등록 */
	@PostMapping
	@Transactional
	public ResponseEntity<String> insertChallenge(@RequestBody Challenge challenge) {

		HttpStatus status = HttpStatus.OK;
		String result = FAIL;

		try {
			int kind = challenge.getKind(); // 챌린지 종류 -> 1-운동, 2-식단
			int dayList[] = challenge.getDayList();// 요일
			String tagList[] = challenge.getTagList();// 태그
			int bodyList[] = challenge.getBodyList();// 부위

			if (kind == 0 || dayList == null || dayList.length == 0) { // *종류와 요일은 필수값
				throw new Exception();
			} else {
				if (kind == 1 && (bodyList == null || bodyList.length == 0)) {// *운동이라면 부위선택 필수
					throw new Exception();
				}
				challenge.setDaylist_string(Arrays.toString(dayList));
				challengeService.writeChallenge(challenge);
				int challengeId = challenge.getChallenge_id();

				// 1. 요일처리 - 동적쿼리
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("challenge_id", challengeId);
				map.put("list", IntStream.of(dayList).boxed().collect(Collectors.toList()));
				challengeService.writeChallengeDay(map);

				// 2. 부위처리 (운동일때만)
				if (kind == 1) {
					for (int i = 0; i < bodyList.length; i++) {
						HashMap<String, Integer> map_body = new HashMap<String, Integer>();
						map_body.put("challenge_id", challengeId);
						map_body.put("body_id", bodyList[i]);
						challengeService.writeChallengeBody(map_body);
					}
				}

				// 3. 태그처리 - 입력받았을 경우만
				if (tagList != null && tagList.length != 0) {
					for (int i = 0; i < tagList.length; i++) {
						HashMap<String, Integer> map_tag = new HashMap<String, Integer>();
						map_tag.put("challenge_id", challengeId);
						Tag tag = tagService.selectTag(tagList[i]);
						if (tag == null) { // 태그가 없다면 추가
							Tag addTag = new Tag(tagList[i]);
							tagService.writeTag(addTag);
							map_tag.put("tag_id", addTag.getTag_id());
							tagService.writeTagInChallenge(map_tag); // tag in challenge
							continue;
						}
						map_tag.put("tag_id", tag.getTag_id());
						tagService.writeTagInChallenge(map_tag); //// tag in challenge
					}
				}

				// 4. 개설자는 참여테이블에 바로 insert
				challengeService.joinChallenge(challengeId, challenge.getMake_uid());

				result = Integer.toString(challengeId); // 개설 성공시 challengeID반환
				// 5. 캐시insert, 캐시 참여테이블 insert

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
	@PutMapping
	@Transactional
	public ResponseEntity<String> updateChallenge(@RequestBody Challenge challenge) {

		// 챌린지안에 다 받기.
		// 태그*
		// 부위 *
		// bodylist가 null이면 안바꿔
		// 다 삭제하고 insert?
		// 태그리스트 다 지우고 다시 insert

		if (challengeService.updateChallenge(challenge)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	/** 챌린지 삭제 - 태그테이블은 연쇄삭제 x */
	@DeleteMapping("{challengeId}")
	@Transactional
	public ResponseEntity<String> deleteChallenge(@PathVariable int challengeId) {
		if (challengeService.deleteChallenge(challengeId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	/** 챌린지 상세보기 */
	@GetMapping("{challengeId}")
	public ResponseEntity<Challenge> detailChallenge(@PathVariable int challengeId) {

		Challenge challenge = challengeService.detailChallenge(challengeId);
		if (challenge == null) {
			return new ResponseEntity<Challenge>(challenge, HttpStatus.NO_CONTENT);
		} else {
			// 태그리스트
			Tag tag[] = tagService.selectTagInChallenge(challengeId);
			System.out.println(Arrays.toString(tag));
			if (tag.length != 0) {
				String[] taglist = new String[tag.length];
				for (int i = 0; i < tag.length; i++) {
					taglist[i] = tag[i].getTag_name();
				}
				challenge.setTagList(taglist);
			}
			// 부위리스트
			challenge.setBodyList(challengeService.selectBodyPart(challengeId));
		}
		return new ResponseEntity<Challenge>(challenge, HttpStatus.OK);
	}

	/**
	 * 챌린지 전체리스트 반환 대표이미지, 챌린지 제목, 개설자, 개설자이미지, 인증빈도(월화수목금), 기간, 참여중 인원 페이징, 카테고리,
	 * 요일, 기간, 정렬에 해당하는 데이터 반환. challengeService.AllChallengeList(); -> cache 사용
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Challenge>> AllChallengeList(@RequestParam String[] day, @RequestParam int sort, @RequestParam int periodStart, @RequestParam int periodEnd,
			@RequestParam int category, @RequestParam int page) {

		List<Challenge> list = challengeService.AllChallengeList();
		List<Challenge> c_list = new ArrayList<Challenge>(); // 반환리스트

		// 1. 카테고리별  - 기본값: 0-전체선택 / 해당카테고리 선택.
		if (category != 0) {
			for (Iterator<Challenge> it = list.iterator(); it.hasNext();) {
				Challenge value = it.next();
				if (value.getFit_id() != category) {
					it.remove();
				}
			}
		}

		// 2. 필터 - 기간
		if (periodStart != 0 && periodEnd != 0) {
			for (Iterator<Challenge> it = list.iterator(); it.hasNext();) {
				Challenge value = it.next();
				if (value.getPeriod() < periodStart || value.getPeriod() > periodEnd) {
					it.remove();
				}
			}
		}

		// 3. 필터 - 요일 -> 일단 같은 요일만.
		String param_s = Arrays.toString(day);
		//param_s = param_s.substring(1, param_s.length()-1);
		System.out.println("파람" + " "+param_s);
		if(day != null && day.length > 0) {
			for (int i = 0; i <list.size(); i++) {
				String s = list.get(i).getDaylist_string();
				//s = s.substring(1, s.length()-1);
				if(param_s.equals(s)) {
					System.out.println(s);
				} 
			}
		}
	
		// 4. 페이징 - 기본값: 1 / 무한스크롤 1, 2, 3 (20p 기준)
		int end_page = page * 20;
		int start_page = end_page - 20;
		if (end_page > list.size()) {
			end_page = list.size();
		}
		for (int i = start_page; i < end_page; i++) {
			c_list.add(list.get(i));
		}

		// 5. 정렬 - 기본값: 최신순  / 0:인기순, 1:최신순
		if(sort == 0) {
			Collections.sort(c_list, new Comparator<Challenge>() {
				@Override
				public int compare(Challenge o1, Challenge o2) {
					return o2.getPeople() - o1.getPeople();
				}	
			});
		}
		
		return new ResponseEntity<List<Challenge>>(c_list, HttpStatus.OK);
	}

}
