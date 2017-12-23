package com.ads.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.RequestAware;

import com.ads.pojo.TEpisode;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
@Namespace("/episode")
public class TestAction extends ActionSupport implements ModelDriven<TEpisode>, RequestAware{
	private TEpisode episode;
	private Map<String, Object> requestMap;
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.requestMap = arg0;
	}

	@Override
	public TEpisode getModel() {
		// TODO Auto-generated method stub
		this.episode = new TEpisode();
		return episode;
	}

	@Action(value="turnAction",
			results={
					@Result(name=SUCCESS, location="/episode/content.jsp")
			})
	public String turnTOContent() {
		this.episode = (TEpisode)requestMap.get("episode");
		System.out.println(this.episode.toString());
		
		return SUCCESS;
	}
}
