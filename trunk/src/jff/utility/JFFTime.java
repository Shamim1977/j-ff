package jff.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Utilities to display and stamp the current time on the log files
 *
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
public class JFFTime {
	
	/**
	 * The date format used in now()
	 * 
	 * @see #now()
	 */
	public static final String DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
	
	/**
	 * Interrogates the Calendar and returns a String
	 * with the current time in the format specified in {@link #DATE_FORMAT}
	 * 
	 * @return a time stamp with the current time
	 */
	public static String now() {
		
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(cal.getTime());
		
	}
	
}
