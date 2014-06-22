package co.nextix.jardine.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.client.utils.URIUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.web.models.BusinessUnitModel;
import co.nextix.jardine.web.requesters.RetrieveRequester;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class PicklistRequests {

	private final String TAG = "Webservice_Picklist";
	private final String operation = "querypicklist";

	public List<RetrieveRequester<BusinessUnitModel>.Result> BusinessUnit(
			String[] ids) {
		// GET
		// http://115.85.42.163/Jardine/webservice.php?elementType=XMarketingIntel&sessionName=11290558539c19875257b&ids={"0":412}&operation=retrieves

		List<RetrieveRequester<BusinessUnitModel>.Result> model = null;
		JSONArray jsonArray = new JSONArray();
		try {
			for (int x = 0; x < ids.length; x++) {
				jsonArray.put(x, ids[x]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.BusinessUnit + "&sessionName="
				+ JardineApp.SESSION_NAME + "&ids=" + jsonArray.toString()
				+ "&operation=" + operation;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<RetrieveRequester<BusinessUnitModel>>() {
				}.getType();
				RetrieveRequester<BusinessUnitModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (List<RetrieveRequester<BusinessUnitModel>.Result>) requester
						.getResult();

			} else {
				// getResponse();
			}

		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}

	public List<PicklistModel> picklists() {

		List<PicklistModel> model = null;

		String query = "";
//		try {
//			query = URLEncoder
//					.encode("\"select fieldid, fieldname, tablename from vtiger_field where uitype = 15 and tablename in ('vtiger_xcustomers', 'vtiger_xactivity', 'vtiger_xccperson', 'vtiger_xeventprotocol', 'vtiger_xactivitytype', 'vtiger_xworkplanentry', 'vtiger_xjdiproductstockcheck', 'vtiger_xcompprodstockcheck', 'vtiger_xjdimerchcheck', 'vtiger_xprojectrequirement')\"&sessionName=16ea00dd53a54a56c8cb2&operation=querypicklist",
//							"UTF-8");
			query = Uri.encode("\"select fieldid, fieldname, tablename from vtiger_field where uitype = 15 and tablename in ('vtiger_xcustomers', 'vtiger_xactivity', 'vtiger_xccperson', 'vtiger_xeventprotocol', 'vtiger_xactivitytype', 'vtiger_xworkplanentry', 'vtiger_xjdiproductstockcheck', 'vtiger_xcompprodstockcheck', 'vtiger_xjdimerchcheck', 'vtiger_xprojectrequirement')\"&sessionName=16ea00dd53a54a56c8cb2&operation=querypicklist",
							"UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String urlString = JardineApp.WEB_URL + "?query=" + query;

		URL url;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			getConnection(url, "GET");

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<PicklistRequester>() {
				}.getType();
				PicklistRequester requester = gson.fromJson(getReader(),
						typeOfT);
				model = (List<PicklistModel>) requester.getResult();

			} else {
				// getResponse();
			}

		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}

	/**********************************************************/

	private void getConnection(URL url, String requestMethod) {

		try {
			JardineApp.httpConnection = (HttpURLConnection) url
					.openConnection();
			JardineApp.httpConnection.setRequestMethod(requestMethod);
			JardineApp.httpConnection.setDoInput(true);
			if (requestMethod.equals("GET") || requestMethod.equals("DELETE")) {
				JardineApp.httpConnection.setDoOutput(false);
				JardineApp.httpConnection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
			} else {
				JardineApp.httpConnection.setDoOutput(true);
				JardineApp.httpConnection.setRequestProperty("Content-Type",
						"multipart/form-data;boundary="
								+ JardineApp.REQUEST_BOUNDARY);
			}

			if (JardineApp.CookieManager.getCookieStore().getCookies().size() > 0) {
				JardineApp.httpConnection.setRequestProperty("Cookie",
						TextUtils.join(",", JardineApp.CookieManager
								.getCookieStore().getCookies()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getJsonConnection(URL url, String requestMethod) {

		try {
			JardineApp.httpConnection = (HttpURLConnection) url
					.openConnection();
			JardineApp.httpConnection.setRequestMethod(requestMethod);
			JardineApp.httpConnection.setDoInput(true);
			if (requestMethod.equals("GET")) {
				JardineApp.httpConnection.setDoOutput(false);

			} else {
				JardineApp.httpConnection.setDoOutput(true);
			}

			JardineApp.httpConnection.setRequestProperty("Content-Type",
					"application/json");

			if (JardineApp.CookieManager.getCookieStore().getCookies().size() > 0) {
				JardineApp.httpConnection.setRequestProperty("Cookie",
						TextUtils.join(",", JardineApp.CookieManager
								.getCookieStore().getCookies()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private JsonReader getReader() {
		InputStream is;
		StringBuffer response = new StringBuffer();
		InputStream in = null;
		JsonReader reader = null;
		try {
			is = JardineApp.httpConnection.getInputStream();

			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;

			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();

			String reply = response.toString();
			Log.i(TAG, reply);

			// reading
			in = new ByteArrayInputStream(reply.getBytes("UTF-8"));
			reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}

	// private void getResponse() {
	// Gson gson = new Gson();
	// ResponseModel errorModel = gson.fromJson(getReader(),
	// ResponseModel.class);
	// JardineApp.ErrorMessage = errorModel.getMessage();
	// }
}
