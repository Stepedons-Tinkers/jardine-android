package co.nextix.jardine.workplan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.WorkplanRecord;

public class WorkPlanFragmentGeneralInfo extends Fragment {
	private View view;
	private TextView crmNo, date, area, city, actType, createdTime, assignedTo,
			customer, status, province, otherRemarks, workplan, modifiedTime;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.workplan_details,
				null);
		initLayout();
		return view;
	}

	private void initLayout() {
		crmNo = (TextView) view.findViewById(R.id.tvWorkPlanInfoCrmNo);
		date = (TextView) view.findViewById(R.id.tvWorkPlanInfoDate);
		area = (TextView) view.findViewById(R.id.tvWorkPlanInfoArea);
		city = (TextView) view.findViewById(R.id.tvWorkPlanInfoCity);
		actType = (TextView) view.findViewById(R.id.tvWorkPlanInfoActType);
		createdTime = (TextView) view.findViewById(R.id.tvWorkPlanInfoCreatedTime);
		assignedTo = (TextView) view.findViewById(R.id.tvWorkPlanInfoAssignedTo);
		customer = (TextView) view.findViewById(R.id.tvWorkPlanInfoCustomer);
		status = (TextView) view.findViewById(R.id.tvWorkPlanInfoStatus);
		province = (TextView) view.findViewById(R.id.tvWorkPlanInfoProvince);
		otherRemarks = (TextView) view.findViewById(R.id.tvWorkPlanInfoOtherRemarks);
		workplan = (TextView) view.findViewById(R.id.tvWorkPlanInfoWorkPlan);
		modifiedTime = (TextView) view.findViewById(R.id.tvWorkPlanInfoModifiedTime);

		WorkplanRecord record = new WorkplanRecord();
		record.setNo("0001");
		
		int i = 0;
		crmNo.setText(record.getNo());
		date.setText(i++ +"");
		area.setText(i++ +"");
		city.setText(i++ +"");
		actType.setText(i++ +"");
		createdTime.setText(i++ +"");
		assignedTo.setText(i++ +"");
		customer.setText(i++ +"");
		status.setText(i++ +"");
		province.setText(i++ +"");
		otherRemarks.setText(i++ +"");
		workplan.setText(i++ +"");
		modifiedTime.setText(i++ +"");
	}
}
