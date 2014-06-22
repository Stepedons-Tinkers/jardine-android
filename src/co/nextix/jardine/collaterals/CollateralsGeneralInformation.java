package co.nextix.jardine.collaterals;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.EventProtocolRecord;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CollateralsGeneralInformation extends Fragment {
	private View view;
	private TextView crmNo, desc, lastUpdate, tags, eventType, isActive,
			createdTime, modifiedTime, assigned;
	

	public static CollateralsGeneralInformation newInstance(long id) {
		CollateralsGeneralInformation fragment = new CollateralsGeneralInformation();
		Bundle b = new Bundle();
		b.putLong(CollateralsConstants.KEY_ROW_ID, id);
		fragment.setArguments(b);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.collaterals_event_protocols_details,
				null);
		initLayout();
		return view;
	}

	private void initLayout() {
		crmNo = (TextView) view.findViewById(R.id.tvWorkPlanInfoCrmNo);
		desc = (TextView) view.findViewById(R.id.tvCollateralsGIDesc);
		lastUpdate = (TextView) view.findViewById(R.id.tvWorkPlanInfoDate);
		tags = (TextView) view.findViewById(R.id.tvCollateralsGITags);
		eventType = (TextView) view.findViewById(R.id.tvWorkPlanInfoArea);
		isActive = (TextView) view.findViewById(R.id.tvCollateralsGIIsActive);
		createdTime = (TextView) view.findViewById(R.id.tvWorkPlanInfoActCity);
		modifiedTime = (TextView) view
				.findViewById(R.id.tvCollateralsGIModifiedTime);
		assigned = (TextView) view.findViewById(R.id.tvCollateralsGIAssigned);

//		JardineApp.DB.getEventProtocol().getById(CollateralsConstants.ROW_ID);
		
		EventProtocolRecord record = new EventProtocolRecord();
		record.setNo("EVP0001");
		record.setDescription("Hello");
		record.setLastUpdate("2014");
		record.setTags("Tugs");
		record.setEventType(10000);
		record.setIsActive(1);
		record.setCreatedTime("2014");
		record.setModifiedTime("2014");
		record.setUser(10000);

		crmNo.setText(record.getNo());
		desc.setText(record.getDescription());
		lastUpdate.setText(record.getLastUpdate());
		tags.setText(record.getTags());
		eventType.setText(record.getEventType() + "");
		isActive.setText(record.getIsActive() > 0 ? "Yes" : "No");
		createdTime.setText(record.getCreatedTime());
		modifiedTime.setText(record.getModifiedTime());
		assigned.setText(record.getUser() + "");
	}
}
