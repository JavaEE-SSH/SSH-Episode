package com.ads.service.impl;

import java.util.List;

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
	public List<TEpisode> getEpisodes(int pageNum) {
		return episodeDao.getEpisodes(pageNum);
	}

	@Override
	public TEpisode getEpisodeById(int episodeId) {
		return episodeDao.getEpisodeById(episodeId);
	}

	@Override
	public void insertGoodEpisode(int userId, int episodeId) {
		//获取段子
		TEpisode episode = episodeDao.getEpisodeById(episodeId);
		//设置段子点赞数+1
		episode.setEpisodeGood(episode.getEpisodeGood()+1);
		//设置关联关系
		TUser user = userDao.getUserById(userId);
		episode.getTUsers_1().add(user);
		user.getTEpisodes_1().add(episode);
		//更新数据
		episodeDao.updateEpisode(episode);
		userDao.updateUser(user);
	}

	@Override
	public void insertCollectEpisode(int userId, int episodeId) {
		//获取段子和用户
		TEpisode episode = episodeDao.getEpisodeById(episodeId);
		TUser user = userDao.getUserById(userId);
		//设置关联
		episode.getTUsers().add(user);
		user.getTEpisodes().add(episode);
		//保存数据
		episodeDao.updateEpisode(episode);
		userDao.updateUser(user);
	}

	@Override
	public void deleteCollectEpisode(int userId, int episodeId) {
		//获取数据
		TEpisode episode = episodeDao.getEpisodeById(episodeId);
		TUser user = userDao.getUserById(userId);
		//删除关联
		episode.getTUsers().remove(user);
		user.getTEpisodes().remove(episode);
		//保存数据
		episodeDao.updateEpisode(episode);
		userDao.updateUser(user);
	}

	@Override
	public void deleteEpisode(String addTime) {
		
	}

	@Override
	public void insertEpisode(String episodeContent) {
		
	}

	@Override
	public long getEpisodeNum() {
		return episodeDao.getEpisodeNum();
	}

	@Override
	public List<TEpisode> getEpisodesByUserId(int userId, int pageNum) {
		return episodeDao.getEpisodeByUserId(userId, pageNum);
	}

	@Override
	public long getEpisodeNumByUserId(int userId) {
		return episodeDao.getEpisodeNumByUserId(userId);
	}
}
