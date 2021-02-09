package com.ssafy.homfit.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.homfit.model.Bookmark;

public interface BookmarkDAO {
	
    public void create(Bookmark bookmark) throws SQLException;
    public void delete(Bookmark bookmark) throws SQLException;
    public String selectBookmark (@Param("challenge_id") int challenge_id, @Param("uid") String uid);
    public List<Bookmark> selectAllBookmark (String uid);
    

}
