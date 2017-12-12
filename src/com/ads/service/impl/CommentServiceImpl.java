package com.ads.service.impl;

import java.util.Set;

import com.ads.dao.CommentDao;
import com.ads.pojo.TComment;
import com.ads.service.CommentService;

public class CommentServiceImpl implements CommentService {
	private CommentDao commentDao;

	@Override
	public Set<TComment> getCommentsByEpisodeId(String episodeId) {
		return null;
	}

	@Override
	public void addGoodComment(String commentId, String userId) {
		
	}

	@Override
	public int getGoodComment(String commentId, String userId) {
		return 0;
	}

	@Override
	public String insertComment(String commentContent, String userId, String episodeId) {
		return null;
	}

	@Override
	public void deleteComment(String commentId) {
		
	}
}
