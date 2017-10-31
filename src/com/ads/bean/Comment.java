package com.ads.bean;

/**
 * POJO评论
 */
public class Comment {
	private String comment_id;//评论唯一编号
	private String comment_content;//评论内容
	private int comment_good;//评论点赞数
	private String user_id;//评论用户编号
	private String episode_id;//段子编号
	
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getComment_good() {
		return comment_good;
	}
	public void setComment_good(int comment_good) {
		this.comment_good = comment_good;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getEpisode_id() {
		return episode_id;
	}
	public void setEpisode_id(String episode_id) {
		this.episode_id = episode_id;
	}
}
