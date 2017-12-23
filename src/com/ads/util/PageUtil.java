package com.ads.util;

public class PageUtil {
	/**
	 * 判断是否还有下一页
	 * @param pageNum
	 * @param perPageNum
	 * @param total
	 * @return boolean
	 */
	public static boolean hasNextPage(int pageNum, int perPageNum, long total) {
		double maxPageNum = (total * 1.0) / perPageNum;
		
		if (maxPageNum > pageNum) {
			return true;
		}
		return false;
	}
}
