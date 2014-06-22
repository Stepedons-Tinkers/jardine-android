package co.nextix.jardine.activites.fragments;

import co.nextix.jardine.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MoreActivityInformationFragment extends Fragment {
	private View myFragmentView = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.myFragmentView = inflater.inflate(R.layout.static_more_information_fragment, container, false);
		return this.myFragmentView;
	}
}
