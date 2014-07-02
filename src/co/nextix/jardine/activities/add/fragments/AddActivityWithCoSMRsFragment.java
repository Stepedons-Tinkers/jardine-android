package co.nextix.jardine.activities.add.fragments;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
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

		((EditText) rootView.findViewById(R.id.smr)).setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					Editor editor = pref.edit();
					editor.putString("smr", v.getText().toString());
					editor.commit(); // commit changes
				}

				return false;
			}
		});

		return rootView;
	}

}
