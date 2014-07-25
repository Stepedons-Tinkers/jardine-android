package co.nextix.jardine.utils;

public class Tools {
	public static int parseIntWithDefault(String number, int defaultVal) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}

	public static long parseLongWithDefault(String number, long defaultVal) {
		try {
			return Long.parseLong(number);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}
}
