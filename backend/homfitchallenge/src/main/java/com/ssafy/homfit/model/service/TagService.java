package com.ssafy.homfit.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.homfit.model.Tag;

public interface TagService {

	/**tag*/
	public boolean writeTag(Tag tag);
	public String[] AllTagList();
	public Tag selectTag(String tag);
	
	/**tag in challenge */
	public boolean writeTagInChallenge (HashMap<String, Integer> map);
	public Tag[] selectTagInChallenge (int challengeID);
}
