package com.ssafy.homfit.model.service;

import com.ssafy.homfit.model.Diary;

public interface DiaryService {

	public boolean create(Diary diary) throws Exception;

	public String update(Diary diary) throws Exception;

	public Diary search(Diary diary) throws Exception;
    
}
