package com.ssafy.homfit.controller;

import java.util.List;

import com.ssafy.homfit.model.ChatVO;
import com.ssafy.homfit.model.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    ChatService chatService;

    @MessageMapping("/{challenge_id}")
    @SendTo("/sub/{challenge_id}")
    public ChatVO chatHandler(@DestinationVariable int challenge_id, ChatVO chatVO) {

        try {
            chatService.save(chatVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(result);

        return chatVO;
    }

    @GetMapping("/chat/log/{challenge_id}")
    public ResponseEntity<List<ChatVO>> findChatLog(@PathVariable int challenge_id){
        HttpStatus status = null;
        List<ChatVO> list = null;

        try{
            list = chatService.findChatLog(challenge_id);
            status = HttpStatus.ACCEPTED;
        } catch(Exception e){
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<List<ChatVO>>(list, status); 
    }
}
