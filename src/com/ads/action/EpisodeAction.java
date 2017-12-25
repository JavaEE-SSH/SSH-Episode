package com.ads.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.ads.pojo.TComment;
import com.ads.pojo.TEpisode;
import com.ads.service.EpisodeService;
import com.ads.util.Page;
import com.ads.util.PageUtil;
import com.opensymphony.xwork2.ActionContext;
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
	private Page page;
	private HttpServletRequest request;
	
	//getter and setter
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setPage(Page page) {
		this.page = page;
	}
	//瀹炵幇鎺ュ彛鏂规硶
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

	//action 寮�濮�
	/**
	 * 鏍规嵁 episodeId 鑾峰彇娈靛瓙淇℃伅
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
	 * 寮傛锛氱偣璧�
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
	 * 寮傛锛氭坊鍔犳敹钘�
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
	 * 寮傛锛氬彇娑堟敹钘�
	 */
	@Action(value="removeCollectEpisode_ajax",
			results={
					@Result(name=SUCCESS, type="json")
			})
	public String removeCollectEpisode_ajax() {
		episodeService.deleteCollectEpisode(userId, episode.getEpisodeId());
		return SUCCESS;
	}
	/**
	 * 寮傛锛氬彇娑堟敹钘�
	 */
	@Action(value="getEpisodes_ajax",
			results={
					@Result(name=SUCCESS, type="json")
			})
	public String getEpisodes_ajax() {
		System.out.println("Action");
		System.out.println(page.getPageNum());
		List<TEpisode> episodes = episodeService
				.getEpisodes(page.getPageNum());//璇勮s
		for(int i =0;i < episodes.size();i++){
			System.out.println(episodes.get(i).getEpisodeContent());
		}
		long total = episodeService.getEpisodeNum();//璇勮鎬绘暟
		System.out.println(total);
		//璁剧疆page灞炴��
		this.page.setHasNextPage(PageUtil.hasNextPage(page.getPageNum(), 10, total));
		this.page.setTotal(total);
		this.page.setPerPageNum(10);
		
		//防止额外数据加载
		for (int i=0; i<episodes.size(); i++) {
			episodes.get(i).setTComments(null);
			episodes.get(i).setTUsers(null);
			episodes.get(i).setTUsers_1(null);
		}
		
//		//灏嗛厤缃ソ鐨勬暟鎹墦鍖�
		Map<String, Object> data = new HashMap<>();
		data.put("episodes", episodes);
		data.put("page", page);
		//灏嗘暟鎹帇鍏ユ爤椤�
		ActionContext.getContext().getValueStack().push(data);
		return SUCCESS;
	}
	
	@Action(value="getEpisodeByUserId_ajax",
			results={
					@Result(name=SUCCESS, type="json")
			})
	public String getEpisodeByUserId_ajax() {
		
		List<TEpisode> episodes = episodeService
				.getEpisodesByUserId(userId, page.getPageNum());
		
		long total = episodeService.getEpisodeNumByUserId(userId);
		
		this.page.setHasNextPage(PageUtil.hasNextPage(page.getPageNum(), 10, total));
		this.page.setTotal(total);
		this.page.setPerPageNum(10);

		Map<String, Object> data = new HashMap<>();
		data.put("episodes", episodes);
		data.put("page", page);
		
		ActionContext.getContext().getValueStack().push(data);
		
		return SUCCESS;
	}
}
