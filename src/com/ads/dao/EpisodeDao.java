package com.ads.dao;

import java.util.List;
import java.util.Set;

import com.ads.pojo.TEpisode;

public interface EpisodeDao {
	/**
	 * 鑾峰彇娈靛瓙闆嗗悎
	 * @return Set<TEpisode>
	 */
	public List<TEpisode> getEpisodes(int pageNum);
	
	/**通
	 * 閫氳繃 episodeId 鑾峰彇娈靛瓙
	 * @param episodeId
	 * @return TEpisode
	 */
	public TEpisode getEpisodeById(int episodeId);
	
	/**通
	 * 鏇存柊 episode
	 * @param episode
	 */
	public void updateEpisode(TEpisode episode);
	
	/**
	 * 鏍规嵁娣诲姞鏃堕棿鍒犻櫎娈靛瓙
	 * @param addTime
	 */
	public void deleteEpisode(String addTime);
	
	/**
	 * 鐢� episodeContent 鍚戞暟鎹簱娣诲姞涓�鏉¤褰�
	 * @param episodeContent
	 */
	public void insertEpisode(String episodeContent);
	public long getEpisodeNum();

}
