package co.nextix.jardine;

import java.net.CookieManager;
import java.net.HttpURLConnection;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import co.nextix.jardine.database.DatabaseAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class JardineApp extends Application {

	public static Context context;
	public static String TAG = "Jardine";
	public static String WEBTAG = "Webservice";
	// public static String WEB_URL =
	// "http://115.85.42.163/Jardine/webservice.php";
	// public static String WEB_URL =
	// "http://192.168.1.208/Jardine/webservice.php";
	// public static String WEB_URL =
	// "http://124.105.240.108/Jardine/webservice.php";
	// public static String WEB_URL =
	// "http://192.168.1.208/Jardine/webservice.php";
	public static String WEB_URL = "http://112.199.54.226/Jardine/webservice.php";
	public static String SESSION_NAME = null;
	public static String COOKIES_HEADER = "Set-Cookie";
	public static CookieManager CookieManager = new CookieManager();
	public static HttpURLConnection httpConnection;

	public static DatabaseAdapter DB;

	// Storage
	public static String JARDINE_DIRECTORY = "";

	/** REQUESTS CONSTANTS **/
	public static final String REQUEST_LINEEND = "\r\n";
	public static final String REQUEST_TWOHYPHENS = "--";
	public static final String REQUEST_BOUNDARY = "*****";

	/** VOLLEY **/
	public static RequestQueue mRequestQueue;

	@Override
	public void onCreate() {
		context = getApplicationContext();
		JARDINE_DIRECTORY = Environment.getExternalStorageDirectory()
				+ "/Android/data/" + context.getPackageName() + "/Jardine";
		super.onCreate();
	}

	public static RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(context);
		}

		return mRequestQueue;
	}

	public static <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

}
