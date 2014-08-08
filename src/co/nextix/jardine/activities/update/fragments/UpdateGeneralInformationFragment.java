package co.nextix.jardine.activities.update.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.ActivityTypeRecord;
import co.nextix.jardine.database.records.BusinessUnitRecord;

import com.dd.CircularProgressButton;

public class UpdateGeneralInformationFragment extends Fragment {

	private View view;

	private ArrayAdapter<ActivityTypeRecord> activityTypeAdapter = null;
	private ArrayAdapter<BusinessUnitRecord> activityBusinessAdapter = null;

	private CircularProgressButton saveBtn = null;

	private Calendar calendar = null;
	private SimpleDateFormat df = null;

	private String formattedDate = null;

	private int day = 0;
	private int month = 0;
	private int year = 0;

	private EditText eCreatedBy;

	private TextView tCheckIn;
	private TextView tCheckOut;
	private TextView tCrmNo;

	private Spinner sBusinessUnit;
	private Spinner sActivityType;

	List<ActivityTypeRecord> activityTypeList;
	List<BusinessUnitRecord> businessUnitList;

	private boolean trapping = false;

	public UpdateGeneralInformationFragment() {
		this.calendar = Calendar.getInstance();
		this.df = new SimpleDateFormat("HH:mm:ss");
		this.day = this.calendar.get(Calendar.DAY_OF_MONTH);
		this.month = this.calendar.get(Calendar.MONTH);
		this.year = this.calendar.get(Calendar.YEAR);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.update_general_info, container, false);

		eCreatedBy = (EditText) view.findViewById(R.id.created_by);
		tCheckIn = (TextView) view.findViewById(R.id.check_in);
		tCheckOut = (TextView) view.findViewById(R.id.check_out);
		tCrmNo = (TextView) view.findViewById(R.id.crm_no);

		sBusinessUnit = (Spinner) view.findViewById(R.id.business_unit);
		sActivityType = (Spinner) view.findViewById(R.id.activity_type);

		activityTypeList = JardineApp.DB.getActivityType().getAllRecords();
		businessUnitList = JardineApp.DB.getBusinessUnit().getAllRecords();

		activityTypeAdapter = new ArrayAdapter<ActivityTypeRecord>(
				JardineApp.context, R.layout.add_activity_textview,
				activityTypeList);

		activityBusinessAdapter = new ArrayAdapter<BusinessUnitRecord>(
				JardineApp.context, R.layout.add_activity_textview,
				businessUnitList);

		if (UpdateConstants.RECORD != null) {
			tCheckIn.setText(displayCheckIn());
			sBusinessUnit.setAdapter(activityBusinessAdapter);
			sActivityType.setAdapter(activityTypeAdapter);

			tCrmNo.setText(UpdateConstants.CRM_NO);
			tCheckIn.setText(UpdateConstants.CHECK_IN);
			tCheckOut.setText(UpdateConstants.CHECK_OUT);
			eCreatedBy.setText(JardineApp.DB.getUser()
					.getById(UpdateConstants.CREATED_BY).toString());
			sBusinessUnit.setSelection(Integer.parseInt(String
					.valueOf(UpdateConstants.BUSINESS_UNIT)) - 1);
			sActivityType.setSelection(Integer.parseInt(String
					.valueOf(UpdateConstants.ACTIVITY_TYPE)) - 1);
		}

		saveBtn = (CircularProgressButton) view.findViewById(R.id.btnWithText1);
		saveBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				v.setClickable(false);
				v.setEnabled(false);

				if (saveBtn.getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(500);
					widthAnimation
							.setInterpolator(new AccelerateDecelerateInterpolator());
					widthAnimation
							.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
								@Override
								public void onAnimationUpdate(
										ValueAnimator animation) {
									Integer value = (Integer) animation
											.getAnimatedValue();
									saveBtn.setProgress(value);

									if (!trapping) {
										saveBtn.setProgress(-1);
									}
								}
							});

					widthAnimation.start();

					long activityType = ((ActivityTypeRecord) sActivityType
							.getSelectedItem()).getId();

					if (activityType != 0) {
						trapping = true;

						UpdateConstants.ACTIVITY_RECORD = new ActivityRecord();

						UpdateConstants.ACTIVITY_RECORD.setCrm(tCrmNo.getText()
								.toString());
						UpdateConstants.ACTIVITY_RECORD
								.setBusinessUnit(((BusinessUnitRecord) sBusinessUnit
										.getSelectedItem()).getId());
						UpdateConstants.ACTIVITY_RECORD
								.setActivityType(activityType);
						UpdateConstants.ACTIVITY_RECORD
								.setCreatedBy(JardineApp.DB.getUser()
										.getCurrentUser().getId());

						// Set the button click to true
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								v.setClickable(true);
								v.setEnabled(true);
							}

						}, 700);

					} else {

						trapping = false;
						Toast.makeText(getActivity(),
								"Please fill up required (RED COLOR) fields",
								Toast.LENGTH_LONG).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								saveBtn.setProgress(0);
								v.setClickable(true);
								v.setEnabled(true);
							}
						}, 1500);
					}

				} else {
					saveBtn.setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);
					
					UpdateFragment.pager.setCurrentItem(3);
				}
			}
		});

		return view;
	}

	protected String FormatDateAndDay(int digit) {
		String formattedStringDigit = digit < 10 ? "0" + String.valueOf(digit)
				: String.valueOf(digit);
		return String.valueOf(formattedStringDigit);
	}

	protected String displayCheckIn() {
		UpdateGeneralInformationFragment.this.formattedDate = UpdateGeneralInformationFragment.this.year
				+ "-"
				+ UpdateGeneralInformationFragment.this
						.FormatDateAndDay((UpdateGeneralInformationFragment.this.month + 1))
				+ "-"
				+ UpdateGeneralInformationFragment.this
						.FormatDateAndDay(UpdateGeneralInformationFragment.this.day);

		return this.formattedDate.concat(" " + df.format(calendar.getTime()));
	}
}
