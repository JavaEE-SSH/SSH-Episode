package com.ads.service;

import com.ads.pojo.TUser;

public interface UserService {
	/**
	 * ͨ���û���Ż�ȡ�û�
	 * @param userId
	 * @return pojo.User
	 */
	public TUser getUserById(int userId);
	/**
	 * �ύ�û��ǳơ��û����������û�,�����û�id
	 * @param userNickname �û��ǳ�
	 * @param userPassword �û�����
	 * @return �����û�id
	 */
	public int insertUser(String userNickname, String userPassword);
	/**
	 * �޸��û��ǳ�
	 * @param userId �û����
	 * @param userNickname �û��ǳ�
	 */
	public void updateNicknameByUserId(int userId, String userNickname);
	/**
	 * �޸��û��Ա�
	 * @param userId �û����
	 * @param user_gender �û��Ա�0-Ů��1-��
	 */
	public void updateGenderByUserId(int userId, int userGender);
	/**
	 * �޸��û�����
	 * @param userId �û����
	 * @param userPassword �û�����
	 */
	public void updatePasswordByUserId(int userId, String userPassword);
	
	/**
	 * �޸�ͷ��
	 * @param userId �û����
	 * @param userImage ͼƬ·��
	 */
	public void updateImageByUserId(int userId, String userImage);
	
	/**
	 * �����û�loginTime
	 * @param userId �û�id
	 */
	public void updateLoginTimeByUserId(int userId);
	
	/**
	 * ɾ����ʱ��δ��¼���û�
	 * @param loginTime ָ���ĵ�¼ʱ��
	 */
	public void deleteUser(String loginTime);
}
