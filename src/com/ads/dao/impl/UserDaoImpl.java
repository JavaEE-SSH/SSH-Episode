package com.ads.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ads.dao.UserDao;
import com.ads.pojo.TComment;
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
	public TUser getUserByCommentId(int commentId) {
		//获取当前 session
		Session session = this.getSessionFactory().getCurrentSession();
		//获取指定 comment
		TComment comment = session.get(TComment.class, commentId);
		//获取 userId
		int userId = comment.getTUser().getUserId();
		//获取指定 user
		TUser user = session.get(TUser.class, userId);
		
		return user;
	}
	
	@Override
	public void insertUser(int userId, String userNickname, String userPassword) {
		
	}

	@Override
	public void updateUser(TUser user) {
		Session session = this.getSessionFactory().getCurrentSession();
		
		session.merge(user);
	}

	@Override
	public void upDateUserNicknameById(int userId, String nickName) {
		Session session = this.getSessionFactory().getCurrentSession();
		
		TUser user = session.get(TUser.class, userId);
		user.setUserNickname(nickName);
		
		session.merge(user);
	}

	@Override
	public void upDateUserGenderById(int userId, int gender) {
		Session session = this.getSessionFactory().getCurrentSession();
		
		TUser user = session.get(TUser.class, userId);
		user.setUserGender(gender);
		
		session.merge(user);
	}

	@Override
	public void upDateUserPasswordById(int userId, String password) {
		Session session = this.getSessionFactory().getCurrentSession();
		
		TUser user = session.get(TUser.class, userId);
		user.setUserPassword(password);
		
		session.merge(user);
		
	}

	@Override
	public void upDateUserImageById(int userId, String image) {
		Session session = this.getSessionFactory().getCurrentSession();
		
		TUser user = session.get(TUser.class, userId);
		user.setUserImage(image);
		
		session.merge(user);
	}
}
