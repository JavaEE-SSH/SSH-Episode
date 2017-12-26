package com.ads.schedule;

import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ads.service.UserService;

@Component
public class UserScheduler {
	@Resource
	private UserService userService;
	
	/**
	 * 时间任务，指定时间（每天0点）重复执行
	 * 删除3年未登录的用户
	 */
	@Scheduled(cron="0 0 0 * * ?")
	public void deleteUser() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 3);//当前时间减去3年
		userService.deleteUser(calendar.getTime());
	}
}
