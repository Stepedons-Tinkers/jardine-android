package co.nextix.jardine.fragments;

import android.content.pm.ActivityInfo;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import co.nextix.jardine.R;

public class StartActivityFragment extends Fragment {

	public StartActivityFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		View rootView = inflater.inflate(
				R.layout.fragment_activity_static_fields, container, false);
		
		((Button) rootView.findViewById(R.id.activity_info)).getBackground().setColorFilter(
				new LightingColorFilter(0x0033FF, 0x0066FF));

		return rootView;
	}
}
