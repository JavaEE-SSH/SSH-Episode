package com.ads.service;

import com.ads.pojo.TUser;

public interface UserService {
	/**
	 * 通过用户编号获取用户
	 * @param userId
	 * @return pojo.User
	 */
	public TUser getUserById(int userId);
	/**
	 * 提交用户昵称、用户密码增加用户,返回用户id
	 * @param userNickname 用户昵称
	 * @param userPassword 用户密码
	 * @return 新增用户id
	 */
	public int insertUser(String userNickname, String userPassword);
	/**
	 * 修改用户昵称
	 * @param userId 用户编号
	 * @param userNickname 用户昵称
	 */
	public void updateNicknameByUserId(int userId, String userNickname);
	/**
	 * 修改用户性别
	 * @param userId 用户编号
	 * @param user_gender 用户性别：0-女，1-男
	 */
	public void updateGenderByUserId(int userId, int userGender);
	/**
	 * 修改用户密码
	 * @param userId 用户编号
	 * @param userPassword 用户密码
	 */
	public void updatePasswordByUserId(int userId, String userPassword);
	
	/**
	 * 修改头像
	 * @param userId 用户编号
	 * @param userImage 图片路径
	 */
	public void updateImageByUserId(int userId, String userImage);
	
	/**
	 * 更新用户loginTime
	 * @param userId 用户id
	 */
	public void updateLoginTimeByUserId(int userId);
	
	/**
	 * 删除长时间未登录的用户
	 * @param loginTime 指定的登录时间
	 */
	public void deleteUser(String loginTime);
}
