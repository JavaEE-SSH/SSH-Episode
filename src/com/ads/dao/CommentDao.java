package com.ads.dao;

import java.util.List;

import com.ads.pojo.TComment;

public interface CommentDao {
	
	/**
	 * 通过 episodeId 获取评论集
	 * @param pageNum
	 * @param episodeId
	 * @return List<TComment>
	 */
	public List<TComment> getCommentsByEpisodeId(int pageNum, int episodeId);
	
	/**
	 * 获取指定 userId 和 commentId 的点赞评论信息
	 * @param userId
	 * @param commentId
	 * @return int 0/1
	 */
	public int getGoodComment(int userId, int commentId);
	
	/**
	 * 获取评论数
	 * @param episodeId
	 */
	public long getCommentNum(int episodeId);
	
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
