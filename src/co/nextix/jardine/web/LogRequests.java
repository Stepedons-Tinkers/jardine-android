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
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.web.requesters.DefaultRequester;
import co.nextix.jardine.web.requesters.LoginModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class LogRequests {
	// final String TAG = "Webservice_Log";
	final String TAG = JardineApp.TAG;

	public LoginModel login(String username, String password) {
		LoginModel model = null;
		String charset = "UTF-8";
		URL url;
		String urlString = JardineApp.WEB_URL;
		BufferedWriter writer = null;
		try {

			url = new URL(urlString);
			Log.d(TAG, urlString);
			Log.d(TAG, "username: " + username + " password: " + password);
			JardineApp.httpConnection = (HttpURLConnection) url
					.openConnection();
			JardineApp.httpConnection.setRequestProperty("Connection",
					"Keep-Alive");
			JardineApp.httpConnection.setRequestMethod("POST");
			JardineApp.httpConnection.setDoInput(true);
			JardineApp.httpConnection.setDoOutput(true);
			JardineApp.httpConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			// appending
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));
			params.add(new BasicNameValuePair("operation", "login"));

			// sending
			OutputStream os = JardineApp.httpConnection.getOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(os, charset));
			writer.write(getQuery(params));
			writer.flush();
			writer.close();
			os.close();

			// status
			int status = JardineApp.httpConnection.getResponseCode();
			Log.w(TAG, "status: " + status);

			if (status == 200) {
				// Get Response
				Gson gson = new Gson();
				Type typeOfT = new TypeToken<DefaultRequester<LoginModel>>() {
				}.getType();
				DefaultRequester<LoginModel> requester = gson.fromJson(
						getReader(), typeOfT);
				model = (LoginModel) requester.getResult();

				Map<String, List<String>> headerFields = JardineApp.httpConnection
						.getHeaderFields();
				List<String> cookiesHeader = headerFields
						.get(JardineApp.COOKIES_HEADER);
				if (cookiesHeader != null) {
					for (String cookie : cookiesHeader) {
						JardineApp.CookieManager.getCookieStore().add(null,
								HttpCookie.parse(cookie).get(0));
					}
				}
			} else if (status == 401) {
				// getResponse();
				// JardineApp.ErrorMessage = "Invalid user credentials";
			} else if (status == 403) {
				// unConfirmed = true;
				// // getResponse();
				// Gson gson = new Gson();
				// Type typeOfT = new TypeToken<Requester<LoginModel>>() {
				// }.getType();
				// Requester<LoginModel> requester = gson.fromJson(
				// getErrorReader(), typeOfT);
				// model = (LoginModel) requester.getContent();
			} else {
				// JardineApp.ErrorMessage =
				// "Something is not right. Please try again later";
				// getResponse();
			}

		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// JardineApp.ErrorMessage =
			// "Oops! Something is not right. Please try again later";
			e.printStackTrace();
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return model;
	}

	/**********************************************************/

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
}
