package co.nextix.jardine.activities.add.fragments;

import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.security.StoreAccount;
import co.nextix.jardine.security.StoreAccount.Account;
import co.nextix.jardine.workplan.WorkPlanConstants;

import com.dd.CircularProgressButton;

public class AddActivityTravelWaitingFragment extends Fragment {
	private boolean flag = false;
	private CircularProgressButton saveBtn = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.add_activity_travel_or_waiting, container, false);

		this.saveBtn = (CircularProgressButton) view.findViewById(R.id.btnWithText1);
		this.saveBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				if (((CircularProgressButton) v).getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(1500);
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

					String reasons = ((EditText) view.findViewById(R.id.reason_remarks)).getText().toString();

					/** Checking of required fields **/
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					if (reasons != null && !reasons.isEmpty()) {
						flag = true;

						new InsertTask("0", pref.getString("crm_no", null), pref.getLong("activity_type", 0), pref.getString("check_in",
								null), pref.getString("check_out", null), pref.getLong("business_unit", 0), Long.parseLong(StoreAccount
								.restore(getActivity()).getString(Account.ROWID)), 123.894882, 10.310235, pref.getString("check_in", null),
								pref.getString("check_out", null), reasons, 0, "", 0, 0, 0, 0, AddActivityFragment.WORKPLAN_ENTRY_ID, "",
								0, 0, "", "", "", "", "", "", "", "", 0, "").execute();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								getFragmentManager().popBackStackImmediate();
							}

						}, 2700);

					} else {

						flag = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_SHORT).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);

							}
						}, 1500);
					}

				} else {

					((CircularProgressButton) v).setProgress(0);
				}
			}
		});

		return view;
	}

	protected class InsertTask extends AsyncTask<Void, Void, Boolean> {

		private ValueAnimator widthAnimation = null;

		// String to be populated
		private String no = null;
		private String crmNo = null;

		private long activityType = 0000;

		private String checkIn = null;
		private String checkOut = null;

		private long businessUnit = 0000;
		private long createdBy = 000;

		private double longitude = 0.00;
		private double latitude = 0.00;

		private String createdTime = null;
		private String modifiedTime = null;
		private String reasonsRemarks = null;

		private long smr = 0000;
		private String adminDetails = null;

		private long customer = 0000;
		private long area = 0000;
		private long province = 0000;
		private long city = 0000;
		private long workplanEntry = 0000;

		private String objective = null;

		private int firstTimeVisit = 0;
		private int plannedVisit = 0;

		private String notes = null;
		private String highlights = null;
		private String nextSteps = null;
		private String followUpCommitmentDate = null;
		private String projectName = null;
		private String projectStage = null;
		private String projectCategory = null;
		private String venue = null;

		private int numberOfAttendees = 0;
		private String endUserActivityTypes = null;

		private boolean flag;

		private InsertTask(String no, String crmNo, long activityType, String checkIn, String checkOut, long businessUnit, long createdBy,
				double longitude, double latitude, String createdTime, String modifiedTime, String reasonsRemarks, long smr,
				String adminDetails, long customer, long area, long province, long city, long workplanEntry, String objective,
				int firstTimeVisit, int plannedVisit, String notes, String highlights, String nextSteps, String followUpCommitmentDate,
				String projectName, String projectStage, String projectCategory, String venue, int numberOfAttendees,
				String endUserActivityTypes) {

			this.no = no;
			this.crmNo = crmNo;
			this.activityType = activityType;
			this.checkIn = checkIn;
			this.checkOut = checkOut;
			this.businessUnit = businessUnit;
			this.createdBy = createdBy;
			this.longitude = longitude;
			this.latitude = latitude;
			this.createdTime = createdTime;
			this.modifiedTime = modifiedTime;
			this.reasonsRemarks = reasonsRemarks;
			this.smr = smr;
			this.adminDetails = adminDetails;
			this.customer = customer;
			this.area = area;
			this.province = province;
			this.city = city;
			this.workplanEntry = workplanEntry;
			this.objective = objective;
			this.firstTimeVisit = firstTimeVisit;
			this.plannedVisit = plannedVisit;
			this.notes = notes;
			this.highlights = highlights;
			this.nextSteps = nextSteps;
			this.followUpCommitmentDate = followUpCommitmentDate;
			this.projectName = projectName;
			this.projectStage = projectStage;
			this.projectCategory = projectCategory;
			this.venue = venue;
			this.numberOfAttendees = numberOfAttendees;
			this.endUserActivityTypes = endUserActivityTypes;
		}

		@Override
		protected void onPreExecute() {
			// Animate Button
			this.widthAnimation = ValueAnimator.ofInt(1, 100);
			this.widthAnimation.setDuration(1500);
			this.widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
			this.widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					Integer value = (Integer) animation.getAnimatedValue();
					saveBtn.setProgress(value);

					if (!flag) {
						saveBtn.setProgress(-1);
					}
				}
			});

			this.widthAnimation.start();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			this.flag = false;
			try {

				saveActivity(this.no, this.crmNo, this.activityType, this.checkIn, this.checkOut, this.businessUnit, this.createdBy,
						this.longitude, this.latitude, this.createdTime, this.modifiedTime, this.reasonsRemarks, this.smr,
						this.adminDetails, this.customer, this.area, this.province, this.city, this.workplanEntry, this.objective,
						this.firstTimeVisit, this.plannedVisit, this.notes, this.highlights, this.nextSteps, this.followUpCommitmentDate,
						this.projectName, this.projectStage, this.projectCategory, this.venue, this.numberOfAttendees,
						this.endUserActivityTypes);

				this.flag = true;
			} catch (Exception e) {
				this.flag = false;
				Log.e("Jardine", e.toString());
			}

			return this.flag;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				saveBtn.setProgress(100);
			} else {
				this.flag = false;
			}
		}
	}

	protected String FormatDateAndDay(int digit) {
		String formattedStringDigit = digit < 10 ? "0" + String.valueOf(digit) : String.valueOf(digit);
		return String.valueOf(formattedStringDigit);
	}

	protected void saveActivity(String no, String crmNo, long activityType, String checkIn, String checkOut, long businessUnit,
			long createdBy, double longitude, double latitude, String createdTime, String modifiedTime, String reasonsRemarks, long smr,
			String adminDetails, long customer, long area, long province, long city, long workplanEntry, String objective,
			int firstTimeVisit, int plannedVisit, String notes, String highlights, String nextSteps, String followUpCommitmentDate,
			String projectName, String projectStage, String projectCategory, String venue, int numberOfAttendees,
			String endUserActivityTypes) {

		// Insert to the database
		JardineApp.DB.getActivity().insert(no, crmNo, activityType, checkIn, checkOut, businessUnit, createdBy, longitude, latitude,
				createdTime, modifiedTime, reasonsRemarks, smr, adminDetails, customer, area, province, city, workplanEntry, objective,
				firstTimeVisit, plannedVisit, notes, highlights, nextSteps, followUpCommitmentDate, projectName, projectStage,
				projectCategory, venue, numberOfAttendees, endUserActivityTypes);
	}
}
