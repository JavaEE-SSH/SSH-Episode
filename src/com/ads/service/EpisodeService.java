package com.ads.service;

import java.util.Set;

import com.ads.pojo.TEpisode;

public interface EpisodeService {
	/**
	 * 获取段子集合
	 * @return List<Episode> 
	 */
	public Set<TEpisode> getEpisodes();
	
	/**
	 * 通过 episodeId 获取 Episode
	 * @param episodeId 
	 * @return Episode 
	 */
	public TEpisode getEpisodeById(int episodeId);
	
	/**
	 * 处理点赞段子操作
	 * @param userId
	 * @param episode
	 */
	public void insertGoodEpisode(int userId, int episodeId);
	
	/**
	 * 添加收藏
	 * @param userId
	 * @param episodeId
	 */
	public void insertCollectEpisode(int userId, int episodeId);
	
	/**
	 * 删除收藏
	 * @param userId
	 * @param episodeId
	 */
	public void deleteCollectEpisode(int userId, int episodeId);
	
	/**
	 *删除段子
	 * @param add_time
	 */
	public void deleteEpisode(String addTime);
	
	/**
	 * 添加段子
	 * @param episode_content
	 */
	public void insertEpisode(String episodeContent);
}
