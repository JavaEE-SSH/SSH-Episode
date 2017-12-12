package com.ads.service;

import java.util.Set;

import com.ads.pojo.TEpisode;

public interface EpisodeService {
	/**
	 * 获取所有段子信息
	 * @return List<Episode> 所有段子信息
	 */
	public Set<TEpisode> getEpisodes();
	/**
	 * 通过段子编号获取单个段子信息
	 * @param episodeId 段子id
	 * @return Episode 单个段子信息
	 */
	public TEpisode getEpisodeById(String episodeId);
	
	/**
	 * 通过用户编号获取个人收藏的段子
	 * @param userId 用户编号
	 * @return 段子信息
	 */
	public Set<TEpisode> getEpisodeByUserId(String userId);
	
	/**
	 * 通过用户id和段子id处理点赞
	 * @param userId 用户编号
	 * @param episodeId 段子编号
	 * @return 是否操作成功 1-是，0-否
	 */
	public int addEpisodeGood(String userId, String episodeId);
	
	/**
	 * 通过段子编号和用户编号查询点赞段子表中是否有数据
	 * @param episodeId 段子编号
	 * @param userId 用户编号
	 * @return 是否有数据，1-是，0-否
	 */
	public int getGoodEpisode(String episodeId, String userId);
	
	/**
	 * 通过段子编号和用户编号查询收藏表中是否有数据
	 * @param episodeId 段子编号
	 * @param userId 用户编号
	 * @return 是否有数据，1-是，0-否
	 */
	public int getCollect(String episodeId, String userId);
	
	/**
	 * 增加指定用户id和段子id的收藏信息
	 * @param userId 用户编号
	 * @param episodeId 段子编号
	 */
	public void insertCollectEpisode(String userId, String episodeId);
	
	/**
	 * 删除指定用户id和段子id的收藏信息
	 * @param userId 用户编号
	 * @param episodeId 段子编号
	 */
	public void deleteCollectEpisode(String userId, String episodeId);
	
	/**
	 * 删除指定时间前的段子
	 * @param add_time
	 */
	public void deleteEpisode(String addTime);
	
	/**
	 * 添加段子
	 * @param episode_content 段子内容
	 */
	public void insertEpisode(String episodeContent);
}
