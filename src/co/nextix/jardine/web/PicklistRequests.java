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
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.text.TextUtils;
import android.util.Log;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.keys.Modules;
import co.nextix.jardine.web.models.BusinessUnitModel;
import co.nextix.jardine.web.models.PicklistDependencyModel;
import co.nextix.jardine.web.models.picklist.PactivityEndUserActivityTypes;
import co.nextix.jardine.web.models.picklist.PactivityProjectcategoryModel;
import co.nextix.jardine.web.models.picklist.PactivityProjectstageModel;
import co.nextix.jardine.web.models.picklist.PactivitytypeCategoryModel;
import co.nextix.jardine.web.models.picklist.PcustomerSizeModel;
import co.nextix.jardine.web.models.picklist.PcustomerTypeModel;
import co.nextix.jardine.web.models.picklist.PcustomercontactPositionModel;
import co.nextix.jardine.web.models.picklist.PeventprotocolTypeModel;
import co.nextix.jardine.web.models.picklist.PjdimerchcheckStatusModel;
import co.nextix.jardine.web.models.picklist.PjdiproductstockStatusModel;
import co.nextix.jardine.web.models.picklist.PprojectrequirementTypeModel;
import co.nextix.jardine.web.models.picklist.PsalesProtocolTypeModel;
import co.nextix.jardine.web.models.picklist.PsmrTimecardEntryModel;
import co.nextix.jardine.web.models.picklist.PworkplanentryStatusModel;
import co.nextix.jardine.web.requesters.PicklistRequester;
import co.nextix.jardine.web.requesters.RetrieveRequester;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class PicklistRequests {

	private final String TAG = "Webservice";
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

	// public List<PicklistModel> picklists() {
	//
	// List<PicklistModel> model = null;
	//
	// String query = "";
	// try {
	// query = URLEncoder
	// .encode("select fieldid, fieldname, tablename from vtiger_field where uitype = 15 and tablename in ('vtiger_xcustomers', 'vtiger_xactivity', 'vtiger_xccperson', 'vtiger_xeventprotocol', 'vtiger_xactivitytype', 'vtiger_xworkplanentry', 'vtiger_xjdiproductstockcheck', 'vtiger_xcompprodstockcheck', 'vtiger_xjdimerchcheck', 'vtiger_xprojectrequirement')",
	// "UTF-8");
	// // Uri query = Uri
	// //
	// .parse("\"select fieldid, fieldname, tablename from vtiger_field where uitype = 15 and tablename in ('vtiger_xcustomers', 'vtiger_xactivity', 'vtiger_xccperson', 'vtiger_xeventprotocol', 'vtiger_xactivitytype', 'vtiger_xworkplanentry', 'vtiger_xjdiproductstockcheck', 'vtiger_xcompprodstockcheck', 'vtiger_xjdimerchcheck', 'vtiger_xprojectrequirement')\"&sessionName=16ea00dd53a54a56c8cb2&operation=querypicklist");
	// } catch (UnsupportedEncodingException e1) {
	// e1.printStackTrace();
	// }
	// String urlString = JardineApp.WEB_URL + "?query=\"" + query
	// + "\"&sessionName=" + JardineApp.SESSION_NAME + "&operation="
	// + operation;
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
	// Type typeOfT = new TypeToken<PicklistRequester<List<PicklistModel>>>() {
	// }.getType();
	// PicklistRequester<List<PicklistModel>> requester = gson
	// .fromJson(getReader(), typeOfT);
	// model = (List<PicklistModel>) requester.getResult();
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
	//
	// return model;
	// }

	public List<String> picklists(String module) {

		List<String> result = null;

		String query = "";
		try {
			query = URLEncoder.encode("select " + module + " from vtiger_"
					+ module, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String urlString = JardineApp.WEB_URL + "?query=\"" + query
				+ "\"&sessionName=" + JardineApp.SESSION_NAME + "&operation="
				+ operation;

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
				if (module.equals(Modules.smrtimecard_entry)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PsmrTimecardEntryModel>>>() {
					}.getType();
					PicklistRequester<List<PsmrTimecardEntryModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PsmrTimecardEntryModel> list = (List<PsmrTimecardEntryModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PsmrTimecardEntryModel model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.customer_size)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PcustomerSizeModel>>>() {
					}.getType();
					PicklistRequester<List<PcustomerSizeModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PcustomerSizeModel> list = (List<PcustomerSizeModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PcustomerSizeModel model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.customer_type)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PcustomerTypeModel>>>() {
					}.getType();
					PicklistRequester<List<PcustomerTypeModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PcustomerTypeModel> list = (List<PcustomerTypeModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PcustomerTypeModel model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.customercontact_position)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PcustomercontactPositionModel>>>() {
					}.getType();
					PicklistRequester<List<PcustomercontactPositionModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PcustomercontactPositionModel> list = (List<PcustomercontactPositionModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PcustomercontactPositionModel model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.eventprotocol_eventtype)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PeventprotocolTypeModel>>>() {
					}.getType();
					PicklistRequester<List<PeventprotocolTypeModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PeventprotocolTypeModel> list = (List<PeventprotocolTypeModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PeventprotocolTypeModel model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.activitytype_category)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PactivitytypeCategoryModel>>>() {
					}.getType();
					PicklistRequester<List<PactivitytypeCategoryModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PactivitytypeCategoryModel> list = (List<PactivitytypeCategoryModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PactivitytypeCategoryModel model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.workplanentry_status)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PworkplanentryStatusModel>>>() {
					}.getType();
					PicklistRequester<List<PworkplanentryStatusModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PworkplanentryStatusModel> list = (List<PworkplanentryStatusModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PworkplanentryStatusModel model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.activity_projectstage)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PactivityProjectstageModel>>>() {
					}.getType();
					PicklistRequester<List<PactivityProjectstageModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PactivityProjectstageModel> list = (List<PactivityProjectstageModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PactivityProjectstageModel model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.activity_projectcategory)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PactivityProjectcategoryModel>>>() {
					}.getType();
					PicklistRequester<List<PactivityProjectcategoryModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PactivityProjectcategoryModel> list = (List<PactivityProjectcategoryModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PactivityProjectcategoryModel model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.jdiprodstock_status)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PjdiproductstockStatusModel>>>() {
					}.getType();
					PicklistRequester<List<PjdiproductstockStatusModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PjdiproductstockStatusModel> list = (List<PjdiproductstockStatusModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PjdiproductstockStatusModel model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.jdimerchcheck_status)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PjdimerchcheckStatusModel>>>() {
					}.getType();
					PicklistRequester<List<PjdimerchcheckStatusModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PjdimerchcheckStatusModel> list = (List<PjdimerchcheckStatusModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PjdimerchcheckStatusModel model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.projrequirement_type)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PprojectrequirementTypeModel>>>() {
					}.getType();
					PicklistRequester<List<PprojectrequirementTypeModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PprojectrequirementTypeModel> list = (List<PprojectrequirementTypeModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PprojectrequirementTypeModel model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.end_user_activity_types)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PactivityEndUserActivityTypes>>>() {
					}.getType();
					PicklistRequester<List<PactivityEndUserActivityTypes>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PactivityEndUserActivityTypes> list = (List<PactivityEndUserActivityTypes>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PactivityEndUserActivityTypes model : list) {
							result.add(model.getValue());
						}
					}
				} else if (module.equals(Modules.salesprotocol_type)) {
					Type typeOfT = new TypeToken<PicklistRequester<List<PsalesProtocolTypeModel>>>() {
					}.getType();
					PicklistRequester<List<PsalesProtocolTypeModel>> requester = gson
							.fromJson(getReader(), typeOfT);
					List<PsalesProtocolTypeModel> list = (List<PsalesProtocolTypeModel>) requester
							.getResult();
					if (list != null) {
						result = new ArrayList<String>();
						for (PsalesProtocolTypeModel model : list) {
							result.add(model.getValue());
						}
					}
				}
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

	public List<PactivityEndUserActivityTypes> endUserActivityTypes() {
		List<PactivityEndUserActivityTypes> model = null;

		String query = "";
		try {
			query = URLEncoder.encode("select * from vtiger_"
					+ Modules.end_user_activity_types, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String urlString = JardineApp.WEB_URL + "?query=\"" + query
				+ "\"&sessionName=" + JardineApp.SESSION_NAME + "&operation="
				+ operation;

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
				Type typeOfT = new TypeToken<PicklistRequester<List<PactivityEndUserActivityTypes>>>() {
				}.getType();
				PicklistRequester<List<PactivityEndUserActivityTypes>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<PactivityEndUserActivityTypes>) requester
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

	public List<PicklistDependencyModel> area() {

		List<PicklistDependencyModel> model = null;

		String query = "";
		try {
			query = URLEncoder
					.encode("select * from vtiger_picklist_dependency where sourcefield = 'z_area'",
							"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String urlString = JardineApp.WEB_URL + "?query=\"" + query
				+ "\"&sessionName=" + JardineApp.SESSION_NAME + "&operation="
				+ operation;

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
				Type typeOfT = new TypeToken<PicklistRequester<List<PicklistDependencyModel>>>() {
				}.getType();
				PicklistRequester<List<PicklistDependencyModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<PicklistDependencyModel>) requester.getResult();

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

	public List<PicklistDependencyModel> province() {

		List<PicklistDependencyModel> model = null;

		String query = "";
		try {
			query = URLEncoder
					.encode("select * from vtiger_picklist_dependency where sourcefield = 'z_province'",
							"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String urlString = JardineApp.WEB_URL + "?query=\"" + query
				+ "\"&sessionName=" + JardineApp.SESSION_NAME + "&operation="
				+ operation;

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
				Type typeOfT = new TypeToken<PicklistRequester<List<PicklistDependencyModel>>>() {
				}.getType();
				PicklistRequester<List<PicklistDependencyModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<PicklistDependencyModel>) requester.getResult();

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

	public List<PicklistDependencyModel> city() {

		List<PicklistDependencyModel> model = null;

		String query = "";
		try {
			query = URLEncoder
					.encode("select * from vtiger_picklist_dependency where sourcefield = 'z_city'",
							"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String urlString = JardineApp.WEB_URL + "?query=\"" + query
				+ "\"&sessionName=" + JardineApp.SESSION_NAME + "&operation="
				+ operation;

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
				Type typeOfT = new TypeToken<PicklistRequester<List<PicklistDependencyModel>>>() {
				}.getType();
				PicklistRequester<List<PicklistDependencyModel>> requester = gson
						.fromJson(getReader(), typeOfT);
				model = (List<PicklistDependencyModel>) requester.getResult();

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

	// public List<PicklistModel> smrEntryType() {
	//
	// List<PicklistModel> model = null;
	//
	// String query = "";
	// try {
	// query = URLEncoder
	// .encode("select * from vtiger_picklist_dependency where sourcefield = 'z_city'",
	// "UTF-8");
	// } catch (UnsupportedEncodingException e1) {
	// e1.printStackTrace();
	// }
	// String urlString = JardineApp.WEB_URL + "?query=\"" + query
	// + "\"&sessionName=" + JardineApp.SESSION_NAME + "&operation="
	// + operation;
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
	// Type typeOfT = new
	// TypeToken<PicklistRequester<List<PicklistDependencyModel>>>() {
	// }.getType();
	// PicklistRequester<List<PicklistDependencyModel>> requester = gson
	// .fromJson(getReader(), typeOfT);
	// model = (List<PicklistDependencyModel>) requester.getResult();
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
	//
	// return model;
	// }

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

	// private List<String> getValues(Class<T> c) {
	// Gson gson = new Gson();
	// List<String> result;
	// Type typeOfT = new TypeToken<PicklistRequester<List<c>>>() {
	// }.getType();
	// PicklistRequester<List<PcustomerSizeModel>> requester = gson.fromJson(
	// getReader(), typeOfT);
	// List<PcustomerSizeModel> list = (List<PcustomerSizeModel>) requester
	// .getResult();
	// if (list != null) {
	// result = new ArrayList<String>();
	// for (PcustomerSizeModel model : list) {
	// result.add(model.getValue());
	// }
	// }
	// }

	// private void getResponse() {
	// Gson gson = new Gson();
	// ResponseModel errorModel = gson.fromJson(getReader(),
	// ResponseModel.class);
	// JardineApp.ErrorMessage = errorModel.getMessage();
	// }
}
