package com.ssafy.homfit.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.homfit.model.ChatVO;

public interface ChatDAO {
    public void save(ChatVO chatVO) throws SQLException;
    public List<ChatVO> findChatLog(int challenge_id) throws SQLException;
}
