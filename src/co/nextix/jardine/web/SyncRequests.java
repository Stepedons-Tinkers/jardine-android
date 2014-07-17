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

import android.text.TextUtils;
import android.util.Log;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.web.models.CalendarModel;
import co.nextix.jardine.web.models.DocuRelModel;
import co.nextix.jardine.web.models.DocumentModel;
import co.nextix.jardine.web.requesters.DefaultRequester;
import co.nextix.jardine.web.requesters.sync.SactRequester;
import co.nextix.jardine.web.requesters.sync.SactRequester.ActResult;
import co.nextix.jardine.web.requesters.sync.SacttypeRequester;
import co.nextix.jardine.web.requesters.sync.SacttypeRequester.ActTypeResult;
import co.nextix.jardine.web.requesters.sync.SbuRequester;
import co.nextix.jardine.web.requesters.sync.SbuRequester.BuResult;
import co.nextix.jardine.web.requesters.sync.ScalendarRequester;
import co.nextix.jardine.web.requesters.sync.ScalendarRequester.CalendarResult;
import co.nextix.jardine.web.requesters.sync.ScompetitorRequester;
import co.nextix.jardine.web.requesters.sync.ScompetitorRequester.ComptResult;
import co.nextix.jardine.web.requesters.sync.ScompetrprodRequester;
import co.nextix.jardine.web.requesters.sync.ScompetrprodRequester.ComptProdResult;
import co.nextix.jardine.web.requesters.sync.ScompetrprodstockRequester;
import co.nextix.jardine.web.requesters.sync.ScompetrprodstockRequester.ComptProdStockResult;
import co.nextix.jardine.web.requesters.sync.ScustRequester;
import co.nextix.jardine.web.requesters.sync.ScustRequester.CustResult;
import co.nextix.jardine.web.requesters.sync.ScustconRequester;
import co.nextix.jardine.web.requesters.sync.ScustconRequester.CustConResult;
import co.nextix.jardine.web.requesters.sync.SeventRequester;
import co.nextix.jardine.web.requesters.sync.SeventRequester.EventResult;
import co.nextix.jardine.web.requesters.sync.SjdimerchRequester;
import co.nextix.jardine.web.requesters.sync.SjdimerchRequester.JdiMerchResult;
import co.nextix.jardine.web.requesters.sync.SjdiprodRequester;
import co.nextix.jardine.web.requesters.sync.SjdiprodRequester.JdiProdResult;
import co.nextix.jardine.web.requesters.sync.SmarkintRequester;
import co.nextix.jardine.web.requesters.sync.SmarkintRequester.MarketIntResult;
import co.nextix.jardine.web.requesters.sync.SmarmatRequester;
import co.nextix.jardine.web.requesters.sync.SmarmatRequester.MarketMatResult;
import co.nextix.jardine.web.requesters.sync.SproductRequester;
import co.nextix.jardine.web.requesters.sync.SproductRequester.ProdResult;
import co.nextix.jardine.web.requesters.sync.SprojreqRequester;
import co.nextix.jardine.web.requesters.sync.SprojreqRequester.ProjReqResult;
import co.nextix.jardine.web.requesters.sync.SsmrRequester;
import co.nextix.jardine.web.requesters.sync.SsmrRequester.SmrResult;
import co.nextix.jardine.web.requesters.sync.SsmrentryRequester;
import co.nextix.jardine.web.requesters.sync.SsmrentryRequester.SmrEntryResult;
import co.nextix.jardine.web.requesters.sync.SsupplierRequester;
import co.nextix.jardine.web.requesters.sync.SsupplierRequester.SuppResult;
import co.nextix.jardine.web.requesters.sync.SworkplanRequester;
import co.nextix.jardine.web.requesters.sync.SworkplanRequester.WorkResult;
import co.nextix.jardine.web.requesters.sync.SworkplanentryRequester;
import co.nextix.jardine.web.requesters.sync.SworkplanentryRequester.WorkEntryResult;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class SyncRequests {
	private final String TAG = "Webservice";
	private final String operation = "sync";

	public BuResult BusinessUnit(String lastSync) {

		BuResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.BusinessUnit + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<SbuRequester>() {
				}.getType();
				SbuRequester requester = gson.fromJson(getReader(), typeOfT);
				result = (BuResult) requester.getResult();

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

		return result;
	}

	public MarketMatResult MarketingMaterials(String lastSync) {

		MarketMatResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.MarketingMaterials + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<SmarmatRequester>() {
				}.getType();
				SmarmatRequester requester = gson
						.fromJson(getReader(), typeOfT);
				result = (MarketMatResult) requester.getResult();

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

		return result;
	}

	public EventResult EventProtocols(String lastSync) {

		EventResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.EventProtocol + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<SeventRequester>() {
				}.getType();
				SeventRequester requester = gson.fromJson(getReader(), typeOfT);
				result = (EventResult) requester.getResult();

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

		return result;
	}

	public ProdResult Product(String lastSync) {

		ProdResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Product + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&modifiedTime=" + time + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<SproductRequester>() {
				}.getType();
				SproductRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (ProdResult) requester.getResult();

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

		return result;
	}

	public SuppResult Supplier(String lastSync) {

		SuppResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Supplier + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&modifiedTime=" + time + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<SsupplierRequester>() {
				}.getType();
				SsupplierRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (SuppResult) requester.getResult();

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

		return result;
	}

	public ComptResult Competitor(String lastSync) {

		ComptResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Competitor + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<ScompetitorRequester>() {
				}.getType();
				ScompetitorRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (ComptResult) requester.getResult();

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

		return result;
	}

	public ComptProdResult CompetitorProduct(String lastSync) {

		ComptProdResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.CompetitorProduct + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<ScompetrprodRequester>() {
				}.getType();
				ScompetrprodRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (ComptProdResult) requester.getResult();

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

		return result;
	}

	public SmrResult SMR(String lastSync) {

		SmrResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType=" + Modules.SMR
				+ "&sessionName=" + JardineApp.SESSION_NAME + "&modifiedTime="
				+ time + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<SsmrRequester>() {
				}.getType();
				SsmrRequester requester = gson.fromJson(getReader(), typeOfT);
				result = (SmrResult) requester.getResult();

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

		return result;
	}

	public SmrEntryResult SMRTimecard(String lastSync) {

		SmrEntryResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.SMRTimeCard + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<SsmrentryRequester>() {
				}.getType();
				SsmrentryRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (SmrEntryResult) requester.getResult();

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

		return result;
	}

	public CustResult Customer(String lastSync) {

		CustResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Customers + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&modifiedTime=" + time + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<ScustRequester>() {
				}.getType();
				ScustRequester requester = gson.fromJson(getReader(), typeOfT);
				result = (CustResult) requester.getResult();

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

		return result;
	}

	public CustConResult CustomerContact(String lastSync) {

		CustConResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.CustomerContact + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<ScustconRequester>() {
				}.getType();
				ScustconRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (CustConResult) requester.getResult();

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

		return result;
	}

	public ActTypeResult ActivityType(String lastSync) {

		ActTypeResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.ActivityType + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<SacttypeRequester>() {
				}.getType();
				SacttypeRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (ActTypeResult) requester.getResult();

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

		return result;
	}

	public WorkResult Workplan(String lastSync) {

		WorkResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Workplan + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&modifiedTime=" + time + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<SworkplanRequester>() {
				}.getType();
				SworkplanRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (WorkResult) requester.getResult();

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

		return result;
	}

	public WorkEntryResult WorkplanEntry(String lastSync) {

		WorkEntryResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.WorkplanEntry + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<SworkplanentryRequester>() {
				}.getType();
				SworkplanentryRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (WorkEntryResult) requester.getResult();

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

		return result;
	}

	public ActResult Activity(String lastSync) {

		ActResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Activity + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&modifiedTime=" + time + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<SactRequester>() {
				}.getType();
				SactRequester requester = gson.fromJson(getReader(), typeOfT);
				result = (ActResult) requester.getResult();

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

		return result;
	}

	public JdiMerchResult JDImerchandising(String lastSync) {

		JdiMerchResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.JDIMerchCheck + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<SjdimerchRequester>() {
				}.getType();
				SjdimerchRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (JdiMerchResult) requester.getResult();

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

		return result;
	}

	public JdiProdResult JDIproduct(String lastSync) {

		JdiProdResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.JDIProductStockCheck + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<SjdiprodRequester>() {
				}.getType();
				SjdiprodRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (JdiProdResult) requester.getResult();

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

		return result;
	}

	public ComptProdStockResult CompetitorProductStockCheck(String lastSync) {

		ComptProdStockResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.CompetitorProductStockCheck + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<ScompetrprodstockRequester>() {
				}.getType();
				ScompetrprodstockRequester requester = gson.fromJson(
						getReader(), typeOfT);
				result = (ComptProdStockResult) requester.getResult();

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

		return result;
	}

	public MarketIntResult MarketingIntel(String lastSync) {

		MarketIntResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.MarketingIntel + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<SmarkintRequester>() {
				}.getType();
				SmarkintRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (MarketIntResult) requester.getResult();

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

		return result;
	}

	public ProjReqResult ProjectRequirement(String lastSync) {

		ProjReqResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.ProjectRequirement + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + time
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
				Type typeOfT = new TypeToken<SprojreqRequester>() {
				}.getType();
				SprojreqRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (ProjReqResult) requester.getResult();

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

		return result;
	}

	public CalendarResult Calendar(String lastSync) {

		CalendarResult result = null;

		String time = "";
		try {
			time = URLEncoder.encode(lastSync, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Calendar + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&modifiedTime=" + time + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<ScalendarRequester>() {
				}.getType();
				ScalendarRequester requester = gson.fromJson(getReader(),
						typeOfT);
				result = (CalendarResult) requester.getResult();

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

		return result;
	}

	public List<DocuRelModel> DocumentRelationships(String crmNo) {

		List<DocuRelModel> result = null;

		String q = "\"select * from vtiger_senotesrel where crmid=" + crmNo
				+ "\"";
		String query = "";
		try {
			query = URLEncoder.encode(q, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Document + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&query=" + query + "&operation=" + "querypicklist";

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
				Type typeOfT = new TypeToken<DefaultRequester<List<DocuRelModel>>>() {
				}.getType();
				DefaultRequester<List<DocuRelModel>> requester = gson.fromJson(
						getReader(), typeOfT);
				result = (List<DocuRelModel>) requester.getResult();

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

		return result;
	}

	public List<DocumentModel> Documents(String[] crmNos) {

		List<DocumentModel> result = null;

		// String time = "";
		// try {
		// time = URLEncoder.encode(lastSync, "UTF-8");
		// } catch (UnsupportedEncodingException e1) {
		// e1.printStackTrace();
		// }
		//
		// String urlString = JardineApp.WEB_URL + "?elementType="
		// + Modules.ProjectRequirement + "&sessionName="
		// + JardineApp.SESSION_NAME + "&modifiedTime=" + time
		// + "&operation=" + operation;
		//
		// URL url;
		// try {
		//
		// url = new URL(urlString);
		// Log.d(TAG, urlString);
		// getConnection(url, "GET");
		//
		// // status
		// int status = JardineApp.httpConnection.getResponseCode();
		// Log.w(TAG, "status: " + status);
		//
		// if (status == 200) {
		//
		// Gson gson = new Gson();
		// Type typeOfT = new TypeToken<SprojreqRequester>() {
		// }.getType();
		// SprojreqRequester requester = gson.fromJson(getReader(),
		// typeOfT);
		// result = (ProjReqResult) requester.getResult();
		//
		// } else {
		// // getResponse();
		// }
		//
		// } catch (ProtocolException e) {
		// e.printStackTrace();
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		return result;
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
