package test.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Stamp {

//		EDITED 2016-03-12 15:00 UserName
	
	public static void main(String[] args) {

//		System.out.println(getStamp("EDITED", "Jessekiel"));
		
		System.out.println("Jessekiel Ragos".toUpperCase().contains("ki".toUpperCase()));
	
	}

	private static String getStamp(String actionComitted, String user) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		
		return String.format("%s %s %s", actionComitted.toUpperCase(), sdf.format(d), user);
	}

}
