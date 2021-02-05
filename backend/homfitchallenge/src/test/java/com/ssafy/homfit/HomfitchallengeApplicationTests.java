package com.ssafy.homfit;


import com.ssafy.homfit.model.User;
import com.ssafy.homfit.model.service.UserServiceImpl;
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
	void signUpTest(){
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		User user = new User();
		user.setUid(Util.generateToken());
		String uidToken = null;

		try {
			while (true) {
				uidToken = Util.generateToken();
				if (userServiceImpl.getUid(uidToken) == null)
					break;
			}
			user.setUid(uidToken);
			userServiceImpl.signup(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
