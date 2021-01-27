package com.ssafy.homfit.model.service;

import com.ssafy.homfit.model.Diary;
import com.ssafy.homfit.model.dao.DiaryDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public boolean create(Diary diary) throws Exception {
        try {
            sqlSession.getMapper(DiaryDAO.class).create(diary);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String update(Diary diary) throws Exception {
        if (diary.getStar_point() == 0 && diary.getDiary_contents().equals("")) {
            try {
                sqlSession.getMapper(DiaryDAO.class).delete(diary);
                return "delete";
            } catch (Exception e) {
                return "fail";
            }
        } else {
            try {
                sqlSession.getMapper(DiaryDAO.class).updateDiaryContents(diary);
                sqlSession.getMapper(DiaryDAO.class).updateStarPoint(diary);
                return "success";
            } catch (Exception e) {
                return "fail";
            }
        }
    }

    @Override
    public Diary search(Diary diary) throws Exception {
        return sqlSession.getMapper(DiaryDAO.class).search(diary);
    }

}
