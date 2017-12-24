package com.ads.service;

import java.util.List;

import com.ads.pojo.TComment;


public interface CommentService {
	/**
	 * 通过段子编号获取相应的评论数据
	 * @param pageNum 页号
	 * @param episodeId 段子编号
	 * @return 评论信息
	 */
	public List<TComment> getCommentsByEpisodeId(int pageNum, int episodeId);
	
	/**
	 * 获取评论数
	 * @param episodeId
	 */
	public long getCommentNum(int episodeId);
	
	/**
	 * 指定 userId 用户是否点赞 commentId 评论
	 * @param userId
	 * @param commentId
	 * @return boolean
	 */
	public boolean isGoodComment(int userId, int commentId);
	/**
	 * 增加点赞评论数
	 * @param commentId 评论编号
	 * @param userId 用户编号
	 */
	public void addGoodComment(int commentId, int userId);

	/**
	 * 新增评论
	 * @param Comment对象
	 * @return 新增评论id
	 */
	public int insertComment(String commentContent, int userId, int episodeId);
	
	/**
	 * 删除评论
	 * @param userId 用户编号
	 * @param episodeId 段字编号
	 * @return int 操作记录数
	 */
	public int deleteComment(int commentId);
}
