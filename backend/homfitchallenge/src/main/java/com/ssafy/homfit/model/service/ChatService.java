package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.ChatVO;

public interface ChatService {
    public void save(ChatVO chatVO) throws Exception;

    public List<ChatVO> findChatLog(int challenge_id) throws Exception;
}
