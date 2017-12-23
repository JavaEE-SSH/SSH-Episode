package com.ads.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.ads.pojo.TEpisode;
import com.ads.service.EpisodeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
@Namespace("/episode")
public class EpisodeAction extends ActionSupport implements ModelDriven<TEpisode>, RequestAware, SessionAware{
	private static final long serialVersionUID = 1L;
	@Resource
	private EpisodeService episodeService;
	private TEpisode episode;
	private Map<String, Object> requestMap;
	private Map<String, Object> sessionMap;
	private int userId;
	
	//getter and setter
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	//实现接口方法
	@Override
	public TEpisode getModel() {
		this.episode = new TEpisode();
		return episode;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.requestMap = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}

	//action 开始
	/**
	 * 根据 episodeId 获取段子信息
	 * @return SUCCESS
	 */
	@Action(value="getEpisodeById",
			results={
					@Result(name=SUCCESS, location="/episode/content.jsp")
			})
	public String getEpisodeById() {
		Integer episodeId = this.episode.getEpisodeId();
		TEpisode e = this.episodeService.getEpisodeById(episodeId);
		requestMap.put("episode", e);
		
		return SUCCESS;
	}
	
	/**
	 * 异步：点赞
	 */
	@Action(value="goodEpisode_ajax",
			results={
					@Result(name=SUCCESS, type="json")
			})
	public String goodEpisode_ajax() {
		episodeService.insertGoodEpisode(userId, episode.getEpisodeId());
		return SUCCESS;
	}
	
	/**
	 * 异步：添加收藏
	 */
	@Action(value="insertCollectEpisode_ajax",
			results={
					@Result(name=SUCCESS, type="json")
			})
	public String insertCollectEpisode_ajax() {
		episodeService.insertCollectEpisode(userId, episode.getEpisodeId());
		return SUCCESS;
	}
	
	/**
	 * 异步：取消收藏
	 */
	@Action(value="removeCollectEpisode_ajax",
			results={
					@Result(name=SUCCESS, type="json")
			})
	public String removeCollectEpisode_ajax() {
		episodeService.deleteCollectEpisode(userId, episode.getEpisodeId());
		return SUCCESS;
	}
}
