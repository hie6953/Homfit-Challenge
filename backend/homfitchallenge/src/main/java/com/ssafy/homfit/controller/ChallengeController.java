package com.ssafy.homfit.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.homfit.api.ChallengeRepository;
import com.ssafy.homfit.api.TagRepository;
import com.ssafy.homfit.api.TodayChallengeRepository;
import com.ssafy.homfit.model.Bookmark;
import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.Favorite;
import com.ssafy.homfit.model.Feed;
import com.ssafy.homfit.model.Point;
import com.ssafy.homfit.model.Review;
import com.ssafy.homfit.model.Tag;
import com.ssafy.homfit.model.TodayChallenge;
import com.ssafy.homfit.model.User;
import com.ssafy.homfit.model.UserRate;
import com.ssafy.homfit.model.service.BookmarkService;
import com.ssafy.homfit.model.service.ChallengeService;
import com.ssafy.homfit.model.service.FavoriteService;
import com.ssafy.homfit.model.service.FeedService;
import com.ssafy.homfit.model.service.PointService;
import com.ssafy.homfit.model.service.ReviewService;
import com.ssafy.homfit.model.service.S3Service;
import com.ssafy.homfit.model.service.TagService;
import com.ssafy.homfit.model.service.UserService;

/**
 * @author ?????????
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
	ReviewService reviewService;
	@Autowired
	FavoriteService favoriteService;
	@Autowired
	FeedService feedService;
	@Autowired
	S3Service s3service;
	@Autowired
	PointService PointService;
	@Autowired
	UserService userService;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private ChallengeRepository challengeRepository;
	@Autowired
	private TodayChallengeRepository todayRepository;
	
	@Autowired
	private TagRepository tagRepository;

	/**
	 * ???????????????
	 * 
	 * @throws Exception
	 */
	@GetMapping("/test")
	public ResponseEntity<HashMap<String, Object>> testChallenge() throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();

		
		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
	}

	/**????????? ???????????? ?????? */
	@GetMapping("/popularTag")
	public ResponseEntity<List<Tag>> popularTagList (){
		
		List<Tag> cacheTag = (List<Tag>) tagRepository.findAll();
		
		if(cacheTag.size() == 0) {
			cacheTag = tagService.selectPopularTag();
		}
		
		//????????? ??????
		Collections.sort(cacheTag, new Comparator<Tag>() {
			@Override
			public int compare(Tag o1, Tag o2) {
				return o2.getTag_count() - o1.getTag_count();
			}
		});
		
		return new ResponseEntity<List<Tag>> (cacheTag, HttpStatus.OK);
	}
	
	/** ????????? ?????? ?????? ?????? */
	@GetMapping("/challengeStatus")
	public ResponseEntity<HashMap<String, Object>> challengeStatus(@RequestParam String uid) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Challenge> cacheList = (List<Challenge>) challengeRepository.findAll();
		List<TodayChallenge> todayList = (List<TodayChallenge>) todayRepository.findAll();

		// 1. ???????????????
		// ?????????
		int[] prelist = challengeService.selectPreChallenge(uid);
		int preCnt = prelist.length;
		map.put("preCnt", preCnt);
		// ?????????
		int[] inglist = challengeService.selectIngChallenge(uid);
		int ingCnt = inglist.length;
		map.put("ingCnt", ingCnt);

		// 2. ????????? ?????????
		int todayCnt = 0;
		for (int i = 0; i < inglist.length; i++) {
			if (todayRepository.findById(inglist[i]).isPresent()) {
				todayCnt++;
			}
		}
		map.put("todayCnt", todayCnt);

		// 3. ?????? ???
		int[] endlist = challengeService.selectEndChallenge(uid);
		int endCnt = endlist.length;
		map.put("endCnt", endCnt);

		// 4. ?????? ???
		int makeCnt = 0;
		for (Challenge challenge : cacheList) {
			if (challenge.getMake_uid().equals(uid)) {
				makeCnt++;
			}
		}
		map.put("makeCnt", makeCnt);
		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
	}

	/** ????????? ?????? ????????? */
	@GetMapping("/management/{kind}")
	public ResponseEntity<List<Challenge>> challengeManagement(@RequestParam String uid, @PathVariable int kind) {

		List<Challenge> returnList = new ArrayList<Challenge>();
		List<Challenge> cacheList = (List<Challenge>) challengeRepository.findAll();
		List<TodayChallenge> todayList = (List<TodayChallenge>) todayRepository.findAll();
		// ???????????? ????????? front??????

		if (kind == 0) { // kind - 0 : ?????? ??? ?????????
			// ????????? ??????????????? ???????????? ????????? ?????????
			int[] c_id = challengeService.selectIngChallenge(uid);
			// ??? id?????? ?????? ????????? ???????????? ????????? ??????.
			for (int i = 0; i < c_id.length; i++) {
				if (todayRepository.findById(c_id[i]).isPresent()) {
					returnList.add(challengeRepository.findById(c_id[i]).get());
				}
			}
			// returnList??? ??????
		} else if (kind == 1) {// kind - 1 : ????????? ?????????
			int[] c_id = challengeService.selectPreChallenge(uid);
			for (int i = 0; i < c_id.length; i++) {
				Optional<Challenge> opt = challengeRepository.findById(c_id[i]);
				if (opt.isPresent()) {
					returnList.add(opt.get());
				}
			}
		} else if (kind == 2) { // kind - 2 : ????????? ?????????
			int[] c_id = challengeService.selectIngChallenge(uid);
			for (int i = 0; i < c_id.length; i++) {
				Optional<Challenge> opt = challengeRepository.findById(c_id[i]);
				if (opt.isPresent()) {
					returnList.add(opt.get());
				}
			}
		} else if (kind == 3) {// kind - 3 : ????????? ?????????
			int[] c_id = challengeService.selectEndChallenge(uid);
			for (int i = 0; i < c_id.length; i++) {
				Optional<Challenge> opt = challengeRepository.findById(c_id[i]);
				if (opt.isPresent()) {
					returnList.add(opt.get());
				}
			}
		} else { // kind - 4 : ????????? ?????????
			for (Challenge challenge : cacheList) {
				if (challenge.getMake_uid().equals(uid)) {
					returnList.add(challenge);
				}
			}
		}

		return new ResponseEntity<List<Challenge>>(returnList, HttpStatus.OK);
	}

	/** ????????? ????????? ?????? ??????????????? */
	@GetMapping("/detailManagement/{challengeId}")
	public ResponseEntity<HashMap<String, Object>> detailManagementChallenge(@RequestParam String uid,
			@PathVariable int challengeId) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		// List<Challenge> cacheList = (List<Challenge>) challengeRepository.findAll();
		Optional<Challenge> opt = challengeRepository.findById(challengeId);
		if (!opt.isPresent()) { // ????????? ?????????
			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.NO_CONTENT);
		} else {
			Challenge challenge = opt.get();
			map.put("challenge", challenge);
			int average_rate = challenge.getAverage_rate();
			double total_cnt = challenge.getCertification(); // ?????????????????? ??????
			int user_cnt = feedService.selectUserFeed(uid, challengeId).length;
			double div = (user_cnt / total_cnt);
			int user_rate = (int) Math.round(div * 100); // ?????? ??????????????? ?????????

			// ?????? ?????? ????????? (?????????)
			map.put("user_rate", user_rate);
			// ????????? ???????????? ????????? ?????? ???
			map.put("user_cnt", user_cnt);
			// ????????? ?????? ?????????
			map.put("average_rate", average_rate);
			// ????????? ??? ??????????????? ??????
			map.put("total_cnt", (int) total_cnt);

			// ??????????????? ??????????????? ??????
			Optional<TodayChallenge> today_opt = todayRepository.findById(challengeId);
			if (!today_opt.isPresent()) {
				map.put("today_cnt", 0);
				map.put("imgList", null);
			} else {// ?????? ???????????????
				int today_cnt = challenge.getDay_certify_count();
				map.put("today_cnt", today_cnt);// ???????????? ??????
				List<Feed> imgList = feedService.selectFeedImg(uid, challengeId);// ?????? ????????? ????????? ????????? ????????? ?????????
				map.put("imgList", imgList);
			}

			// ??????????????? ??????
			Review r = reviewService.selectReview(uid, challengeId);
			map.put("review", r);

		}
		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
	}

	/** ????????? ?????? ?????? */
	@PostMapping("/review")
	public ResponseEntity<String> writeReview(@RequestBody Review review) {

		if (reviewService.writeReview(review)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	/** ????????? ?????? */
	@GetMapping("/search")
	public ResponseEntity<List<Challenge>> searchChallenge(@RequestParam String keyword, @RequestParam int kind) {

		List<Challenge> returnList = new ArrayList<Challenge>(); // ???????????????
		List<Challenge> cacheList = (List<Challenge>) challengeRepository.findAll();

		// kind = 0 -> ???????????? ??????
		if (kind == 0) {
			for (Challenge challenge : cacheList) {
				if (challenge.getChallenge_title().contains(keyword)) {
					returnList.add(challenge);
				}
			}
		} else {// kind = 1 -> ???????????? ??????

			Tag tag = tagService.selectTag(keyword);
			if (tag != null) {
				int tagId = tag.getTag_id();
				Tag[] tagList = tagService.selectChallengeInTag(tagId);
				for (int i = 0; i < tagList.length; i++) {
					for (Challenge challenge : cacheList) {
						if (challenge.getChallenge_id() == tagList[i].getChallenge_id()) {
							returnList.add(challenge);
							break;
						}

					}
				}
			}
		}

		// ?????? - ?????????
		Collections.sort(returnList, new Comparator<Challenge>() {
			@Override
			public int compare(Challenge o1, Challenge o2) {
				return o2.getPeople() - o1.getPeople();
			}
		});

		return new ResponseEntity<List<Challenge>>(returnList, HttpStatus.OK);

	}

	/** ?????? ????????? */
	@GetMapping("/recommend/{kind}")
	public ResponseEntity<HashMap<String, Object>> recommendChallenge(@RequestParam String uid,
			@PathVariable int kind) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		Favorite userFavorite = favoriteService.selectUserInfo(uid);
		String fit = userFavorite.getFit_list();
		String body = userFavorite.getBody_list();
		String day = userFavorite.getDay_list();

		// string -> string[]
		String[] fit_string = fit.substring(1, fit.length() - 1).split(",");
		String[] body_string = body.substring(1, body.length() - 1).split(",");
		String[] day_string = day.substring(1, day.length() - 1).split(",");

		// string[] -> int[]
		int[] fit_arr = Arrays.asList(fit_string).stream().mapToInt(Integer::parseInt).toArray();

		List<Challenge> cacheList = (List<Challenge>) challengeRepository.findAll();
		List<Challenge> returnList = new ArrayList<Challenge>();

		// ?????????, ????????? ???????????? ??????
		for (Iterator<Challenge> it = cacheList.iterator(); it.hasNext();) {
			Challenge value = it.next();
			if (value.getCheck_date() == 1 || value.getCheck_date() == 2) {
				it.remove();
			}
		}

		if (kind == 1) { // ?????? ???????????? kind - 1
			for (int i = 0; i < fit_arr.length; i++) {
				for (Challenge challenge : cacheList) {
					if (challenge.getFit_id() == fit_arr[i])
						returnList.add(challenge);
				}
			}
		} else if (kind == 2) { // ?????? ?????? kind - 2
			for (int i = 0; i < body_string.length; i++) {
				for (Challenge challenge : cacheList) {
					if (challenge.getBodylist_string().contains(body_string[i])) {
						returnList.add(challenge);
					}
				}
			}
		} else if (kind == 3) { // ?????? ?????? kind - 3
			for (Challenge challenge : cacheList) {
				String s = challenge.getDaylist_string().replace(" ", "");
				if (day.equals(s))
					returnList.add(challenge);
			}

		} else if (kind == 4) { // ??????, ?????? ?????? kind - 4 //??????

		}

		map.put("returnList", returnList);
		map.put("fit", fit_string);
		map.put("body", body_string);
		map.put("day", day_string);

		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
	}

	/** ????????? ?????? */
	@PostMapping("/join/{challengeId}")
	@Transactional
	public ResponseEntity<String> joinChallenge(@PathVariable int challengeId, @RequestBody User user) {
		String uid = user.getUid();
		if (challengeService.joinChallenge(challengeId, uid)) {
			// ????????? ????????? ?????? people ++;
			Optional<Challenge> opt = challengeRepository.findById(challengeId);
			Challenge ch = opt.get();
			int people = ch.getPeople();
			ch.setPeople(people + 1);
			challengeRepository.save(ch);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	/** ????????? ?????? ?????? -> ????????? ?????????, ???????????? ????????? ????????? ????????? */
	@DeleteMapping("/join/{challengeId}")
	@Transactional
	public ResponseEntity<String> quitChallenge(@PathVariable int challengeId, @RequestParam String uid) {

		if (challengeService.quitChallenge(challengeId, uid)) {
			// ????????? ?????? ????????? ?????? people --;
			Optional<Challenge> opt = challengeRepository.findById(challengeId);
			Challenge ch = opt.get();
			int people = ch.getPeople();
			ch.setPeople(people - 1);
			challengeRepository.save(ch);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	/** ????????? ?????? */
	@PostMapping
	@Transactional
	public ResponseEntity<String> insertChallenge(@ModelAttribute Challenge challenge,
			@ModelAttribute MultipartFile challengeImgFile, @ModelAttribute MultipartFile badImgFile,
			@ModelAttribute MultipartFile goodImgFile) {

		HttpStatus status = HttpStatus.OK;
		String result = FAIL;

		try {
			int kind = challenge.getKind(); // ????????? ?????? -> 1-??????, 2-??????
			int dayList[] = challenge.getDayList();// ??????
			String tagList[] = challenge.getTagList();// ??????
			int bodyList[] = challenge.getBodyList();// ??????

			if (kind == 0 || dayList == null || dayList.length == 0) { // *????????? ????????? ?????????
				throw new Exception();
			} else {
				if (kind == 1 && (bodyList == null || bodyList.length == 0)) {// *??????????????? ???????????? ??????
					throw new Exception();
				}
				challenge.setDaylist_string(Arrays.toString(dayList));
				challenge.setBodylist_string(Arrays.toString(bodyList));

				// 0. ??? ??????????????? ?????? ??? ??????
				int cert_day = certification_day(dayList, challenge.getStart_date(), challenge.getDay_certify_count(),
						challenge.getPeriod());
				challenge.setCertification(cert_day);

				// 1. ???????????? - ???????????? null??? ???????????????
				// null?????? ????????? ?????? url ??????
				challenge.setGood_img(
						"https://homfitimage.s3.ap-northeast-2.amazonaws.com/182165c5919612baffdfcd8091cfe7bc");
				challenge.setBad_img(
						"https://homfitimage.s3.ap-northeast-2.amazonaws.com/14b28a4957875f43d9f3aed789d2d520");
				challenge.setChallenge_img(
						"https://homfitimage.s3.ap-northeast-2.amazonaws.com/d42ee9bafd0856a5a0b6bd481415f399");

				// multipartfile??? ??????????????? ?????? ?????? url??????
				if (goodImgFile != null) {
					challenge.setGood_img(s3service.uploadImg(goodImgFile));
				}
				if (badImgFile != null) {
					challenge.setBad_img(s3service.uploadImg(badImgFile));
				}
				if (challengeImgFile != null) {
					challenge.setChallenge_img(s3service.uploadImg(challengeImgFile));
				}

				challengeService.writeChallenge(challenge); // ??????
				int challengeId = challenge.getChallenge_id();

				// 2. ???????????? - ????????????
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("challenge_id", challengeId);
				map.put("list", IntStream.of(dayList).boxed().collect(Collectors.toList()));
				challengeService.writeChallengeDay(map);

				// 3. ???????????? (???????????????)
				if (kind == 1) {
					for (int i = 0; i < bodyList.length; i++) {
						HashMap<String, Integer> map_body = new HashMap<String, Integer>();
						map_body.put("challenge_id", challengeId);
						map_body.put("body_id", bodyList[i]);
						challengeService.writeChallengeBody(map_body);
					}
				}

				// 4. ???????????? - ??????????????? ?????????
				if (tagList != null && tagList.length != 0) {
					for (int i = 0; i < tagList.length; i++) {
						HashMap<String, Integer> map_tag = new HashMap<String, Integer>();
						map_tag.put("challenge_id", challengeId);
						Tag tag = tagService.selectTag(tagList[i]);
						if (tag == null) { // ????????? ????????? ??????
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

				// 5. ???????????? ?????????????????? ?????? insert
				challengeService.joinChallenge(challengeId, challenge.getMake_uid());
				result = Integer.toString(challengeId); // ?????? ????????? challengeID??????

				// 6. ??????insert
				User makeuser = userService.getUid(challenge.getMake_uid());
				challenge.setUser_img(makeuser.getUser_img()); //?????? ????????? ??????
				challenge.setPeople(1); // ?????????
				challenge.setNick_name(challengeService.selectUserNickname(challengeId)); // ?????????
				challengeRepository.save(challenge);

			}

		} catch (Exception e) {
			logger.error("????????? ?????? ?????? : {}", e);
			result = e.getMessage();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(result, status);
	}

	/** ????????? ?????? */
	@PutMapping("{challengeId}")
	@Transactional
	public ResponseEntity<String> updateChallenge(@PathVariable int challengeId, @ModelAttribute Challenge challenge,
			@ModelAttribute MultipartFile challengeImgFile, @ModelAttribute MultipartFile badImgFile,
			@ModelAttribute MultipartFile goodImgFile) {

		HttpStatus status = HttpStatus.OK;
		String result = FAIL;

		String tagList[] = challenge.getTagList();// ??????
		int challenge_id = challengeId;

		try {
			// 1. ???????????? - ???????????? null??? ???????????????
			if (goodImgFile != null) {
				challenge.setGood_img(s3service.uploadImg(goodImgFile));
			}
			if (badImgFile != null) {
				challenge.setBad_img(s3service.uploadImg(badImgFile));
			}
			if (challengeImgFile != null) {
				challenge.setChallenge_img(s3service.uploadImg(challengeImgFile));
			}

			if (challengeService.updateChallenge(challenge)) {

				// 0. ???????????? ????????? ????????? ??????
				Tag[] check = tagService.selectTagInChallenge(challenge_id);

				if (check.length != 0) { // ????????? ????????? ?????????
					// 1. ?????? ?????? ??????
					if (tagService.deleteTagInChallenge(challenge_id)) {
					} else {
						throw new Exception();
					}
				}
				// 2. ????????? ?????? ??????
				for (int i = 0; i < tagList.length; i++) {
					HashMap<String, Integer> map_tag = new HashMap<String, Integer>();
					map_tag.put("challenge_id", challenge_id);
					Tag tag = tagService.selectTag(tagList[i]);
					if (tag == null) { // ????????? ????????? ??????
						Tag addTag = new Tag(tagList[i]);
						tagService.writeTag(addTag);
						map_tag.put("tag_id", addTag.getTag_id());
						tagService.writeTagInChallenge(map_tag); // tag in challenge
						continue;
					}
					map_tag.put("tag_id", tag.getTag_id());
					tagService.writeTagInChallenge(map_tag); // tag in challenge
				}

				// 3. ?????? ????????????
				challengeRepository.save(challenge);
				result = Integer.toString(challenge_id); // ?????? ????????? challengeID??????

			} else {
				throw new Exception(); // ????????? ????????????
			}
		} catch (Exception e) {
			logger.error("????????? ?????? ?????? : {}", e);
			result = e.getMessage();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<String>(result, status);

	}

	/** ????????? ?????? - ?????????????????? ???????????? x */
	@DeleteMapping("{challengeId}")
	@Transactional
	public ResponseEntity<String> deleteChallenge(@PathVariable int challengeId) {

		if (challengeService.deleteChallenge(challengeId)) {
			challengeRepository.deleteById(challengeId); // ??????????????? ??????
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	/** ?????? ????????? user??? ????????????, user??? ????????? */
	@GetMapping("/user/{challengeId}")
	public ResponseEntity<HashMap<String, String>> userInChallenge(@PathVariable int challengeId,
			@RequestParam String uid) {

		HashMap<String, String> map = new HashMap<String, String>();

		// ?????? ?????? ??????
		String participant = challengeService.selectParticipant(challengeId, uid);
		if (participant != null) {
			map.put("participant", "1"); // ?????? o -> true
		} else {
			map.put("participant", "0"); // false
		}
		// ????????? ????????? ??????
		String bookmark = bookmarkService.selectBookmark(challengeId, uid);
		if (bookmark != null) {
			map.put("bookmark", "1"); // ?????? o -> true
		} else {
			map.put("bookmark", "0"); // false
		}

		return new ResponseEntity<HashMap<String, String>>(map, HttpStatus.OK);
	}

	/** ????????? ?????? ????????? ?????? */
	@GetMapping("/review/{challengeId}/{sort}")
	public ResponseEntity<List<Review>> ReviewList(@PathVariable int challengeId, @PathVariable int sort) {
		List<Review> returnList = reviewService.selectAllReview(challengeId);
		if (returnList.size() == 0) {
			return new ResponseEntity<List<Review>>(returnList, HttpStatus.NO_CONTENT);
		}

		// ?????? - sort = 0: ?????? -> ??????
		if (sort == 1) { // sort = 1:?????????
			Collections.sort(returnList, new Comparator<Review>() {
				@Override
				public int compare(Review o1, Review o2) {
					return o2.getStar_point() - o1.getStar_point();
				}
			});
		} else if (sort == 2) {// sort = 2:?????????
			Collections.sort(returnList, new Comparator<Review>() {
				@Override
				public int compare(Review o1, Review o2) {
					return o1.getStar_point() - o2.getStar_point();
				}
			});
		}

		return new ResponseEntity<List<Review>>(returnList, HttpStatus.OK);
	}

	/** ????????? ???????????? -> ????????? ???????????? ?????? ????????? 3????????? */
	@GetMapping("{challengeId}")
	public ResponseEntity<HashMap<String, Object>> detailChallenge(@PathVariable int challengeId) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Review> reviewList = new ArrayList<Review>();
		Challenge challenge = new Challenge();
		Optional<Challenge> opt = challengeRepository.findById(challengeId); // ???????????? ?????????
		if (!opt.isPresent()) {
			map.put("challenge", challenge);
			map.put("reivew", reviewList);
			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.NO_CONTENT);
		} else {
			challenge = opt.get();
			// ???????????????
			Tag tag[] = tagService.selectTagInChallenge(challengeId);
			if (tag.length != 0) {
				String[] taglist = new String[tag.length];
				for (int i = 0; i < tag.length; i++) {
					taglist[i] = tag[i].getTag_name();
				}
				challenge.setTagList(taglist);
			}
			// ???????????????
			challenge.setBodyList(challengeService.selectBodyPart(challengeId));

			// ????????? ????????? ??? ?????? ?????? 3??? ????????? ??????
			if (challenge.getCheck_date() == 2) {
				reviewList = reviewService.selectThreeReview(challengeId);
			}

			map.put("challenge", challenge);
			map.put("review", reviewList);

			// ?????? ??? ?????? ??????
			List<Review> allReviewList = reviewService.selectAllReview(challengeId);
			int avg_review = 0;
			int size = allReviewList.size();
			if (size != 0) {
				double sum = 0.0;
				for (Review review : allReviewList) {
					sum += review.getStar_point();
				}
				avg_review = (int) Math.round(sum / size);
			}

			map.put("avg_review", avg_review);

		}
		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
	}

	/**
	 * ???????????? ????????? ????????? ??????
	 */
	@GetMapping("/bookmark")
	public ResponseEntity<List<Challenge>> BookmarkCahllengeList(@RequestParam String uid) {

		List<Challenge> returnList = new ArrayList<Challenge>(); // ???????????????

		List<Bookmark> bookmark = bookmarkService.selectAllBookmark(uid);

		if (bookmark.size() != 0) {
			for (Bookmark b : bookmark) {
				Challenge ch = challengeRepository.findById(b.getChallenge_id()).get();
				returnList.add(ch);
			}
		}

		return new ResponseEntity<List<Challenge>>(returnList, HttpStatus.OK);
	}

	/**
	 * ?????? ????????? ????????? ??????
	 */
	@GetMapping("/main")
	public ResponseEntity<List<Challenge>> mainChallengeList(@RequestParam int sort) {

		// ?????? ????????? - ?????? ??????
		if (!redisTemplate.hasKey("challenge")) {
			List<Challenge> list = challengeService.AllChallengeList();
			challengeRepository.saveAll(list);
		}

		// ??????????????? - ?????? ??????
		List<Challenge> cacheList = (List<Challenge>) challengeRepository.findAll();
		List<Challenge> returnList = new ArrayList<Challenge>(); // ?????? ????????? ?????????

		// 0. ?????? - ?????????: ????????? / 0:?????????, 1:??????
		if (sort == 0) {
			Collections.sort(cacheList, new Comparator<Challenge>() {
				@Override
				public int compare(Challenge o1, Challenge o2) {
					return o2.getPeople() - o1.getPeople();
				}
			});
		} else {// ?????????
			Collections.sort(cacheList, new Comparator<Challenge>() {
				@Override
				public int compare(Challenge o1, Challenge o2) {
					return o2.getChallenge_id() - o1.getChallenge_id();
				}
			});

		}

		int size = cacheList.size();
		if (cacheList.size() > 20)
			size = 20;
		for (int i = 0; i < size; i++) {
			returnList.add(cacheList.get(i));
		}

		return new ResponseEntity<List<Challenge>>(returnList, HttpStatus.OK);
	}

	/**
	 * ????????? ??????????????? ??????
	 * 
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Challenge>> AllChallengeListId(@RequestParam String[] day, @RequestParam int sort,
			@RequestParam int periodStart, @RequestParam int periodEnd, @RequestParam int category) {

		// ?????? ????????? - ?????? ??????
		if (!redisTemplate.hasKey("challenge")) {
			List<Challenge> list = challengeService.AllChallengeList();
			challengeRepository.saveAll(list);
		}

		// ??????????????? - ?????? ??????
		List<Challenge> cacheList = (List<Challenge>) challengeRepository.findAll();
		List<Challenge> returnList = new ArrayList<Challenge>(); // ?????? ID ?????????

		// 0. ?????? - ?????????: ????????? / 0:?????????, 1:??????
		if (sort == 0) {
			Collections.sort(cacheList, new Comparator<Challenge>() {
				@Override
				public int compare(Challenge o1, Challenge o2) {
					return o2.getPeople() - o1.getPeople();
				}
			});
		} else {// ?????????
			Collections.sort(cacheList, new Comparator<Challenge>() {
				@Override
				public int compare(Challenge o1, Challenge o2) {
					return o2.getChallenge_id() - o1.getChallenge_id();
				}
			});

		}

		// 1. ??????????????? - ?????????: 0-???????????? / ?????????????????? ??????.
		if (category != 0) {
			for (Iterator<Challenge> it = cacheList.iterator(); it.hasNext();) {
				Challenge value = it.next();
				if (value.getFit_id() != category) {
					it.remove();
				}
			}
		}

		// 2. ?????? - ??????
		if (periodStart != 0 && periodEnd != 0) {
			for (Iterator<Challenge> it = cacheList.iterator(); it.hasNext();) {
				Challenge value = it.next();
				if (value.getPeriod() < periodStart || value.getPeriod() > periodEnd) {
					it.remove();
				}
			}
		}

		// 3. ?????? - ?????? -> ?????? ?????? ?????????.
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

		// 4. ??????????????? ??????
		for (Challenge ch : cacheList) {
			returnList.add(ch);
		}

		return new ResponseEntity<List<Challenge>>(returnList, HttpStatus.OK);
	}

	/** ????????? ?????? 
	 * @throws Exception */
	@GetMapping("/figures/{tap}/{month}")
	public ResponseEntity<HashMap<String, Object>> figures(@RequestParam String uid, @PathVariable int month,
			@PathVariable int tap) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		
		//?????? ??????
		if (tap == 1) { 
			// 1. ???????????????
			List<UserRate> monthList = challengeService.selectMonthChallenge(uid, month);
			map.put("monthList", monthList);

			// 1-1. ????????? ?????? ????????? ??????.
			int current_average_rate = 0;
			int monthCnt = monthList.size();
			double sum = 0.0;
			for (UserRate userRate : monthList) {
				sum += userRate.getAchievement_rate();
			}
			current_average_rate = (int) Math.round((sum / monthCnt)); // ?????? ??????????????? ?????????
			map.put("current_average_rate", current_average_rate);

			// 2. ????????? ????????? ?????? -> ????????? ?????? ??????
			int premonth = month - 1;
			int previous_average_rate = 0;
			if (premonth > 0) { // 1??? ????????? 12?????? x
				List<UserRate> preList = challengeService.selectMonthChallenge(uid, premonth);
				int premonthCnt = preList.size();
				double presum = 0.0;
				for (UserRate userRate : preList) {
					presum += userRate.getAchievement_rate();
				}
				previous_average_rate = (int) Math.round((presum / premonthCnt)); // ?????? ??????????????? ?????????
			}
			map.put("previous_average_rate", previous_average_rate);
		}
		//?????? ?????? -> ??????
		else if (tap == 2) {
			// 3. ????????? ????????? ?????? ???????????? ??? ?????? ??????
			int fitList[] = new int[11];
			int dbfitList[] = challengeService.selectFitId(uid);
			for (int i = 0; i < dbfitList.length; i++) {
				int num = dbfitList[i];
				fitList[num]++;
			}
			map.put("categoryList", fitList);

			// 4. ????????? ????????? ?????? ??? ?????? ??????
			int bodyList[] = new int[10];
			int dbbodyList[] = challengeService.selectBodyId(uid);
			for (int i = 0; i < dbbodyList.length; i++) {
				int num = dbbodyList[i];
				bodyList[num]++;
			}
			map.put("bodyList", bodyList);
		}
		//??????,?????? ??????
		else if (tap == 3) {
			 User user = userService.getUid(uid);
			 List<Favorite> favList = favoriteService.selectUserByAgeSex(user.getAge(), user.getSex());
			 int favoriteBodyList [] = new int[10];
			 int favoriteFitList[] = new int[11];
			 int people = favList.size();
			 
			 for (Favorite favorite : favList) { //string -> int????????? ????????? cnt??????
				String fit = favorite.getFit_list();
				String body = favorite.getBody_list();
				
				//string -> string []
				String[] fit_string = fit.substring(1, fit.length() - 1).split(",");
				String[] body_string = body.substring(1, body.length() - 1).split(",");

				if(fit_string.length != 0) {
					// string[] -> int[]
					int[] fit_arr = Arrays.asList(fit_string).stream().mapToInt(Integer::parseInt).toArray();
					for (int i = 0; i < fit_arr.length; i++) {
						int num = fit_arr[i];
						favoriteFitList[num]++;
					}
				}

				if(body_string.length != 0) {
					int[] body_arr = Arrays.asList(body_string).stream().mapToInt(Integer::parseInt).toArray();
					for (int i = 0; i < body_arr.length; i++) {
						int num = body_arr[i];
						favoriteBodyList[num]++;
					}
				}
				
			}
			 map.put("people", people);
			 map.put("favoriteBodyList", favoriteBodyList);
			 map.put("favoriteFitList", favoriteFitList);
			 map.put("age", user.getAge());
			 map.put("sex", user.getSex());
		}


		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
	}

	/**
	 * ????????? ?????? method
	 * 
	 * @throws ParseException
	 */
	static public int certification_day(int[] dayList, String date, int oneCnt, int period) throws ParseException {

		DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date data = dataFormat.parse(date);
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		cal.setTime(data);

		// int oneCnt = ch.getDay_certify_count();//?????? ????????????
		int certDay = 0; // ???????????? ???
		int startDayNum = cal.get(Calendar.DAY_OF_WEEK) - 1;// ????????? ???????????? ??????(ex.??? =1, ??? = 3)
		if (startDayNum == 0)
			startDayNum = 7; // ????????? 0 -> 7

		if (period <= 7) {
			certDay = dayList.length;
		} else {
			int count = 0;
			// int period = ch.getPeriod();
			for (int i = 0; i < dayList.length; ++i) {
				int day = dayList[i];
				if ((startDayNum <= day && day < period % 7 + startDayNum)
						|| ((startDayNum <= day + 7 && day + 7 < period % 7 + startDayNum))) {
					++count;
				}
			}
			certDay = (period / 7 * dayList.length) + count;
		}
		int certification = certDay * oneCnt;
		return certification;
	}

}
