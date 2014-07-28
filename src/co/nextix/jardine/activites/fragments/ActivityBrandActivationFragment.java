package co.nextix.jardine.activites.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.nextix.jardine.R;

public class ActivityBrandActivationFragment extends Fragment {
	private static volatile ActivityBrandActivationFragment instance = null;
	private boolean flag = false;

	public static ActivityBrandActivationFragment getInstance() {
		if (instance == null) {
			synchronized (ActivityBrandActivationFragment.class) {
				// Double check
				if (instance == null) {
					instance = new ActivityBrandActivationFragment();
				}
			}
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.view_activity_brand_activation, container, false);

		return rootView;
	}
}
