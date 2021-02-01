package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Comment;
import com.ssafy.homfit.model.CommentVo;

public interface CommentService {
    public boolean create(Comment comment) throws Exception;
    public List<CommentVo> searchByFeedId(int feed_id) throws Exception;
    public boolean update(Comment comment) throws Exception;
    public boolean delete(int feed_id) throws Exception;
}
