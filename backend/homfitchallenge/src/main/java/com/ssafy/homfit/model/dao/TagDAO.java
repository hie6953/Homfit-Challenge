package com.ssafy.homfit.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.homfit.model.Tag;

public interface TagDAO {

	/** tag*/
	public int insertTag (Tag tag);
	public String[] selectAllTag ();
	public Tag selectTag(String tag_name);
	
	/**tag in challenge */
	public int insertTagInChallenge (HashMap<String,Integer> map);
	public Tag[] selectTagInChallenge (int challengeID);
	public Tag[] selectChallengeInTag (int tagID);
	public int deleteTagInChallenge (int challengeID);
	
}
