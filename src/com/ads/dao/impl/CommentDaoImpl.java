package com.ads.dao.impl;

import java.util.Set;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.ads.dao.CommentDao;
import com.ads.pojo.TComment;

public class CommentDaoImpl extends HibernateDaoSupport implements CommentDao {

	@Override
	public Set<TComment> getCommentsByEpisodeId(String episodeId) {
		return null;
	}

	@Override
	public void addGoodCommentById(String commentId) {

	}

	@Override
	public void insertGoodComment(String commentId, String userId) {

	}

	@Override
	public int getGoodComment(String commentId, String userId) {
		return 0;
	}

	@Override
	public void insertComment(String commentContent, String userId, String episodeId) {

	}

	@Override
	public String getNewCommentId() {
		return null;
	}

	@Override
	public void deleteComment(String commentId) {

	}

}
