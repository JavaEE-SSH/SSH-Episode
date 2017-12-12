package com.ads.dao;

import java.util.Set;

import com.ads.pojo.TComment;

public interface CommentDao {
	/**获取指定段子编号的所有评论
	 * 
	 * @param episodeId
	 * @return Set<TComment>
	 */
	public Set<TComment> getCommentsByEpisodeId(String episodeId);
	
	/**通过评论id增加评论点赞数
	 * 
	 * @param commentId
	 */
	public void addGoodCommentById(String commentId);
	
	/**向点赞评论表插入一条数据
	 * 
	 * @param commentId
	 * @param userId
	 */
	public void insertGoodComment(String commentId, String userId);
	
	/**获取点赞评论数据
	 * 
	 * @param commentId
	 * @param userId
	 * @return
	 */
	public int getGoodComment(String commentId, String userId);
	
	/**新增评论
	 * 
	 * @param commentContent
	 * @param userId
	 * @param episodeId
	 */
	public void insertComment(String commentContent, String userId, String episodeId);
	
	/**获取最新添加评论编号
	 * 
	 * @return 评论id
	 */
	public String getNewCommentId();
	
	/**删除评论
	 * 
	 * @param commentId
	 */
	public void deleteComment(String commentId);
}
