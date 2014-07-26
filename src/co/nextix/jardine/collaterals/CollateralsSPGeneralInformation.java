package co.nextix.jardine.collaterals;

import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.MarketingMaterialsRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.SalesProtocolRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.utils.MyDateUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CollateralsSPGeneralInformation extends Fragment {
	private View view;
	private TextView crmNo, desc, lastUpdate, tags, eventType, isActive,
			createdTime, modifiedTime, assigned;

	private TextView vEventType, txtIsActive;

	public static CollateralsSPGeneralInformation newInstance(long id) {
		CollateralsSPGeneralInformation fragment = new CollateralsSPGeneralInformation();
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
		txtIsActive = (TextView) view.findViewById(R.id.tvCollateralsIsActive);

		vEventType.setText(getResources().getString(
				R.string.collaterals_sp_protocol_type));

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

		SalesProtocolRecord record = JardineApp.DB.getSalesProtocol().getById(
				CollateralsConstants.ROW_ID);
		UserTable user = JardineApp.DB.getUser();
		UserRecord userRecord = user.getById(record.getCreatedBy());

		crmNo.setText(record.getCrm());
		desc.setText(record.getDescription());
		lastUpdate.setText(MyDateUtils.convertDate(record.getLastUpdate()));
		tags.setText(record.getTags());

		PicklistRecord rec = JardineApp.DB.getSalesProtocolType().getById(
				record.getProtocolType());

		if (rec != null)
			eventType.setText(rec.getName());

		isActive.setText(record.getIsActive() > 0 ? "Yes" : "No");
		createdTime
				.setText(MyDateUtils.convertDateTime(record.getCreatedTime()));
		modifiedTime.setText(MyDateUtils.convertDateTime(record
				.getModifiedTime()));
		assigned.setText(userRecord.getFirstNameName() + " "
				+ userRecord.getLastname());
	}
}
