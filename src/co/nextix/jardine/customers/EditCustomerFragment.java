package co.nextix.jardine.customers;

import java.util.Calendar;
import java.util.List;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.utils.MyDateUtils;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditCustomerFragment extends Fragment implements OnClickListener {

	private View view;
	private Button save, cancel;

	private TextView field1;
	private EditText field2, field3, field4, field5;
	private Spinner field6, field7;
	private Spinner field8, field9, field10, field11;
	private EditText field12;
	private TextView field13;
	private Spinner field14;

	private long userId;
	private String userName;

	private CustomerRecord record;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String id = StoreAccount.restore(JardineApp.context).getString(
				Account.ROWID);
		userName = StoreAccount.restore(JardineApp.context).getString(
				Account.USERNAME);
		userId = Long.parseLong(id);
		record = CustomerConstants.CUSTOMER_RECORD;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.customer_add_new, container, false);
		initLayout();
		return view;
	}

	private void initLayout() {

		record = CustomerConstants.CUSTOMER_RECORD;

		cancel = (Button) view.findViewById(R.id.bCustomerAddCancel);
		save = (Button) view.findViewById(R.id.bCustomerAddCreate);

		cancel.setOnClickListener(this);
		save.setOnClickListener(this);

		cancel.setBackgroundColor(Color.RED);

		save.setText("Update");

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

		List<BusinessUnitRecord> bRecord = JardineApp.DB.getBusinessUnit()
				.getAllRecords();

		List<PicklistRecord> customerRecordStatus = JardineApp.DB
				.getCustomerRecordStatus().getAllRecords();

		field9.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				PicklistRecord pArea = (PicklistRecord) parent.getAdapter()
						.getItem(position);

				List<ProvinceRecord> province = JardineApp.DB.getProvince()
						.getRecordsByAreaId(pArea.getId());
				ArrayAdapter<ProvinceRecord> adapter10 = new ArrayAdapter<ProvinceRecord>(
						JardineApp.context, R.layout.customer_spinner_row,
						province);
				field10.setAdapter(adapter10);
				for (int i = 0; i < province.size(); i++) {

					if (province.get(i).getId() == record.getProvince()) {
						field10.setSelection(i);

						break;
					}
				}

				//
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});
		field10.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ProvinceRecord pProvince = (ProvinceRecord) parent.getAdapter()
						.getItem(position);

				List<CityTownRecord> pCityTown = JardineApp.DB.getCityTown()
						.getRecordsByProvinceId(pProvince.getId());
				ArrayAdapter<CityTownRecord> adapter11 = new ArrayAdapter<CityTownRecord>(
						JardineApp.context, R.layout.customer_spinner_row,
						pCityTown);
				field11.setAdapter(adapter11);

				for (int i = 0; i < pCityTown.size(); i++) {

					if (pCityTown.get(i).getId() == record.getCityTown()) {
						field11.setSelection(i);

						break;
					}
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

		// ArrayAdapter for spinners
		ArrayAdapter<PicklistRecord> adapter6 = new ArrayAdapter<PicklistRecord>(
				JardineApp.context, R.layout.customer_spinner_row, customerSize);

		ArrayAdapter<PicklistRecord> adapter7 = new ArrayAdapter<PicklistRecord>(
				JardineApp.context, R.layout.customer_spinner_row, customerType);

		ArrayAdapter<PicklistRecord> adapter9 = new ArrayAdapter<PicklistRecord>(
				JardineApp.context, R.layout.customer_spinner_row, area);

		ArrayAdapter<BusinessUnitRecord> adapter8 = new ArrayAdapter<BusinessUnitRecord>(
				JardineApp.context, R.layout.customer_spinner_row, bRecord);

		ArrayAdapter<PicklistRecord> adapter14 = new ArrayAdapter<PicklistRecord>(
				JardineApp.context, R.layout.customer_spinner_row,
				customerRecordStatus);

		field6.setAdapter(adapter6);
		field7.setAdapter(adapter7);
		field9.setAdapter(adapter9);
		field8.setAdapter(adapter8);
		field14.setAdapter(adapter14);

		field13.setText(userName);

		field2.setText(record.getCustomerName());
		field3.setText(record.getStreetAddress());
		field4.setText(record.getChainName());
		field5.setText(record.getLandline());

		field6.setSelection((int) record.getCustomerSize() - 1);
		field7.setSelection((int) record.getCustomerType() - 1);
		field14.setSelection((int) record.getCustomerRecordStatus() - 1);

		BusinessUnitRecord business = JardineApp.DB.getBusinessUnit().getById(
				record.getBusinessUnit());
		int bPosition;
		if (business == null) {
			bPosition = 0;
		} else {
			bPosition = adapter8.getPosition(business);
		}

		field8.setSelection(bPosition);
		field9.setSelection((int) record.getArea() - 1);
		field12.setText(record.getFax());

		UserTable u = DatabaseAdapter.getInstance().getUser();
		if (u != null) {
			UserRecord user = u.getCurrentUser();
			if (user != null) {
				field13.setText(user.getFirstNameName() + " " + " "
						+ user.getLastname());
			}
		}

	}

	public boolean checker() {
		boolean flag = false;

		if (!field2.getText().toString().contentEquals("")
				&& !field3.getText().toString().contentEquals("")
				&& !field4.getText().toString().contentEquals("")
				&& !field5.getText().toString().contentEquals("")
				&& field6.getSelectedItemPosition() != 0
				&& field7.getSelectedItemPosition() != 0
				&& field9.getSelectedItemPosition() != 0) {

			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}

	public void insert() {

		// JardineApp.DB.getCustomer().update(record.getId(), record.getNo(),
		// record.getCrm(), field2.getText().toString(),
		// field4.getText().toString(), field5.getText().toString(),
		// field12.getText().toString(),
		// ((PicklistRecord) field6.getSelectedItem()).getId(),
		// field3.getText().toString(),
		// ((PicklistRecord) field7.getSelectedItem()).getId(),
		// ((BusinessUnitRecord) field8.getSelectedItem()).getId(),
		// ((PicklistRecord) field9.getSelectedItem()).getId(),
		// ((ProvinceRecord) field10.getSelectedItem()).getId(),
		// ((CityTownRecord) field11.getSelectedItem()).getId(), 1,
		// record.getCreatedTime(), c.getTime().toString(), userId);

		long id = record.getId();
		String no = record.getNo();
		String crmNo = record.getCrm();
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
		long created_by = record.getCreatedBy();
		long customerRecordStatus = ((PicklistRecord) field14.getSelectedItem())
				.getId();
		int isActive = 1;

		String daysUnchanged = 0 + "";

		String createdTime = record.getCreatedTime();
		String modifiedTime = MyDateUtils.getCurrentTimeStamp();

		JardineApp.DB.getCustomer().update(id, no, crmNo, customerName,
				chainName, landline, fax, customerSize, streetAddress,
				customerRecordStatus, customerType, businessUnit, area,
				province, cityTown, isActive, daysUnchanged, createdTime,
				modifiedTime, created_by);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bCustomerAddCancel:
			getActivity().onBackPressed();
			break;
		case R.id.bCustomerAddCreate:
			if (checker()) {
				new InsertTask().execute();
			} else {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						getActivity());
				dialog.setTitle("Warning");
				dialog.setMessage("Fields that are allowed to be empty are fax and street address only.");
				dialog.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();

							}
						});
				dialog.show();
			}

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
				showMsg("Successfully Updated Customer");
				getActivity().onBackPressed();
			} else {
				showMsg("Something went wrong, failed to update customer");
			}
		}

	}

	private void showMsg(String txt) {
		Toast.makeText(getActivity(), txt, Toast.LENGTH_LONG).show();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		CustomerGeneralInformation fragment = (CustomerGeneralInformation) getTargetFragment();
		fragment.initLayout();
	}

	@Override
	public void onResume() {
		super.onResume();
	}
}
