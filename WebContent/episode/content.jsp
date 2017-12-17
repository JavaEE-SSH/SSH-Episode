<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.ads.pojo.TUser,com.ads.pojo.TEpisode"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	//----获取用户数据
	int flag = session.getAttribute("flag")==null?0:(Integer)session.getAttribute("flag");
	TUser user = new TUser();
	if (flag == 1 && request.getAttribute("flag")==null) {
		user = (TUser)session.getAttribute("user");
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
	//----获取段子信息
	TEpisode episode = (TEpisode)request.getAttribute("episode");
	if (episode != null) {//第一次正常进入次段子信息界面
		session.setAttribute("episodeId", episode.getEpisodeId());
	}
	else if (session.getAttribute("episodeId") != null) {//已经进入过此段子信息界面（段子信息界面登录后）
		episode = new TEpisode();//防止异常
		response.sendRedirect("/Episode/episode/getEpisodeById?episodeId="+session.getAttribute("episodeId").toString());
	}
	else {//异常
		episode = new TEpisode();
		out.print("<script>alert('数据异常！');</script>");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>【爱段社】</title>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/content.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/index.js" type="text/javascript"></script>
</head>
<body class="page-article">
	<div class="header-channel-nav header-mini">
		<div class="content">
			<a href="episode/index.jsp" class="logo float-left iconfont icon-logo"></a>

			<div class="float-right header-links anim" style="display:<%= flag==0?"block":"none"%>;">
				<button class="btn btn-login js-to-login">登录</button>
			</div>
		</div>
	</div>
	
	<div class="main">
		<div class="left-wrapper">
			<h2 class="joke-title">爆笑段子</h2>
			<div class="content-bd"><%= episode.getEpisodeContent()%></div>
			
			<div class="interact">
				<a class="article-like"><%= episode.getEpisodeGood()%>赞</a>
				<span class="line"></span>
				<a class="article-report">收藏</a>
			</div>
			
			<div class="comments">
				<div class="hd-comment">
					<a href="<%= flag==1?"episode/personalCenter.jsp":"javascript:void(0)"%>">
						<img src="images/<%= user.getUserImage()==null?"she.png":user.getUserImage()%>" />
					</a>
					<textarea placeholder="发表你的精彩评论，还可以输入200字" maxlength="140" class="comment-input"></textarea>
					<a class="add-comment-btn">发表</a>
				</div>
				
				<h5>
					<span>最新评论</span>
					<p></p>
				</h5>
				
				<ul class="comments-list">
				</ul>
				
				<div class="no-comment">
					<img src="images/noComment.png" />
					<p>暂无评论，快来抢沙发！</p>
				</div>
				
				<div class="error-get-comments comments-tip" style="display: none;">加载失败，请点击重试</div>
				<div class="no-more-comments" style="display: inline-block;">已加载全部评论</div>
			</div>
		</div>
		
		<div class="right-wrapper">
			<div class="advertisement">
				<p>老<br />板<br />，<br />打<br />广<br />告<br />吗<br />？</p>
			</div>
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
	
	<div class="dialog login-register-dialog login-dialog" style="display: none;">
		<div class="close iconfont icon-close"></div>
		<div class="logo iconfont icon-logo"></div>
		<form name="login-form" class="login-form" action="user/userLogin" method="post">
			<div class="input-box input-username-box">
				<input type="text" class="input-username" name="username" placeholder="用户名" value="">
			</div>
			<div class="input-box input-password-box">
				<input type="password" class="input-password" name="password" placeholder="密码" value="">
			</div>
			<div class="error-msg-inner">
				<p class="error-msg"></p>
			</div>
			<input type="hidden" value="content" name="page"/>
			<button type="button" class="btn-login" id="click-to-login" style="pointer-events: auto;">登录</button>
		</form>
	</div>

	<div class="mask"></div>
	<div class="widget-tool">
	 	<div class="item back-up icon iconfont icon-back-up anim" style="display:none;"></div>
	</div>
<script type="text/javascript">
	$(document).ready(function () {
		var pageComments = null;
		var users = null;
		var total = 0;//评论总数
		var loading = false;
		//--获取评论及用户信息
		$.ajax({
			type : "post",
			url : "comment/getCommentsAndUsersByEpisodeId_ajax",
			data : {
				"episodeId" : "<%= episode.getEpisodeId()%>",
				"page_num" : 1
			},
			dataType:"json",
			success : function(data) {
				pageComments = data.comments;
				users = data.users;
				total = pageComments.total;//保存评论总数
				fnCallbackComment(pageComments, users);
			},
			error : function(msg) {
				alert("请求失败！");
			}
		});
		//----滚动加载数据
		$(window).scroll(function(){
            var srollPos = $(window).scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)
            var totalheight = parseFloat($(window).height()) + parseFloat(srollPos);
            var content = $(".comments-list");
            
            if(($(document).height()) <= totalheight) {//执行添加一页数据!!滚动过快重复加载数据
            	if (pageComments != null && pageComments.hasNextPage) {//有下一页
            		loading = true;
            		//获取下一页段子数据
            		$.ajax({
            			type : "post",
            			url : "comment/getCommentsAndUsersByEpisodeId_ajax",
            			data : {
            				"episodeId" : "<%= episode.getEpisodeId()%>",
            				"page_num" : pageComments.nextPage
            			},
            			dataType:"json",
            			success : function(data) {
            				pageComments = data.comments;
            				users = data.users;
            				fnCallbackComment(pageComments, users);
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
		
		//--处理点赞收藏评论
		//----如果已经登录获取点赞段子数据
		if (<%=flag%> == 1) {
			$.ajax({
				type : "post",
				url : "episode/getGoodEpisodeAndCollect_ajax",
				data : {
					"episodeId" : "<%= episode.getEpisodeId()%>",
					"userId" : "<%= user.getUserId()%>"
				},
				dataType:"json",
				success : function(data) {
					if (data.good == 1) {
						$(".article-like").addClass("hasliked");
					}
					if (data.collect == 1) {
						$(".article-report").html("已收藏");
						$(".article-report").addClass("article-hasliked");
					}
				},
				error : function(msg) {
					alert("请求失败！");
				}
			});
		}
		//----点赞段子ajax
		$(".article-like").click(function () {
			var flag = '<%= flag%>';
		
			if (flag == '0') {
				$(".js-to-login").click();
			}
			else if (!$(".article-like").hasClass("hasliked")) {
				$.ajax({
					type : "post",
					url : "episode/addEpisodeGood_ajax",
					data : {
						"userId" : "<%= user.getUserId()%>",
						"episodeId" : "<%= episode.getEpisodeId()%>"
					},
					dataType:"json"
				});
				$(".article-like").html("<%= episode.getEpisodeGood()+1%>赞");
				$(".article-like").addClass("hasliked");
			}
		})
		//----收藏、取消收藏段子
		if (<%= flag%> == 1) {
			$(".article-report").click(function () {
				if ($(this).hasClass("article-hasliked")) {
					$.ajax({
						type : "post",
						url : "episode/removeCollectEpisode_ajax",
						data : {
							"userId" : "<%= user.getUserId()%>",
							"episodeId" : "<%= episode.getEpisodeId()%>"
						},
						dataType:"json"
					});
					$(this).removeClass("article-hasliked");
					$(this).html("收藏");
				}
				else {
					$.ajax({
						type : "post",
						url : "episode/addCollectEpisode_ajax",
						data : {
							"userId" : "<%= user.getUserId()%>",
							"episodeId" : "<%= episode.getEpisodeId()%>"
						},
						dataType:"json"
					});
					$(this).addClass("article-hasliked");
					$(this).html("已收藏");
				}
			})
		}
		else {
			$(".article-report").click(function() {
				$(".js-to-login").click();
			})
		}
		//----发表评论
		if (<%= flag%> == 1) {
			$(".add-comment-btn").click(function() {
				var text = $(".comment-input").val();
				if (text.length == 0 || text.length > 200) {
					alert("请输入200个字以内！");
				}
				else {
					var comment = new Object();
					comment.commentContent = text;
					comment.userId = '<%= user.getUserId()%>';
					comment.episodeId = '<%= episode.getEpisodeId()%>';
					
					$.ajax({
						type : "post",
						url : "comment/addComment_ajax",
						data : {
							"commentContent" : comment.commentContent,
							"userId" : comment.userId,
							"episodeId" : comment.episodeId
						},
						dataType:"json",
						success : function(data) {
							total += 1;//新增一条评论
							if ($(".no-comment").css("display") == "block") {//没有评论
								$(".comments-list").css("display", "block");
								$(".no-comment").css("display", "none");
								$(".no-more-comments").css("display", "inline-block");
							}
							$(".comments-list").prepend('<li><img src="images/<%= user.getUserImage()%>" />'
									+'<div class="comment-box">'
									+'<span class="comment-nickname"><%= user.getUserNickname()%></span>'
									+'<a class="float-right comment-delete">删除</a>'
									+'<a class="float-right comment-like"><p style="display:none;">'+data.commentId+'</p>0赞</a>'
									+'</div><p>'+comment.commentContent+'</p></li>');
						},
						error : function() {
							alert("请求失败！");
						}
					})
				}
			})
		}
		else {
			$(".add-comment-btn").click(function(){$(".js-to-login").click()});
		}
		//----删除评论ajax--on()绑定click事件
		$(".comments-list").on("click", ".comment-delete", function () {
			 $.ajax({
				type : "post",
				url : "comment/removeComment_ajax",
				data : {
					"commentId" : $(this).next().find("p").html()
				},
				dataType:"json"
			}); 
			 $(this).parent().parent().slideUp("fast");
			 total -= 1;//减少一条评论
			 if (total == 0) {
					$(".comments-list").css("display", "none");
					$(".no-comment").css("display", "block");
					$(".no-more-comments").css("display", "none");
			}
		});
		//----点赞评论ajax--on()绑定click事件
		$(".comments-list").on("click", ".comment-like", function () {
			var flag = '<%= flag%>';

			if (flag == '0') {
				$(".js-to-login").click();
			}
			else if (!$(this).hasClass("hasliked")) {
				//已登录未点赞
				$.ajax({//异步保存点赞评论数据
					type : "post",
					url : "comment/addGoodComment_ajax",
					data : {
						"commentId" : $(this).find("p").html(),
						"userId" : "<%= user.getUserId()%>"
					},
					dataType:"json"
				});
				$(this).addClass("hasliked");
				var str = $(this).html();
				$(this).html((Number(str.substring(39,str.length-1))+1)+"赞");
			}
		})
	})
	
	//***************
	function fnCallbackComment(pageComments, users) {//评论回调函数
		if (pageComments == null || users == null) {
			alert('数据异常,请稍后重试！');
		}
		else if (pageComments.total == 0) {
			$(".no-comment").css("display", "block");
			$(".no-more-comments").css("display", "none");
		}
		else {
			var listComments = pageComments.list;
			var content = $(".comments-list");
			
			//已经登录获取点赞评论数据
			if (<%=flag%> == 1) {
				var commentIds = "";
				
				for (var i=0; i<listComments.length; i++) {//保存评论id
					commentIds += listComments[i].commentId+',';
				}
				
				$.ajax({//异步获取点赞段子信息
					type : "post",
					url : "comment/getGoodComment_ajax",
					dataType:"json",
					data : {
						"commentIds" : commentIds,
						"userId" : "<%= user.getUserId()%>"
					},
					success : function(data) {//不能访问listComments和users
						var hasliked = new Array();
						var textDelete = new Array();
						
						for (var i=0; i<data.good.length; i++) {//遍历保存点赞信息
							if (data.good[i] ==1) {
								hasliked[i] = "hasliked";
							}
							else {
								hasliked[i] = "";
							}
							if (<%= user.getUserId()%> == users[i].userId) {
								textDelete[i] = '<a class="float-right comment-delete">删除</a>';
							}
							else {
								textDelete[i] = '';
							}
						}
						
						for (var i=0; i<listComments.length; i++) {//遍历添加组件
							content.append('<li><img src="images/'+users[i].userImage+'" />'
									+'<div class="comment-box">'
									+'<span class="comment-nickname">'+users[i].user_nickname+'</span>'
									+textDelete[i]
									+'<a class="float-right comment-like '+hasliked[i]+'"><p style="display:none;">'+listComments[i].commentId+'</p>'
									+listComments[i].comment_good+'赞</a>'
									+'</div><p>'+listComments[i].commentContent+'</p></li>');
						}
					},
					error : function(msg) {
						alert("请求失败！");
					}
				});
			}
			else {//没有登录
				for (var i=0; i<listComments.length; i++) {
					content.append('<li><img src="images/'+users[i].userImage+'" />'
							+'<div class="comment-box">'
							+'<span class="comment-nickname">'+users[i].user_nickname+'</span>'
							+'<a class="float-right comment-like"><p style="display:none;">'+listComments[i].commentId+'</p>'
							+listComments[i].comment_good+'赞</a>'
							+'</div><p>'+listComments[i].commentContent+'</p></li>');
				}
			}
		}
	}
	//登录回调函数
	function fnCallback_login(data) {
		if (data.flag == 1) {
			$(".login-form").submit();
		}
		else {
			$(".login-dialog .error-msg-inner").css("display", "block");
 			$(".login-dialog .error-msg").html('用户名或密码错误！');
		}
	}
	//处理登录AJAX
 	$("#click-to-login").click(function() {
 		if ($("input[name='username']").val() == ''
 				|| $("input[name='password']").val() == '') {
 			$(".login-dialog .error-msg-inner").css("display", "block");
 			$(".login-dialog .error-msg").html('用户名和密码不能为空！');
 		}
 		else if ($("input[name='username']").val().length != 8) {
 			$(".login-dialog .error-msg-inner").css("display", "block");
 			$(".login-dialog .error-msg").html('用户名必须为8个字符！');
 		}
 		else if ($("input[name='password']").val().length > 20
 				|| $("input[name='password']").val().length < 6) {
 			$(".login-dialog .error-msg-inner").css("display", "block");
 			$(".login-dialog .error-msg").html('密码必须为6-20个字符！');
 		}
 		else {
			$.ajax({
				type : "post",
				url : "user/userLogin_ajax",
				data : {
					"username" : $("input[name='username']").val(),
					"password" : $("input[name='password']").val()
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
		window.location.replace("episode/index.jsp");
	})
</script>
</body>
</html>