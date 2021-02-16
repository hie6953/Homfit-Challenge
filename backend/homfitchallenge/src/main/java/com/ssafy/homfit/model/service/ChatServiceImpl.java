package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.ChatVO;
import com.ssafy.homfit.model.dao.ChatDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatDAO chatDAO;

    @Override
    public void save(ChatVO chatVO) throws Exception {
        chatDAO.save(chatVO);
    }

    @Override
    public List<ChatVO> findChatLog(int challenge_id) throws Exception {
        return chatDAO.findChatLog(challenge_id);
    }
    
}
