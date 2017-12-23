package com.ads.dao;

import java.util.Set;

import com.ads.pojo.TEpisode;

public interface EpisodeDao {
	/**
	 * 获取段子集合
	 * @return Set<TEpisode>
	 */
	public Set<TEpisode> getEpisodes();
	
	/**ͨ
	 * 通过 episodeId 获取段子
	 * @param episodeId
	 * @return TEpisode
	 */
	public TEpisode getEpisodeById(int episodeId);
	
	/**ͨ
	 * 更新 episode
	 * @param episode
	 */
	public void updateEpisode(TEpisode episode);
	
	/**
	 * 向 good_episode 添加记录
	 * 通过 episode.getUsers().add(user)
	 * @param episode
	 */
//	public void insertGoodEpisode(TEpisode episode);
	
	/**
	 * 向 collect 添加一条记录
	 * @param userId
	 * @param episodeId
	 */
	public void insertCollectEpisode(int userId, int episodeId);
	
	/**
	 * 删除一条记录
	 * @param userId
	 * @param episodeId
	 */
	public void deleteCollectEpisode(int userId, int episodeId);
	
	/**
	 * 根据添加时间删除段子
	 * @param addTime
	 */
	public void deleteEpisode(String addTime);
	
	/**
	 * 由 episodeContent 向数据库添加一条记录
	 * @param episodeContent
	 */
	public void insertEpisode(String episodeContent);
}
