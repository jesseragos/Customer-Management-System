package com.cms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Stamp {

	public static String getStamp(String actionComitted, String user) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		
		return String.format("%s %s %s", actionComitted.toUpperCase(), sdf.format(d), user);
	}

}
