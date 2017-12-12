package com.ads.service.impl;

import com.ads.dao.UserDao;
import com.ads.pojo.TUser;
import com.ads.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	@Override
	public TUser getUserById(String userId) {
		return null;
	}

	@Override
	public int insertUser(String userNickname, String userPassword) {
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
