package co.nextix.jardine.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import co.nextix.jardine.JardineApp;

import com.google.gson.stream.JsonReader;

public class NetworkUtils {
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

	public static JsonReader getReader(String object) {
		String charset = "UTF-8";
		InputStream is = new ByteArrayInputStream(object.getBytes());
		StringBuffer response = new StringBuffer();
		InputStream in = null;
		JsonReader reader = null;
		try {

			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;

			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();

			String reply = response.toString();
			Log.i(JardineApp.WEBTAG, reply);

			// reading
			in = new ByteArrayInputStream(reply.getBytes(charset));
			reader = new JsonReader(new InputStreamReader(in, charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}
}
