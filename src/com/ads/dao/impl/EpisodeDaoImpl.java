package com.ads.dao.impl;

import java.util.Set;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ads.dao.EpisodeDao;
import com.ads.pojo.TEpisode;

public class EpisodeDaoImpl extends HibernateDaoSupport implements EpisodeDao {

	@Override
	public Set<TEpisode> getEpisodes() {
		return null;
	}

	@Override
	public TEpisode getEpisodeById(String episodeId) {
		return null;
	}

	@Override
	public Set<TEpisode> getEpisodeByUserId(String userId) {
		return null;
	}

	@Override
	public void addEpisodeGoodById(String episodeId) {
		
	}

	@Override
	public void insertEpisode_Good(String userId, String episodeId) {
		
	}

	@Override
	public int getGoodEpisode(String episodeId, String userId) {
		return 0;
	}

	@Override
	public int getCollect(String episodeId, String userId) {
		return 0;
	}

	@Override
	public void insertCollectEpisode(String userId, String episodeId) {
		
	}

	@Override
	public void deleteCollectEpisode(String userId, String episodeId) {
		
	}

	@Override
	public void deleteEpisode(String addTime) {
		
	}

	@Override
	public void insertEpisode(String episodeContent) {
		
	}

}
