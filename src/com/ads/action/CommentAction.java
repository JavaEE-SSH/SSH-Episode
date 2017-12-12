package com.ads.action;

import com.ads.pojo.TComment;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommentAction extends ActionSupport implements ModelDriven<TComment> {
	private static final long serialVersionUID = 1L;
	private TComment comment;
	
	@Override
	public TComment getModel() {
		this.comment = new TComment();
		
		return comment;
	}

}
