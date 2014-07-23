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
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

public class EditCustomerContacts extends Activity implements OnClickListener {

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

	private CustomerContactRecord record;

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

		record = CustomerConstants.CUSTOMER_CONTACT_RECORD;
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

		formattedDate = year + "-" + (month + 1) + "-" + day;

		cancel = (Button) findViewById(R.id.bCustomerContactAddCancel);
		save = (Button) findViewById(R.id.bCustomerContactAddCreate);

		cancel.setOnClickListener(this);
		save.setOnClickListener(this);

		save.setText("Update");

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
		field6b.setOnClickListener(this);
		field8.setText(CustomerConstants.CUSTOMER_NAME);
		
		UserTable u = DatabaseAdapter.getInstance().getUser();
		if (u != null) {
			UserRecord user = u.getCurrentUser();
			if (user != null) {
				field10.setText(user.getFirstNameName() + " " + user.getLastname());
			}
		}

		field2.setText(record.getFirstName());
		field3.setText(record.getLastName());
		field4.setSelection((int) record.getPosition() - 1);
		field5.setText(record.getMobileNo());
		field6a.setText(record.getBirthday());
		field7.setText(record.getEmailAddress());

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
			formattedDate = year + "-" + (month + 1) + "-" + day;
			field6a.setText(formattedDate);

		}

	};

	private class InsertTask extends AsyncTask<Void, Void, Boolean> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(EditCustomerContacts.this);
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
				showMsg("Successfully updated Customer Contact");
				finish();
			} else {
				showMsg("Something went wrong, failed to update customer contact");
			}
		}

	}

	public void insert() {

		Calendar c = Calendar.getInstance();

		// JardineApp.DB.getCustomerContact().insert("", "",
		// field2.getText().toString(), field3.getText().toString(),
		// ((PicklistRecord) field4.getSelectedItem()).getId(),
		// field5.getText().toString(), field6a.getText().toString(),
		// field7.getText().toString(), customerId, 1,
		// c.getTime().toString(), c.getTime().toString(), userId);

		JardineApp.DB.getCustomerContact().update(record.getId(),
				record.getNo(), record.getCrm(), field2.getText().toString(),
				field3.getText().toString(),
				((PicklistRecord) field4.getSelectedItem()).getId(),
				field5.getText().toString(), field6a.getText().toString(),
				field7.getText().toString(), customerId, 1,
				record.getCreatedTime(), c.getTime().toString(), userId);

	}

	private void showMsg(String txt) {
		Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
	}
}
