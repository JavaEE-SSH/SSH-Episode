package com.ads.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ads.dao.CommentDao;
import com.ads.pojo.TComment;
import com.ads.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Resource
	private CommentDao commentDao;

	@Override
	public List<TComment> getCommentsByEpisodeId(int pageNum, int episodeId) {
		
		return commentDao.getCommentsByEpisodeId(pageNum, episodeId);
	}

	@Override
	public long getCommentNum(int episodeId) {
		return commentDao.getCommentNum(episodeId);
	}

	@Override
	public boolean isGoodComment(int userId, int commentId) {
		int count = commentDao.getGoodComment(userId, commentId);
		
		if (count != 0) {
			return true;
		}
		return false;
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
