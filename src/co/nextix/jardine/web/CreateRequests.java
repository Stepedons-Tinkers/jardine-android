package co.nextix.jardine.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.SMRtimeCardRecord;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.picklists.PSMRentryTypeTable;
import co.nextix.jardine.web.requesters.DefaultRequester;
import co.nextix.jardine.web.requesters.WebCreateModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class CreateRequests {

	private final String TAG = "Webservice_Create";
	private final String operation = "creates";

	public List<WebCreateModel> competitorProduct(
			List<CompetitorProductRecord> records) {

		List<WebCreateModel> model = null;

		String sessionName, elements, elementType;
		JSONObject requestList = new JSONObject();
		try {
			// String assignedUser, String date,
			// String time, String entry

			UserTable userTable = DatabaseAdapter.getInstance().getUser();
			PSMRentryTypeTable smrEntry = DatabaseAdapter.getInstance()
					.getSMRentryType();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

//				// get user id from db
//				String id = userTable.getNoById(records.get(x).getUser());
//				requestObject.put("assigned_user_id", id);
//				requestObject.put("z_stc_date", records.get(x).getDate());
//				requestObject.put("z_stc_time", records.get(x).getTimestamp());
//				// get entry type from db
//				String entryType = smrEntry.getById(
//						records.get(x).getEntryType()).getName();
//				requestObject.put("z_stc_picklist", entryType);

				requestList.put(String.valueOf(records.get(x).getId()),
						requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getJsonConnection(url, "POST");

			DataOutputStream dos = new DataOutputStream(
					JardineApp.httpConnection.getOutputStream());

			dos.writeBytes(requestList.toString());

			dos.flush();
			dos.close();

			// status
			int status = JardineApp.httpConnection.getResponseCode();

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<DefaultRequester<List<WebCreateModel>>>() {
				}.getType();
				DefaultRequester<List<WebCreateModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WebCreateModel>) requester.getResult();

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

	public List<WebCreateModel> smrTimecard(List<SMRtimeCardRecord> records) {

		List<WebCreateModel> model = null;

		String sessionName, elements, elementType;
		JSONObject requestList = new JSONObject();
		try {
			// String assignedUser, String date,
			// String time, String entry

			UserTable userTable = DatabaseAdapter.getInstance().getUser();
			PSMRentryTypeTable smrEntry = DatabaseAdapter.getInstance()
					.getSMRentryType();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

				// get user id from db
				String id = userTable.getNoById(records.get(x).getUser());
				requestObject.put("assigned_user_id", id);
				requestObject.put("z_stc_date", records.get(x).getDate());
				requestObject.put("z_stc_time", records.get(x).getTimestamp());
				// get entry type from db
				String entryType = smrEntry.getById(
						records.get(x).getEntryType()).getName();
				requestObject.put("z_stc_picklist", entryType);

				requestList.put(String.valueOf(records.get(x).getId()),
						requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getJsonConnection(url, "POST");

			DataOutputStream dos = new DataOutputStream(
					JardineApp.httpConnection.getOutputStream());

			dos.writeBytes(requestList.toString());

			dos.flush();
			dos.close();

			// status
			int status = JardineApp.httpConnection.getResponseCode();

			if (status == 200) {

				Gson gson = new Gson();
				Type typeOfT = new TypeToken<DefaultRequester<List<WebCreateModel>>>() {
				}.getType();
				DefaultRequester<List<WebCreateModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<WebCreateModel>) requester.getResult();

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
