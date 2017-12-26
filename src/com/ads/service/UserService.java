package com.ads.service;

import java.util.Date;

import com.ads.pojo.TUser;

public interface UserService {
	/**
	 * 通过 userid 获取用户
	 * @param userId
	 * @return pojo.User
	 */
	public TUser getUserById(int userId);
	
	/**
	 * 通过 commentId 获取用户
	 * @param commentId
	 * @return TUser
	 */
	public TUser getUserByCommentId(int commentId);
	
	/**
	 * 修改 user
	 * @param user
	 */
	public void updateUserInfo(TUser user);
	
	/**
	 * 新增用户
	 * @param user_nickname
	 * @param password
	 */
	public int insertUser(String user_nickname, String user_password);

	/**
	 * 删除用户
	 * @param string
	 */
	public void deleteUser(Date string);
}
