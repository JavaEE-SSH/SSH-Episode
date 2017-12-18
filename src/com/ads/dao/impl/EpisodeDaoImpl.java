package com.ads.dao.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ads.dao.EpisodeDao;
import com.ads.pojo.TEpisode;

@Repository("episodeDao")
public class EpisodeDaoImpl extends HibernateDaoSupport implements EpisodeDao {

	@Override
	public Set<TEpisode> getEpisodes() {
		return null;
	}

	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory); 
	}
	
	@Override
	@Transactional
	public TEpisode getEpisodeById(int episodeId) {
		//¿ªÆô»á»°
		Session session = this.getSessionFactory().openSession();
		return session.get(TEpisode.class, episodeId);
	}

	@Override
	public Set<TEpisode> getEpisodeByUserId(int userId) {
		return null;
	}

	@Override
	public void addEpisodeGoodById(int episodeId) {
		
	}

	@Override
	public void insertEpisode_Good(int userId, int episodeId) {
		
	}

	@Override
	public int getGoodEpisode(int episodeId, int userId) {
		return 0;
	}

	@Override
	public int getCollect(int episodeId, int userId) {
		return 0;
	}

	@Override
	public void insertCollectEpisode(int userId, int episodeId) {
		
	}

	@Override
	public void deleteCollectEpisode(int userId, int episodeId) {
		
	}

	@Override
	public void deleteEpisode(String addTime) {
		
	}

	@Override
	public void insertEpisode(String episodeContent) {
		
	}

}
