package com.ads.schedule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ads.service.EpisodeService;

@Component
public class EpisodeScheduler {
	@Resource
	private EpisodeService episodeService;
		
	/**
	 * 时间任务，指定时间（每天0:10）重复执行
	 * 删除7天以前的段子
	 */
	@Scheduled(cron="0 10 0 * * ?")
	public void deleteEpisode() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 7);//当前时间减去7天
		episodeService.deleteEpisode(calendar.getTime());
	}
	
	/**
	 * 时间任务，指定时间（每天0:20）重复执行
	 * 执行Python脚本爬取段子保存到文件
	 */
	@Scheduled(cron="0 20 0 * * ?")
	public void execSpider () {
		System.out.println("执行Python脚本...");
		//服务器上的绝对地址
		String serverPath = this.getClass().getClassLoader().getResource("").getPath().substring(1);
		String pythonFilePath = "com/ads/python/spider_main.py";
		System.out.println("python脚本地址:"+serverPath+pythonFilePath);
		String[] cmd = new String[2];
		cmd[0] = "python";
		cmd[1] = serverPath + pythonFilePath;
		Runtime rt = Runtime.getRuntime();
		Process pr;
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		try {
			//cmd执行Python脚本
			pr = rt.exec(cmd);
			//Charset.forName("GBK")解决中文乱码问题
			br1 = new BufferedReader(new InputStreamReader(pr.getInputStream(), Charset.forName("GBK")));
			//输出正常执行信息
			String line = "";
			while((line = br1.readLine()) != null) {
			System.out.println(line);
			}
			//错误信息流
			br2 = new BufferedReader(new InputStreamReader(pr.getErrorStream(), Charset.forName("GBK")));
			//输出错误执行信息
			String error = "";
			while((error = br2.readLine()) != null) {
			System.out.println(error);
			}
		} catch (IOException e) {
			System.out.println("执行Python脚本出错");
			e.printStackTrace();
		} finally {//关闭数据流
			try {
				if (br1 != null) {
					br1.close();
				}
				if (br2 != null) {
					br2.close();
				}
			} catch (IOException e) {
					System.out.println("关闭数据流出错");
					e.printStackTrace();
			}
		}
		System.out.println("脚本执行完成！");
	}
	
	/**
	 * 时间任务，指定时间（每天0:30）重复执行
	 * 读取文件保存到数据库
	 */
	@Scheduled(cron="0 30 0 * * ?")
	public void addEpisode() {
		//获取当天日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filePath = "E:\\Python\\Data\\episode";//文件名
		String fileName = sdf.format(new Date()) + ".dat";//文件名
		File filePath_f = new File(filePath);
		File destFile = new File(filePath+"\\"+fileName);//目标文件流
		BufferedReader br = null;
		System.out.println("读取文件...("+fileName+")");
		
		try {
			//确保文件存在
			if (!filePath_f.exists()) {
				filePath_f.mkdirs();
			}
			if (!destFile.exists()) {
				destFile.createNewFile();
			}
			//获取输入流，Charset.forName("UTF-8")解决中文乱码问题
			br = new BufferedReader(new InputStreamReader(new FileInputStream(destFile), Charset.forName("UTF-8")));
			//读取数据，存入数据库
			System.out.println("保存数据中...");
			String line = "";
			while ((line=br.readLine()) != null) {
				episodeService.insertEpisode(line);
			}
			System.out.println("保存完成！");
		} catch (FileNotFoundException e) {
			System.out.println("文件打开异常");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件读取异常");
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("文件关闭异常");
					e.printStackTrace();
				}
			}
		}
	}
}
