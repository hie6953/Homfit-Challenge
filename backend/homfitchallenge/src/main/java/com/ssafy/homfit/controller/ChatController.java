package com.ssafy.homfit.controller;

import com.ssafy.homfit.model.ChatVO;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    
    @MessageMapping("/chat/{challenge_id}/receive")
    @SendTo("/chat/{challenge_id}/send")
    public ChatVO chatHandler(@DestinationVariable int challenge_id, ChatVO chatVO){
        String userNickName = chatVO.getUserNickName();
        String content = chatVO.getContent();

        ChatVO result = new ChatVO(userNickName, content);

        return result;
    }
}
