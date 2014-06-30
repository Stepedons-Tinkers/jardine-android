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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CustomerContactList extends Fragment implements OnClickListener {

	private View view, header;
	private ListView list;
	private int rowSize = 6;
	private int totalPage = 0;
	private int currentPage = 0;

	private List<CustomerContactRecord> realRecord;
	private List<CustomerContactRecord> tempRecord;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;
	private TextView col1, col2, col3, col4, col5;
	private TableRow trow;
	private EditText search;
	private Button bntAdd;
	private long customerId = 0;

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
		view = inflater.inflate(R.layout.workplan_activities, container, false);
		header = inflater.inflate(R.layout.customer_contact_row, null, false);
		initLayout();
		return view;
	}

	private void initLayout() {

		// Header Data

		trow = (TableRow) header.findViewById(R.id.trCustomerContactRow);
		trow.setBackgroundResource(R.color.tab_pressed);
		header.setClickable(false);
		header.setFocusable(false);
		header.setFocusableInTouchMode(false);
		header.setOnClickListener(null);
		//

		list = (ListView) view.findViewById(R.id.lvWorkPlanActList);

		list.addHeaderView(header);

		bntAdd = (Button) view.findViewById(R.id.bWorkPlanActAddActivity);
		bntAdd.setText(CustomerConstants.ADD_CUSTOMER_CONTACT);

		txtPage = (TextView) view.findViewById(R.id.tvWorkPlanActPage);

		arrowLeft = (ImageButton) view.findViewById(R.id.ibWorkPlanActLeft);
		arrowRight = (ImageButton) view.findViewById(R.id.ibWorkPlanActRight);

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

		}

		bntAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DashBoardActivity act = (DashBoardActivity) getActivity();
				act.getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.frame_container, new AddActivityFragment(),
								JardineApp.TAG).addToBackStack(JardineApp.TAG)
						.commit();

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

					DashBoardActivity act = (DashBoardActivity) getActivity();
					act.getSupportFragmentManager()
							.beginTransaction()
							.add(R.id.frame_container,
									CustomerContactPersonFragment.newInstance(customerId), JardineApp.TAG)
							.addToBackStack(JardineApp.TAG).commit();
				}

			}
		});
		ListViewUtility.setListViewHeightBasedOnChildren(list);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ibWorkPlanActLeft:
			if (currentPage > 0) {
				currentPage--;
				addItem(currentPage);
			}
			break;
		case R.id.ibWorkPlanActRight:
			if (currentPage < totalPage - 1) {
				currentPage++;
				addItem(currentPage);
			}
			break;
		}

	}
}
