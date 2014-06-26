package co.nextix.jardine.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.tables.CompetitorTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.web.requesters.DefaultRequester;
import co.nextix.jardine.web.requesters.WebCreateModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class UpdateRequests {
	private final String TAG = "Webservice";
	private final String operation = "updates";
	private final String charset = "UTF-8";
	private final DatabaseAdapter DB = DatabaseAdapter.getInstance();

	public List<WebCreateModel> customer(
			List<CustomerRecord> records) {

		List<WebCreateModel> model = null;
		JSONObject requestList = new JSONObject();
		try {

			UserTable userTable = DB.getUser();
			CompetitorTable compTable = DB.getCompetitor();

			for (int x = 0; x < records.size(); x++) {
				JSONObject requestObject = new JSONObject();

//				// get user id from db
//				String id = userTable.getNoById(records.get(x).getUser());
//				requestObject.put("assigned_user_id", id);
//				String competitor = compTable.getNoById(records.get(x)
//						.getCompetitor());
//				requestObject.put("z_cmp_comp", competitor);
//				requestObject.put("z_cmp_prbrnd", records.get(x)
//						.getProductBrand());
//				requestObject.put("z_cmp_prdesc", records.get(x)
//						.getProductDescription());
//				requestObject.put("z_cmp_prsize", records.get(x)
//						.getProductSize());
//				requestObject.put("z_cmp_isactive", records.get(x)
//						.getIsActive());
//
				requestList.put(String.valueOf(records.get(x).getId()),
						requestObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		BufferedWriter writer;
		URL url;
		String urlString = JardineApp.WEB_URL;
		Log.d(TAG, urlString);

		try {

			url = new URL(urlString);
			getConnection(url, "POST");

			// DataOutputStream dos = new DataOutputStream(
			// JardineApp.httpConnection.getOutputStream());
			//
			// dos.writeBytes(requestList.toString());
			//
			// dos.flush();
			// dos.close();

			// appending
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("sessionName",
					JardineApp.SESSION_NAME));
			params.add(new BasicNameValuePair("operation", operation));
			params.add(new BasicNameValuePair("elementType",
					Modules.CompetitorProduct));
			params.add(new BasicNameValuePair("elements", requestList
					.toString()));

			// sending
			OutputStream os = JardineApp.httpConnection.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			writer.write(getQuery(params));
			writer.flush();
			writer.close();
			os.close();

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

	private String getQuery(List<NameValuePair> params)
			throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;

		for (NameValuePair pair : params) {
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
		}

		return result.toString();
	}

	// private void getResponse() {
	// Gson gson = new Gson();
	// ResponseModel errorModel = gson.fromJson(getReader(),
	// ResponseModel.class);
	// JardineApp.ErrorMessage = errorModel.getMessage();
	// }
}
