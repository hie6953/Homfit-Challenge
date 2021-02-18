package com.ssafy.homfit.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.Feed;
import com.ssafy.homfit.model.Point;
import com.ssafy.homfit.model.Tag;
import com.ssafy.homfit.model.TodayChallenge;
import com.ssafy.homfit.model.UserRate;
import com.ssafy.homfit.model.service.ChallengeService;
import com.ssafy.homfit.model.service.FeedService;
import com.ssafy.homfit.model.service.PointService;
import com.ssafy.homfit.model.service.TagService;

/**
 * batch작업 진행
 *
 */

@Component
public class ScheduleTask {

	@Autowired
	ChallengeService challengeService;

	@Autowired
	ChallengeRepository challengeRepository;

	@Autowired
	private TodayChallengeRepository todayRepository;

	@Autowired
	private FeedService feedService;

	@Autowired
	private PointService PointService;

	@Autowired
	TagService tagService;

	@Autowired
	private TagRepository tagRepository;

	/**
	 * 매일 밤 12시마다 할 batch 작업
	 * 
	 * @throws Exception
	 */
	@Scheduled(cron = "0 0 0 * * ? ", zone = "Asia/Seoul")
	private void publ() throws Exception {

		 //1. 진행중 챌린지별 평균 달성률 업데이트
		 List<Challenge> challengelist = challengeService.AllChallengeList();
		 for (Challenge challenge : challengelist) {
		 	if (challenge.getCheck_date() == 1) { // 진행중 챌린지 id 가져와서 하나씩 다 업데이트
		 		int challenge_id = challenge.getChallenge_id();
		 		List<Feed> feedList = feedService.searchByChallenge(challenge_id);
		 		int size = feedList.size();
		 		Challenge c = challengeRepository.findById(challenge_id).get();
		 		int people = c.getPeople();
		 		int cerCnt = c.getCertification();
		 		double totalCnt = people * cerCnt;
		 		int average_rate = (int) Math.round((size / totalCnt) * 100);
		 		challengeService.updateAverageRate(challenge_id, average_rate);
		 	}
		 }

		 // 2. 챌린지 시작전 -> 진행중 변경
		 int[] ingId = challengeService.selectBefoToIng(); // ->체크데이트가 0이면서 시작날짜가 오늘인 애들뽑고
		 if (ingId.length != 0) {
		 	Map<String, Object> map = new HashMap<String, Object>();
		 	map.put("list", ingId);
		 	map.put("checkDate", 1);
		 	challengeService.updateChallengeStatus(map);
		 }

		 // 3. 진행중 -> 완료로 변경
		 int[] compId = challengeService.selectIngToComp(); // -> 체크데이트가 1이면서 끝날짜가 "오늘-1" -> select
		 if (compId.length != 0) {
		 	Map<String, Object> map2 = new HashMap<String, Object>();
		 	map2.put("list", compId);
		 	map2.put("checkDate", 2);
		 	challengeService.updateChallengeStatus(map2);
		 }

		 // cache 전체 챌린지 리스트 업데이트
		 challengeRepository.deleteAll(); // 처음 등록된 캐시 다 지움
		 List<Challenge> reloadList = challengeService.AllChallengeList();
		 challengeRepository.saveAll(reloadList);
		
		
		 // 4. 완료 후 할 것
		 //요일관련
		 // 4-1. 오늘 요일에 해당하는 챌린지 cache에 업데이트
		 Calendar cal = new GregorianCalendar(Locale.KOREA);
		 DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		 Date data = dataFormat.parse(challengeService.selectDate()); //12시기준 날짜
		 cal.setTime(data);
		
		 //db테이블 대로 세팅
		 int today = cal.get(Calendar.DAY_OF_WEEK) -1;
		 if(today == 0) today = 7;
		
		 //오늘의 챌린지 업데이트!
		 todayRepository.deleteAll(); // (기존꺼 지우고)
		 List<TodayChallenge> list = challengeService.selectTodayChallenge(today);
		 todayRepository.saveAll(list);

		 // 4-2. 완료된 챌린지 유저별 개인 달성률 insert, + 포인트적립
		 int yesterday = today-1;
		
		 for (Challenge challenge : challengelist) { // 오늘 기준, 어제 완료된 챌린지 유저 달성률 등록
		 	if (challenge.getCheck_date() == 2 && challenge.getEnd_date().equals(yesterday)) {
		 		boolean everyoneDoPerfect = true; // 모두의 100프로 여부
		 		int compChallengeId = challenge.getChallenge_id();
		 		double certification = challenge.getCertification();
		 		String c_title = challenge.getChallenge_title();
		 		String c_endDate = challenge.getEnd_date();
		 		int period = challenge.getPeriod();
		 		String user[] = challengeService.selectUidByChallenge(compChallengeId);
		 		List<String> pointUser = new ArrayList<String>();
		 		for (int i = 0; i < user.length; i++) {
		 			int size = feedService.selectUserFeed(user[i], compChallengeId).length;
		 			// 유저 달성률 평균
		 			int avg = (int) Math.round((size / certification) * 100);
		 			challengeService.insertUserRate(new UserRate(user[i], compChallengeId, avg, c_endDate, c_title));
		 			if (avg == 100) {
		 				pointUser.add(user[i]); // 달성률 100프로시
		 			} else {// 한명이라도 100프로가 아니면 false
		 				everyoneDoPerfect = false;
		 			}
		 		}
		 		// 포인트 적립
		 		if (pointUser.size() != 0) { // 포인트 받을 유저가 있을 경우만
		 			int point = PointService.calcPoint(user.length, period, everyoneDoPerfect);
		 			Point p = new Point();
		 			p.setPoint(point);
		 			p.setTitle("챌린지 100% 달성");
		 			String content = "";
		 			if (everyoneDoPerfect) {
		 				content = "참가자 모두 100% 달성";
		 			} else {
		 				content = "참가자 개인 100% 달성";
		 			}
		 			p.setContent(content);
		 			for (String pointUid : pointUser) {
		 				p.setUid(pointUid);
		 				PointService.earn(p);
		 			}
		 		}
		 	}
		 }

	}

	// 매일 1시간마다 배치작업 - 인기태그
	@Scheduled(cron = "0 0 0/1 * * ? ", zone = "Asia/Seoul")
	private void pup2() {
		List<Tag> list = tagService.selectPopularTag();
		tagRepository.deleteAll(); // 한번 다 지우고
		tagRepository.saveAll(list); // 태그 새로 저장
	}
	
	//혹시 모를 대비책으로 캐시 주기적 업데이트
//	@Scheduled(cron = "0 0 0/6 * * ? ", zone = "Asia/Seoul")
//	private void pup3() {
//		
//		challengeRepository.deleteAll(); // 처음 등록된 캐시 다 지움
//		List<Challenge> reloadList = challengeService.AllChallengeList();
//		challengeRepository.saveAll(reloadList);
//	}
	
	

}
