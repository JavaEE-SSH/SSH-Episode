package com.ads.service.impl;

import java.util.List;
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
	public List<TEpisode> getEpisodes(int pageNum) {
		return episodeDao.getEpisodes(pageNum);
	}

	@Override
	public TEpisode getEpisodeById(int episodeId) {
		return episodeDao.getEpisodeById(episodeId);
	}

	@Override
	public void insertGoodEpisode(int userId, int episodeId) {
		//鏇存柊娈靛瓙淇℃伅鈥斺�旀瀛愯〃鐐硅禐鏁�+1
		TEpisode episode = episodeDao.getEpisodeById(episodeId);
		episode.setEpisodeGood(episode.getEpisodeGood()+1);
		//娣诲姞鐐硅禐娈靛瓙
		TUser user = userDao.getUserById(userId);
		episode.getTUsers_1().add(user);
		user.getTEpisodes_1().add(episode);
		//淇濆瓨
		episodeDao.updateEpisode(episode);
		userDao.updateUser(user);
	}

	@Override
	public void insertCollectEpisode(int userId, int episodeId) {
		//鑾峰彇瀵瑰簲鎸佷箙鍖栧璞�
		TEpisode episode = episodeDao.getEpisodeById(episodeId);
		TUser user = userDao.getUserById(userId);
		//澶氬澶氬叧鑱�
		episode.getTUsers().add(user);
		user.getTEpisodes().add(episode);
		//鏇存柊鏁版嵁
		episodeDao.updateEpisode(episode);
		userDao.updateUser(user);
	}

	@Override
	public void deleteCollectEpisode(int userId, int episodeId) {
		//鑾峰彇瀵瑰簲鎸佷箙鍖栧璞�
		TEpisode episode = episodeDao.getEpisodeById(episodeId);
		TUser user = userDao.getUserById(userId);
		//澶氬澶氬叧鑱�
		episode.getTUsers().remove(user);
		user.getTEpisodes().remove(episode);
		//鏇存柊鏁版嵁
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
		// TODO Auto-generated method stub
		return episodeDao.getEpisodeNum();
	}
}
