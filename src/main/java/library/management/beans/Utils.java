package library.management.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.List;
//import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils {
	
	Date date, returnDate;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Calendar c = Calendar.getInstance();
	
	public Date getDate(int count) {
		date = new Date();
		returnDate = null;
		String newDateStr = sdf.format(date);
//		if (count > 0) {
//			c.setTime(date);
//			c.add(Calendar.DATE, count);
//			return c.getTime();
//		}
//		try {
//			returnDate = sdf.parse(newDateStr);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return returnDate;
		c.setTime(date);
		if (count > 0) {
			c.add(Calendar.DATE, count);
		}
		return c.getTime();
	}

	public long getDateDiff(Date date1, Date date2) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    long data = TimeUnit.DAYS.convert(diffInMillies,TimeUnit.DAYS);
	    return (data/86400000);
	}
	
	
	
	
	
	
	
	
	
	
}
