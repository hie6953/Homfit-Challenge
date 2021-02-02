package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Report;

public interface ReportService {

	public List<Report> searchAll() throws Exception;
    public boolean ignore(int report_id) throws Exception;
    public boolean report(Report report) throws Exception;
}
