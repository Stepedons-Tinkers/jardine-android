package co.nextix.jardine.workplan;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.AddActivityFragment;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.records.WorkplanEntryRecord;
import co.nextix.jardine.database.records.WorkplanRecord;
import co.nextix.jardine.utils.MyDateUtils;

public class WorkPlanFragmentGeneralInfo extends Fragment {
	private View view;
	private TextView crmNo, date, area, city, actType, createdTime, assignedTo,
			customer, status, province, otherRemarks, workplan, modifiedTime;

	private Button btnActivity;
	private AddActivityFragment fragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.workplan_details, null);
		initLayout();
		return view;
	}

	private void initLayout() {

		btnActivity = (Button) view.findViewById(R.id.bWorkPlanActAddActivity);

		crmNo = (TextView) view.findViewById(R.id.tvWorkPlanInfoCrmNo);
		date = (TextView) view.findViewById(R.id.tvCustomerInfoAddress);
		area = (TextView) view.findViewById(R.id.tvCustomerInfoLandline);
		city = (TextView) view.findViewById(R.id.tvWorkPlanInfoCity);
		actType = (TextView) view.findViewById(R.id.tvWorkPlanInfoActType);
		createdTime = (TextView) view
				.findViewById(R.id.tvWorkPlanInfoCreatedTime);
		assignedTo = (TextView) view
				.findViewById(R.id.tvWorkPlanInfoAssignedTo);
		customer = (TextView) view.findViewById(R.id.tvWorkPlanInfoCustomer);
		status = (TextView) view.findViewById(R.id.tvWorkPlanInfoStatus);
		province = (TextView) view.findViewById(R.id.tvWorkPlanInfoProvince);
		otherRemarks = (TextView) view
				.findViewById(R.id.tvWorkPlanInfoOtherRemarks);
		workplan = (TextView) view.findViewById(R.id.tvWorkPlanInfoWorkPlan);
		modifiedTime = (TextView) view
				.findViewById(R.id.tvWorkPlanInfoModifiedTime);

		final WorkplanEntryRecord record = JardineApp.DB.getWorkplanEntry()
				.getById(WorkPlanConstants.WORKPLAN_ID);
		record.setNo("0001");

		int i = 0;
		crmNo.setText(record.getNo());
		date.setText(MyDateUtils.convertDate(record.getDate()));

		String strArea = JardineApp.DB.getArea().getNameById(record.getArea());
		area.setText(strArea);

		String strCity = JardineApp.DB.getCityTown().getNameById(
				record.getCity());
		city.setText(strCity);

		ActivityTypeRecord strAct = JardineApp.DB.getActivityType().getById(
				record.getActivityType());
		actType.setText(strAct.getName());

		createdTime
				.setText(MyDateUtils.convertDateTime(record.getCreatedTime()));

		UserRecord userRecord = JardineApp.DB.getUser().getById(
				record.getCreatedBy());
		assignedTo.setText(userRecord.getFirstNameName() + " "
				+ userRecord.getLastname());

		// String sCustomer = JardineApp.DB.getCustomer().getNoById(
		// record.getCustomer());
		// customer.setText(sCustomer);

		PicklistRecord pic = JardineApp.DB.getWorkplanEntryStatus().getById(
				record.getStatus());
		status.setText(pic.getName());

		String sProvince = JardineApp.DB.getProvince().getNameById(
				record.getProvince());
		province.setText(sProvince);

		otherRemarks.setText(record.getRemarks());

		WorkplanRecord sWorkplan = JardineApp.DB.getWorkplan().getById(
				record.getWorkplan());
		workplan.setText(sWorkplan.getCrm());
		modifiedTime.setText(MyDateUtils.convertDateTime(record
				.getModifiedTime()));

		Bundle b = new Bundle();
		b.putLong(WorkPlanConstants.WORKPLAN_ENTRY_ROW_ID, record.getId());
		b.putLong(WorkPlanConstants.WORKPLAN_ACTIVITY_TYPE_ID,
				record.getActivityType());

		fragment = new AddActivityFragment();
		fragment.setArguments(b);

		btnActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity()
						.getSupportFragmentManager()
						.beginTransaction()
						.setTransition(
								FragmentTransaction.TRANSIT_FRAGMENT_FADE)
						.add(R.id.frame_container, fragment, JardineApp.TAG)
						.addToBackStack(JardineApp.TAG).commit();

			}
		});
	}
}
