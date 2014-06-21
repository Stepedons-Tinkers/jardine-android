package co.nextix.jardine;

import java.net.CookieManager;
import java.net.HttpURLConnection;

import co.nextix.jardine.database.DatabaseAdapter;
import android.app.Application;
import android.content.Context;

public class JardineApp extends Application {

	public static Context context;
	public static String TAG = "Jardine";
	public static String WEB_URL = "http://115.85.42.163/Jardine/webservice.php";
	public static String SESSION_NAME = null;
	public static String COOKIES_HEADER = "Set-Cookie";
	public static CookieManager CookieManager = new CookieManager();
	public static HttpURLConnection httpConnection;
	/** REQUESTS CONSTANTS **/
	public static final String REQUEST_LINEEND = "\r\n";
	public static final String REQUEST_TWOHYPHENS = "--";
	public static final String REQUEST_BOUNDARY = "*****";

	@Override
	public void onCreate() {
		DatabaseAdapter.init(this);
		DatabaseAdapter.getInstance().open();
		super.onCreate();
	}

}
