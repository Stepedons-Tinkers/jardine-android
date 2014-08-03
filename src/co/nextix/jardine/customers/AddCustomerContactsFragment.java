package co.nextix.jardine.customers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.AddActivityFragment;
import co.nextix.jardine.activities.add.fragments.AddActivityGeneralInformationFragment;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.utils.MyDateUtils;

import com.dd.CircularProgressButton;

public class AddCustomerContactsFragment extends Fragment implements
		OnClickListener {

	private View view;
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

	private Bundle bundle;

	private CircularProgressButton saveORdone;
	private boolean flag = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// long id =
		// StoreAccount.restore(JardineApp.context).getLong(Account.ROWID);
		userName = StoreAccount.restore(JardineApp.context).getString(
				Account.USERNAME);
		userId = StoreAccount.restore(getActivity()).getLong(Account.ROWID);
	}

	@SuppressWarnings("unused")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.customer_contact_add_new, container,
				false);
		bundle = getArguments();
		initLayout();

		SharedPreferences pref = getActivity().getApplicationContext()
				.getSharedPreferences("ActivityInfo", 0);
		CustomerContactRecord record = JardineApp.DB.getCustomerContact()
				.getById(pref.getLong("activity_id_customer", 0));
		List<PicklistRecord> posi = JardineApp.DB.getCustomerContactPosition()
				.getAllRecords();

		if (record != null) {

			String crmNo = null;
			String firstName = null;
			String lastName = null;
			long positionInput = 0;
			String mobileNo = null;
			String customerBirthday = null;
			String customerEmail = null;
			String customerCustomer = null;
			String customerCreatedBy = null;

			try {
				crmNo = record.getCrm();
				mobileNo = record.getMobileNo();
				record.getBirthday();
				firstName = record.getFirstName();
				lastName = record.getLastName();
				positionInput = record.getPosition();
				customerEmail = record.getEmailAddress();
				customerCustomer = JardineApp.DB.getCustomer()
						.getById(record.getId()).toString();
				customerCreatedBy = record.toString();

				if (crmNo != null || firstName != null || lastName != null
						|| positionInput != 0 || mobileNo != null
						|| customerBirthday != null || customerEmail != null
						|| customerCustomer != null
						|| customerCreatedBy != null) {

					field1.setText(crmNo);
					field2.setText(firstName);
					field3.setText(lastName);

					field5.setText(mobileNo);
					field6a.setText(customerBirthday);
					field7.setText(customerEmail);
					field8.setText(customerCustomer);
					field9.setText(customerCreatedBy);

					for (int i = 0; i < posi.size(); i++) {
						if (posi.get(i).getId() == record.getPosition()) {
							field4.setSelection(i);
						}
					}
				}

			} catch (Exception e) {

			}
		}

		return view;
	}

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

	private void initLayout() {

		if (bundle != null) {
			customerId = bundle.getLong(CustomerConstants.KEY_CUSTOMER_LONG_ID);
			customerName = bundle
					.getString(CustomerConstants.KEY_CUSTOMER_USERNAME);
		}

		final Calendar c = Calendar.getInstance();
		df = new SimpleDateFormat("MM/dd/yyyy");
		today = new Date();

		day = c.get(Calendar.DAY_OF_MONTH);
		month = c.get(Calendar.MONTH);
		year = c.get(Calendar.YEAR);

		formattedDate = year + "-" + (checkDigit(month + 1)) + "-"
				+ checkDigit(day);

		cancel = (Button) view.findViewById(R.id.bCustomerContactAddCancel);
		save = (Button) view.findViewById(R.id.bCustomerContactAddCreate);

		cancel.setOnClickListener(this);
		save.setOnClickListener(this);

		Log.e("fromOtherMother", "" + AddActivityFragment.fromOther);

		if (AddActivityFragment.fromOther) {
			Log.e("fromOther", "fucker");
			saveORdone = (CircularProgressButton) view
					.findViewById(R.id.btnWithText1);
			saveORdone.setVisibility(View.VISIBLE);
			saveORdone.setOnClickListener(this);

			cancel.setVisibility(View.GONE);
			save.setVisibility(View.GONE);
		}

		cancel.setBackgroundColor(Color.RED);

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
				JardineApp.context, R.layout.customer_spinner_row, posi);

		field4.setAdapter(adapter4);
		field6a.setText(MyDateUtils.convertDate(formattedDate));
		field6b.setOnClickListener(this);

		field8.setText(CustomerConstants.CUSTOMER_NAME);

		UserTable u = DatabaseAdapter.getInstance().getUser();
		if (u != null) {
			UserRecord user = u.getCurrentUser();
			if (user != null) {
				field9.setText(user.getFirstNameName() + user.getLastname());
			}
		}
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
		case R.id.btnWithText1:
			saveData();
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

			formattedDate = year + "-" + (checkDigit(month + 1)) + "-"
					+ checkDigit(day);
			field6a.setText(MyDateUtils.convertDate(formattedDate));

		}

	};

	public String checkDigit(int number) {
		return number <= 9 ? "0" + number : String.valueOf(number);
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
				showMsg("Successfully added Customer Contact");
				CustomerContactList contactList = (CustomerContactList) getTargetFragment();
				contactList.initLayout();
				getActivity().onBackPressed();
			} else {
				showMsg("Something went wrong, failed to add customer contact");
			}
		}

	}

	public void insert() {

		Calendar c = Calendar.getInstance();

		String no = "";
		String crmNo = "";
		String firstName = field2.getText().toString();
		String lastName = field3.getText().toString();
		long position = ((PicklistRecord) field4.getSelectedItem()).getId();
		String mobileNo = field5.getText().toString();
		String birthday = formattedDate;
		String emailAddress = field7.getText().toString();
		long customer = customerId;
		int isActive = 1;

		String createdTime = MyDateUtils.getCurrentTimeStamp();
		String modifiedTime = MyDateUtils.getCurrentTimeStamp();
		long user = userId;

		JardineApp.DB.getCustomerContact().insert(no, crmNo, firstName,
				lastName, position, mobileNo, birthday, emailAddress, customer,
				isActive, createdTime, modifiedTime, user);
	}

	private void showMsg(String txt) {
		Toast.makeText(getActivity(), txt, Toast.LENGTH_LONG).show();
	}

	private void saveData() {
		if (saveORdone.getProgress() == 0) {
			saveORdone.setClickable(false);
			saveORdone.setEnabled(false);

			ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
			widthAnimation.setDuration(1500);
			widthAnimation
					.setInterpolator(new AccelerateDecelerateInterpolator());
			widthAnimation
					.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(ValueAnimator animation) {
							Integer value = (Integer) animation
									.getAnimatedValue();
							saveORdone.setProgress(value);

							if (!flag) {
								saveORdone.setProgress(-1);
							}
						}
					});

			widthAnimation.start();

			String firstName = field2.getText().toString();
			String lastName = field3.getText().toString();
			long position = ((PicklistRecord) field4.getSelectedItem()).getId();
			String mobileNo = field5.getText().toString();
			String birthday = field6a.getText().toString();
			String emailAddress = field7.getText().toString();

			/** Checking of required fields **/
			SharedPreferences pref = getActivity().getApplicationContext()
					.getSharedPreferences("ActivityInfo", 0);

			if (firstName != null && !firstName.isEmpty() && lastName != null
					&& !lastName.isEmpty() && position != 0 && mobileNo != null
					&& !mobileNo.isEmpty() && birthday != null
					&& !birthday.isEmpty() && emailAddress != null
					&& !emailAddress.isEmpty()) {

				flag = true;
				Editor editor = pref.edit();
				editor.putLong("position", position);
				editor.putString("first_name", firstName);
				editor.putString("last_name", lastName);
				editor.putString("mobile_number", mobileNo);
				editor.putString("birthday", birthday);
				editor.putString("email_address", emailAddress);
				editor.putLong("user_id", userId);
				editor.commit(); // commit changes

				saveORdone.setClickable(true);
				saveORdone.setEnabled(true);

			} else {

				flag = false;
				Toast.makeText(getActivity(),
						"Please fill up required (RED COLOR) fields",
						Toast.LENGTH_LONG).show();

				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						saveORdone.setProgress(0);
						saveORdone.setClickable(true);
						saveORdone.setEnabled(true);
					}
				}, 1500);
			}

		} else {
			saveORdone.setProgress(0);
			saveORdone.setClickable(true);
			saveORdone.setEnabled(true);

			if (AddActivityGeneralInformationFragment.ActivityType == 4) { // retails
				DashBoardActivity.tabIndex.add(3, 6);
				AddActivityFragment.pager.setCurrentItem(6);
			} else if (AddActivityGeneralInformationFragment.ActivityType == 9) { // ki
																					// visits
				DashBoardActivity.tabIndex.add(3, 10);
				AddActivityFragment.pager.setCurrentItem(10);
			} else if (AddActivityGeneralInformationFragment.ActivityType == 101) { // major
																					// training
				DashBoardActivity.tabIndex.add(3, 13);
				AddActivityFragment.pager.setCurrentItem(13);
			} else if (AddActivityGeneralInformationFragment.ActivityType == 102) { // end
																					// user
				DashBoardActivity.tabIndex.add(3, 14);
				AddActivityFragment.pager.setCurrentItem(14);

			} else if (AddActivityGeneralInformationFragment.ActivityType == 41) { // full
																					// brand
				DashBoardActivity.tabIndex.add(3, 15);
				AddActivityFragment.pager.setCurrentItem(15);

			} else if (AddActivityGeneralInformationFragment.ActivityType == 100) { // others
				DashBoardActivity.tabIndex.add(3, 16);
				AddActivityFragment.pager.setCurrentItem(16);
			}
		}
	}
}
