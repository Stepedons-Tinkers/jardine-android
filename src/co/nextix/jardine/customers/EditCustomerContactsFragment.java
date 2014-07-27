package co.nextix.jardine.customers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.utils.MyDateUtils;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditCustomerContactsFragment extends Fragment implements
		OnClickListener {

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
	private TextView field8, field9;

	private Button save, cancel;
	private int day, month, year;
	public static Date today = null;
	public static SimpleDateFormat df = null;
	public static String formattedDate = null;

	private View view;

	private CustomerContactRecord record;

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

		view = inflater.inflate(R.layout.customer_contact_add_new, container,
				false);

		initLayout();
		return view;
	}

	private void initLayout() {

		record = CustomerConstants.CUSTOMER_CONTACT_RECORD;
		customerId = getArguments().getLong(
				CustomerConstants.KEY_CUSTOMER_LONG_ID);
		final Calendar c = Calendar.getInstance();
		df = new SimpleDateFormat("MM/dd/yyyy");
		today = new Date();

		day = c.get(Calendar.DAY_OF_MONTH);
		month = c.get(Calendar.MONTH);
		year = c.get(Calendar.YEAR);

		formattedDate = year + "-" + (month + 1) + "-" + day;

		cancel = (Button) view.findViewById(R.id.bCustomerContactAddCancel);
		save = (Button) view.findViewById(R.id.bCustomerContactAddCreate);

		cancel.setOnClickListener(this);
		save.setOnClickListener(this);

		save.setText("Update");

		field1 = (TextView) view.findViewById(R.id.tvCustomerContactAddField1);

		field2 = (EditText) view.findViewById(R.id.etCustomerContactAddField2);
		field3 = (EditText) view.findViewById(R.id.etCustomerContactAddField3);

		field4 = (Spinner) view.findViewById(R.id.spiCustomerContactAddField4);

		field5 = (EditText) view.findViewById(R.id.etCustomerContactAddField5);

		field6a = (TextView) view.findViewById(R.id.tvCustomerContactAddField6);
		field6b = (ImageButton) view
				.findViewById(R.id.ibCustomerContactAddField6);

		field7 = (EditText) view.findViewById(R.id.etCustomerContactAddField7);

		field8 = (TextView) view.findViewById(R.id.tvCustomerContactAddField8);

		field9 = (TextView) view.findViewById(R.id.tvCustomerContactAddField9);
		// List to be populated in spinner adapter
		List<PicklistRecord> posi = JardineApp.DB.getCustomerContactPosition()
				.getAllRecords();
		ArrayAdapter<PicklistRecord> adapter4 = new ArrayAdapter<PicklistRecord>(
				JardineApp.context, R.layout.workplan_spinner_row, posi);

		field4.setAdapter(adapter4);
		field6b.setOnClickListener(this);
		field8.setText(CustomerConstants.CUSTOMER_NAME);

		UserTable u = DatabaseAdapter.getInstance().getUser();
		if (u != null) {
			UserRecord user = u.getCurrentUser();
			if (user != null) {
				field9.setText(user.getFirstNameName() + " "
						+ user.getLastname());
			}
		}

		field2.setText(record.getFirstName());
		field3.setText(record.getLastName());
		field4.setSelection((int) record.getPosition() - 1);
		field5.setText(record.getMobileNo());
		field6a.setText(MyDateUtils.convertDate(record.getBirthday()));
		field7.setText(record.getEmailAddress());

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bCustomerContactAddCancel:
			getActivity().onBackPressed();
			break;
		case R.id.bCustomerContactAddCreate:
			if (checker()) {
				new InsertTask().execute();
			} else {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						getActivity());
				dialog.setTitle("Warning");
				dialog.setMessage("Fields that are allowed to be empty are birthday and email address only.");
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
		case R.id.ibCustomerContactAddField6:
			DatePickerDialog pickDialog = new DatePickerDialog(getActivity(),
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

	private boolean checker() {
		boolean flag = false;

		if (!field2.getText().toString().contentEquals("")
				&& !field3.getText().toString().contentEquals("")
				&& field4.getSelectedItemPosition() != 0
				&& !field5.getText().toString().contentEquals("")) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;
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
				showMsg("Successfully updated Customer Contact");
				CustomerContactPersonGeneralInformation genInfo = (CustomerContactPersonGeneralInformation) getTargetFragment();
				genInfo.initLayout();
				getActivity().onBackPressed();
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
				field5.getText().toString(), formattedDate,
				field7.getText().toString(), customerId, 1,
				record.getCreatedTime(), c.getTime().toString(), userId);

	}

	private void showMsg(String txt) {
		Toast.makeText(getActivity(), txt, Toast.LENGTH_LONG).show();
	}
}
