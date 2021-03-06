package com.ads.dao;

import java.util.Date;

import com.ads.pojo.TUser;

public interface UserDao {
	/**
	 * 通过 userId 获取用户 
	 * @param userId
	 * @return TUser
	 */
	public TUser getUserById(int userId);
	
	/**
	 * 通过 commentId 获取用户
	 * @param commentId
	 * @return TUser
	 */
	public TUser getUserByCommentId(int commentId);
	
	/**
	 * 添加用户
	 * @param userId
	 * @param userNickname
	 * @param userPassword
	 */
	public void insertUser(int userId, String userNickname, String userPassword);
	
	/**
	 * 获取新用户的id
	 */
	public int getNewUserId();
	
	/**
	 * 修改 user
	 * @param user
	 */
	public void updateUser(TUser user);
	
	/**
	 * 删除用户
	 * @param loginTime
	 */
	public void deleteUser(Date loginTime);
}
