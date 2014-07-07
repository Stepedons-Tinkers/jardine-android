package co.nextix.jardine.activities.add.fragments;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.Toast;
import co.nextix.jardine.R;

public class AddActivityWithCoSMRsFragment extends Fragment {
	private static volatile AddActivityWithCoSMRsFragment instance = null;

	public static AddActivityWithCoSMRsFragment getInstance() {
		if (instance == null) {
			synchronized (AddActivityWithCoSMRsFragment.class) {
				// Double check
				if (instance == null) {
					instance = new AddActivityWithCoSMRsFragment();
				}
			}
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.add_activity_with_co_smrs, container, false);
		//SMRRecord smrList = JardineApp.DB.getSMR().getById(JardineApp.DB.getUser().getCurrentUser().getId());
		//ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(JardineApp.context, R.layout.add_activity_textview);
		
		((Spinner) rootView.findViewById(R.id.smr)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
				Editor editor = pref.edit();
				editor.putLong("smr", Long.parseLong(parent.getSelectedItem().toString()));
				editor.commit(); // commit changes
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				if (parent.getSelectedItemPosition() == 0) {
					Toast.makeText(getActivity(), "This is field is required", Toast.LENGTH_SHORT).show();
				}
			}
		});

		return rootView;
	}
}
