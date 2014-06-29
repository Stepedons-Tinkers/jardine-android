package co.nextix.jardine.customers.fragments;

import java.util.ArrayList;
import java.util.List;

import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.AddActivityFragment;
import co.nextix.jardine.collaterals.CollateralsDetails;
import co.nextix.jardine.database.records.ActivityRecord;
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
import android.widget.AdapterView.OnItemClickListener;

public class CustomerContactList extends Fragment implements OnClickListener {

	private View view, header;
	private ListView list;
	private int rowSize = 6;
	private int totalPage = 0;
	private int currentPage = 0;

	private List<ActivityRecord> realRecord;
	private List<ActivityRecord> tempRecord;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;
	private TextView col1, col2, col3, col4, col5;
	private TableRow trow;
	private EditText search;
	private Button bntAdd;

	public static CustomerContactList newInstance(long custd) {
		CustomerContactList fragment = new CustomerContactList();
		Bundle bundle = new Bundle();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.workplan_activities, container, false);
		initLayout();
		return view;
	}

	private void initLayout() {

		// Header Data

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

		realRecord = new ArrayList<ActivityRecord>();
		tempRecord = new ArrayList<ActivityRecord>();

		ActivityTable table = JardineApp.DB.getActivity();
		realRecord.addAll(table
				.getAllRecordsByWorkEntry(WorkPlanConstants.WORKPLAN_ID));

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					ActivityRecord rec = new ActivityRecord();
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

		// AdapterCollateralsEventProtocols adapter = new
		// AdapterCollateralsEventProtocols(getActivity(),
		// R.layout.collaterals_event_protocol_row, tempRecord);
		// list.setAdapter(adapter);

		AdapterWorkplanActivity adapter = new AdapterWorkplanActivity(
				getActivity(), R.layout.collaterals_event_protocol_row,
				tempRecord);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				EventProtocolRecord epr = (EventProtocolRecord) parent
						.getAdapter().getItem(position);

				if (epr.getNo() != null) {

					DashBoardActivity act = (DashBoardActivity) getActivity();
					act.getSupportFragmentManager()
							.beginTransaction()
							.add(R.id.frame_container,
									new CollateralsDetails(), JardineApp.TAG)
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
