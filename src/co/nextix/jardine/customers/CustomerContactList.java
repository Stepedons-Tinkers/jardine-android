package co.nextix.jardine.customers;

import java.util.ArrayList;
import java.util.List;

import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.AddActivityFragment;
import co.nextix.jardine.collaterals.CollateralsDetails;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.database.records.EventProtocolRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.view.group.utils.ListViewUtility;
import co.nextix.jardine.workplan.AdapterWorkplanActivity;
import co.nextix.jardine.workplan.WorkPlanConstants;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CustomerContactList extends Fragment implements OnClickListener {

	private View view, header;
	private ListView list;
	private int rowSize = 8;
	private int totalPage = 0;
	private int currentPage = 0;

	private List<CustomerContactRecord> realRecord;
	private List<CustomerContactRecord> tempRecord;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;
	private TextView col1, col2, col3, col4, col5;
	private TableRow trow;
	private Button bntAdd;
	private long customerId = 0;
	private String customerName;

	public static CustomerContactList newInstance(long custd) {
		CustomerContactList fragment = new CustomerContactList();
		Bundle bundle = new Bundle();
		bundle.putLong(CustomerConstants.KEY_CUSTOMER_LONG_ID, custd);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		customerId = getArguments().getLong(
				CustomerConstants.KEY_CUSTOMER_LONG_ID);

		customerName = getArguments().getString(
				CustomerConstants.KEY_CUSTOMER_LONG_ID);
		view = inflater.inflate(R.layout.customer_view_all, container, false);
		header = inflater.inflate(R.layout.customer_contact_row, null, false);
		initLayout();
		return view;
	}

	public void initLayout() {

		// Header Data

		trow = (TableRow) header.findViewById(R.id.trCustomerContactRow);
		trow.setBackgroundResource(R.color.tab_pressed);

		col1 = (TextView) header.findViewById(R.id.tvCustomerContactCol1);
		col2 = (TextView) header.findViewById(R.id.tvCustomerContactCol2);
		col3 = (TextView) header.findViewById(R.id.tvCustomerContactCol3);
		col4 = (TextView) header.findViewById(R.id.tvCustomerContactCol4);
		col5 = (TextView) header.findViewById(R.id.tvCustomerContactCol5);

		trow.setGravity(Gravity.CENTER);
		col1.setGravity(Gravity.CENTER);
		col2.setGravity(Gravity.CENTER);
		col3.setGravity(Gravity.CENTER);
		col4.setGravity(Gravity.CENTER);
		col5.setGravity(Gravity.CENTER);

		col1.setTypeface(null, Typeface.BOLD);
		col2.setTypeface(null, Typeface.BOLD);
		col3.setTypeface(null, Typeface.BOLD);
		col4.setTypeface(null, Typeface.BOLD);
		col5.setTypeface(null, Typeface.BOLD);

		header.setClickable(false);
		header.setFocusable(false);
		header.setFocusableInTouchMode(false);
		header.setOnClickListener(null);
		//

		list = (ListView) view.findViewById(R.id.lvCustomers);

		list.addHeaderView(header);

		bntAdd = (Button) view.findViewById(R.id.btnAddCustomer);
		bntAdd.setText(CustomerConstants.ADD_CUSTOMER_CONTACT);

		txtPage = (TextView) view.findViewById(R.id.tvCustomersPage);

		arrowLeft = (ImageButton) view.findViewById(R.id.ibCustomersLeft);
		arrowRight = (ImageButton) view.findViewById(R.id.ibCustomersRight);

		arrowLeft.setOnClickListener(this);
		arrowRight.setOnClickListener(this);

		realRecord = new ArrayList<CustomerContactRecord>();
		tempRecord = new ArrayList<CustomerContactRecord>();

		realRecord.addAll(JardineApp.DB.getCustomerContact()
				.getAllRecordsByCustomerId(customerId));

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

		} else {
			AdapterCustomerContacts adapter = new AdapterCustomerContacts(
					getActivity(), R.layout.customer_contact_row, realRecord);
			for (int i = 0; i < rowSize; i++) {
				CustomerContactRecord rec = new CustomerContactRecord();
				realRecord.add(rec);
			}

			adapter.notifyDataSetChanged();

			list.setAdapter(adapter);
		}

		bntAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent(JardineApp.context,
				// AddCustomerContacts.class);
				Bundle bundle = new Bundle();
				bundle.putLong(CustomerConstants.KEY_CUSTOMER_LONG_ID,
						customerId);
				bundle.putString(CustomerConstants.KEY_CUSTOMER_USERNAME,
						customerName);
				// intent.putExtras(bundle);
				// startActivity(intent);

				AddCustomerContactsFragment addFragment = new AddCustomerContactsFragment();
				addFragment.setArguments(bundle);
				addFragment.setTargetFragment(CustomerContactList.this, 15);
				
				addFragment.setTargetFragment(CustomerContactList.this, 15);

				getActivity()
						.getSupportFragmentManager()
						.beginTransaction()
						.setTransition(
								FragmentTransaction.TRANSIT_FRAGMENT_FADE)
						.add(R.id.frame_container, addFragment)
						.addToBackStack(null).commit();
			}
		});
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

		AdapterCustomerContacts adapter = new AdapterCustomerContacts(
				getActivity(), R.layout.customer_contact_row, tempRecord);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				CustomerContactRecord epr = (CustomerContactRecord) parent
						.getAdapter().getItem(position);

				if (epr.getNo() != null) {

					CustomerContactPersonGeneralInformation fragment = CustomerContactPersonGeneralInformation
							.newInstance(customerId);
					fragment.setTargetFragment(CustomerContactList.this, 15);

					DashBoardActivity act = (DashBoardActivity) getActivity();
					act.getSupportFragmentManager()
							.beginTransaction()
							.setTransition(
									FragmentTransaction.TRANSIT_FRAGMENT_FADE)
							.add(R.id.frame_container, fragment, JardineApp.TAG)
							.addToBackStack(JardineApp.TAG).commit();
				}

			}
		});
		ListViewUtility.setListViewHeightBasedOnChildren(list);
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
		}

	}
}
