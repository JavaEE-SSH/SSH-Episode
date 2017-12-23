package com.ads.dao;

public interface CommentDao {
	
	/**
	 * 点赞评论
	 * @param commentId
	 * @param userId
	 */
	public void insertGoodComment(String commentId, String userId);
	
	/**
	 * 新增评论
	 * @param commentContent
	 * @param userId
	 * @param episodeId
	 */
	public void insertComment(String commentContent, String userId, String episodeId);
	
	/**
	 * 删除评论
	 * @param commentId
	 */
	public void deleteComment(String commentId);
}
