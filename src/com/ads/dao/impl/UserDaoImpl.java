package com.ads.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ads.dao.UserDao;
import com.ads.pojo.TUser;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory); 
	}
	
	@Override
	public TUser getUserById(int userId) {
		Session session = this.getSessionFactory().getCurrentSession();
		
		return session.get(TUser.class, userId);
	}

	@Override
	public void insertUser(int userId, String userNickname, String userPassword) {
		
	}

	@Override
	public void updateUser(TUser user) {
		Session session = this.getSessionFactory().getCurrentSession();
		
		session.merge(user);
	}
}
