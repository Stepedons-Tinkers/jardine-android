package co.nextix.jardine.customers;

import java.util.Calendar;
import java.util.List;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddCustomerFragment extends Fragment implements OnClickListener {

	private View view;
	private long userId;
	private String userName;

	private Button save, cancel;
	private TextView field1;
	private EditText field2, field3, field4, field5;
	private Spinner field6, field7;
	private Spinner field8, field9, field10, field11;
	private EditText field12;
	private TextView field13;
	private Spinner field14;

	public static AddCustomerFragment newInstance() {
		AddCustomerFragment fragment = new AddCustomerFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		String id = StoreAccount.restore(JardineApp.context).getString(
				Account.ROWID);
		userName = StoreAccount.restore(JardineApp.context).getString(
				Account.USERNAME);
		userId = Long.parseLong(id);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.customer_add_new, container, false);

		initLayout();
		return view;
	}

	private void initLayout() {
		cancel = (Button) view.findViewById(R.id.bCustomerAddCancel);
		save = (Button) view.findViewById(R.id.bCustomerAddCreate);

		cancel.setOnClickListener(this);
		save.setOnClickListener(this);

		field1 = (TextView) view.findViewById(R.id.tvCustomerAddField1);

		field2 = (EditText) view.findViewById(R.id.etCustomerAddField2);
		field3 = (EditText) view.findViewById(R.id.etCustomerAddField3);
		field4 = (EditText) view.findViewById(R.id.etCustomerAddField4);
		field5 = (EditText) view.findViewById(R.id.etCustomerAddField5);

		field6 = (Spinner) view.findViewById(R.id.spiCustomerAddField6);
		field7 = (Spinner) view.findViewById(R.id.spiCustomerAddField7);

		field8 = (Spinner) view.findViewById(R.id.spiCustomerAddField8);

		field9 = (Spinner) view.findViewById(R.id.spiCustomerAddField9);
		field10 = (Spinner) view.findViewById(R.id.spiCustomerAddField10);
		field11 = (Spinner) view.findViewById(R.id.spiCustomerAddField11);

		field12 = (EditText) view.findViewById(R.id.etCustomerAddField12);

		field13 = (TextView) view.findViewById(R.id.tvCustomerAddField13);

		field14 = (Spinner) view.findViewById(R.id.spiCustomerAddField14);

		// List to be populated in spinner adapter
		List<PicklistRecord> customerSize = JardineApp.DB.getCustomerSize()
				.getAllRecords();
		List<PicklistRecord> customerType = JardineApp.DB.getCustomerType()
				.getAllRecords();
		List<PicklistRecord> area = JardineApp.DB.getArea().getAllRecords();
		List<ProvinceRecord> province = JardineApp.DB.getProvince()
				.getAllRecords();
		List<CityTownRecord> city = JardineApp.DB.getCityTown().getAllRecords();

		List<BusinessUnitRecord> bRecord = JardineApp.DB.getBusinessUnit()
				.getAllRecords();

		List<PicklistRecord> customerRecordStatus = JardineApp.DB
				.getCustomerRecordStatus().getAllRecords();

		// ArrayAdapter for spinners
		ArrayAdapter<PicklistRecord> adapter6 = new ArrayAdapter<PicklistRecord>(
				JardineApp.context, R.layout.workplan_spinner_row, customerSize);

		ArrayAdapter<PicklistRecord> adapter7 = new ArrayAdapter<PicklistRecord>(
				JardineApp.context, R.layout.workplan_spinner_row, customerType);

		ArrayAdapter<PicklistRecord> adapter9 = new ArrayAdapter<PicklistRecord>(
				JardineApp.context, R.layout.workplan_spinner_row, area);
		ArrayAdapter<ProvinceRecord> adapter10 = new ArrayAdapter<ProvinceRecord>(
				JardineApp.context, R.layout.workplan_spinner_row, province);
		ArrayAdapter<CityTownRecord> adapter11 = new ArrayAdapter<CityTownRecord>(
				JardineApp.context, R.layout.workplan_spinner_row, city);

		ArrayAdapter<BusinessUnitRecord> adapter8 = new ArrayAdapter<BusinessUnitRecord>(
				JardineApp.context, R.layout.workplan_spinner_row, bRecord);

		ArrayAdapter<PicklistRecord> adapter14 = new ArrayAdapter<PicklistRecord>(
				JardineApp.context, R.layout.workplan_spinner_row,
				customerRecordStatus);

		field6.setAdapter(adapter6);
		field7.setAdapter(adapter7);
		field9.setAdapter(adapter9);
		field10.setAdapter(adapter10);
		field11.setAdapter(adapter11);
		field8.setAdapter(adapter8);
		field14.setAdapter(adapter14);

		UserTable u = DatabaseAdapter.getInstance().getUser();
		if (u != null) {
			UserRecord user = u.getCurrentUser();
			if (user != null) {
				field13.setText(user.getFirstNameName() + " " + " "
						+ user.getLastname());
			}
		}

	}

	public void insert() {

		Calendar c = Calendar.getInstance();

		String no = "";
		String crmNo = "";
		String customerName = field2.getText().toString();
		String streetAddress = field3.getText().toString();
		String chainName = field4.getText().toString();
		String landline = field5.getText().toString();
		long customerSize = ((PicklistRecord) field6.getSelectedItem()).getId();
		long customerType = ((PicklistRecord) field7.getSelectedItem()).getId();
		long businessUnit = ((BusinessUnitRecord) field8.getSelectedItem())
				.getId();
		long area = ((PicklistRecord) field9.getSelectedItem()).getId();
		long province = ((ProvinceRecord) field10.getSelectedItem()).getId();
		long cityTown = ((CityTownRecord) field11.getSelectedItem()).getId();
		String fax = field12.getText().toString();
		long created_by = userId;
		long customerRecordStatus = ((PicklistRecord) field14.getSelectedItem())
				.getId();
		int isActive = 1;

		String createdTime = "";
		String modifiedTime = "";

		JardineApp.DB.getCustomer().insert(no, crmNo, customerName, chainName,
				landline, fax, customerSize, streetAddress,
				customerRecordStatus, customerType, businessUnit, area,
				province, cityTown, isActive, createdTime, modifiedTime,
				created_by);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bCustomerAddCancel:
			getActivity().onBackPressed();
			break;
		case R.id.bCustomerAddCreate:
			new InsertTask().execute();
			break;
		}
	}

	private class InsertTask extends AsyncTask<Void, Void, Boolean> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(getActivity());
			dialog.setMessage("Saving new Customer");
			dialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			boolean flag = false;
			try {
				insert();
				flag = true;
			} catch (Exception e) {
				flag = false;
				Log.e("Jardine", e.toString());
			}

			return flag;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if (result) {
				showMsg("Successfully added Customer");
				getActivity().onBackPressed();
			} else {
				showMsg("Something went wrong, failed to add customer");
			}
		}

	}

	private void showMsg(String txt) {
		Toast.makeText(getActivity(), txt, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onPrepareOptionsMenu(menu);

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.empty_menu, menu);
	}

}
