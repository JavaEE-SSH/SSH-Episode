package com.ads.dao;

import java.util.Set;

import com.ads.pojo.TEpisode;

public interface EpisodeDao {
	/**获取所有段子信息
	 * 
	 * @return Set<TEpisode>
	 */
	public Set<TEpisode> getEpisodes();
	/**通过段子编号获取单个段子信息
	 * 
	 * @param episodeId
	 * @return TEpisode
	 */
	public TEpisode getEpisodeById(int episodeId);
	/**通过用户编号获取个人收藏的段子
	 * 
	 * @param userId
	 * @return Set<TEpisode>集合
	 */
	public Set<TEpisode> getEpisodeByUserId(int userId);
	
	/**通过段子编号增加段子点赞数
	 * 
	 * @param episodeId
	 */
	public void addEpisodeGoodById(int episodeId);
	/**将用户id和段子id关联到段子点赞表
	 * 
	 * @param userId
	 * @param episodeId
	 */
	public void insertEpisode_Good(int userId, int episodeId);
	
	/**通过段子编号和用户编号查询点赞段子表中是否有数据
	 * 
	 * @param episodeId
	 * @param userId
	 * @return 0-没有
	 */
	public int getGoodEpisode(int episodeId, int userId);
	/**通过段子编号和用户编号查询收藏表中是否有数据
	 * 
	 * @param episodeId
	 * @param userId
	 * @return 0-没有
	 */
	public int getCollect(int episodeId, int userId);
	
	/**添加收藏段子
	 * 
	 * @param userId
	 * @param episodeId
	 */
	public void insertCollectEpisode(int userId, int episodeId);
	/**取消收藏段子
	 * 
	 * @param userId
	 * @param episodeId
	 */
	public void deleteCollectEpisode(int userId, int episodeId);
	
	/**删除指定时间前的段子
	 * 
	 * @param addTime
	 */
	public void deleteEpisode(String addTime);
	/**添加段子
	 * 
	 * @param episodeContent
	 */
	public void insertEpisode(String episodeContent);
}
