package co.nextix.jardine.customers.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.PicklistRecord;

public class CustomerContactPersonFragment extends Fragment {
	private View view;
	private TextView crmNo, firstName, lastName, mobileNo, email, position,
			birthday, isActive;
	private long customerId = 0;

	public static CustomerContactPersonFragment newInstance(long custId) {
		CustomerContactPersonFragment fragment = new CustomerContactPersonFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(CustomerConstants.KEY_CUSTOMER_LONG_ID, custId);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		customerId = getArguments().getLong(
				CustomerConstants.KEY_CUSTOMER_LONG_ID);
		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		view = inflater.inflate(R.layout.contact_person_details, container,
				false);
		initLayout();
		return view;
	}

	private void initLayout() {
		crmNo = (TextView) view.findViewById(R.id.tvContactPersonCrmNoInfo);
		firstName = (TextView) view
				.findViewById(R.id.tvContactPersonFirstNameInfo);
		lastName = (TextView) view
				.findViewById(R.id.tvContactPersonLastNameInfo);
		mobileNo = (TextView) view
				.findViewById(R.id.tvContactPersonMobileNoInfo);
		email = (TextView) view.findViewById(R.id.tvContactPersonEmailInfo);
		position = (TextView) view
				.findViewById(R.id.tvContactPersonPositionInfo);
		birthday = (TextView) view
				.findViewById(R.id.tvContactPersonBirthdayInfo);
		isActive = (TextView) view
				.findViewById(R.id.tvContactPersonIsActiveInfo);

		CustomerContactRecord record = JardineApp.DB.getCustomerContact()
				.getById(customerId);

		crmNo.setText(record.getNo());
		firstName.setText(record.getFirstName());
		lastName.setText(record.getLastName());
		mobileNo.setText(record.getMobileNo());
		email.setText(record.getEmailAddress());

		PicklistRecord cPos = JardineApp.DB.getCustomerContactPosition()
				.getById(record.getPosition());
		position.setText(cPos.getName());

		birthday.setText(record.getBirthday());

		if (record.getIsActive() == 1) {
			isActive.setText("Active");
		} else {
			isActive.setText("Inactive");
		}

	}
}
