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
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

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
	RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	ChallengeService challengeService;
	
	
	//서버 시작시 바로 캐시 등록
	@Bean
	public ApplicationRunner applicationRunner() {
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				ListOperations<String, Object> listOperation = redisTemplate.opsForList();
				Challenge ch = new Challenge();
				String key = "challengeList";
				//기존에 있던 캐시 삭제
				//redisTemplate.delete(key);
				Long size = listOperation.size(key);
				if(size == 0) {
					List<Challenge> list = challengeService.AllChallengeList();
					for (int i = 0; i < list.size(); i++) {
						listOperation.rightPush(key, list.get(i));
					}
				}
				
			}
		};

	}
}
