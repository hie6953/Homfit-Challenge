package com.ssafy.homfit.model.service;

import java.util.List;

import com.ssafy.homfit.model.Favorite;

import io.lettuce.core.dynamic.annotation.Param;

public interface FavoriteService {

	public boolean init(String uid) throws Exception;

	public boolean updateFavorite(Favorite favorite) throws Exception;

	public boolean isSetting(String uid) throws Exception;
	
	public Favorite selectUserInfo (String uid);
	
	public List<Favorite> selectUserByAgeSex (int age , char sex );
}
