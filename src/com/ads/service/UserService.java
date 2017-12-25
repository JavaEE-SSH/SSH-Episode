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
	
	/**
	 * 更新用户头像
	 * @param userId
	 * @param image
	 */
	public void upDateUserImageById(int userId,String image);
}
