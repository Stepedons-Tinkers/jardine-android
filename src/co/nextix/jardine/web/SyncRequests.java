package co.nextix.jardine.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import android.text.TextUtils;
import android.util.Log;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.web.models.ActivityModel;
import co.nextix.jardine.web.models.ActivityTypeModel;
import co.nextix.jardine.web.models.BusinessUnitModel;
import co.nextix.jardine.web.models.CompetitorModel;
import co.nextix.jardine.web.models.CompetitorProductModel;
import co.nextix.jardine.web.models.CompetitorProductStockCheckModel;
import co.nextix.jardine.web.models.CustomerContactModel;
import co.nextix.jardine.web.models.CustomerModel;
import co.nextix.jardine.web.models.EventProtocolModel;
import co.nextix.jardine.web.models.JDImerchandisingCheckModel;
import co.nextix.jardine.web.models.JDIproductStockCheckModel;
import co.nextix.jardine.web.models.MarketingIntelModel;
import co.nextix.jardine.web.models.MarketingMaterialsModel;
import co.nextix.jardine.web.models.ProductModel;
import co.nextix.jardine.web.models.ProjectRequirementModel;
import co.nextix.jardine.web.models.SMRModel;
import co.nextix.jardine.web.models.SMRtimeCardModel;
import co.nextix.jardine.web.models.SupplierModel;
import co.nextix.jardine.web.models.WorkplanEntryModel;
import co.nextix.jardine.web.models.WorkplanModel;
import co.nextix.jardine.web.requesters.SyncRequester;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class SyncRequests {
	private final String TAG = "Webservice_Sync";
	private final String operation = "sync";

	public SyncRequester<List<BusinessUnitModel>>.Result BusinessUnit(
			String lastSync) {

		SyncRequester<List<BusinessUnitModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.BusinessUnit + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<BusinessUnitModel>>>() {
				}.getType();
				SyncRequester<List<BusinessUnitModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<MarketingMaterialsModel>>.Result MarketingMaterials(
			String lastSync) {

		SyncRequester<List<MarketingMaterialsModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.MarketingMaterials + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<MarketingMaterialsModel>>>() {
				}.getType();
				SyncRequester<List<MarketingMaterialsModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<EventProtocolModel>>.Result EventProtocols(
			String lastSync) {

		SyncRequester<List<EventProtocolModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.EventProtocol + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<EventProtocolModel>>>() {
				}.getType();
				SyncRequester<List<EventProtocolModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<ProductModel>>.Result Product(String lastSync) {

		SyncRequester<List<ProductModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Product + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&modifiedTime=" + lastSync + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<SyncRequester<List<ProductModel>>>() {
				}.getType();
				SyncRequester<List<ProductModel>> requester = gson.fromJson(
						getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<SupplierModel>>.Result Supplier(String lastSync) {

		SyncRequester<List<SupplierModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Supplier + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&modifiedTime=" + lastSync + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<SyncRequester<List<SupplierModel>>>() {
				}.getType();
				SyncRequester<List<SupplierModel>> requester = gson.fromJson(
						getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<CompetitorModel>>.Result Competitor(
			String lastSync) {

		SyncRequester<List<CompetitorModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Competitor + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<CompetitorModel>>>() {
				}.getType();
				SyncRequester<List<CompetitorModel>> requester = gson.fromJson(
						getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<CompetitorProductModel>>.Result CompetitorProduct(
			String lastSync) {

		SyncRequester<List<CompetitorProductModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.CompetitorProduct + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<CompetitorProductModel>>>() {
				}.getType();
				SyncRequester<List<CompetitorProductModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<SMRModel>>.Result SMR(String lastSync) {

		SyncRequester<List<SMRModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType=" + Modules.SMR
				+ "&sessionName=" + JardineApp.SESSION_NAME + "&modifiedTime="
				+ lastSync + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<SyncRequester<List<SMRModel>>>() {
				}.getType();
				SyncRequester<List<SMRModel>> requester = gson.fromJson(
						getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<SMRtimeCardModel>>.Result SMRTimecard(
			String lastSync) {

		SyncRequester<List<SMRtimeCardModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.SMRTimeCard + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<SMRtimeCardModel>>>() {
				}.getType();
				SyncRequester<List<SMRtimeCardModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<CustomerModel>>.Result Customer(String lastSync) {

		SyncRequester<List<CustomerModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Customers + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&modifiedTime=" + lastSync + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<SyncRequester<List<CustomerModel>>>() {
				}.getType();
				SyncRequester<List<CustomerModel>> requester = gson.fromJson(
						getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<CustomerContactModel>>.Result CustomerContact(
			String lastSync) {

		SyncRequester<List<CustomerContactModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.CustomerContact + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<CustomerContactModel>>>() {
				}.getType();
				SyncRequester<List<CustomerContactModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<ActivityTypeModel>>.Result ActivityType(
			String lastSync) {

		SyncRequester<List<ActivityTypeModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.ActivityType + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<ActivityTypeModel>>>() {
				}.getType();
				SyncRequester<List<ActivityTypeModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<WorkplanModel>>.Result Workplan(String lastSync) {

		SyncRequester<List<WorkplanModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Workplan + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&modifiedTime=" + lastSync + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<SyncRequester<List<WorkplanModel>>>() {
				}.getType();
				SyncRequester<List<WorkplanModel>> requester = gson.fromJson(
						getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<WorkplanEntryModel>>.Result WorkplanEntry(
			String lastSync) {

		SyncRequester<List<WorkplanEntryModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.ActivityType + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<WorkplanEntryModel>>>() {
				}.getType();
				SyncRequester<List<WorkplanEntryModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<ActivityModel>>.Result Activity(String lastSync) {

		SyncRequester<List<ActivityModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.Activity + "&sessionName=" + JardineApp.SESSION_NAME
				+ "&modifiedTime=" + lastSync + "&operation=" + operation;

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
				Type typeOfT = new TypeToken<SyncRequester<List<ActivityModel>>>() {
				}.getType();
				SyncRequester<List<ActivityModel>> requester = gson.fromJson(
						getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<JDImerchandisingCheckModel>>.Result JDImerchandising(
			String lastSync) {

		SyncRequester<List<JDImerchandisingCheckModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.JDIMerchCheck + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<JDImerchandisingCheckModel>>>() {
				}.getType();
				SyncRequester<List<JDImerchandisingCheckModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<JDIproductStockCheckModel>>.Result JDIproduct(
			String lastSync) {

		SyncRequester<List<JDIproductStockCheckModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.JDIProductStockCheck + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<JDIproductStockCheckModel>>>() {
				}.getType();
				SyncRequester<List<JDIproductStockCheckModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<CompetitorProductStockCheckModel>>.Result CompetitorProductStockCheck(
			String lastSync) {

		SyncRequester<List<CompetitorProductStockCheckModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.CompetitorProductStockCheck + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<CompetitorProductStockCheckModel>>>() {
				}.getType();
				SyncRequester<List<CompetitorProductStockCheckModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<MarketingIntelModel>>.Result MarketingIntel(
			String lastSync) {

		SyncRequester<List<MarketingIntelModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.MarketingIntel + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<MarketingIntelModel>>>() {
				}.getType();
				SyncRequester<List<MarketingIntelModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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

	public SyncRequester<List<ProjectRequirementModel>>.Result ProjectRequirement(
			String lastSync) {

		SyncRequester<List<ProjectRequirementModel>>.Result result = null;

		String urlString = JardineApp.WEB_URL + "?elementType="
				+ Modules.ProjectRequirement + "&sessionName="
				+ JardineApp.SESSION_NAME + "&modifiedTime=" + lastSync
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
				Type typeOfT = new TypeToken<SyncRequester<List<ProjectRequirementModel>>>() {
				}.getType();
				SyncRequester<List<ProjectRequirementModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				result = requester.getResult();

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
