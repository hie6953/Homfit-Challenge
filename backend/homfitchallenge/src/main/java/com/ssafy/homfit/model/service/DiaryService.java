package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Diary;

public interface DiaryService {

	public boolean create(Diary diary) throws Exception;

	public String update(Diary diary) throws Exception;

	public Diary search(Diary diary) throws Exception;

	public List<Diary> getMonthDiary(String uid, String date) throws Exception;
    
}
