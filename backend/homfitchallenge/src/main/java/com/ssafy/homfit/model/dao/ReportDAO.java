package com.ssafy.homfit.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.homfit.model.Report;

public interface ReportDAO {
    public void report(Report report) throws SQLException;
    public List<Report> searchAll() throws SQLException;
    public void ignore(int report_id) throws SQLException;
}
