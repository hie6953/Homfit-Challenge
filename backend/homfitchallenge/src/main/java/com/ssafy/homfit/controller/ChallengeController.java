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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
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

import com.ssafy.homfit.api.ChallengeRepository;
import com.ssafy.homfit.model.Bookmark;
import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.Tag;
import com.ssafy.homfit.model.User;
import com.ssafy.homfit.model.service.BookmarkService;
import com.ssafy.homfit.model.service.ChallengeService;
import com.ssafy.homfit.model.service.TagService;

import io.lettuce.core.dynamic.annotation.Param;

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
	BookmarkService bookmarkService;

	@Autowired
	TagService tagService;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private ChallengeRepository challengeRepository;

	/** 테스트코드 */
	@GetMapping("/test")
	public ResponseEntity<String> testChallenge() {
//		Challenge ch = challengeService.detailChallenge(176);
//		challengeRepository.save(ch);

		// System.out.println(challengeRepository.findAll());

//		List<Challenge> clist = (List<Challenge>) challengeRepository.findAll();
//		System.out.println(clist.toString());
//		Optional<Challenge> opt =  challengeRepository.findById(175);
//		Challenge ch = opt.get();
//		System.out.println(ch);
//		ch.setChallenge_title("테스트로바꿀게요!");
//		challengeRepository.save(ch);
//		clist =  (List<Challenge>) challengeRepository.findAll();
//		System.out.println(clist.toString());

		return new ResponseEntity<String>("hi", HttpStatus.NO_CONTENT);
	}
	
	
	/**챌린지 검색 */
	@GetMapping("/search")
	public ResponseEntity<List<Challenge>> searchChallenge(@RequestParam String keyword, @RequestParam int kind){
		
		List<Challenge> returnList = new ArrayList<Challenge>(); // 반환리스트
		List<Challenge> cacheList = (List<Challenge>) challengeRepository.findAll();
		
		//kind = 0 -> 제목기반 검색
		if(kind == 0) {
			for (Challenge challenge : cacheList) {
				if(challenge.getChallenge_title().contains(keyword)) {
					returnList.add(challenge);
				}
			}
		}else {//kind = 1 -> 태그기반 검색
			
			Tag tag = tagService.selectTag(keyword);
			if(tag != null) {
				int tagId = tag.getTag_id();
				Tag[] tagList = tagService.selectChallengeInTag(tagId);
				for (int i = 0; i < tagList.length; i++) {
					for (Challenge challenge : cacheList) {
						if(challenge.getChallenge_id() == tagList[i].getChallenge_id()) {
							returnList.add(challenge);
							break;
						}
							
					}
				}
			}
		}
			
		//정렬 - 인기순
		Collections.sort(returnList, new Comparator<Challenge>() {
			@Override
			public int compare(Challenge o1, Challenge o2) {
				return o2.getPeople() - o1.getPeople();
			}
		});
		
		return new ResponseEntity<List<Challenge>>(returnList, HttpStatus.OK);
		
	}
	
	/** 챌린지 참여 */
	@PostMapping("/join/{challengeId}")
	@Transactional
	public ResponseEntity<String> joinChallenge(@PathVariable int challengeId, @RequestBody User user) {
		String uid = user.getUid();
		if (challengeService.joinChallenge(challengeId, uid)) {
			// 챌린지 참여시 캐시 people ++;
			Optional<Challenge> opt = challengeRepository.findById(challengeId);
			Challenge ch = opt.get();
			int people = ch.getPeople();
			ch.setPeople(people + 1);
			challengeRepository.save(ch);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	/** 챌린지 참여 삭제 -> 참여자 일때만, 개설자는 챌린지 삭제로 가야함 */
	@DeleteMapping("/join/{challengeId}")
	@Transactional
	public ResponseEntity<String> quitChallenge(@PathVariable int challengeId, @RequestBody User user) {
		String uid = user.getUid();
		if (challengeService.quitChallenge(challengeId, uid)) {
			// 챌린지 참여 삭제시 캐시 people --;
			Optional<Challenge> opt = challengeRepository.findById(challengeId);
			Challenge ch = opt.get();
			int people = ch.getPeople();
			ch.setPeople(people - 1);
			challengeRepository.save(ch);
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
						tagService.writeTagInChallenge(map_tag); // tag in challenge
					}
				}

				// 4. 개설자는 참여테이블에 바로 insert
				challengeService.joinChallenge(challengeId, challenge.getMake_uid());
				result = Integer.toString(challengeId); // 개설 성공시 challengeID반환

				// 5. 캐시insert
				challengeRepository.save(challenge);

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
			challengeRepository.deleteById(challengeId); // 캐시에서도 삭제
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	/** 해당 챌린지 user의 참여여부, user의 북마크 */
	@GetMapping("/user/{challengeId}")
	public ResponseEntity<HashMap<String, String>> userInChallenge(@PathVariable int challengeId,
			@RequestBody User user) {
		String uid = user.getUid();
		HashMap<String, String> map = new HashMap<String, String>();

		// 참여 여부 확인
		String  participant= challengeService.selectParticipant(challengeId, uid);
		if(participant!=null) {
			map.put("participant", "1"); //참여 o -> true
		}else {
			map.put("participant", "0"); //false
		}
		// 북마크 했는지 확인
		String bookmark = bookmarkService.selectBookmark(challengeId, uid);
		if(bookmark!=null) {
			map.put("bookmark", "1"); //참여 o -> true
		}else {
			map.put("bookmark", "0"); //false
		}
		
		return new ResponseEntity<HashMap<String, String>>(map, HttpStatus.OK);
	}

	/** 챌린지 상세보기 */
	@GetMapping("{challengeId}")
	public ResponseEntity<Challenge> detailChallenge(@PathVariable int challengeId) {

		// 캐시에서 가져옴
		Optional<Challenge> opt = challengeRepository.findById(challengeId);
		Challenge challenge = opt.get();
		if (challenge == null) {
			return new ResponseEntity<Challenge>(challenge, HttpStatus.NO_CONTENT);
		} else {
			// 태그리스트
			Tag tag[] = tagService.selectTagInChallenge(challengeId);
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
	 * 북마크한 챌린지 리스트 반환
	 */
	@GetMapping("/bookmark")
	public ResponseEntity<List<Challenge>> BookmarkCahllengeList(@RequestBody User user){
		String uid = user.getUid();
		List<Challenge> returnList = new ArrayList<Challenge>(); // 반환리스트
		
		List<Bookmark> bookmark =  bookmarkService.selectAllBookmark(uid);
		System.out.println(bookmark.toString());
		
		if(bookmark.size()!=0) {
			for (Bookmark b : bookmark) {
				Challenge ch =  challengeRepository.findById(b.getChallenge_id()).get();
				returnList.add(ch);
			}
		}
	
		return new ResponseEntity<List<Challenge>>(returnList, HttpStatus.OK);
	}

	/**
	 * 챌린지 전체리스트 반환 
	 * 
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Challenge>> AllChallengeList(@RequestParam String[] day, @RequestParam int sort,
			@RequestParam int periodStart, @RequestParam int periodEnd, @RequestParam int category,
			@RequestParam int page) {

		// 캐시 없으면 - 캐시 생성
		if (!redisTemplate.hasKey("challenge")) {
			List<Challenge> list = challengeService.AllChallengeList();
			challengeRepository.saveAll(list);
		}

		// 캐시있다면 - 캐시 뿌림
		List<Challenge> cacheList = (List<Challenge>) challengeRepository.findAll();
		List<Challenge> returnList = new ArrayList<Challenge>(); // 반환리스트

		// 0. 정렬 - 기본값: 최신순 / 0:인기순, 1:최신
		if (sort == 0) {
			Collections.sort(cacheList, new Comparator<Challenge>() {
				@Override
				public int compare(Challenge o1, Challenge o2) {
					return o2.getPeople() - o1.getPeople();
				}
			});
		} else {// 최신순
			Collections.sort(cacheList, new Comparator<Challenge>() {
				@Override
				public int compare(Challenge o1, Challenge o2) {
					return o2.getChallenge_id() - o1.getChallenge_id();
				}
			});

		}

		// 1. 카테고리별 - 기본값: 0-전체선택 / 해당카테고리 선택.
		if (category != 0) {
			for (Iterator<Challenge> it = cacheList.iterator(); it.hasNext();) {
				Challenge value = it.next();
				if (value.getFit_id() != category) {
					it.remove();
				}
			}
		}

		// 2. 필터 - 기간
		if (periodStart != 0 && periodEnd != 0) {
			for (Iterator<Challenge> it = cacheList.iterator(); it.hasNext();) {
				Challenge value = it.next();
				if (value.getPeriod() < periodStart || value.getPeriod() > periodEnd) {
					it.remove();
				}
			}
		}

		// 3. 필터 - 요일 -> 일단 같은 요일만.2^7
		String param_s = Arrays.toString(day);
		if (day != null && day.length > 0) {
			for (Iterator<Challenge> it = cacheList.iterator(); it.hasNext();) {
				Challenge value = it.next();
				String s = value.getDaylist_string();
				if (!param_s.equals(s)) {
					it.remove();
				}
			}
		}

		// 4. 페이징 - 기본값: 1 / 무한스크롤 1, 2, 3 (20p 기준)
		int end_page = page * 20;
		int start_page = end_page - 20;
		if (end_page > cacheList.size()) {
			end_page = cacheList.size();
		}
		for (int i = start_page; i < end_page; i++) {
			returnList.add((Challenge) cacheList.get(i));
		}
		return new ResponseEntity<List<Challenge>>(returnList, HttpStatus.OK);
	}
	
	

}
