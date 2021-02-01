package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Comment;
import com.ssafy.homfit.model.CommentVo;
import com.ssafy.homfit.model.dao.CommentDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public boolean create(Comment comment) throws Exception {
        try {
            sqlSession.getMapper(CommentDAO.class).create(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<CommentVo> searchByFeedId(int feed_id) throws Exception {
        List<CommentVo> list = null;
        try {
            list = sqlSession.getMapper(CommentDAO.class).searchByFeedId(feed_id);       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean update(Comment comment) throws Exception {
        try {
            sqlSession.getMapper(CommentDAO.class).update(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int feed_id) throws Exception {
        try {
            sqlSession.getMapper(CommentDAO.class).delete(feed_id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
