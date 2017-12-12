package com.ads.action;

import com.ads.pojo.TUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<TUser> {
	private static final long serialVersionUID = 1L;
	private TUser user;
	
	@Override
	public TUser getModel() {
		this.user = new TUser();
		return user;
	}

}
