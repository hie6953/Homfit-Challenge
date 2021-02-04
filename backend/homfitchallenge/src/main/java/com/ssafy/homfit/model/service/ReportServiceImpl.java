package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Report;
import com.ssafy.homfit.model.dao.ReportDAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public List<Report> searchAll() throws Exception {
        List<Report> list = null;

        list = sqlSession.getMapper(ReportDAO.class).searchAll();
        return list;
    }

    @Override
    public boolean ignore(int report_id) throws Exception {
        try {
            sqlSession.getMapper(ReportDAO.class).ignore(report_id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    @Override
    public boolean report(Report report) throws Exception {
        try {
            sqlSession.getMapper(ReportDAO.class).report(report);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}
