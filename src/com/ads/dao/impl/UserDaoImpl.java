package com.ads.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ads.dao.UserDao;
import com.ads.pojo.TUser;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory); 
	}
	
	/**
	 * 通过用户id获取用户
	 * @return TUser
	 */
	@Override
	public TUser getUserById(int userId) {
		Session session = this.getSessionFactory().openSession();
		return session.get(TUser.class, userId);
	}

	@Override
	public void insertUser(int userId, String userNickname, String userPassword) {
		
	}

	@Override
	public int getNewUserId() {
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
