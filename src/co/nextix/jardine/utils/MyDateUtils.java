package co.nextix.jardine.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;

public class MyDateUtils {
	
	@SuppressLint("SimpleDateFormat")
	public static String getCurrentTimeStamp() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
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

	@SuppressLint("SimpleDateFormat")
	public static String getCurrentDate() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		String text = sdf.format(date);

		return text;
	}

	@SuppressLint("SimpleDateFormat")
	public static String getCurrentTime() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		String text = sdf.format(date);

		return text;
	}

	@SuppressLint("SimpleDateFormat")
	public static String getOneYearAgo() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -365);
		return dateFormat.format(cal.getTime());

		// return "2000-09-27 09:00:00";
	}
	
	@SuppressLint("SimpleDateFormat")
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

	@SuppressLint("SimpleDateFormat")
	public static String convertDate(String YearMonthDay) {
		String txtDate = "";
		String expectedPattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		Date date;
		try {
			date = formatter.parse(YearMonthDay);
			DateFormat df = new SimpleDateFormat("MMMM dd,yyyy");
			txtDate = df.format(date);

		} catch (Exception e) {

		}

		return txtDate;
	}

	@SuppressLint("SimpleDateFormat")
	public static String convertDateTime(String YearMonthDayHourMinuteSeconds) {
		String txtDate = "";
		String expectedPattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		Date date;
		try {
			date = formatter.parse(YearMonthDayHourMinuteSeconds);
			DateFormat df = new SimpleDateFormat("MMMM dd,yyyy @ HH:mm");
			txtDate = df.format(date);

		} catch (Exception e) {

		}

		return txtDate;
	}

	@SuppressLint("SimpleDateFormat")
	public static boolean isDateNew(String YearMonthDayHourMinuteSeconds) {
		boolean flag = false;

		int numOfDays = -14;

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, numOfDays);
		Date fourteenDaysAgo = cal.getTime();

		String expectedPattern = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		Date date;
		try {
			date = formatter.parse(YearMonthDayHourMinuteSeconds);
			if (date.after(fourteenDaysAgo) || date.equals(fourteenDaysAgo)) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	@SuppressLint("SimpleDateFormat")
	public static String convertDateStringToDash(String MonthDayYear) {
		String convertedDate = "";
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date;
		try {
			date = formatter.parse(MonthDayYear);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			convertedDate = df.format(date);

		} catch (Exception e) {

		}

		return convertedDate;
	}

}
