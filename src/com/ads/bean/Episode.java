package com.ads.bean;

/**
 * POJO段子类
 * 
 */
public class Episode {
	private String episode_id;//段子唯一编号
	private String episode_content;//段子内容
	private int episode_good;//段子点赞数
	private int episode_collect;//段子收藏数
	private String add_time;//段子添加时间
	
	public String getEpisode_id() {
		return episode_id;
	}
	public void setEpisode_id(String episode_id) {
		this.episode_id = episode_id;
	}
	public String getEpisode_content() {
		return episode_content;
	}
	public void setEpisode_content(String episode_content) {
		this.episode_content = episode_content;
	}
	public int getEpisode_good() {
		return episode_good;
	}
	public void setEpisode_good(int episode_good) {
		this.episode_good = episode_good;
	}
	public int getEpisode_collect() {
		return episode_collect;
	}
	public void setEpisode_collect(int episode_collect) {
		this.episode_collect = episode_collect;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
}
