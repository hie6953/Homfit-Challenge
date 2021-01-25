package com.ssafy.homfit.model.dao;

import java.util.List;

import com.ssafy.homfit.model.Tag;

public interface TagDAO {

	/** tag*/
	public int insertTag (String tag);
	public String[] selectAllTag ();
	public String selectTag(String tag_name);
	
	/**tag in challenge */
	
	
}
