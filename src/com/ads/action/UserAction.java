package com.ads.action;

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

import com.ads.pojo.TUser;
import com.ads.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/user")
@ParentPackage("json-default")
public class UserAction extends ActionSupport implements ModelDriven<TUser>, RequestAware, SessionAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> requestMap;
	private Map<String, Object> sessionMap;
	private HttpServletRequest request;
	@Resource
	private UserService userSerivce;
	private TUser user;
//	private String[] page;
//	
//	//灞炴�х殑 getter setter
//	public String[] getPage() {
//		return page;
//	}
//	public void setPage(String[] page) {
//		this.page = page;
//	}

	//瀹炵幇鎺ュ彛鏂规硶
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

	//action 寮�濮�
	/**
	 * 寮傛鐧诲綍
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
		int userId = this.user.getUserId();// 浼犻�掔殑鐢ㄦ埛id
		String password = this.user.getUserPassword(); //  浼犻�掔殑鐢ㄦ埛瀵嗙爜
		TUser u = this.userSerivce.getUserById(userId);// 浠庢暟鎹簱涓幏鍙栫殑鐢ㄦ埛淇℃伅

		if (u != null
				&& password.equals(u.getUserPassword())) {//鐧诲綍鎴愬姛
			//淇濆瓨 u
			this.sessionMap.put("user", u);//淇濆瓨 user 鍒� session 涓�
			ActionContext.getContext().getValueStack().push(1);
		}
		else {//鐧诲綍澶辫触
			ActionContext.getContext().getValueStack().push(0);// 鍦ㄦ爤椤舵斁涓�涓� 0, 鏍囧織鐧诲綍澶辫触
		}
		
		return SUCCESS;
	}
	
	@Action(value="updateUserInfo_ajax",
			results={
					@Result(name=SUCCESS, type="json")
			})
	public String updateUserInfo_ajax() {
		
		this.request = ServletActionContext.getRequest();
		
		String type = this.request.getParameter("type");
		String user_id = this.request.getParameter("user_id");
		String user_info = this.request.getParameter("user_info");
		
		if(type.equals("1")) {
			this.userSerivce.upDateUserNicknameById(Integer.parseInt(user_id), user_info);
			ActionContext.getContext().getValueStack().push(1);
		}
		
		return SUCCESS;
	}
	@Action(value="userRegister",
			results={
					@Result(name=SUCCESS, type="json"),
					@Result(name=SUCCESS, location="/episode/index.jsp")
			})
	public String userRegister() {
		
		this.request = ServletActionContext.getRequest();		
		String userNickname = this.request.getParameter("userNickname");
		String userPassword = this.request.getParameter("userPassword");
		int userId = userSerivce.insertUser(userNickname, userPassword);
		requestMap.put("user_id", userId);
		
		return SUCCESS;
	}
}
