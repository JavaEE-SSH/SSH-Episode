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
	public void addGoodComment(String commentId, String userId);
	
	/**
	 * 获取点赞评论数据
	 * @param commentId 评论编号
	 * @param userId 用户编号
	 * @return 是否查询到数据， 1-是，0-否
	 */
	public int getGoodComment(String commentId, String userId);
	
	/**
	 * 新增评论
	 * @param Comment对象
	 * @return 新增评论id
	 */
	public String insertComment(String commentContent, String userId, String episodeId);
	
	/**
	 * 删除评论
	 * @param userId 用户编号
	 * @param episodeId 段字编号
	 */
	public void deleteComment(String commentId);
}
