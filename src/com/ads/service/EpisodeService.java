package com.ads.service;

import java.util.Set;

import com.ads.pojo.TEpisode;

public interface EpisodeService {
	/**
	 * ��ȡ���ж�����Ϣ
	 * @return List<Episode> ���ж�����Ϣ
	 */
	public Set<TEpisode> getEpisodes();
	/**
	 * ͨ�����ӱ�Ż�ȡ����������Ϣ
	 * @param episodeId ����id
	 * @return Episode ����������Ϣ
	 */
	public TEpisode getEpisodeById(String episodeId);
	
	/**
	 * ͨ���û���Ż�ȡ�����ղصĶ���
	 * @param userId �û����
	 * @return ������Ϣ
	 */
	public Set<TEpisode> getEpisodeByUserId(String userId);
	
	/**
	 * ͨ���û�id�Ͷ���id�������
	 * @param userId �û����
	 * @param episodeId ���ӱ��
	 * @return �Ƿ�����ɹ� 1-�ǣ�0-��
	 */
	public int addEpisodeGood(String userId, String episodeId);
	
	/**
	 * ͨ�����ӱ�ź��û���Ų�ѯ���޶��ӱ����Ƿ�������
	 * @param episodeId ���ӱ��
	 * @param userId �û����
	 * @return �Ƿ������ݣ�1-�ǣ�0-��
	 */
	public int getGoodEpisode(String episodeId, String userId);
	
	/**
	 * ͨ�����ӱ�ź��û���Ų�ѯ�ղر����Ƿ�������
	 * @param episodeId ���ӱ��
	 * @param userId �û����
	 * @return �Ƿ������ݣ�1-�ǣ�0-��
	 */
	public int getCollect(String episodeId, String userId);
	
	/**
	 * ����ָ���û�id�Ͷ���id���ղ���Ϣ
	 * @param userId �û����
	 * @param episodeId ���ӱ��
	 */
	public void insertCollectEpisode(String userId, String episodeId);
	
	/**
	 * ɾ��ָ���û�id�Ͷ���id���ղ���Ϣ
	 * @param userId �û����
	 * @param episodeId ���ӱ��
	 */
	public void deleteCollectEpisode(String userId, String episodeId);
	
	/**
	 * ɾ��ָ��ʱ��ǰ�Ķ���
	 * @param add_time
	 */
	public void deleteEpisode(String addTime);
	
	/**
	 * ��Ӷ���
	 * @param episode_content ��������
	 */
	public void insertEpisode(String episodeContent);
}
