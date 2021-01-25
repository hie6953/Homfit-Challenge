package com.ssafy.homfit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.ssafy.hofit.model.dao")
public class HomfitchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomfitchallengeApplication.class, args);
	}

}
