<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ads.pojo.TUser, java.util.Enumeration"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//----获取用户数据
	int flag = session.getAttribute("flag")==null?0:(Integer)session.getAttribute("flag");
	TUser user = new TUser();
	if (flag == 1 && request.getAttribute("flag")==null) {
		user = (TUser)session.getAttribute("user");
		if (request.getAttribute("isUpload") != null) {
			if ((Integer)request.getAttribute("isUpload") == 1) {
				user.setUserImage(request.getAttribute("user_image").toString());
			}
			else {
				out.println("<script>alert('上传失败');</script>");
			}
		}
	}
	else {
		//登录操作获取用户数据
		flag = request.getAttribute("flag")==null?0:1;
		if (flag == 1) {
			user = (TUser)request.getAttribute("user");
			session.setAttribute("user", user);
		}
		session.setAttribute("flag", flag);
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
<link href="css/personal_center.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.Jcrop.min.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/jquerysession.js" type="text/javascript"></script>
<script src="js/index.js" type="text/javascript"></script>
<script src="js/profile.js" type="text/javascript"></script>
<script src="js/jquery.Jcrop.min.js"></script>
<script src="js/script.js"></script>
<style>
</style>
</head>
<body>
	<div class="header-channel-nav header-mini">
		<div class="content">
			<a href="episode/index.jsp" class="logo float-left iconfont icon-logo"></a>

			<%-- <div class="float-right header-links anim" style="display:<%= flag==0?"block":"none"%>;">
				<button class="btn btn-login js-to-login">登录</button>
			</div> --%>
		</div>
	</div>
	
	<div class="main">
		<div class="left-wrapper">
			<div class="profile-wrapper">
				<img src="images/<%= user.getUserImage()==null?"she.png":user.getUserImage()%>" />
				<p id="p-nick" class="nickname"><%= user.getUserNickname()==null?"":user.getUserNickname()%></p>
				<p><%= user.getUserId()==0?"":user.getUserId()%></p>
			</div>
			<ul class="nav-list">
				<li id="li-profile" class="active">账号信息</li>
				<li id="li-collect">个人收藏</li>
				<span id="span-item" class="span-item1" style="top: 0px;"></span>
				<span id="span-item" class="span-item2" style="top: 47px;display: none;"></span>
			</ul>
		</div>
		
		<div class="right-wrapper">
			<div id="profile" class="nav-show profile-edit-wrapper active">
				<div class="profile-account">
					<div class="account-box">
						<label class="img-label">头像</label>
						<div class="account-op">
							<img id="profile_url" src="images/<%= user.getUserImage()==null?"she.png":user.getUserImage()%>" />
							<input id="js-to-upload" type="button" value="上传头像" class="btn" style="display:block;margin:0;margin-bottom: 20px;"/>
							<p style="font-size: 12px;color: #999;line-height: 18px;">支持.jpg .jepg .png格式照片，大小不超过512K</p>
						</div>
					</div>
					<div class="account-box">
						<label>用户名</label>
						<div class="account-op">
							<span id="user-id"><%= user.getUserId()==0?"":user.getUserId()%></span>
						</div>
					</div>
					<div class="account-box">
						<label>昵称</label>
						<div class="account-op">
							<form class="change-nickname">
								<p>
									<input id="input-nickname" type="text" value="<%= user.getUserNickname()==null?"":user.getUserNickname()%>" class="input user-name" />
								</p>
								<p class="hint">
									<span class="icon-delete iconfont"></span>2-20个字符，支持汉字、数字、英文、“-”、“_”等
								</p>
								<p class="save-name">
									<button id="button-nickname" type="button" class="btn" style="display: none;">保存</button>
								</p>
							</form>
						</div>
					</div>
					<div class="account-box">
						<label>性别</label>
						<div class="account-op">
							<form class="change-gender">
								<p>
									<input id="input-man" type="radio" value="1" name="change_gender" class="radio-gender"  <%= user.getUserGender()==1?"checked":""%>/>男
									<input id="input-woman" type="radio" value="0" name="change_gender" class="radio-gender" <%= user.getUserGender()==1?"":"checked"%>/>女
								</p>
								<p class="save-name">
									<button id="button-gender" type="button" class="btn" style="display: none;">保存</button>
								</p>
							</form>
						</div>
					</div>
					<div class="account-box">
						<label>密码</label>
						<div class="account-op">
							<form class="change-password">
								<p>
									<input id="input-password" type="password" value="<%= user.getUserPassword()==null?"********":user.getUserPassword()%>" class="input" />
								</p>
								<p class="hint">
									<span class="icon-delete iconfont"></span>6-20个字符，支持数字、英文、“-”、“_”等
								</p>
								<p class="save-name">
									<button id="button-password" type="button" class="btn" style="display: none;">保存</button>
								</p>
							</form>
						</div>
					</div>
				</div>
			</div>
			
			<div id="collect" class="nav-show">
				<ul class="like-list" style="display:<%= flag==1?"block":"none"%>;"></ul>
				<div style="display: <%= flag==0?"block":"none"%>" class="no-like">
					<img src="images/no_collect.png" /><p>暂无收藏，赶紧去看看吧</p>
				</div>
			</div>
			
		</div>
	</div>
	<!-- 登录 -->
	<div class="dialog login-register-dialog login-dialog" style="display: none;">
		<div class="close iconfont icon-close"></div>
		<div class="logo iconfont icon-logo"></div>
		<form name="login-form" class="login-form" action="user/userLogin" method="post">
			<div class="input-box input-username-box">
				<input type="text" class="input-username" name="username" placeholder="用户名" value="" />
			</div>
			<div class="input-box input-password-box">
				<input type="password" class="input-password" name="password" placeholder="密码" value="" />
			</div>
			<div class="error-msg-inner">
				<p class="error-msg"></p>
			</div>
			<input type="hidden" value="personal_center" name="page"/>
			<button type="button" class="btn-login" id="click-to-login" style="pointer-events: auto;">登录</button>
		</form>
	</div>
	<!-- 上传图片box -->
	<div class="upload-box" style="display:none;">
		<div class="close iconfont icon-close"></div>
		<div class="box-header"><h2 style="color:white;">上传图片</h2></div>
		<div class="box-body">
			<form id="upload_form" enctype="multipart/form-data" method="post" action="user/uploadImage" onsubmit="return checkForm()">
				<!-- hidden crop params -->
				<input type="hidden" id="x1" name="x1" />
				<input type="hidden" id="y1" name="y1" />
				<input type="hidden" id="x2" />
				<input type="hidden" id="y2" />

				<div>
					<input type="file" id="image_file" name="file" onchange="fileSelectHandler()" />
				</div>

				<div class="error"></div>

				<div class="step2">
					<h2>请选择需要截图的部位,然后按上传</h2>
					<img id="preview" />

					<div class="info">
						<label>文件大小</label>
						<input type="text" id="filesize" class="file-info" />
						<label>类型</label>
						<input type="text" id="filetype" class="file-info" />
						<label>图像尺寸</label>
						<input type="text" id="filedim" class="file-info" />
						<label>宽度</label>
						<input type="text" id="w" name="w" class="file-info" />
						<label>高度</label>
						<input type="text" id="h" name="h" class="file-info" />
					</div>
					<input type="hidden" value="<%= user.getUserId()%>" name="user_id"/>
					<input type="submit" class="upload-btn" value="上传" />
				</div>
			</form>
		</div>
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
	<div class="mask"></div> 
	<div class="widget-tool">
	 	<div class="item back-up icon iconfont icon-back-up anim" style="display:none;"></div>
	</div>
<script type="text/javascript">
	$(document).ready(function() {
		if (<%=flag%> == 1) {//若已经登录
			var page_episodes = null;
			var total = 0;
			var loading = false;
			//获取段子数据
			$.ajax({
				type : "post",
				url : "episode/getEpisodeByUserId_ajax",
				data : {
					"page_num" : 1,
					"user_id" : <%= user.getUserId()%>
				},
				dataType:"json",
				success : function(data) {
					if (data.page_episodes.total == 0) {
						$(".like-list").css("display", "none");
						$(".no-like").css("display","block");
					}
					else {
						page_episodes = data.page_episodes;
						total = page_episodes.total;
						fnCallback_collect(page_episodes);
					}
				},
				error : function(msg) {
					alert("请求失败！");
				}
			});
			//滚动加载数据
			$(window).scroll(function(){
	            var srollPos = $(window).scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)
	            var totalheight = parseFloat($(window).height()) + parseFloat(srollPos);
	            
	            if(($(document).height()) <= totalheight) {//执行添加一页数据!!滚动过快重复加载数据
	            	if (total != 0 && page_episodes.hasNextPage) {//有下一页
	            		loading = true;
	            		//获取下一页段子数据
	            		$.ajax({
	            			type : "post",
	            			url : "episode/getEpisodes_ajax",
	            			data : {
	            				"page_num" : page_episodes.nextPage
	            			},
	            			dataType:"json",
	            			success : function(data) {
	            				page_episodes = data.page_episodes;//更新 page_episodes 数据
	            				fnCallback_collect(page_episodes);
	            			},
	            			error : function(msg) {
	            				alert("请求失败！");
	            			}
	            		}).done(function() {
	            			loading = false;
	            		});
	            	}
	            }
	        });
			
			//取消收藏--on()绑定click()事件
			$(".like-list").on("click", ".like-remove", function() {
				var text = $(this).parent().prev().attr("href");
				var episode_id = text.substring(text.length-10, text.length);
			
				$.ajax({
					type : "post",
					url : "episode/removeCollectEpisode_ajax",
					data : {
						"user_id" : "<%= user.getUserId()%>",
						"episode_id" : episode_id
					},
					dataType:"json"
				});
				total -= 1;
				$(this).parents("li").slideUp("fast");
				if (total == 0) {
					$(".like-list").css("display", "none");
					$(".no-like").css("display","block");
				}
			});
		}
	});

	function fnCallback_collect(page_episodes) {
		var list = page_episodes.list;//段子分页数据
		var content = $(".like-list");
		
		if (list != null) {
			for (var i=0; i<list.length; i++) {
				content.append('<li class="hasnoimg"><div class="doc-items-wrapper"><div class="doc-items">'
						+'<a href="episode/getEpisodeById?episode_id='+list[i].episode_id+'">'
						+'<h3>'+list[i].episode_content.substr(0,70)+'…</h3></a>'
						+'<p><span class="like-remove">取消收藏<i class="iconfont icon-close-half"></i></span></p>'
						+'</div></div></li>');
			}
		}
		else {
			alert("读取数据失败，请稍后重试！");
		}
	}


	//回调函数
 	function fnCallback(data) {
		if (data.flag == 1) {
			alert("修改成功！");
			$("input[name='username']").val($("#user-id").html());
			$("input[name='password']").val(""+<%= user.getUserPassword()%>);
			$(".login-form").submit();
		}
		else {
			alert("系统错误，请稍后重试！");
		}
	}
	//处理修改昵称信息AJAX
 	$("#button-nickname").click(function() {
 		if ($("#input-nickname").val() == ''
 				|| $("#input-nickname").val().length < 2
 				|| $("#input-nickname").val().length > 10) {
 			$(".change-nickname .hint").css("display","block");
 		}
 		else {
 			$(".change-nickname .hint").css("display","none");
			$.ajax({
				type : "post",
				url : "user/updateUserInfo_ajax",
				data : {
					"type" : "1",
					"user_id" : $("#user-id").html(),
					"user_info" : $("#input-nickname").val()
				},
				dataType:"json",
				success : function(data) {
					fnCallback(data);
				},
				error : function(msg) {
					alert("请求失败！");
				}
			});
 		}
	})
	//处理修改性别信息AJAX
 	$("#button-gender").click(function() {
		$.ajax({
			type : "post",
			url : "user/updateUserInfo_ajax",
			data : {
				"type" : "2",
				"user_id" : $("#user-id").html(),
				"user_info" : $("input[type=radio]:checked").val()
			},
			dataType:"json",
			success : function(data) {
				fnCallback(data);
			},
			error : function(msg) {
				alert("请求失败！");
			}
		});
	})
	//处理修改密码信息AJAX
 	$("#button-password").click(function() {
 		if ($("#input-password").val() == ''
 				|| $("#input-password").val().length < 6
 				|| $("#input-password").val().length > 20) {
 			$(".change-password .hint").css("display","block");
 		}
 		else {
 			$(".change-password .hint").css("display","none");
			$.ajax({
				type : "post",
				url : "user/updateUserInfo_ajax",
				data : {
					"type" : "3",
					"user_id" : $("#user-id").html(),
					"user_info" : $("#input-password").val()
				},
				dataType:"json",
				success : function(data) {
					fnCallback(data);
				},
				error : function(msg) {
					alert("请求失败！");
				}
			});
 		}
	})
</script>
</body>
</html>