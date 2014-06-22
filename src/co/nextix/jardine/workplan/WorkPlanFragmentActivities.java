package co.nextix.jardine.workplan;

import java.util.ArrayList;
import java.util.List;

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
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.collaterals.AdapterCollateralsEventProtocols;
import co.nextix.jardine.collaterals.CollateralsDetails;
import co.nextix.jardine.database.records.EventProtocolRecord;
import co.nextix.jardine.view.group.utils.ListViewUtility;

public class WorkPlanFragmentActivities extends Fragment implements
		OnClickListener {

	private View view;
	private ListView list;
	private int rowSize = 6;
	private int totalPage = 0;
	private int currentPage = 0;

	private List<EventProtocolRecord> realRecord;
	private List<EventProtocolRecord> tempRecord;

	private ImageButton arrowLeft, arrowRight;
	private TextView txtPage;
	private View header;
	private TextView txtCrm, txtDesc, txtEvent, txtIsActive;
	private TableRow trow;
	private EditText search;
	private Button bntAddActity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.workplan_activities, null);
		header = inflater
				.inflate(R.layout.collaterals_event_protocol_row, null);
		initLayout();
		return view;
	}

	private void initLayout() {

		// Header Data
		trow = (TableRow) header
				.findViewById(R.id.trCollateralsEventerProtocolRow);
		txtCrm = (TextView) header
				.findViewById(R.id.tvCollateralsEventerProtocolCrmNo);
		txtDesc = (TextView) header
				.findViewById(R.id.tvCollateralsEventerProtocolDescription);
		txtEvent = (TextView) header
				.findViewById(R.id.tvCollateralsEventerProtocolEventType);
		txtIsActive = (TextView) header
				.findViewById(R.id.tvCollateralsEventerProtocolIsActive);

		txtCrm.setText(getResources().getString(R.string.collaterals_ep_crm_no));
		txtDesc.setText(getResources().getString(R.string.workplan_start_time));
		txtEvent.setText(getResources().getString(R.string.workplan_end_time));
		txtIsActive.setText(getResources().getString(
				R.string.workplan_info_customer));
		trow.setBackgroundResource(R.color.tab_pressed);
		header.setClickable(false);
		header.setFocusable(false);
		header.setFocusableInTouchMode(false);
		header.setOnClickListener(null);
		//

		list = (ListView) view.findViewById(R.id.lvWorkPlanActList);

		list.addHeaderView(header);

		bntAddActity = (Button) view.findViewById(R.id.bWorkPlanActAddActivity);
		txtPage = (TextView) view.findViewById(R.id.tvWorkPlanActPage);

		arrowLeft = (ImageButton) view.findViewById(R.id.ibWorkPlanActLeft);
		arrowRight = (ImageButton) view.findViewById(R.id.ibWorkPlanActRight);

		arrowLeft.setOnClickListener(this);
		arrowRight.setOnClickListener(this);

		realRecord = new ArrayList<EventProtocolRecord>();
		tempRecord = new ArrayList<EventProtocolRecord>();

		for (int i = 1; i <= 37; i++) {
			EventProtocolRecord rec = new EventProtocolRecord();
			rec.setNo("EVP00" + i);
			rec.setDescription("Description " + i);
			rec.setEventType(i);
			if (i % 2 == 0) {
				rec.setIsActive(1);
			} else {
				rec.setIsActive(0);
			}

			realRecord.add(rec);
		}

		if (realRecord.size() > 0) {
			int remainder = realRecord.size() % rowSize;
			if (remainder > 0) {
				for (int i = 0; i < rowSize - remainder; i++) {
					EventProtocolRecord rec = new EventProtocolRecord();
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

		AdapterCollateralsEventProtocols adapter = new AdapterCollateralsEventProtocols(
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
