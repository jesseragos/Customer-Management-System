package com.cms.utils;

import com.cms.domains.User;

public class AdminChecker {

	public static Boolean isAdmin(User user){
		return user.getUserID().contains("ADMIN") && user.getUserID().length() == 7;
	}
	
}
