package jff.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JFFTime {

	public static String now() {
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
		return sdf.format(cal.getTime());
		
	}
	
}
