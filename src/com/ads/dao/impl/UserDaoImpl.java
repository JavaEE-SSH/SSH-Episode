package com.ads.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ads.dao.UserDao;
import com.ads.pojo.TUser;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public TUser getUserById(String userId) {
		return null;
	}

	@Override
	public void insertUser(String userId, String userNickname, String userPassword) {
		
	}

	@Override
	public int getNewUserId() {
		return 0;
	}

	@Override
	public void updateNicknameByUserId(String userId, String userNickname) {
		
	}

	@Override
	public void updateGenderByUserId(String userId, String userGender) {
		
	}

	@Override
	public void updatePasswordByUserId(String userId, String userPassword) {
		
	}

	@Override
	public void updateImageByUserId(String userId, String userImage) {
		
	}

	@Override
	public void updateLoginTimeByUserId(String userId) {
		
	}

	@Override
	public void deleteUser(String loginTime) {
		
	}

}
