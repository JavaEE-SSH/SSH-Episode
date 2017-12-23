package com.ads.util;

import java.util.Set;

import com.ads.pojo.TUser;

public class ManyHasOneUtil {
	public static boolean isContains(Set<TUser> users, TUser user) {
		if (users != null){
			for (TUser u : users) {
				if (u.getUserId() == user.getUserId()) {
					return true;
				}
			}
		}
		
		return false;
	}
}
