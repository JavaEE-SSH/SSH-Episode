package com.ads.service;

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
}
