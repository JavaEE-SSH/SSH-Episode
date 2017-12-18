package com.ads.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.stereotype.Controller;

import com.ads.pojo.TEpisode;
import com.ads.service.EpisodeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@ParentPackage("json-default")
@Namespace("/episode")
public class EpisodeAction extends ActionSupport implements ModelDriven<TEpisode>, RequestAware{
	private static final long serialVersionUID = 1L;
	@Resource
	private EpisodeService episodeService;
	private TEpisode episode;
	private Map<String, Object> requestMap;
	
	@Override
	public TEpisode getModel() {
		this.episode = new TEpisode();
		return episode;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.requestMap = arg0;
	}

	//action 开始
	/**
	 * 传入段子id，获取此段子相关信息
	 * @return SUCCESS
	 */
	@Action(value="episodeInfo",
			results={
					@Result(name=SUCCESS, location="/episode/content.jsp")
			})
	public String getEpisodeById() {
		TEpisode e = this.episodeService.getEpisodeById(this.episode.getEpisodeId());
		requestMap.put("episode", e);
		
		return SUCCESS;
	}
}
