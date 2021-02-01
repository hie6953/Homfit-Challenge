package com.ssafy.homfit.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.homfit.model.Comment;
import com.ssafy.homfit.model.CommentVo;

public interface CommentDAO {
    public void create(Comment comment) throws SQLException;
    public List<CommentVo> searchByFeedId(int feed_id) throws SQLException;
    public void update(Comment comment) throws SQLException;
    public void delete(int feed_id) throws SQLException;
}
