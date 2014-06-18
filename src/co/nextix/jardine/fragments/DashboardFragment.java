package co.nextix.jardine.fragments;

import java.util.Calendar;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.TextView;
import co.nextix.jardine.R;

public class DashboardFragment extends Fragment {

	private Calendar c = null;
	private int day = 0;
	private int month = 0;
	private int year = 0;
	private int date = 0;
	private int counter = 0;
	
	
	public DashboardFragment() {
		this.c = Calendar.getInstance();
		this.day = c.get(Calendar.DAY_OF_WEEK);
		this.month = c.get(Calendar.MONTH);
		this.date = c.get(Calendar.DATE);
		this.year = c.get(Calendar.YEAR);
	}

	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		View rootView = inflater.inflate(R.layout.fragment_dashboard,
				container, false);

		Button today = (Button) rootView.findViewById(R.id.date_today);
		today.setText("Today - "
				+ this.concatenateDay(DateUtils.getDayOfWeekString(this.day,
						DateUtils.LENGTH_SHORT)) + ", "
				+ DateUtils.getMonthString(this.month, DateUtils.LENGTH_LONG)
				+ " " + this.date + ", " + year);
		
		this.startAnimationPopOut(rootView);
		return rootView;
	}

	private void startAnimationPopOut(final View view) {
		TextView myLayout = (TextView) view.findViewById(R.id.update_activities);
		Animation animation = AnimationUtils.loadAnimation(
				getActivity(), R.anim.tile_animation);

		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(final Animation animation) {
				counter++;
				TextView myLayout = (TextView) view.findViewById(R.id.update_activities);
				myLayout.setText("" + counter + " remaining stocks \n" + counter + " updates for marketing intel");
				myLayout.startAnimation(animation);
			}
		});

		animation.setRepeatCount(Animation.INFINITE);
		animation.setRepeatMode(Animation.INFINITE);

		myLayout.clearAnimation();
		myLayout.startAnimation(animation);
	}

	protected String concatenateDay(String day) {
		String concatenatedDay = "";

		concatenatedDay = day.equals("Su") ? day + "nday"
				: day.equals("Mo") ? day + "nday" : day.equals("Tu") ? day
						+ "esday" : day.equals("We") ? day + "dnesday" : day
						.equals("Th") ? day + "ursday" : day.equals("Fr") ? day
						+ "iday" : day.equals("Sa") ? day + "turday" : "";

		return concatenatedDay;
	}
}
