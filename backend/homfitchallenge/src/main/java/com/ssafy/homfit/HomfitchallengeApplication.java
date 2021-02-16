package com.ssafy.homfit;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ssafy.homfit.api.ChallengeRepository;
import com.ssafy.homfit.api.TagRepository;
import com.ssafy.homfit.api.TodayChallengeRepository;
import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.Feed;
import com.ssafy.homfit.model.Tag;
import com.ssafy.homfit.model.TodayChallenge;
import com.ssafy.homfit.model.service.ChallengeService;
import com.ssafy.homfit.model.service.FeedService;
import com.ssafy.homfit.model.service.TagService;

@SpringBootApplication
@MapperScan(value = "com.ssafy.homfit.model.dao")
@EnableCaching
@EnableScheduling 
public class HomfitchallengeApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(HomfitchallengeApplication.class, args);
	}


	
	@Autowired
	ChallengeService challengeService;
	@Autowired
	private ChallengeRepository challengeRepository;
	@Autowired
	private TodayChallengeRepository todayRepository;
	@Autowired
	private FeedService feedService;
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	TagService tagService;
	
	
	//임시적으로 서버 시작시 바로 batch작업 실행
	@Bean
	public ApplicationRunner applicationRunner() {
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {

			
				//서버 구동전 챌린지 리스트 업데이트
				challengeRepository.deleteAll(); // 처음 등록된 캐시 다 지움
				List<Challenge> reloadList = challengeService.AllChallengeList();
				challengeRepository.saveAll(reloadList);
				
				//태그업로드
				List<Tag> list = tagService.selectPopularTag();
				tagRepository.deleteAll(); //한번 다 지우고 
				tagRepository.saveAll(list); //태그 새로 저장

			}
		};

	}
}
