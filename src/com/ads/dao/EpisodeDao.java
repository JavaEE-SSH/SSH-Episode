package com.ads.dao;

import java.util.List;

import com.ads.pojo.TEpisode;

public interface EpisodeDao {
	/**
	 * 获取指定 pageNum 的段子列表
	 * @return Set<TEpisode>
	 */
	public List<TEpisode> getEpisodes(int pageNum);
	
	/**
	 * 获取用户收藏段子
	 * @param pageNum
	 * @return
	 */
	public List<TEpisode> getEpisodeByUserId(int userId,int pageNum);
	
	/**
	 * 通过 episodeId 获取段子信息
	 * @param episodeId
	 * @return TEpisode
	 */
	public TEpisode getEpisodeById(int episodeId);
	
	/**
	 * 通过 episode 更新段子信息
	 * @param episode
	 */
	public void updateEpisode(TEpisode episode);
	
	/**
	 * 按时间删除段子
	 * @param addTime
	 */
	public void deleteEpisode(String addTime);
	
	/**
	 * 新增段子
	 * @param episodeContent
	 */
	public void insertEpisode(String episodeContent);
	
	/**
	 * 获取段子总数
	 */
	public long getEpisodeNum();
	
	/**
	 * 获取用户收藏段子总页数
	 * @param userId
	 * @return
	 */
	public long getEpisodeNumByUserId(int userId);

}
