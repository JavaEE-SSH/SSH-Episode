package com.ads.dao;

import com.ads.pojo.TUser;

public interface UserDao {
	/**通过用户编号获取用户
	 * 
	 * @param userId
	 * @return TUser
	 */
	public TUser getUserById(int userId);
	
	/**提交用户昵称、用户密码增加用户
	 * 
	 * @param userId
	 * @param userNickname
	 * @param userPassword
	 */
	public void insertUser(int userId, String userNickname, String userPassword);
	/**获取预新增用户id
	 * 
	 * @return 用户id
	 */
	public int getNewUserId();
	
	/**修改用户昵称
	 * 
	 * @param userId
	 * @param userNickname
	 */
	public void updateNicknameByUserId(int userId, String userNickname);
	/**修改用户性别
	 * 
	 * @param userId
	 * @param userGender
	 */
	public void updateGenderByUserId(int userId, int userGender);
	/**修改用户密码
	 * 
	 * @param userId
	 * @param userPassword
	 */
	public void updatePasswordByUserId(int userId, String userPassword);
	/**修改头像
	 * 
	 * @param userId
	 * @param userImage
	 */
	public void updateImageByUserId(int userId, String userImage);
	/**更新用户login_time
	 * 
	 * @param userId
	 */
	public void updateLoginTimeByUserId(int userId);
	
	/**删除长时间未登录的用户
	 * 
	 * @param loginTime
	 */
	public void deleteUser(String loginTime);
}
