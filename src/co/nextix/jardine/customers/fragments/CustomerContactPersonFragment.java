package co.nextix.jardine.customers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CustomerContactRecord;

public class CustomerContactPersonFragment extends Fragment {
	private View view;
	private TextView crmNo, firstName, lastName, mobileNo, 
	email, position, birthday, isActive;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(
				R.layout.contact_person_details, null);
		initLayout();
		return view;
	}

	private void initLayout() {
		crmNo = (TextView) view.findViewById(R.id.tvContactPersonCrmNoInfo);
		firstName = (TextView) view.findViewById(R.id.tvContactPersonFirstNameInfo);
		lastName = (TextView) view.findViewById(R.id.tvContactPersonLastNameInfo);
		mobileNo = (TextView) view.findViewById(R.id.tvContactPersonMobileNoInfo);
		email = (TextView) view.findViewById(R.id.tvContactPersonEmailInfo);
		position = (TextView) view.findViewById(R.id.tvContactPersonPositionInfo);
		birthday = (TextView) view.findViewById(R.id.tvContactPersonBirthdayInfo);
		isActive = (TextView) view.findViewById(R.id.tvContactPersonIsActiveInfo);

		CustomerContactRecord record = new CustomerContactRecord();
		record.setNo("EVP0001");
		record.setFirstName("John");
		record.setLastName("Doe");
		record.setMobileNo("0922-000-0000");
		record.setEmailAddress("johndoe@gmail.com");
		record.setPosition(001);
		record.setBirthday("12-06-1898");
		record.setIsActive(1);
		record.setCreatedTime("2014");
		record.setModifiedTime("2014");
		record.setUser(10000);

		crmNo.setText(record.getNo());
		firstName.setText(record.getFirstName());
		lastName.setText(record.getLastName());
		mobileNo.setText(record.getMobileNo());
		email.setText(record.getEmailAddress());
		position.setText(String.valueOf(record.getPosition()));
		birthday.setText(record.getBirthday());
		isActive.setText(String.valueOf(record.getIsActive()));

	}
}
