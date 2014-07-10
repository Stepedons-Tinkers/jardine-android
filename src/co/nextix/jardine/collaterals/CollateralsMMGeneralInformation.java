package co.nextix.jardine.collaterals;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.EventProtocolRecord;
import co.nextix.jardine.database.records.MarketingMaterialsRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.UserTable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CollateralsMMGeneralInformation extends Fragment {
	private View view;
	private TextView crmNo, desc, lastUpdate, tags, eventType, isActive,
			createdTime, modifiedTime, assigned;

	private TextView vEventType;

	public static CollateralsMMGeneralInformation newInstance(long id) {
		CollateralsMMGeneralInformation fragment = new CollateralsMMGeneralInformation();
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

		vEventType = (TextView) view
				.findViewById(R.id.tvCollateralsEventTypeView);

		vEventType.setVisibility(View.INVISIBLE);

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

		MarketingMaterialsRecord record = JardineApp.DB.getMarketingMaterials()
				.getById(CollateralsConstants.ROW_ID);
		UserTable user = JardineApp.DB.getUser();
		UserRecord userRecord = user.getById(record.getUser());

		crmNo.setText(record.getCrm());
		desc.setText(record.getDescription());
		lastUpdate.setText(record.getLastUpdate());
		tags.setText(record.getTags());
		// isActive.setText(record.getIsActive() > 0 ? "Yes" : "No");
		createdTime.setText(record.getCreatedTime());
		modifiedTime.setText(record.getModifiedTime());
		assigned.setText(userRecord.getFirstNameName() + " "
				+ userRecord.getLastname());
	}
}
