package com.ads.dao;

import java.util.Set;

import com.ads.pojo.TEpisode;

public interface EpisodeDao {
	/**��ȡ���ж�����Ϣ
	 * 
	 * @return Set<TEpisode>
	 */
	public Set<TEpisode> getEpisodes();
	/**ͨ�����ӱ�Ż�ȡ����������Ϣ
	 * 
	 * @param episodeId
	 * @return TEpisode
	 */
	public TEpisode getEpisodeById(int episodeId);
	/**ͨ���û���Ż�ȡ�����ղصĶ���
	 * 
	 * @param userId
	 * @return Set<TEpisode>����
	 */
	public Set<TEpisode> getEpisodeByUserId(int userId);
	
	/**ͨ�����ӱ�����Ӷ��ӵ�����
	 * 
	 * @param episodeId
	 */
	public void addEpisodeGoodById(int episodeId);
	/**���û�id�Ͷ���id���������ӵ��ޱ�
	 * 
	 * @param userId
	 * @param episodeId
	 */
	public void insertEpisode_Good(int userId, int episodeId);
	
	/**ͨ�����ӱ�ź��û���Ų�ѯ���޶��ӱ����Ƿ�������
	 * 
	 * @param episodeId
	 * @param userId
	 * @return 0-û��
	 */
	public int getGoodEpisode(int episodeId, int userId);
	/**ͨ�����ӱ�ź��û���Ų�ѯ�ղر����Ƿ�������
	 * 
	 * @param episodeId
	 * @param userId
	 * @return 0-û��
	 */
	public int getCollect(int episodeId, int userId);
	
	/**����ղض���
	 * 
	 * @param userId
	 * @param episodeId
	 */
	public void insertCollectEpisode(int userId, int episodeId);
	/**ȡ���ղض���
	 * 
	 * @param userId
	 * @param episodeId
	 */
	public void deleteCollectEpisode(int userId, int episodeId);
	
	/**ɾ��ָ��ʱ��ǰ�Ķ���
	 * 
	 * @param addTime
	 */
	public void deleteEpisode(String addTime);
	/**��Ӷ���
	 * 
	 * @param episodeContent
	 */
	public void insertEpisode(String episodeContent);
}
