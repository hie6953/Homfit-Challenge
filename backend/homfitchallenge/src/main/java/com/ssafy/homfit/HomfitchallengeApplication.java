package com.ssafy.homfit;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import com.ssafy.homfit.api.ChallengeRepository;
import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.service.ChallengeService;

@SpringBootApplication
@MapperScan(value = "com.ssafy.homfit.model.dao")
@EnableCaching
public class HomfitchallengeApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(HomfitchallengeApplication.class, args);
	}


	
	@Autowired
	ChallengeService challengeService;
	
	@Autowired
	private ChallengeRepository challengeRepository;

	
	//서버 시작시 바로 캐시 등록
	@Bean
	public ApplicationRunner applicationRunner() {
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				challengeRepository.deleteAll(); //처음 등록된 캐시 다 지움
				List<Challenge> challengelist = challengeService.AllChallengeList();
				challengeRepository.saveAll(challengelist);
			}
		};

	}
}
