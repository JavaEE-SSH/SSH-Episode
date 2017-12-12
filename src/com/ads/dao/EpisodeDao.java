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
	public TEpisode getEpisodeById(String episodeId);
	/**ͨ���û���Ż�ȡ�����ղصĶ���
	 * 
	 * @param userId
	 * @return Set<TEpisode>����
	 */
	public Set<TEpisode> getEpisodeByUserId(String userId);
	
	/**ͨ�����ӱ�����Ӷ��ӵ�����
	 * 
	 * @param episodeId
	 */
	public void addEpisodeGoodById(String episodeId);
	/**���û�id�Ͷ���id���������ӵ��ޱ�
	 * 
	 * @param userId
	 * @param episodeId
	 */
	public void insertEpisode_Good(String userId, String episodeId);
	
	/**ͨ�����ӱ�ź��û���Ų�ѯ���޶��ӱ����Ƿ�������
	 * 
	 * @param episodeId
	 * @param userId
	 * @return 0-û��
	 */
	public int getGoodEpisode(String episodeId, String userId);
	/**ͨ�����ӱ�ź��û���Ų�ѯ�ղر����Ƿ�������
	 * 
	 * @param episodeId
	 * @param userId
	 * @return 0-û��
	 */
	public int getCollect(String episodeId, String userId);
	
	/**����ղض���
	 * 
	 * @param userId
	 * @param episodeId
	 */
	public void insertCollectEpisode(String userId, String episodeId);
	/**ȡ���ղض���
	 * 
	 * @param userId
	 * @param episodeId
	 */
	public void deleteCollectEpisode(String userId, String episodeId);
	
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
