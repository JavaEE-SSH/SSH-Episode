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
	 * 修改 user
	 * @param user
	 */
	public void updateUser(TUser user);
	
	/**
	 * 更新用户昵称
	 * @param userId
	 * @param nickName
	 */
	public void upDateUserNicknameById(int userId,String nickName);
	
	/**
	 * 更新用户性别
	 * @param userId
	 * @param gender
	 */
	public void upDateUserGenderById(int userId,int gender);
	
	/**
	 * 更新用户密码
	 * @param userId
	 * @param password
	 */
	public void upDateUserPasswordById(int userId,String password);
}
