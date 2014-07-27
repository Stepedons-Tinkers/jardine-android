package co.nextix.jardine.activities.add.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.nextix.jardine.R;

public class AddActivityMajorTraining extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.add_activity_major_training, container, false);

		return view;
	}
}
