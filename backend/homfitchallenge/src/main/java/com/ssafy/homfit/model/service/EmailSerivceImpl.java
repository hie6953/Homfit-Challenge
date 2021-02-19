package com.ssafy.homfit.model.service;

import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ssafy.homfit.api.ConnectToRedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSerivceImpl implements EmailService {

    @Autowired
    JavaMailSender emailSender;

    private MimeMessage createMessage(String to) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(RecipientType.TO, to); // 보내는 대상
        message.setSubject("HomFit Challenge 이메일 인증번호가 도착했습니다."); // 제목

        StringBuilder sb = new StringBuilder();
        String key = createKey();
        sb.append("<div style='margin:100px;'>");
        sb.append("<h1> 안녕하세요 HomFit Challenge입니다!</h1>");
        sb.append("<br>");
        sb.append("<h2>아래 코드를 확인하여 이메일 인증란에 입력해주세요</h2>");
        sb.append("<br>");
        sb.append("<h2>Code : <strong>");
        sb.append(key + "</strong></h2>");
        sb.append("<br>");

        message.setText(sb.toString(), "utf-8", "html");
        message.setFrom(new InternetAddress("homfitChallenge@gmail.com", "HomFit Challenge"));

        // key값 캐시에 저장 로직
        this.setVerifyCode(to, key);

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3);

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }
        return key.toString();
    }

    @Override
    public void sendSimpleMessage(String to) throws Exception {
        MimeMessage message = createMessage(to);
        try {
            emailSender.send(message);
        } catch (MailException me) {
            me.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public void setVerifyCode(String email, String code) throws Exception {
        ConnectToRedis ctr = new ConnectToRedis();
        ctr.setCache("VerifyCode:" + email, code, 60 * 10);
        ctr = null;
    }

    @Override
    public String getVerifyCode(String email) throws Exception {
        ConnectToRedis ctr = new ConnectToRedis();
        return ctr.getCache("VerifyCode:" + email);
    }

}
