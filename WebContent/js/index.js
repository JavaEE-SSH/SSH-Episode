$(document).ready(function() {
	$('.js-to-login').click(function() {//登录弹出
		$('.mask').fadeIn(100);
		$('.login-dialog').slideDown(200);
	});
	$('.js-to-register').click(function() {//注册弹出
		$('.login-dialog').slideUp(200);
		$('.register-dialog').slideDown(200);
	});
	$('.close').click(function() {//关闭登录注册
		$('.mask').fadeOut(100);
		$('.login-dialog').slideUp(200);
		$('.register-dialog').slideUp(200);
	});
	$(".register-ok-dialog .close").click(function() {
		$(".mask").css("display", "none");
		$(".register-ok-dialog").css("display", "none");
	});
	$('.refresh').click(function() {//刷新页面
		window.location.reload();
	});
	$(window).scroll(function() {//设置滚动显示
		var sc = $(window).scrollTop();
		if (sc > 20) {
			$(".back-up").css("display", "block");
		} else {
			$(".back-up").css("display", "none");
		}
	});
	
	$(".back-up").click(function(){scrollTop();});//点击回滚顶部
	//用户信息js实现显示与隐藏
	$(".user-info").click(function() {
		$(".user-info-menu").slideToggle("fast");
	});
});

function scrollTop() {//实现回滚函数
		var sc = $(window).scrollTop();
		$('body,html').animate({scrollTop : 0}, 500);
}