package co.nextix.jardine.activities.add.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.DashBoardActivity;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.PicklistRecord;

import com.dd.CircularProgressButton;

public class AddProjectRequirementsFragment extends Fragment {

	private boolean flag = false;
	private View view = null;
	private ArrayAdapter<PicklistRecord> projectRequirementAdapter = null;

	private Calendar calendar = null;
	private String formattedDate = null;
	private int day = 0;
	private int month = 0;
	private int year = 0;
	public static int ActivityType = 0;

	public AddProjectRequirementsFragment() {
		this.calendar = Calendar.getInstance();
		this.day = this.calendar.get(Calendar.DAY_OF_MONTH);
		this.month = this.calendar.get(Calendar.MONTH);
		this.year = this.calendar.get(Calendar.YEAR);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		String assignedToFname = JardineApp.DB.getUser().getCurrentUser().getFirstNameName();
		String assignedToLname = JardineApp.DB.getUser().getCurrentUser().getLastname();

		List<PicklistRecord> projectRequirementList = JardineApp.DB.getProjectRequirementType().getAllRecords();
		this.projectRequirementAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context, R.layout.add_activity_textview,
				projectRequirementList);

		this.view = inflater.inflate(R.layout.fragment_activity_add_proj_requirements, container, false);
		((Spinner) this.view.findViewById(R.id.project_requirements)).setAdapter(this.projectRequirementAdapter);

		// Setting to text field auto populate data
		((TextView) this.view.findViewById(R.id.created_by)).setText(assignedToLname + "," + assignedToFname);
		((TextView) this.view.findViewById(R.id.created_by)).setEnabled(false);
		((TextView) this.view.findViewById(R.id.created_by)).setClickable(false);

		((TextView) this.view.findViewById(R.id.activity)).setText("AUTO_GEN_ON_SAVE");
		((TextView) this.view.findViewById(R.id.activity)).setEnabled(false);
		((TextView) this.view.findViewById(R.id.activity)).setClickable(false);

		((TextView) this.view.findViewById(R.id.date_needed)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DatePickerDialog pickDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Panel, datePickerListener,
						year, month, day);
				pickDialog.show();
			}
		});

		((CircularProgressButton) view.findViewById(R.id.btnWithText1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				v.setClickable(false);
				v.setEnabled(false);

				if (((CircularProgressButton) v).getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(500);
					widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
					widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(ValueAnimator animation) {

							Integer value = (Integer) animation.getAnimatedValue();
							((CircularProgressButton) v).setProgress(value);

							if (!flag) {

								((CircularProgressButton) v).setProgress(-1);
							}
						}
					});

					widthAnimation.start();

					// String details = ((EditText)
					// view.findViewById(R.id.details)).getText().toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					long projectRequirementType = ((PicklistRecord) ((Spinner) view.findViewById(R.id.project_requirement_type))
							.getSelectedItem()).getId();

					String date_needed = ((TextView) view.findViewById(R.id.date_needed)).getText().toString().concat(displayCheckOut());
					String square_meters_proj_requirement = ((EditText) view.findViewById(R.id.square_meters_proj_requirements)).getText()
							.toString();
					String product_brand = ((EditText) view.findViewById(R.id.product_brand)).getText().toString();
					String other_details = ((EditText) view.findViewById(R.id.other_details)).getText().toString();

					if (projectRequirementType != 0) {
						flag = true;
						Editor editor = pref.edit();
						editor.putLong("project_requirement_type", projectRequirementType);
						editor.putString("date_needed_project_requirement_type", date_needed);
						editor.putString("square_meters_proj_requirement", square_meters_proj_requirement);
						editor.putString("product_brand_proj_requirement", product_brand);
						editor.putString("other_details_proj_requirement", other_details);
						editor.commit(); // commit changes

						v.setClickable(true);
						v.setEnabled(true);

					} else {
						flag = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_SHORT).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);
								v.setClickable(true);
								v.setEnabled(true);
							}
						}, 1500);
					}

				} else {
					((CircularProgressButton) v).setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);

					if (AddActivityGeneralInformationFragment.ActivityType == 9) { // ki
																					// visits
						DashBoardActivity.tabIndex.add(6, 16);
						AddActivityFragment.pager.setCurrentItem(16);
					}
				}
			}
		});

		return view;
	}

	protected DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker date, int selectedYear, int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			formattedDate = year + "-" + FormatDateAndDay((month + 1)) + "-" + FormatDateAndDay(day);
			((TextView) view.findViewById(R.id.date_needed)).setText(formattedDate);

		}
	};

	protected String displayCheckOut() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		return " " + df.format(calendar.getTime());
	}

	protected String FormatDateAndDay(int digit) {
		String formattedStringDigit = digit < 10 ? "0" + String.valueOf(digit) : String.valueOf(digit);
		return String.valueOf(formattedStringDigit);
	}

}
