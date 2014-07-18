package co.nextix.jardine.activities.update.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.ActivitiesConstant;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.BusinessUnitRecord;
import co.nextix.jardine.database.records.CityTownRecord;
import co.nextix.jardine.database.records.CustomerRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProvinceRecord;
import co.nextix.jardine.database.records.WorkplanEntryRecord;
import co.nextix.jardine.database.tables.picklists.PCityTownTable;
import co.nextix.jardine.database.tables.picklists.PProvinceTable;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;

import com.dd.CircularProgressButton;

public class SaveActivityInfoFragment extends Fragment {
	private ActivityRecord activityRecord = null;
	private View rootView = null;
	private ArrayAdapter<String> workplanAdapter = null;
	private ArrayAdapter<PicklistRecord> areaAdapter = null;
	private ArrayAdapter<CustomerRecord> customerAdapter = null;
	private ArrayAdapter<ProvinceRecord> provinceAdapter = null;
	private ArrayAdapter<CityTownRecord> cityTownAdapter = null;
	private ArrayAdapter<PicklistRecord> sourceAdapter = null;
	private ArrayAdapter<ActivityTypeRecord> activityTypeAdapter = null;
	private ArrayAdapter<WorkplanEntryRecord> workplanEntryRecordAdapter = null;
	private CircularProgressButton saveBtn = null;
	private String userName = null;
	private long userId = 0;

	public SaveActivityInfoFragment() {
		String id = StoreAccount.restore(JardineApp.context).getString(Account.ROWID);
		this.userName = StoreAccount.restore(JardineApp.context).getString(Account.USERNAME);
		this.userId = Long.parseLong(id);
		this.activityRecord = ActivitiesConstant.ACTIVITY_RECORD;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_activity_edit, container, false);

		// List to be populated in spinner adapter
		List<PicklistRecord> areaList = JardineApp.DB.getArea().getAllRecords();
		List<PicklistRecord> sourceList = JardineApp.DB.getArea().getAllRecords();
		List<String> workplanList = JardineApp.DB.getWorkplan().getAllWorkplan(JardineApp.DB.getUser().getCurrentUser().getId());

		// Karon clear na
		List<ActivityTypeRecord> activityTypeList = JardineApp.DB.getActivityType().getAllRecords();
		List<WorkplanEntryRecord> workplanEntryList = JardineApp.DB.getWorkplanEntry().getAllRecords();
		List<CustomerRecord> customerList = JardineApp.DB.getCustomer().getAllRecords();

		// Auto populate fields
		BusinessUnitRecord activity = JardineApp.DB.getBusinessUnit().getById(JardineApp.DB.getUser().getCurrentUser().getId());
		String assignedToFname = JardineApp.DB.getUser().getCurrentUser().getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getCurrentUser().getLastname();

