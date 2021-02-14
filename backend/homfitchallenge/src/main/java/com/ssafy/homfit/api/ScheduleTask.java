package com.ssafy.homfit.api;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.TodayChallenge;
import com.ssafy.homfit.model.service.ChallengeService;

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

	/**
	 * 매일 밤 12시마다 할 batch 작업
	 */
	@Scheduled(cron = "0 0 0 * * ? ", zone = "Asia/Seoul")
	private void publ() {
		// 1. 진행중 챌린지별 평균 달성률 업데이트
		
		// 2. 챌린지 시작전 -> 진행중 변경
		int[] ingId = challengeService.selectBefoToIng(); // ->체크데이트가 0이면서 시작날짜가 오늘인 애들뽑고
		if (ingId.length != 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", ingId);
			map.put("checkDate", 1);
		}

		// 3. 진행중 -> 완료로 변경
		int[] compId = challengeService.selectIngToComp(); // -> 체크데이트가 1이면서 끝날짜가 오늘-1 -> select
		if (compId.length != 0) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("list", compId);
			map2.put("checkDate", 2);
		}

		// 4. 완료 후 할 것
		// 4-1. 오늘 요일에 해당하는 챌린지 cache에 업데이트 
		Calendar cal = Calendar.getInstance();
		int date = cal.get(Calendar.DAY_OF_WEEK) - 1; // 요일 db테이블에 맞게 파싱
		if (date == 0) date = 7;
		todayRepository.deleteAll(); //(기존꺼 지우고)
		List<TodayChallenge> list = challengeService.selectTodayChallenge(date);
		todayRepository.saveAll(list);
		
		// 4-2. 완료된 챌린지 유저별 개인 달성률 insert
		
		// 4-3. 포인트 적립
		
		// 4-4. cache 챌린지 리스트 업데이트
		challengeRepository.deleteAll(); // 처음 등록된 캐시 다 지움
		List<Challenge> challengelist = challengeService.AllChallengeList();
		challengeRepository.saveAll(challengelist);

	}

}
