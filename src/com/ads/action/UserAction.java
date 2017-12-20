package com.ads.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import com.ads.pojo.TUser;
import com.ads.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Namespace("/user")
@ParentPackage("json-default")
public class UserAction extends ActionSupport implements ModelDriven<TUser>, RequestAware, SessionAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> requestMap;
	private Map<String, Object> sessionMap;
	@Resource
	private UserService userSerivce;
	private TUser user;
//	private String[] page;
//	
//	//属性的 getter setter
//	public String[] getPage() {
//		return page;
//	}
//	public void setPage(String[] page) {
//		this.page = page;
//	}

	//实现接口方法
	@Override
	public TUser getModel() {
		this.user = new TUser();
		return user;
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
	 * 异步登录
	 * 
	 * @param userId
	 * @param userPassword
	 * 
	 */
	@Action(value="userLogin_ajax_*",
			results={
					@Result(name=SUCCESS, type="json")
			})
	public String userLogin_ajax() {
		int userId = this.user.getUserId();// 传递的用户id
		String password = this.user.getUserPassword(); //  传递的用户密码
		TUser u = this.userSerivce.getUserById(userId);// 从数据库中获取的用户信息

		if (u != null
				&& password.equals(u.getUserPassword())) {//登录成功
			//保存 u
			this.sessionMap.put("user", u);//保存 user 到 session 中
			ActionContext.getContext().getValueStack().push(1);
		}
		else {//登录失败
			ActionContext.getContext().getValueStack().push(0);// 在栈顶放一个 0, 标志登录失败
		}
		
		return SUCCESS;
	}
}
