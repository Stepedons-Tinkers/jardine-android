package co.nextix.jardine.contactperson.fragments;

import java.util.ArrayList;
import java.util.List;

import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.customers.fragments.AdapterCustomers;
import co.nextix.jardine.customers.fragments.CustomerDetailsFragment;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ViewAllContactPersonFragment extends Fragment implements
OnClickListener {
	private View view;
	private ListView list;
	private int rowSize = 6;
	private int totalPage = 0;
	private int currentPage = 0;

	private List<CustomerContactRecord> realRecord;
	private List<CustomerContactRecord> tempRecord;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;
	private View header;
	private TextView txtCrm, txtFirstName, txtLastName, txtPosition, 
	txtMobileNo;
	private TableRow tablerow;
	private EditText search;
	private Button bntAddContact;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_customer_contact_person_view_all, null);
		header = inflater
				.inflate(R.layout.table_row_customers, null);
		initLayout();
		return view;
	}

	private void initLayout() {

		// Header Data
		tablerow = (TableRow) header.findViewById(R.id.trContactPersonRow);
		txtCrm = (TextView) header.findViewById(R.id.tvContactPersonCRMNo);
		txtFirstName = (TextView) header.findViewById(R.id.tvContactPersonFirstName);
		txtLastName = (TextView) header.findViewById(R.id.tvContactPersonLastName);
		txtPosition = (TextView) header.findViewById(R.id.tvContactPersonPosition); 
		txtMobileNo = (TextView) header.findViewById(R.id.tvContactPersonMobileNo);

		txtCrm.setText(getResources().getString(R.string.customer_crm_no));
		tablerow.setBackgroundResource(R.color.tab_pressed);
		header.setClickable(false);
		header.setFocusable(false);
		header.setFocusableInTouchMode(false);
		header.setOnClickListener(null);

		list = (ListView) view
				.findViewById(R.id.lvCustomers);

		list.addHeaderView(header);

		bntAddContact = (Button) view.findViewById(R.id.btnAddContactPerson);
		txtPage = (TextView) view
				.findViewById(R.id.ibCustomersPage);

		arrowLeft = (ImageButton) view
				.findViewById(R.id.ibWorkPlanActLeft);
		arrowRight = (ImageButton) view
				.findViewById(R.id.ibWorkPlanActRight);

		arrowLeft.setOnClickListener(this);
		arrowRight.setOnClickListener(this);

		realRecord = new ArrayList<CustomerContactRecord>();
		tempRecord = new ArrayList<CustomerContactRecord>();

		for (int i = 1; i <= 37; i++) {
			CustomerContactRecord rec = new CustomerContactRecord();
			rec.setNo("CUST000" + i);
			rec.setFirstName("John");
			rec.setLastName("Doe");
			rec.setPosition(001);
			rec.setMobileNo("0922-000-0000");

			realRecord.add(rec);
		}

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					CustomerContactRecord rec = new CustomerContactRecord();
					realRecord.add(rec);
				}
			}
			totalPage = realRecord.size() / rowSize;
			addItem(currentPage);

		}
	}

	private void addItem(int count) {
		tempRecord.clear();
		count = count * rowSize;
		int temp = currentPage + 1;
		txtPage.setText(temp + " of " + totalPage);

		for (int j = 0; j < rowSize; j++) {
			tempRecord.add(j, realRecord.get(count));
			count = count + 1;
		}

		setView();
	}

	private void setView() {

		AdapterContactPerson adapter = new AdapterContactPerson(
				getActivity(), R.layout.table_row_contact_person,
				tempRecord);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				CustomerContactRecord cr = (CustomerContactRecord) parent
						.getAdapter().getItem(position);

				if (cr.getNo() != null) {

					DashBoardActivity act = (DashBoardActivity) getActivity();
					act.getSupportFragmentManager()
							.beginTransaction()
							.add(R.id.frame_container,
									new ContactPersonDetailsFragment(), JardineApp.TAG)
							.addToBackStack(JardineApp.TAG).commit();
				}

			}
		});
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ibCustomersLeft:
			if (currentPage > 0) {
				currentPage--;
				addItem(currentPage);
			}
			break;
		case R.id.ibCustomersRight:
			if (currentPage < totalPage - 1) {
				currentPage++;
				addItem(currentPage);
			}
			break;
		case R.id.btnAddContactPerson:
			// add customer contact here
			break;
		}

	}
}
