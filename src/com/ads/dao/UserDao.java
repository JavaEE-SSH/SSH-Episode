package com.ads.dao;

import com.ads.pojo.TUser;

public interface UserDao {
	/**
	 * 通过 userId 获取用户 
	 * @param userId
	 * @return TUser
	 */
	public TUser getUserById(int userId);
	
	/**
	 * 添加用户
	 * @param userId
	 * @param userNickname
	 * @param userPassword
	 */
	public void insertUser(int userId, String userNickname, String userPassword);
	
	/**
	 * 修改 user
	 * @param user
	 */
	public void updateUser(TUser user);
}
