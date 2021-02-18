package com.ssafy.homfit.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    public static String generateToken(String someText) {
        String token = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            token = new String(digest.digest(someText.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return token;
    }
    
  

}
