package com.ads.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
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
	
	private File myFile;
	private String myFileContentType;
	private String myFileFileName;
	private String destPath;
	
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

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}
	
	
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
	
	@Action(value="updateUserInfo_ajax",
			results={
					@Result(name=SUCCESS, type="json")
			})
	public String updateUserInfo_ajax() {
		
		this.request = ServletActionContext.getRequest();
		
		String type = this.request.getParameter("type");
		String user_id = this.request.getParameter("user_id");
		String user_info = this.request.getParameter("user_info");
		
		/*String type = (String) this.requestMap.get("type");
		String user_id = (String) this.requestMap.get("user_id");
		String user_info = (String) this.requestMap.get("user_info");*/
		
		if(type.equals("1")) {
			this.userSerivce.upDateUserNicknameById(Integer.parseInt(user_id), user_info);
			ActionContext.getContext().getValueStack().push(1);
		}else if(type.equals("2")) {
			this.userSerivce.upDateUserGenderById(Integer.parseInt(user_id), Integer.parseInt(user_info));
			ActionContext.getContext().getValueStack().push(1);
		}else if(type.equals("3")) {
			this.userSerivce.upDateUserPasswordById(Integer.parseInt(user_id), user_info);
			ActionContext.getContext().getValueStack().push(1);
		}
		TUser u = this.userSerivce.getUserById(Integer.parseInt(user_id));
		this.sessionMap.put("user", u);//保存 user 到 session 中
		return SUCCESS;
	}
	
	@Action(value="uploadImage",
			results={
					@Result(name=SUCCESS,location="/episode/personal_center.jsp")
			})
	public String uploadImage() {
		
		this.request = ServletActionContext.getRequest();
		String user_id = this.request.getParameter("user_id");
		destPath = ServletActionContext.getServletContext().getRealPath("/images");

		System.out.println("Src File name: " + myFile);
		System.out.println("Dst File name: " + myFileFileName);
		System.out.println("destPath: " + destPath);
		File destFile = new File(destPath,myFileFileName);
		try {
			FileUtils.copyFile(myFile, destFile);
			this.userSerivce.upDateUserImageById(Integer.parseInt(user_id), myFileFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		TUser u = this.userSerivce.getUserById(Integer.parseInt(user_id));
		this.sessionMap.put("user", u);//保存 user 到 session 中
		return SUCCESS;
	}
	
}
