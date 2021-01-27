package com.ssafy.homfit.model.dao;

import java.sql.SQLException;

import com.ssafy.homfit.model.Diary;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiaryDAO {

	public void create(Diary diary) throws SQLException;

	public void updateStarPoint(Diary diary) throws SQLException;

	public void updateDiaryContents(Diary diary) throws SQLException;

	public void delete(Diary diary) throws SQLException;

	public Diary search(Diary diary) throws SQLException;

}
