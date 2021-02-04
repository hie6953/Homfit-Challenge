package com.ssafy.homfit;


import com.ssafy.homfit.util.Util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomfitchallengeApplicationTests {

	@Test
	void contextLoads() {
		String tk = Util.generateToken();
		System.out.println(tk);
	}

	@Test
	void redisTest(){
		ConnectToRedis s = new ConnectToRedis();
		s.setCache("hi", "hello", 60 * 10);
	}

}
