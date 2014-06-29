package co.nextix.jardine.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MyDateUtils {
	public static String getCurrentTimeStamp() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		// DateFormat outputFormat = sdf;
		// String text = outputFormat.format(date);

		// Date gmtTime = new Date(sdf.format(date));

		String text = sdf.format(date);

		// // Convert UTC to Local Time
		// Date fromGmt = new Date(gmtTime.getTime()
		// + TimeZone.getDefault().getOffset(date.getTime()));
		// DateFormat outputFormat = sdf;
		// String text = outputFormat.format(fromGmt);

		return text;
	}

	public static String getCurrentDate() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		String text = sdf.format(date);

		return text;
	}

	public static String getCurrentTime() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		String text = sdf.format(date);

		return text;
	}

	public static String getYesterday() {
		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DATE, -1);
		// return dateFormat.format(cal.getTime());

		return "2000-09-27 09:00:00";
	}

	public static int isTimeAfter(String webTime, String localTime) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// Log.i("WEB", "isTimeAfter: time1 " + theTime + " time2 " + theTime2);

		Date serverTime = null;
		Date deviceTime = null;
		try {
			serverTime = sdf.parse(webTime);
			deviceTime = sdf.parse(localTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// n < 0 before
		// n > 0 after
		// n == 0 equal
		return serverTime.compareTo(deviceTime);
	}
}
