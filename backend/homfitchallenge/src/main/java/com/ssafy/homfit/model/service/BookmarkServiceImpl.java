package com.ssafy.homfit.model.service;

import com.ssafy.homfit.model.Bookmark;
import com.ssafy.homfit.model.dao.BookmarkDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public boolean create(Bookmark bookmark) throws Exception {
        try {
            sqlSession.getMapper(BookmarkDAO.class).create(bookmark);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Bookmark bookmark) throws Exception {
        try {
            sqlSession.getMapper(BookmarkDAO.class).delete(bookmark);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
}
