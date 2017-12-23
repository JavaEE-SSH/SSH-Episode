package com.ads.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ads.dao.UserDao;
import com.ads.pojo.TUser;
import com.ads.service.UserService;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	
	@Override
	public TUser getUserById(int userId) {
		return this.userDao.getUserById(userId);
	}

	@Override
	public int insertUser(String userNickname, String userPassword) {
		return 0;
	}

	@Override
	public void updateNicknameByUserId(int userId, String userNickname) {
		
	}

	@Override
	public void updateGenderByUserId(int userId, int userGender) {
		
	}

	@Override
	public void updatePasswordByUserId(int userId, String userPassword) {
		
	}

	@Override
	public void updateImageByUserId(int userId, String userImage) {
		
	}

	@Override
	public void updateLoginTimeByUserId(int userId) {
		
	}

	@Override
	public void deleteUser(String loginTime) {
		
	}

}
