package com.ads.dao;

import java.util.Set;

import com.ads.pojo.TComment;

public interface CommentDao {
	/**��ȡָ�����ӱ�ŵ���������
	 * 
	 * @param episodeId
	 * @return Set<TComment>
	 */
	public Set<TComment> getCommentsByEpisodeId(String episodeId);
	
	/**ͨ������id�������۵�����
	 * 
	 * @param commentId
	 */
	public void addGoodCommentById(String commentId);
	
	/**��������۱����һ������
	 * 
	 * @param commentId
	 * @param userId
	 */
	public void insertGoodComment(String commentId, String userId);
	
	/**��ȡ������������
	 * 
	 * @param commentId
	 * @param userId
	 * @return
	 */
	public int getGoodComment(String commentId, String userId);
	
	/**��������
	 * 
	 * @param commentContent
	 * @param userId
	 * @param episodeId
	 */
	public void insertComment(String commentContent, String userId, String episodeId);
	
	/**��ȡ����������۱��
	 * 
	 * @return ����id
	 */
	public String getNewCommentId();
	
	/**ɾ������
	 * 
	 * @param commentId
	 */
	public void deleteComment(String commentId);
}
