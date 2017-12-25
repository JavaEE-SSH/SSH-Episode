package com.ads.service;

import java.util.List;

import com.ads.pojo.TEpisode;

public interface EpisodeService {
	/**
	 * 获取段子集合
	 * @return List<Episode> 
	 */
	public List<TEpisode> getEpisodes(int pageNum);
	
	/**
	 * 获取用户收藏的段子
	 * @param pageNum
	 * @return
	 */
	public List<TEpisode> getEpisodesByUserId(int userId,int pageNum);
	
	/**
	 * 通过 episodeId 获取段子总数
	 * @param episodeId 
	 * @return long 
	 */
	public long getEpisodeNum();
	
	/**
	 * 获取用户收藏段子的总页数
	 * @param userId
	 * @return long
	 */
	public long getEpisodeNumByUserId(int userId);
	
	/**
	 * 通过 userId 获取段子
	 * @param episodeId
	 * @return TEpisode
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
