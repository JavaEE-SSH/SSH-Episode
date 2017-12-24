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
	 * 通过 userId 和 episodeId 获取评论id
	 * @param userId
	 * @param episodeId
	 * @return int
	 */
	public int getCommentIdByUserIdAndEpisodeId(int userId, int episodeId);
	/**
	 * 点赞评论
	 * @param commentId
	 * @param userId
	 */
	public void insertGoodComment(int commentId, int userId);
	
	/**
	 * 新增评论
	 * @param comment
	 */
	public void insertComment(TComment comment);
	
	/**
	 * 删除评论
	 * @param commentId
	 * @return int 操作记录数
	 */
	public int deleteComment(int commentId);
}
