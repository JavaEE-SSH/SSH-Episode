package com.ads.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.ads.pojo.TComment;
import com.ads.service.CommentService;
import com.ads.service.UserService;
import com.ads.util.Page;
import com.ads.util.PageUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
@Namespace("/comment")
public class CommentAction extends ActionSupport implements ModelDriven<Page> {
	private static final long serialVersionUID = 1L;
	@Resource
	private CommentService commentService;
	@Resource
	private UserService userService;
	private Page page;
	private int episodeId;
	
	//getter and setter
	public int getEpisodeId() {
		return episodeId;
	}
	public void setEpisodeId(int episodeId) {
		this.episodeId = episodeId;
	}

	//ModelDriven 
	@Override
	public Page getModel() {
		this.page = new Page();
		
		return page;
	}

	//Action 开始
	@Action(value="getCommentsAndUsersByEpisodeId_ajax",
			results={
					@Result(name=SUCCESS, type="json")
			})
	public String getCommentsAndUsersByEpisodeId_ajax() {
		List<TComment> comments = commentService
				.getCommentsByEpisodeId(page.getPageNum(), episodeId);//评论s
		long total = commentService.getCommentNum(episodeId);//评论总数
		//设置page属性
		this.page.setHasNextPage(PageUtil.hasNextPage(page.getPageNum(), 10, total));
		this.page.setTotal(total);
		this.page.setPerPageNum(10);
		
		//防止加载额外数据
		for (int i=0; i<comments.size(); i++) {
			comments.get(i).setTEpisode(null);
			comments.get(i).setTUsers(null);
			comments.get(i).getTUser().setTComments(null);
			comments.get(i).getTUser().setTComments_1(null);
			comments.get(i).getTUser().setTEpisodes(null);
			comments.get(i).getTUser().setTEpisodes_1(null);
		}
		
		//将配置好的数据打包
		Map<String, Object> data = new HashMap<>();
		data.put("comments", comments);
		data.put("page", page);
		//将数据压入栈顶
		ActionContext.getContext().getValueStack().push(data);
		return SUCCESS;
	}
}
