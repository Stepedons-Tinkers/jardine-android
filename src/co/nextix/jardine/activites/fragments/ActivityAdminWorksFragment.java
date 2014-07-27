package co.nextix.jardine.activites.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.nextix.jardine.R;

public class ActivityAdminWorksFragment extends Fragment {
	private static volatile ActivityAdminWorksFragment instance = null;
	private boolean flag = false;

	public static ActivityAdminWorksFragment getInstance() {
		if (instance == null) {
			synchronized (ActivityAdminWorksFragment.class) {
				// Double check
				if (instance == null) {
					instance = new ActivityAdminWorksFragment();
				}
			}
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.view_activity_admin_works, container, false);


		return rootView;
	}
}
