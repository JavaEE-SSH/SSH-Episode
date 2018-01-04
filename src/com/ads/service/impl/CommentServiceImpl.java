package com.ads.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ads.dao.CommentDao;
import com.ads.dao.EpisodeDao;
import com.ads.dao.UserDao;
import com.ads.pojo.TComment;
import com.ads.pojo.TEpisode;
import com.ads.pojo.TUser;
import com.ads.service.CommentService;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {
	@Resource
	private CommentDao commentDao;
	@Resource
	private UserDao userDao;
	@Resource
	private EpisodeDao episodeDao;

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
	public void addGoodComment(int commentId, int userId) {
		commentDao.insertGoodComment(commentId, userId);
	}

	@Override
	public int insertComment(String commentContent, int userId, int episodeId) {
		TUser user = userDao.getUserById(userId);
		TEpisode episode = episodeDao.getEpisodeById(episodeId);
		int commentId = commentDao.getNewCommentId();
		TComment comment = new TComment(episode, user, commentContent, 0);
		comment.setCommentId(commentId);
		commentDao.insertComment(comment);
		
		return commentId;
	}

	@Override
	public int deleteComment(int commentId) {
		return commentDao.deleteComment(commentId);
	}
}
