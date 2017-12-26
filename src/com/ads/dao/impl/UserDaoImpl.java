package com.ads.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ads.dao.UserDao;
import com.ads.pojo.TComment;
import com.ads.pojo.TEpisode;
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
		Session session = this.getSessionFactory().getCurrentSession();
		TUser user = new TUser();
		user.setUserId(userId);
		user.setUserNickname(userNickname);
		user.setUserPassword(userPassword);
		user.setLoginTime(new Date());
		user.setUserGender(1);
		user.setUserImage("");
		session.save(user);
	}

	@Override
	public void updateUser(TUser user) {
		Session session = this.getSessionFactory().getCurrentSession();
		
		session.merge(user);
	}
	@Override
	public int getNewUserId(){
		Session session = this.getSessionFactory().getCurrentSession();
		//创建 criteria 对象
		Criteria criteria = session.createCriteria(TUser.class);		
		@SuppressWarnings("unchecked")
		List<TUser> TUsers = criteria.list();
		
		return TUsers.get(TUsers.size()-1).getUserId()+1;
	}
}
