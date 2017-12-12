package com.ads.service.impl;

import java.util.Set;

import com.ads.dao.EpisodeDao;
import com.ads.pojo.TEpisode;
import com.ads.service.EpisodeService;

public class EpisodeServiceImpl implements EpisodeService {
	private EpisodeDao episodeDao;
	
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
	public int addEpisodeGood(String userId, String episodeId) {
		return 0;
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
