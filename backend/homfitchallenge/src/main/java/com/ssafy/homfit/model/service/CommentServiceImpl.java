package com.ssafy.homfit.model.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ssafy.homfit.model.Alarm;
import com.ssafy.homfit.model.Comment;
import com.ssafy.homfit.model.CommentVo;
import com.ssafy.homfit.model.Feed;
import com.ssafy.homfit.model.dao.AlarmDAO;
import com.ssafy.homfit.model.dao.CommentDAO;
import com.ssafy.homfit.model.dao.FeedDAO;

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
            // 회원 댓글 생성에 따른 알림 생성
            Feed feed = sqlSession.getMapper(FeedDAO.class).searchByFeedIdForUseAlarm(comment.getFeed_id());
            Date date = feed.getRegister_date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String strDate = dateFormat.format(date);
            String content = feed.getChallenge_title() + " 챌린지의 " + strDate + " 날짜 피드에 읽지 않은 댓글이 존재합니다.";
            if (!sqlSession.getMapper(AlarmDAO.class).alreadyExist(content)) {
                Alarm alarm_comment = new Alarm();
                alarm_comment.setAlarm_title("댓글");
                alarm_comment.setAlarm_content(content);
                alarm_comment.setUid(feed.getMaked_uid());
                sqlSession.getMapper(AlarmDAO.class).create(alarm_comment);
            }

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
    public boolean delete(int comment_id) throws Exception {
        try {
            sqlSession.getMapper(CommentDAO.class).delete(comment_id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