		// ArrayAdapter for spinners
		this.areaAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context, R.layout.add_activity_textview, areaList);
		this.sourceAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context, R.layout.add_activity_textview, sourceList);
		this.workplanAdapter = new ArrayAdapter<String>(JardineApp.context, R.layout.add_activity_textview, workplanList);
		this.activityTypeAdapter = new ArrayAdapter<ActivityTypeRecord>(JardineApp.context, R.layout.add_activity_textview,
				activityTypeList);
		this.workplanEntryRecordAdapter = new ArrayAdapter<WorkplanEntryRecord>(JardineApp.context, R.layout.add_activity_textview,
				workplanEntryList);
		this.customerAdapter = new ArrayAdapter<CustomerRecord>(JardineApp.context, R.layout.add_activity_textview, customerList);

		// Setting to text field auto populate data
		((EditText) this.rootView.findViewById(R.id.business_unit)).setText(activity != null ? activity.toString() : "");
		((EditText) this.rootView.findViewById(R.id.assigned_to)).setText(assignedToLname + "," + assignedToFname);

		((Spinner) this.rootView.findViewById(R.id.area)).setAdapter(this.areaAdapter);
		((Spinner) this.rootView.findViewById(R.id.source)).setAdapter(this.sourceAdapter);
		((Spinner) this.rootView.findViewById(R.id.workplan)).setAdapter(workplanAdapter);
		((Spinner) this.rootView.findViewById(R.id.activity_type)).setAdapter(activityTypeAdapter);
		((Spinner) this.rootView.findViewById(R.id.workplan_entry)).setAdapter(workplanEntryRecordAdapter);
		((Spinner) this.rootView.findViewById(R.id.customer)).setAdapter(customerAdapter);

		((Spinner) this.rootView.findViewById(R.id.area)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				PProvinceTable provinceTable = JardineApp.DB.getProvince();
				provinceAdapter = new ArrayAdapter<ProvinceRecord>(JardineApp.context, R.layout.add_activity_textview, provinceTable
						.getRecordsByAreaId(id + 1));
				((Spinner) SaveActivityInfoFragment.this.rootView.findViewById(R.id.province)).setAdapter(provinceAdapter);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getActivity().getApplicationContext(), "Must be filled!", Toast.LENGTH_SHORT).show();
			}
		});

		((Spinner) this.rootView.findViewById(R.id.province)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				PCityTownTable cityTable = JardineApp.DB.getCityTown();
				cityTownAdapter = new ArrayAdapter<CityTownRecord>(JardineApp.context, R.layout.add_activity_textview, cityTable
						.getRecordsByProvinceId(id + 1));
				((Spinner) SaveActivityInfoFragment.this.rootView.findViewById(R.id.city_town)).setAdapter(cityTownAdapter);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getActivity().getApplicationContext(), "Must be filled!", Toast.LENGTH_SHORT).show();
			}
		});

		((Spinner) this.rootView.findViewById(R.id.area)).setSelection((int) this.activityRecord.getArea());
		((Spinner) this.rootView.findViewById(R.id.source)).setSelection((int) this.activityRecord.getSource() - 1);
		((Spinner) this.rootView.findViewById(R.id.workplan)).setSelection((int) this.activityRecord.getWorkplan() - 1);
		((Spinner) this.rootView.findViewById(R.id.activity_type)).setSelection((int) this.activityRecord.getActivityType() - 1);
		((Spinner) this.rootView.findViewById(R.id.workplan_entry)).setSelection((int) this.activityRecord.getWorkplanEntry());
		((Spinner) this.rootView.findViewById(R.id.customer)).setSelection((int) this.activityRecord.getCustomer());
		((Spinner) this.rootView.findViewById(R.id.province)).setSelection((int) this.activityRecord.getProvince());

		((TextView) this.rootView.findViewById(R.id.crm_no)).setText(this.activityRecord.getCrm());
		((TextView) this.rootView.findViewById(R.id.workplan)).setText(String.valueOf(this.activityRecord.getWorkplan()));
		((TextView) this.rootView.findViewById(R.id.start_time)).setText(this.activityRecord.getStartTime());
		((TextView) this.rootView.findViewById(R.id.end_time)).setText(this.activityRecord.getEndTime());
		((TextView) this.rootView.findViewById(R.id.latitude)).setText(String.valueOf(this.activityRecord.getLatitude()));
		((TextView) this.rootView.findViewById(R.id.longitude)).setText(String.valueOf(this.activityRecord.getLongitude()));
		((EditText) this.rootView.findViewById(R.id.objective)).setText(this.activityRecord.getObjectives());
		((EditText) this.rootView.findViewById(R.id.notes)).setText(this.activityRecord.getNotes());
		((EditText) this.rootView.findViewById(R.id.competitor_activities)).setText("getCompetitorActivities()");
		((EditText) this.rootView.findViewById(R.id.highlights)).setText(this.activityRecord.getHighlights());
		((EditText) this.rootView.findViewById(R.id.nextSteps)).setText(this.activityRecord.getNextSteps());
		((TextView) this.rootView.findViewById(R.id.follow_up_commitment_date)).setText(this.activityRecord.getFollowUpCommitmentDate());
		((TextView) this.rootView.findViewById(R.id.activity_type)).setText(String.valueOf(this.activityRecord.getActivityType()));
		((EditText) this.rootView.findViewById(R.id.others)).setText("getOthers()");
		((TextView) this.rootView.findViewById(R.id.workplan_entry)).setText(String.valueOf(this.activityRecord.getWorkplanEntry()));
		((TextView) this.rootView.findViewById(R.id.customer)).setText(String.valueOf(this.activityRecord.getCustomer()));
		((TextView) this.rootView.findViewById(R.id.area)).setText(String.valueOf(this.activityRecord.getArea()));
		((TextView) this.rootView.findViewById(R.id.province)).setText(String.valueOf(this.activityRecord.getProvince()));
		((TextView) this.rootView.findViewById(R.id.city_town)).setText(String.valueOf(this.activityRecord.getCityTown()));
		((CheckBox) this.rootView.findViewById(R.id.first_time_visit)).setChecked(this.activityRecord.getFirstTimeVisit() == 1 ? true
				: false);
		((CheckBox) this.rootView.findViewById(R.id.planned_visit)).setChecked(this.activityRecord.getPlannedVisit() == 1 ? true : false);
		((EditText) this.rootView.findViewById(R.id.reason_remarks)).setText("getRemarks()");
		((EditText) this.rootView.findViewById(R.id.details_admin_works)).setText("getDetailedWorks()");
		((TextView) this.rootView.findViewById(R.id.source)).setText(String.valueOf(this.activityRecord.getSource()));
		((TextView) this.rootView.findViewById(R.id.created_time)).setText(this.activityRecord.getCreatedTime());

		return rootView;
	}
}
