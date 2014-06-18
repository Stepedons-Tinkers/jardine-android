package co.nextix.jardine.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import co.nextix.jardine.R;

public class WorkplanMenuBarFragment extends Fragment {

	public static EditText editMonth;
	public static Calendar c = null;
	public static Date today = null;
	public static SimpleDateFormat df = null;
	public static String formattedDate = null;

	public WorkplanMenuBarFragment() {
		c = Calendar.getInstance();
		df = new SimpleDateFormat("MM/dd/yyyy");
		today = new Date();
		formattedDate = df.format(c.getTime());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		View rootView = inflater.inflate(R.layout.fragment_workplan, container,
				false);

		editMonth = (EditText) rootView.findViewById(R.id.editMonth);
		editMonth.setText(formattedDate);

		// Now formattedDate have current date/time
		Toast.makeText(getActivity(), "" + today, Toast.LENGTH_SHORT).show();
		return rootView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

}
