package co.nextix.jardine.profile;

import co.nextix.jardine.R;
import co.nextix.jardine.database.DatabaseAdapter;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.utils.MyDateUtils;
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileInformationFragment extends Fragment {

	TextView txtUsername, txtFirstname, txtLastname, txtAreas, txtLastSync;

	public static Fragment newInstance() {
		ProfileInformationFragment fragment = new ProfileInformationFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.profile_info_layout, null);

		txtUsername = (TextView) view.findViewById(R.id.profileinfo_txt_name);
		txtFirstname = (TextView) view
				.findViewById(R.id.profileinfo_txt_firstname);
		txtLastname = (TextView) view
				.findViewById(R.id.profileinfo_txt_lastname);
		txtAreas = (TextView) view.findViewById(R.id.profileinfo_txt_area);
		txtLastSync = (TextView) view
				.findViewById(R.id.profileinfo_txt_lastsync);

		txtUsername.setTypeface(Typeface.createFromAsset(getActivity()
				.getAssets(), "fonts/Roboto-Light.ttf"));

		populate();

		return view;
	}

	private void populate() {
		UserTable u = DatabaseAdapter.getInstance().getUser();
		if (u != null) {
			UserRecord user = u.getCurrentUser();
			if (user != null) {
				txtUsername.setText(user.getUsername());
				txtFirstname.setText(user.getFirstNameName());
				txtLastname.setText(user.getLastname());
				String lastSync = user.getLastSync();
				if (lastSync != null) {
					if (!lastSync.equals("")) {
						txtLastSync.setText(MyDateUtils
								.convertDateTime(lastSync));

					} else {
						txtLastSync.setText("not yet synced");
					}
				} else {
					txtLastSync.setText("not yet synced");
				}
				String area = user.getArea().replace("|", "")
						.replace("##", ",");
				txtAreas.setText(area);
			}
		}
		// txtLastSync.
	}
}
