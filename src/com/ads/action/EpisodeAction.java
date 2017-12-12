package com.ads.action;

import com.ads.pojo.TEpisode;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EpisodeAction extends ActionSupport implements ModelDriven<TEpisode>{
	private static final long serialVersionUID = 1L;
	private TEpisode episode;
	
	
	@Override
	public TEpisode getModel() {
		this.episode = new TEpisode();
		return episode;
	}

}
