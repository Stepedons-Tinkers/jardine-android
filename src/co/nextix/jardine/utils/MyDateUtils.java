package co.nextix.jardine.utils;

import java.text.DateFormat;
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime());
	}
}
