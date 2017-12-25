package com.ads.dao.impl;

import java.util.List;
import java.util.Set;

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

import com.ads.dao.EpisodeDao;
import com.ads.pojo.TComment;
import com.ads.pojo.TEpisode;

@Repository("episodeDao")
public class EpisodeDaoImpl extends HibernateDaoSupport implements EpisodeDao {
	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory); 
	}
	
	@Override
	public List<TEpisode> getEpisodes(int pageNum) {
		//鑾峰彇褰撳墠 session
		Session session = this.getSessionFactory().getCurrentSession();
		//鍒涘缓 criteria 瀵硅薄
		Criteria criteria = session.createCriteria(TEpisode.class);
		criteria.setFirstResult((pageNum-1) * 10);
		criteria.setMaxResults(10);
		
		@SuppressWarnings("unchecked")
		List<TEpisode> TEpisodes = criteria.list();
		return TEpisodes;
	}
	
	@Override
	public TEpisode getEpisodeById(int episodeId) {
		Session session = this.getSessionFactory().getCurrentSession();
		return session.get(TEpisode.class, episodeId);
	}

	@Override
	public void updateEpisode(TEpisode episode) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.merge(episode);
	}

	@Override
	public void deleteEpisode(String addTime) {
		
	}

	@Override
	public void insertEpisode(String episodeContent) {
		
	}
	public long getEpisodeNum(){
		Session session = this.getSessionFactory().getCurrentSession();
		String hql = "SELECT count(*) FROM TEpisode";
		Query query = session.createQuery(hql);
		Long count = (Long) query.list().get(0);
		if (count != null) {
			return count;
		}
		return 0;
	}

	@Override
	public List<TEpisode> getEpisodeByUserId(int userId, int pageNum) {
		
		Session session = this.getSessionFactory().getCurrentSession();
		String sql =  
                "SELECT e.episode_id,e.episode_content,e.add_date,e.episode_good "  
                    + "FROM t_episode e LEFT JOIN t_collect c ON e.episode_id=c.episode_id where c.user_id = "+userId;
		Query query = session.createSQLQuery(sql);
		query.setFirstResult((pageNum-1) * 10);
		query.setMaxResults(10);
				
		@SuppressWarnings("unchecked")
		List<TEpisode> TEpisodes = query.list();
		return TEpisodes;
	}

	@Override
	public long getEpisodeNumByUserId(int userId) {
		
		Session session = this.getSessionFactory().getCurrentSession();
		String sql =  
                "SELECT e.episode_id "  
                    + "FROM t_episode e LEFT JOIN t_collect c ON e.episode_id=c.episode_id where c.user_id = "+userId;
		Query query = session.createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<TEpisode> TEpisodes = query.list();
		Number count = TEpisodes.size();
		if (count != null) {
			return count.intValue();
		}
		return 0;
	}
}
