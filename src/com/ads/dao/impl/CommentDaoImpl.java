package com.ads.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ads.dao.CommentDao;
import com.ads.pojo.TComment;

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
	public void insertGoodComment(String commentId, String userId) {

	}

	@Override
	public void insertComment(String commentContent, String userId, String episodeId) {

	}
	
	@Override
	public void deleteComment(String commentId) {

	}
}
