package com.ssafy.homfit.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.homfit.model.Challenge;
import com.ssafy.homfit.model.service.ChallengeService;

public class Util {

    public static String generateToken() {
        String token = null;
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            secureRandom.setSeed(secureRandom.generateSeed(128));
            token = new String(digest.digest((secureRandom.nextLong() + "").getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return token;
    }
    
  

}
