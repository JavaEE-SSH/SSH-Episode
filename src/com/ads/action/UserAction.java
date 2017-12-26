package com.ads.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

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
import com.ads.util.ImageCutUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/user")
@ParentPackage("json-default")
public class UserAction extends ActionSupport implements ModelDriven<TUser>, SessionAware, RequestAware {
	private static final long serialVersionUID = 1L;
	@Resource
	private UserService userSerivce;
	private Map<String, Object> sessionMap;
	private Map<String, Object> requestMap;
	
	//截图相关属性
	private File myFile;
//	private String myFileContentType;
	private String myFileFileName;
	private String destPath;
	private double x1;
	private double y1;
	private double w;
	private double h;
	
	private TUser user;
	private int type;

	//getter and setter
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
//	public String getMyFileContentType() {
//		return myFileContentType;
//	}
//	public void setMyFileContentType(String myFileContentType) {
//		this.myFileContentType = myFileContentType;
//	}
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
	public double getX1() {
		return x1;
	}
	public void setX1(double x1) {
		this.x1 = x1;
	}
	public double getY1() {
		return y1;
	}
	public void setY1(double y1) {
		this.y1 = y1;
	}
	public double getW() {
		return w;
	}
	public void setW(double w) {
		this.w = w;
	}
	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	//实现接口方法
	@Override
	public TUser getModel() {
		this.user = new TUser();
		return user;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.requestMap = arg0;
	}

	//action 开始
	/**
	 * 异步：登录
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
	
	/**
	 * 异步：修改个人信息
	 */
	@Action(value="updateUserInfo_ajax",
			results={
					@Result(name=SUCCESS, type="json")
			})
	public String updateUserInfo_ajax() {
		//获取对应 userId 的 User
		TUser u = this.userSerivce.getUserById(this.user.getUserId());
		
		//修改指定信息
		if (type == 1) {
			u.setUserNickname(this.user.getUserNickname());
		}
		else if (type == 2) {
			u.setUserGender(this.user.getUserGender());
		}
		else if (type == 3) {
			u.setUserPassword(this.user.getUserPassword());
		}
		//更新数据库
		this.userSerivce.updateUserInfo(u);
		this.sessionMap.put("user", u);//保存 user 到 session 中
		
		return SUCCESS;
	}
	
	/**
	 * 上传头像并截图
	 */
	@Action(value="uploadImage",
			results={
					@Result(name=SUCCESS, type="redirect", location="/episode/personal_center.jsp"),
					@Result(name=ERROR, type="redirect", location="/episode/index.jsp")
			})
	public String uploadImage() {
		//获取当前 user
		TUser u = this.userSerivce.getUserById((int)this.requestMap.get("userId"));
		destPath = ServletActionContext.getServletContext().getRealPath("/images");
		
		System.out.println("My file path: " + myFile);
		System.out.println("My file name: " + myFileFileName);
		System.out.println("DestPath: " + destPath);
		File destFile = new File(destPath,myFileFileName);
		
		Date date = new Date(System.currentTimeMillis());  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");  
        String fileName = dateFormat.format(date) + myFileFileName;
        
        File finalFile = new File(destPath,fileName);
		try {
			FileUtils.copyFile(myFile, destFile);
			Boolean flag = ImageCutUtil.cutImage(destFile.getAbsolutePath(), finalFile.getAbsolutePath(), (int)x1, (int)y1, (int)w, (int)h);
			if (flag) {
				u.setUserImage(fileName);
			}
			else {
				System.out.println("剪切未成功");
			}

			this.userSerivce.updateUserInfo(u);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		
		this.sessionMap.put("user", u);//保存 user 到 session 中
		
		return SUCCESS;
	}
	
	/**
	 * 注册
	 * @return
	 */
	@Action(value="userRegister",
			results={
					@Result(name=SUCCESS, type="redirect", location="/episode/index.jsp")
			})
	public String userRegister() {
		
		String userNickname = this.user.getUserNickname();
		String userPassword = this.user.getUserPassword();
		int userId = userSerivce.insertUser(userNickname, userPassword);
		requestMap.put("user_id", userId);
		
		return SUCCESS;
	}
}
