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
	public TEpisode getEpisodeById(int episodeId);
	
	/**
	 * ͨ���û���Ż�ȡ�����ղصĶ���
	 * @param userId �û����
	 * @return ������Ϣ
	 */
	public Set<TEpisode> getEpisodeByUserId(int userId);
	
	/**
	 * ͨ���û�id�Ͷ���id�������
	 * @param userId �û����
	 * @param episodeId ���ӱ��
	 * @return �Ƿ�����ɹ� 1-�ǣ�0-��
	 */
	public int addEpisodeGood(int userId, int episodeId);
	
	/**
	 * ͨ�����ӱ�ź��û���Ų�ѯ���޶��ӱ����Ƿ�������
	 * @param episodeId ���ӱ��
	 * @param userId �û����
	 * @return �Ƿ������ݣ�1-�ǣ�0-��
	 */
	public int getGoodEpisode(int episodeId, int userId);
	
	/**
	 * ͨ�����ӱ�ź��û���Ų�ѯ�ղر����Ƿ�������
	 * @param episodeId ���ӱ��
	 * @param userId �û����
	 * @return �Ƿ������ݣ�1-�ǣ�0-��
	 */
	public int getCollect(int episodeId, int userId);
	
	/**
	 * ����ָ���û�id�Ͷ���id���ղ���Ϣ
	 * @param userId �û����
	 * @param episodeId ���ӱ��
	 */
	public void insertCollectEpisode(int userId, int episodeId);
	
	/**
	 * ɾ��ָ���û�id�Ͷ���id���ղ���Ϣ
	 * @param userId �û����
	 * @param episodeId ���ӱ��
	 */
	public void deleteCollectEpisode(int userId, int episodeId);
	
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
