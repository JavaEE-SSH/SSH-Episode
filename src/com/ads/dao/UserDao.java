package com.ads.dao;

import com.ads.pojo.TUser;

public interface UserDao {
	/**ͨ���û���Ż�ȡ�û�
	 * 
	 * @param userId
	 * @return TUser
	 */
	public TUser getUserById(String userId);
	
	/**�ύ�û��ǳơ��û����������û�
	 * 
	 * @param userId
	 * @param userNickname
	 * @param userPassword
	 */
	public void insertUser(String userId, String userNickname, String userPassword);
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
	public void updateNicknameByUserId(String userId, String userNickname);
	/**�޸��û��Ա�
	 * 
	 * @param userId
	 * @param userGender
	 */
	public void updateGenderByUserId(String userId, String userGender);
	/**�޸��û�����
	 * 
	 * @param userId
	 * @param userPassword
	 */
	public void updatePasswordByUserId(String userId, String userPassword);
	/**�޸�ͷ��
	 * 
	 * @param userId
	 * @param userImage
	 */
	public void updateImageByUserId(String userId, String userImage);
	/**�����û�login_time
	 * 
	 * @param userId
	 */
	public void updateLoginTimeByUserId(String userId);
	
	/**ɾ����ʱ��δ��¼���û�
	 * 
	 * @param loginTime
	 */
	public void deleteUser(String loginTime);
}
