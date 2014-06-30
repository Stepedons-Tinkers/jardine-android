package co.nextix.jardine.customers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
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

public class AddCustomerContacts extends Activity implements OnClickListener {

	private long customerId;
	private String customerName;
	private String userName;
	private long userId;
	private TextView field1;
	private EditText field2, field3;
	private Spinner field4;
	private EditText field5;
	private TextView field6a;
	private ImageButton field6b;
	private EditText field7;
	private TextView field8, field9, field10;

	private Button save, cancel;
	private int day, month, year;
	public static Date today = null;
	public static SimpleDateFormat df = null;
	public static String formattedDate = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_contact_add_new);
		String id = StoreAccount.restore(JardineApp.context).getString(
				Account.ROWID);
		userName = StoreAccount.restore(JardineApp.context).getString(
				Account.USERNAME);
		userId = Long.parseLong(id);
		initLayout();
	}

	private void initLayout() {
		customerId = this.getIntent().getExtras()
				.getLong(CustomerConstants.KEY_CUSTOMER_LONG_ID);
		customerName = this.getIntent().getExtras()
				.getString(CustomerConstants.KEY_CUSTOMER_USERNAME);
		final Calendar c = Calendar.getInstance();
		df = new SimpleDateFormat("MM/dd/yyyy");
		today = new Date();

		day = c.get(Calendar.DAY_OF_MONTH);
		month = c.get(Calendar.MONTH);
		year = c.get(Calendar.YEAR);

		formattedDate = (month + 1) + "/" + day + "/" + year;

		cancel = (Button) findViewById(R.id.bCustomerContactAddCancel);
		save = (Button) findViewById(R.id.bCustomerContactAddCreate);

		cancel.setOnClickListener(this);
		save.setOnClickListener(this);

		field1 = (TextView) findViewById(R.id.tvCustomerContactAddField1);

		field2 = (EditText) findViewById(R.id.etCustomerContactAddField2);
		field3 = (EditText) findViewById(R.id.etCustomerContactAddField3);

		field4 = (Spinner) findViewById(R.id.spiCustomerContactAddField4);

		field5 = (EditText) findViewById(R.id.etCustomerContactAddField5);

		field6a = (TextView) findViewById(R.id.tvCustomerContactAddField6);
		field6b = (ImageButton) findViewById(R.id.ibCustomerContactAddField6);

		field7 = (EditText) findViewById(R.id.etCustomerContactAddField7);

		field8 = (TextView) findViewById(R.id.tvCustomerContactAddField8);

		field9 = (TextView) findViewById(R.id.tvCustomerContactAddField9);
		field10 = (TextView) findViewById(R.id.tvCustomerContactAddField10);
		// List to be populated in spinner adapter
		List<PicklistRecord> posi = JardineApp.DB.getCustomerContactPosition()
				.getAllRecords();
		ArrayAdapter<PicklistRecord> adapter4 = new ArrayAdapter<PicklistRecord>(
				this, R.layout.workplan_spinner_row, posi);

		field4.setAdapter(adapter4);
		field6a.setText(formattedDate);
		field6b.setOnClickListener(this);
		field8.setText(CustomerConstants.CUSTOMER_NAME);
		field10.setText(userName);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bCustomerContactAddCancel:
			finish();
			break;
		case R.id.bCustomerContactAddCreate:
			new InsertTask().execute();
			break;
		case R.id.ibCustomerContactAddField6:
			DatePickerDialog pickDialog = new DatePickerDialog(this,
					android.R.style.Theme_Holo_Panel, datePickerListener, year,
					month, day);
			pickDialog.show();
			break;
		}
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			// txtActualDate.setText(new StringBuilder().append(month + 1)
			// .append("/").append(day).append("/").append(year)
			// .append(" "));
			formattedDate = (month + 1) + "/" + day + "/" + year;
			field6a.setText(formattedDate);

		}

	};

	private class InsertTask extends AsyncTask<Void, Void, Boolean> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(AddCustomerContacts.this);
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
				showMsg("Successfully added Customer Contact");
			} else {
				showMsg("Something went wrong, failed to add customer contact");
			}
		}

	}

	public void insert() {

		Calendar c = Calendar.getInstance();

		JardineApp.DB.getCustomerContact().insert("", "",
				field2.getText().toString(), field3.getText().toString(),
				((PicklistRecord) field4.getSelectedItem()).getId(),
				field5.getText().toString(), field6a.getText().toString(),
				field7.getText().toString(), customerId, 1,
				c.getTime().toString(), c.getTime().toString(), userId);

	}

	private void showMsg(String txt) {
		Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
	}
}
