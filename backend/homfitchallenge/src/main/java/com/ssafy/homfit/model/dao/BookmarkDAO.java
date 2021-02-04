package com.ssafy.homfit.model.dao;

import java.sql.SQLException;

import com.ssafy.homfit.model.Bookmark;

public interface BookmarkDAO {
    public void create(Bookmark bookmark) throws SQLException;
    public void delete(Bookmark boomark) throws SQLException;
}
