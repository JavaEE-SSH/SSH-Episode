package com.ads.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ads.dao.CommentDao;

public class CommentDaoImpl extends HibernateDaoSupport implements CommentDao {

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
