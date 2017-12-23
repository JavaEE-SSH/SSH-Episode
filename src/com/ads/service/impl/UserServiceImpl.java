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
	public TUser getUserByCommentId(int commentId) {
		return userDao.getUserByCommentId(commentId);
	}
}
