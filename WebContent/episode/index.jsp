<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="com.ads.pojo.TUser,java.util.Enumeration,com.ads.pojo.TEpisode,java.util.List,java.util.ArrayList"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//----登出处理
	if (request.getParameter("logout") != null
		&& request.getParameter("logout").equals("1")) {
		session.removeAttribute("user");
		session.removeAttribute("flag");
	}
	//----获取用户数据
	TUser user = (TUser)session.getAttribute("user");
	int flag = 0;
	if (user != null) {
		flag = 1;
	}
	else {
		user = new TUser();
	}
	//----注册处理
	String user_id = null;
	if (request.getAttribute("user_id") != null) {
		user_id = request.getAttribute("user_id").toString();
		System.out.println("1"+user_id);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%= basePath %>>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>【爱段社】</title>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/index-style.css" rel="stylesheet" type="text/css"/>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/index.js" type="text/javascript"></script>
</head>
<body class="page-channel">
	<div class="header-top"></div>
	<div class="header-search">
		<div class="content">
			<a href="episode/index.jsp" class="logo iconfont icon-logo"></a>
			<div class="suggestion-search-box">
				<input placeholder="点击搜索" value="" class="input input-search"/>
			</div>
			<button class="btn btn-search"  onclick="javascript:alert('没实现，谢谢！');">搜  索</button>
			<div class="float-right header-links">
				<button class="btn btn-login js-to-login" style="display: <%=flag==0?"inline-block":"none"%>;">登录</button>
				<!-- 登陆成功，个人信息 -->
				<div class="user-info-box" style="display: <%=flag==1?"block":"none"%>;">
					<a class="user-info">
						<img src="images/<%= user.getUserImage()==null?"she.png":user.getUserImage()%>" class="profile"/>
						<span class="nickname"><%= user.getUserNickname()==null?"":user.getUserNickname()%></span>
						<span class="iconfont icon-down"></span>
					</a>
					<div class="user-info-menu" style="display: none;">
						<a href="episode/personal_center.jsp">个人中心</a>
						<a class="logout-btn">退出</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="main index-main">
		<!-- 右侧广告 -->
		<div class="aside">
			<!-- <div class="normal-channel-info js-channel-subscribe-item">
				<img src="images/she.png" alt="icon" class="channel-image"/>
				<p class="user-info"></p>
			</div> -->
			<a href="<%= flag==1?"episode/personal_center.jsp":"episode/index.jsp"%>">
				<div class="profile-wrapper">
					<img src="images/<%= user.getUserImage()==null?"she.png":user.getUserImage()%>" />
					<p id="p-nick" class="nickname"><%= user.getUserNickname()==null?"":user.getUserNickname()%></p>
					<p><%= user.getUserId()==0?"":user.getUserId()%></p>
				</div>
			</a>
			<div class="advertisement">
				<p>老<br />板<br />，<br />打<br />广<br />告<br />吗<br />？</p>
			</div>
		</div>
		<!-- 左侧段子 -->
		<div id="episode" class="channel-news channel-new-0">
		</div>
		<div class="no-more-episodes" style="display: inline-block;">已加载全部评论</div>
	</div>
	
	<div class="footer-mini-wrapper">
		<div class="footer-mini">
			<p>
				<a>
					<span class="icon-jgw"></span><span>京公网安备 xxxxxxxxxxxxx号</span>
				</a>
				<span>蜀网文[2017]xxxx-xxx号</span> <span>蜀ICP证xxxxxx号</span>
			</p>
			<p>
				<a href="mailto:wlx-959@qq.com">举报邮箱：wlx.959@qq.com</a>
				<span>公司名称：明日之星概不负责公司</span>
			</p>
		</div>
	</div>
	
	<!-- 登录界面 -->
	<div class="dialog login-register-dialog login-dialog" style="display: none;">
		<div class="close iconfont icon-close"></div>
		<div class="logo iconfont icon-logo"></div>
		<form name="login-form" class="login-form" action="user/userLogin" method="post">
			<div class="input-box input-username-box">
				<input id="login-userId" type="text" class="input-username" name="userId" placeholder="用户名" value="">
			</div>
			<div class="input-box input-password-box">
				<input id="login-userPassword" type="password" class="input-password" name="userPassword" placeholder="密码" value="">
			</div>
			<div class="error-msg-inner">
				<p class="error-msg"></p>
			</div>
			<input type="hidden" value="index" name="page"/>
			<button type="button" class="btn-login" id="click-to-login" style="pointer-events: auto;">登录</button>
			<button type="button" class="btn-register js-to-register btn-has-border">注册</button>
		</form>
	</div>
	<!-- 注册界面 -->
	<div class="dialog login-register-dialog register-dialog" style="display: none;">
		<div class="close iconfont icon-close"></div>
		<div class="logo iconfont icon-logo"></div>
		<form name="register-form" class="register-form" action="user/userRegister" method="post">
			<div class="input-box input-username-box">
				<input id="register-userId" type="text" class="input-username" name="userNickname" placeholder="给自己起一个昵称吧" value="">
			</div>
			<div class="input-box input-password-box">
				<input id="register-userPassword" type="password" class="input-password" name="userPassword" placeholder="在这里输入您的密码" value="">
			</div>
			<div class="input-box input-password-box">
				<input id="register-userPassword2" type="password" class="input-password2" name="userPassword2" placeholder="请重新确认一遍密码" value="">
			</div>
			<div class="error-msg-inner">
				<p class="error-msg"></p>
			</div>
			<button type="button" class="btn-register" id="click-to-register">注册</button>
		</form>
	</div>
	<!-- 注册成功界面 -->
	<div class="dialog register-ok-dialog" style="display: <%= user_id==null?"none":"block"%>;">
		<div class="close iconfont icon-close"></div>
		<div class="logo iconfont icon-logo"></div>
		<p>注册成功！<br />您的账号为：<%= user_id%>,快用账号登录吧！</p>
	</div>
	<div class="mask" style="display: <%= user_id==null?"none":"block"%>;"></div>
	<div class="widget-tool">
	 	<div class="item back-up icon iconfont icon-back-up anim" style="display:none;"></div>
	 	<div class="item refresh icon iconfont icon-refresh anim"></div>
	</div>
	
<script type="text/javascript">
	$(document).ready(function() {
		var page = null;
        var loading = false;//标志未加载
        
		//获取段子数据
		$.ajax({
			type : "post",
			url : "episode/getEpisodes_ajax",
			data : {
				"page.pageNum" : 1
			},
			dataType:"json",
			success : function(data) {
 				page = data.page;
				fnCallback_episode(data);
			},
			error : function(msg) {
				alert("请求失败！");
			}
		});
		//滚动加载数据
		$(window).scroll(function(){
            var srollPos = $(window).scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)
            var totalheight = parseFloat($(window).height()) + parseFloat(srollPos);
            
            if(($(document).height()) <= totalheight + 50) {//执行添加一页数据!!滚动过快重复加载数据
            	if (page.hasNextPage && !loading) {//有下一页,且没有加载
            		loading = true;
            		//获取下一页段子数据
            		$.ajax({
            			type : "post",
            			url : "episode/getEpisodes_ajax",
            			data : {
            				"page.pageNum" : page.pageNum+1
            			},
            			dataType:"json",
            			success : function(data) {
            				page = data.page;//更新 page_data 数据
            				fnCallback_episode(data);
            			},
            			error : function(msg) {
            				alert("请求失败！");
            			}
            		}).done(function() {
            			loading = false;
            		})
            	}
            	else if (!page.hasNextPage) {//数据加载完成
            		$(".no-more-episodes").html("已加载全部内容");
            	}
            }
        });
	})
	function fnCallback_episode(data) {

		var list = data.episodes;//段子分页数据
		var content = $("#episode");
		
		if (list != null) {
			for (var i=0; i<list.length; i++) {
				content.append('<a href="episode/getEpisodeById?episodeId='+list[i].episodeId+'" target="_blank" class="item doc doc-joke">'
						+'<div class="doc-title">'+list[i].episodeContent.substr(0,10)+'...</div>'
						+'<h3 class="doc-summary">'+list[i].episodeContent+'</h3>'
						+'<div class="doc-info"></div></a>');

			}
		}
		else {
			alert("读取数据失败，请稍后重试！");
		}
	}

	//登录回调函数
	function fnCallback_login(data) {
		if (data == 0) {
			$(".login-dialog .error-msg-inner").css("display", "block");
			$(".login-dialog .error-msg").html('用户名或密码错误！');
		}
		else {
			window.location.replace("episode/index.jsp");
		}
	}
	//处理登录AJAX
 	$("#click-to-login").click(function() {
 		if ($("#login-userId").val() == ''
 				|| $("#login-userPassword").val() == '') {
 			$(".login-dialog .error-msg-inner").css("display", "block");
 			$(".login-dialog .error-msg").html('用户名和密码不能为空！');
 		}
 		else if ($("#login-userId").val().length != 8) {
 			$(".login-dialog .error-msg-inner").css("display", "block");
 			$(".login-dialog .error-msg").html('用户名必须为8个字符！');
 		}
 		else if ($("#login-userPassword").val().length > 20
 				|| $("#login-userPassword").val().length < 6) {
 			$(".login-dialog .error-msg-inner").css("display", "block");
 			$(".login-dialog .error-msg").html('密码必须为6-20个字符！');
 		}
 		else {
			$.ajax({
				type : "post",
				url : "user/userLogin_ajax_Index",
				data : {
					"userId" : $("#login-userId").val(),
					"userPassword" : $("#login-userPassword").val()
				},
				dataType:"json",
				success : function(data) {
					fnCallback_login(data);
				},
				error : function(msg) {
					alert("请求失败！");
				}
			});
 		}
	})
	//处理退出登录
	$(".logout-btn").click(function() {
		window.location.replace("episode/index.jsp?logout=1");
	})
	//处理注册输入正确性
	$("#click-to-register").click(function() {
		if ($("#register-userId").val().length < 2
				|| $("#register-userId").val().length > 10) {
 			$(".register-dialog .error-msg-inner").css("display", "block");
 			$(".register-dialog .error-msg").html('昵称必须为2-20个字符！');
		}
		else if ($("#register-userPassword").val().length < 6
				|| $("#register-userPassword").val().length > 20) {
			$(".register-dialog .error-msg-inner").css("display", "block");
 			$(".register-dialog .error-msg").html('密码必须为6-20个字符！');
		}
		else if ($("#register-userPassword").val() != $("#register-userPassword2").val()) {
			$(".register-dialog .error-msg-inner").css("display", "block");
 			$(".register-dialog .error-msg").html('两次密码输入不同，请重新确认！');
		}
		else {
			$(".register-form").submit();
		}
	})
</script>
</body>
</html>