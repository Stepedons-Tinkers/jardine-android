package co.nextix.jardine.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MyDateUtils {
	public static String getCurrentTimeStamp() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		// DateFormat outputFormat = sdf;
		// String text = outputFormat.format(date);
		Date gmtTime = new Date(sdf.format(date));

		// Convert UTC to Local Time
		Date fromGmt = new Date(gmtTime.getTime()
				+ TimeZone.getDefault().getOffset(date.getTime()));
		DateFormat outputFormat = sdf;
		String text = outputFormat.format(fromGmt);

		return text;
	}
}
