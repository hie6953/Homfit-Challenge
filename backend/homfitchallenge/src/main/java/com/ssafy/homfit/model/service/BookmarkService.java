package com.ssafy.homfit.model.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.homfit.model.Bookmark;

public interface BookmarkService {

	boolean create(Bookmark bookmark) throws Exception;

	boolean delete(Bookmark bookmark) throws Exception;

	public String selectBookmark(@Param("challenge_id") int challenge_id, @Param("uid") String uid);

	public List<Bookmark> selectAllBookmark(String uid);
}
