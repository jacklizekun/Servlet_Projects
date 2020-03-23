package time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class timeformat {
	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public timeformat() {
		// TODO Auto-generated constructor stub
	}
	public static String format(java.util.Date date,String dateFormat) {
        if(date == null) {
           return null;
       }
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
   }
}
