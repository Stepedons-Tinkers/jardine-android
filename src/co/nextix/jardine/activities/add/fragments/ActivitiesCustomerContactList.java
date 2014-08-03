package co.nextix.jardine.activities.add.fragments;

import java.util.ArrayList;
import java.util.List;

import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.customers.AdapterCustomerContacts;
import co.nextix.jardine.customers.AddCustomerContactsFragment;
import co.nextix.jardine.customers.CustomerConstants;
import co.nextix.jardine.customers.CustomerContactList;
import co.nextix.jardine.customers.CustomerContactPersonGeneralInformation;
import co.nextix.jardine.database.records.CustomerContactRecord;
import co.nextix.jardine.view.group.utils.ListViewUtility;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ActivitiesCustomerContactList extends Fragment implements
		View.OnClickListener {

	private View view, header;
	private TextView col1, col2, col3, col4, col5;
	private TableRow trow;
	private ListView list;
	private int rowSize = 6;
	private int totalPage = 0;
	private int currentPage = 0;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;

	private Button btnAdd, btnNext;

	private List<CustomerContactRecord> tempRecord;
	private List<CustomerContactRecord> realRecord;
	private AdapterCustomerContactListWithCheck finalAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(
				R.layout.activity_add_customer_contact_list_step1, container,
				false);
		header = inflater.inflate(R.layout.customer_contact_row, null, false);

		initLayout();
		return view;
	}

	private void initLayout() {
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

		col1.setText(getResources().getString(R.string.crm_no_contact_person));
		col2.setText(getResources()
				.getString(R.string.firstname_contact_person));
		col3.setText(getResources().getString(R.string.lastname_contact_person));
		col4.setText(getResources().getString(R.string.position_contact_person));
		col5.setText(getResources().getString(R.string.select));

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
		btnAdd = (Button) view.findViewById(R.id.bActivityAddCustomerContact);
		btnNext = (Button) view.findViewById(R.id.bActivityContactNext);
		btnNext.setOnClickListener(this);

		realRecord = new ArrayList<CustomerContactRecord>();
		tempRecord = new ArrayList<CustomerContactRecord>();

		finalAdapter = new AdapterCustomerContactListWithCheck(getActivity(),
				R.layout.customer_contact_row, realRecord);

		list = (ListView) view.findViewById(R.id.lvActiviyCustomerContactList);

		txtPage = (TextView) view.findViewById(R.id.tvActivityCustomerPage);

		arrowLeft = (ImageButton) view
				.findViewById(R.id.ibActivityCustomerLeft);
		arrowRight = (ImageButton) view
				.findViewById(R.id.ibActivityCustomerRight);

		arrowLeft.setOnClickListener(this);
		arrowRight.setOnClickListener(this);

		list.addHeaderView(header);
		list.setAdapter(finalAdapter);

		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});

	}

	public void populate() {

		List<CustomerContactRecord> tempLOL = null;
		try {
			tempLOL = JardineApp.DB
					.getCustomerContact()
					.getAllRecordsByCustomerId(
							ActivitiesConstant.ACTIVITY_CUSTOMER_RECORD.getId());
		} catch (Exception e) {

		}

		if (tempLOL != null) {
			realRecord.addAll(tempLOL);
			if (realRecord.size() > 0) {

				for (int i = 0; i < realRecord.size(); i++) {
					realRecord.get(i).setSelected(true);
				}

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
				AdapterCustomerContactListWithCheck adapter = new AdapterCustomerContactListWithCheck(
						getActivity(), R.layout.customer_contact_row,
						realRecord);
				for (int i = 0; i < rowSize; i++) {
					CustomerContactRecord rec = new CustomerContactRecord();
					realRecord.add(rec);
				}

				adapter.notifyDataSetChanged();
				list.setAdapter(adapter);
				ListViewUtility.setListViewHeightBasedOnChildren(list);

			}
		}

	}

	public void populate2() {
		List<CustomerContactRecord> tempLOL = null;
		try {
			tempLOL = JardineApp.DB
					.getCustomerContact()
					.getAllRecordsByCustomerId(
							ActivitiesConstant.ACTIVITY_CUSTOMER_RECORD.getId());
		} catch (Exception e) {

		} finally {
			if (tempLOL != null) {
				realRecord.addAll(tempLOL);
				finalAdapter.notifyDataSetChanged();
				ListViewUtility.setListViewHeightBasedOnChildren(list);
			}

		}

	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void setMenuVisibility(boolean menuVisible) {
		super.setMenuVisibility(menuVisible);
		if (menuVisible) {
			populate2();
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

		final AdapterCustomerContactListWithCheck adapter = new AdapterCustomerContactListWithCheck(
				getActivity(), R.layout.customer_contact_row, tempRecord);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				CustomerContactRecord epr = (CustomerContactRecord) parent
						.getAdapter().getItem(position);

				if (epr.isSelected()) {
					epr.setSelected(false);
				} else {
					epr.setSelected(true);
				}

				adapter.notifyDataSetChanged();

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

		case R.id.bActivityContactNext:
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
			break;
		}

	}

}
