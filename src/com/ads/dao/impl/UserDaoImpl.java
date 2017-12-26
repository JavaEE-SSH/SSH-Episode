package com.ads.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
		Session session = this.getSessionFactory().getCurrentSession();
		TUser user = new TUser();
		user.setUserId(userId);
		user.setUserNickname(userNickname);
		user.setUserPassword(userPassword);
		user.setLoginTime(new Date());
		user.setUserGender(1);
		user.setUserImage("default.png");
		session.save(user);
	}

	@Override
	public void updateUser(TUser user) {
		Session session = this.getSessionFactory().getCurrentSession();
		
		session.merge(user);
	}
	
	@Override
	public int getNewUserId() {
		Session session = this.getSessionFactory().getCurrentSession();
		//创建 criteria 对象
		Criteria criteria = session.createCriteria(TUser.class);		
		criteria.addOrder(Order.desc("userId"));
		criteria.setMaxResults(1);//只返回一条数据
		
		return ((TUser) criteria.uniqueResult()).getUserId()+1;
	}

	@Override
	public void deleteUser(Date loginTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Session session = this.getSessionFactory().getCurrentSession();
		
		String hql = "DELETE FROM TUser u WHERE u.loginTime < ?";
		
		Query query = session.createQuery(hql);
		query.setParameter(0, loginTime);
		int count = query.executeUpdate();
		
		System.out.println(sdf.format(new Date())+" : 删除用户"+count+"条");
	}
}
