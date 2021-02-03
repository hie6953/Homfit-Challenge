package com.ssafy.homfit.model.service;

import com.ssafy.homfit.model.Bookmark;

public interface BookmarkService {

	boolean create(Bookmark bookmark) throws Exception;

	boolean delete(Bookmark bookmark) throws Exception;

}
