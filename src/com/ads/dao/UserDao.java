package com.ads.dao;

import com.ads.pojo.TUser;

public interface UserDao {
	/**ͨ���û���Ż�ȡ�û�
	 * 
	 * @param userId
	 * @return TUser
	 */
	public TUser getUserById(int userId);
	
	/**�ύ�û��ǳơ��û����������û�
	 * 
	 * @param userId
	 * @param userNickname
	 * @param userPassword
	 */
	public void insertUser(int userId, String userNickname, String userPassword);
	/**��ȡԤ�����û�id
	 * 
	 * @return �û�id
	 */
	public int getNewUserId();
	
	/**�޸��û��ǳ�
	 * 
	 * @param userId
	 * @param userNickname
	 */
	public void updateNicknameByUserId(int userId, String userNickname);
	/**�޸��û��Ա�
	 * 
	 * @param userId
	 * @param userGender
	 */
	public void updateGenderByUserId(int userId, int userGender);
	/**�޸��û�����
	 * 
	 * @param userId
	 * @param userPassword
	 */
	public void updatePasswordByUserId(int userId, String userPassword);
	/**�޸�ͷ��
	 * 
	 * @param userId
	 * @param userImage
	 */
	public void updateImageByUserId(int userId, String userImage);
	/**�����û�login_time
	 * 
	 * @param userId
	 */
	public void updateLoginTimeByUserId(int userId);
	
	/**ɾ����ʱ��δ��¼���û�
	 * 
	 * @param loginTime
	 */
	public void deleteUser(String loginTime);
}
