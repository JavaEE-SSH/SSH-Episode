package com.ads.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ads.dao.EpisodeDao;
import com.ads.dao.UserDao;
import com.ads.pojo.TEpisode;
import com.ads.pojo.TUser;
import com.ads.service.EpisodeService;

@Transactional
@Service("episodeService")
public class EpisodeServiceImpl implements EpisodeService {
	@Resource
	private EpisodeDao episodeDao;
	@Resource
	private UserDao userDao;
	
	@Override
	public Set<TEpisode> getEpisodes() {
		return null;
	}

	@Override
	public TEpisode getEpisodeById(int episodeId) {
		return episodeDao.getEpisodeById(episodeId);
	}

	@Override
	public void insertGoodEpisode(int userId, int episodeId) {
		//更新段子信息——段子表点赞数+1
		TEpisode episode = episodeDao.getEpisodeById(episodeId);
		episode.setEpisodeGood(episode.getEpisodeGood()+1);
		//添加点赞段子
		TUser user = userDao.getUserById(userId);
		episode.getTUsers_1().add(user);
		user.getTEpisodes_1().add(episode);
		//保存
		episodeDao.updateEpisode(episode);
		userDao.updateUser(user);
	}

	@Override
	public void insertCollectEpisode(int userId, int episodeId) {
		//获取对应持久化对象
		TEpisode episode = episodeDao.getEpisodeById(episodeId);
		TUser user = userDao.getUserById(userId);
		//多对多关联
		episode.getTUsers().add(user);
		user.getTEpisodes().add(episode);
		//更新数据
		episodeDao.updateEpisode(episode);
		userDao.updateUser(user);
	}

	@Override
	public void deleteCollectEpisode(int userId, int episodeId) {
		//获取对应持久化对象
		TEpisode episode = episodeDao.getEpisodeById(episodeId);
		TUser user = userDao.getUserById(userId);
		//多对多关联
		episode.getTUsers().remove(user);
		user.getTEpisodes().remove(episode);
		//更新数据
		episodeDao.updateEpisode(episode);
		userDao.updateUser(user);
	}

	@Override
	public void deleteEpisode(String addTime) {
		
	}

	@Override
	public void insertEpisode(String episodeContent) {
		
	}
}
