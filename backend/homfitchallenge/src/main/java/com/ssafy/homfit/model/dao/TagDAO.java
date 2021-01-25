package com.ssafy.homfit.model.dao;

import java.util.List;

import com.ssafy.homfit.model.Tag;

public interface TagDAO {

	public int insertTag (String tag);
	public String[] selectAllTag ();
	
}
