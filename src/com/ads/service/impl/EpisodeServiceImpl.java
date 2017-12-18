package com.ads.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ads.dao.EpisodeDao;
import com.ads.pojo.TEpisode;
import com.ads.service.EpisodeService;

@Service("episodeService")
public class EpisodeServiceImpl implements EpisodeService {
	@Resource
	private EpisodeDao episodeDao;
	
	@Override
	public Set<TEpisode> getEpisodes() {
		return null;
	}

	@Override
	public TEpisode getEpisodeById(int episodeId) {
		return episodeDao.getEpisodeById(episodeId);
	}

	@Override
	public Set<TEpisode> getEpisodeByUserId(int userId) {
		return null;
	}

	@Override
	public int addEpisodeGood(int userId, int episodeId) {
		return 0;
	}

	@Override
	public int getGoodEpisode(int episodeId, int userId) {
		return 0;
	}

	@Override
	public int getCollect(int episodeId, int userId) {
		return 0;
	}

	@Override
	public void insertCollectEpisode(int userId, int episodeId) {
		
	}

	@Override
	public void deleteCollectEpisode(int userId, int episodeId) {
		
	}

	@Override
	public void deleteEpisode(String addTime) {
		
	}

	@Override
	public void insertEpisode(String episodeContent) {
		
	}
}
