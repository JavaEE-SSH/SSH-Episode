$(document).ready(function() {
	//点击切换显示内容
	$("#li-collect").click(function() {
		$("#profile").slideUp("slow");
		$("#collect").slideDown("slow");
		$("#li-collect").addClass("active");
		$("#li-profile").removeClass("active");
		$(".span-item1").fadeOut("fast");
		$(".span-item2").fadeIn("fast");
	})
	$("#li-profile").click(function() {
		$("#collect").slideUp("slow");
		$("#profile").slideDown("slow");
		$("#li-profile").addClass("active");
		$("#li-collect").removeClass("active");
		$(".span-item2").fadeOut("fast");
		$(".span-item1").fadeIn("fast");
	})
	
	//焦点事件，显示隐藏提交按钮
	$("#input-nickname").focus(function() {
		$("#button-nickname").css("display","inline-block");
	})
	$("#input-nickname").blur(function() {
		if (!$("#button-nickname").is(":hover")) {
			$("#button-nickname").css("display","none");
		}
	})
	$("#input-password").focus(function() {
		$("#button-password").css("display","inline-block");
	})
	$("#input-password").blur(function() {
		if (!$("#button-password").is(":hover")) {
			$("#button-password").css("display","none");
		}
	})
	$("#input-man").focus(function() {
		$("#button-gender").css("display","inline-block");
	})
	$("#input-woman").focus(function() {
		$("#button-gender").css("display","inline-block");
	})
	$("#input-man").blur(function() {
		if (!$("#input-woman").is(":focus") && !$("#button-gender").is(":hover")) {
			$("#button-gender").css("display","none");
		}
	})
	$("#input-woman").blur(function() {
		if (!$("#input-man").is(":focus") && !$("#button-gender").is(":hover")) {
			$("#button-gender").css("display","none");
		}
	})
	
	//点击弹出上传图片box
	$("#js-to-upload").click(function() {
		$(".mask").fadeIn(100);
		$(".upload-box").slideDown(200);
	});
	$(".close").click(function() {
		$(".upload-box").slideUp(200);
		$(".mask").fadeOut(100);
	});
});