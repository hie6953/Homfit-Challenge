package com.ssafy.homfit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(value = "com.ssafy.homfit.model.dao")
@EnableCaching
public class HomfitchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomfitchallengeApplication.class, args);
	}

}
