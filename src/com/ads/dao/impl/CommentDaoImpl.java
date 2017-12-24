package com.ads.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ads.dao.CommentDao;
import com.ads.pojo.TComment;
import com.ads.pojo.TUser;

@Repository("commentDao")
public class CommentDaoImpl extends HibernateDaoSupport implements CommentDao {
	//注入 sessionFactory
	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory); 
	}
	
	@Override
	public List<TComment> getCommentsByEpisodeId(int pageNum, int episodeId) {
		//获取当前 session
		Session session = this.getSessionFactory().getCurrentSession();
		//创建 criteria 对象
		Criteria criteria = session.createCriteria(TComment.class);
		//设置查询条件
		Criterion criterion = Restrictions.eq("TEpisode.episodeId", episodeId);
		criteria.add(criterion);
		criteria.addOrder(Order.desc("commentGood"));//按点赞降序排列
		criteria.setFirstResult((pageNum-1) * 10);
		criteria.setMaxResults(10);
		
		@SuppressWarnings("unchecked")
		List<TComment> comments = criteria.list();
		return comments;
	}

	@Override
	public long getCommentNum(int episodeId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "SELECT count(*) FROM TComment WHERE TEpisode.episodeId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, episodeId);
		Long count = (Long) query.list().get(0);
		if (count != null) {
			return count;
		}
		return 0;
	}

	@Override
	public int getGoodComment(int userId, int commentId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "SELECT count(*) FROM TComment c left outer join c.TUsers u "
				+"WHERE u.userId = ? AND c.commentId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		query.setParameter(1, commentId);
		int count = ((Long) query.list().get(0)).intValue();
		return count;
	}
	
	@Override
	public int getCommentIdByUserIdAndEpisodeId(int userId, int episodeId) {
		//获取当前 session
		Session session = this.getSessionFactory().getCurrentSession();
		//创建 criteria 对象
		Criteria criteria = session.createCriteria(TComment.class);
		//设置查询条件
		Criterion criterion1 = Restrictions.eq("TEpisode.episodeId", episodeId);
		Criterion criterion2 = Restrictions.eq("TUser.userId", userId);
		criteria.add(criterion1);
		criteria.add(criterion2);
		criteria.addOrder(Order.desc("commentId"));
		criteria.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<TComment> comments = criteria.list();
		return comments.get(0).getCommentId();
	}
	
	@Override
	public void insertGoodComment(int commentId, int userId) {
		Session session = this.getSessionFactory().getCurrentSession();
		TUser user = session.get(TUser.class, userId);//用户
		TComment comment = session.get(TComment.class, commentId);//评论
		//设置关联关系
		user.getTComments().add(comment);
		comment.getTUsers().add(user);
		comment.setCommentGood(comment.getCommentGood()+1);
		//保存数据
		session.update(comment);
		session.update(user);
	}

	@Override
	public void insertComment(TComment comment) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.save(comment);
	}
	
	@Override
	public int deleteComment(int commentId) {
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "DELETE FROM TComment c WHERE c.commentId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, commentId);
		
		return query.executeUpdate();
	}
}
