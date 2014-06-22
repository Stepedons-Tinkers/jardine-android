package co.nextix.jardine.activities.add.fragments;

import co.nextix.jardine.R;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AddDIYorSupermarket extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.add_activity_fragment, container, false);
		((Button) rootView.findViewById(R.id.general_info_button)).getBackground().setColorFilter(
				new LightingColorFilter(0x0033FF, 0x0066FF));

		return rootView;
	}

}
