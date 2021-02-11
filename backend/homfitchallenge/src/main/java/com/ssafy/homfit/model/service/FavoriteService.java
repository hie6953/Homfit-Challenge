package com.ssafy.homfit.model.service;

import com.ssafy.homfit.model.Favorite;

public interface FavoriteService {

	public boolean init(String uid) throws Exception;

	public boolean updateFavorite(Favorite favorite) throws Exception;

	public boolean isSetting(String uid) throws Exception;
}
