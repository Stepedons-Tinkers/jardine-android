package co.nextix.jardine.customers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

public class EditCustomer extends Activity implements OnClickListener {

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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_add_new);
		String id = StoreAccount.restore(JardineApp.context).getString(
				Account.ROWID);
		userName = StoreAccount.restore(JardineApp.context).getString(
				Account.USERNAME);
		userId = Long.parseLong(id);
		initLayout();
	}

	private void initLayout() {

		record = CustomerConstants.CUSTOMER_RECORD;

		cancel = (Button) findViewById(R.id.bCustomerAddCancel);
		save = (Button) findViewById(R.id.bCustomerAddCreate);

		cancel.setOnClickListener(this);
		save.setOnClickListener(this);

		save.setText("Update");

		field1 = (TextView) findViewById(R.id.tvCustomerAddField1);

		field2 = (EditText) findViewById(R.id.etCustomerAddField2);
		field3 = (EditText) findViewById(R.id.etCustomerAddField3);
		field4 = (EditText) findViewById(R.id.etCustomerAddField4);
		field5 = (EditText) findViewById(R.id.etCustomerAddField5);

		field6 = (Spinner) findViewById(R.id.spiCustomerAddField6);
		field7 = (Spinner) findViewById(R.id.spiCustomerAddField7);

		field8 = (Spinner) findViewById(R.id.spiCustomerAddField8);

		field9 = (Spinner) findViewById(R.id.spiCustomerAddField9);
		field10 = (Spinner) findViewById(R.id.spiCustomerAddField10);
		field11 = (Spinner) findViewById(R.id.spiCustomerAddField11);

		field12 = (EditText) findViewById(R.id.etCustomerAddField12);

		field13 = (TextView) findViewById(R.id.tvCustomerAddField13);

		field14 = (Spinner) findViewById(R.id.spiCustomerAddField14);

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

		field6.setAdapter(adapter6);
		field7.setAdapter(adapter7);
		field9.setAdapter(adapter9);
		field10.setAdapter(adapter10);
		field11.setAdapter(adapter11);
		field8.setAdapter(adapter8);

		field13.setText(userName);

		field2.setText(record.getCustomerName());
		field3.setText(record.getStreetAddress());
		field4.setText(record.getChainName());
		field5.setText(record.getLandline());

		field6.setSelection((int) record.getCustomerSize() - 1);
		field7.setSelection((int) record.getCustomerType() - 1);

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
		field10.setSelection((int) record.getProvince() - 1);
		field11.setSelection((int) record.getCityTown() - 1);
		field12.setText(record.getFax());
		field13.setText(userName);

	}

	public void insert() {

		Calendar c = Calendar.getInstance();

		// JardineApp.DB.getCustomer().insert("", "",
		// field2.getText().toString(),
		// field4.getText().toString(), field5.getText().toString(),
		// field12.getText().toString(),
		// ((PicklistRecord) field6.getSelectedItem()).getId(),
		// field3.getText().toString(),
		// ((PicklistRecord) field7.getSelectedItem()).getId(),
		// ((BusinessUnitRecord) field8.getSelectedItem()).getId(),
		// ((PicklistRecord) field9.getSelectedItem()).getId(),
		// ((ProvinceRecord) field10.getSelectedItem()).getId(),
		// ((CityTownRecord) field11.getSelectedItem()).getId(), 1,
		// c.getTime().toString(), c.getTime().toString(), userId);

		JardineApp.DB.getCustomer().update(record.getId(), record.getNo(),
				record.getCrm(), field2.getText().toString(),
				field4.getText().toString(), field5.getText().toString(),
				field12.getText().toString(),
				((PicklistRecord) field6.getSelectedItem()).getId(),
				field3.getText().toString(),
				((PicklistRecord) field7.getSelectedItem()).getId(),
				((BusinessUnitRecord) field8.getSelectedItem()).getId(),
				((PicklistRecord) field9.getSelectedItem()).getId(),
				((ProvinceRecord) field10.getSelectedItem()).getId(),
				((CityTownRecord) field11.getSelectedItem()).getId(), 1,
				c.getTime().toString(), c.getTime().toString(), userId);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bCustomerAddCancel:
			finish();
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
			dialog = new ProgressDialog(EditCustomer.this);
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
				finish();
			} else {
				showMsg("Something went wrong, failed to add customer");
			}
		}

	}

	private void showMsg(String txt) {
		Toast.makeText(this, txt, Toast.LENGTH_LONG).show();

	}

}
