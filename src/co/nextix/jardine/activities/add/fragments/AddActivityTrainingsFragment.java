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

public class AddActivityTrainingsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.add_activity_trainings, container, false);
		((TextView) rootView.findViewById(R.id.venue)).setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					Editor editor = pref.edit();
					editor.putString("venue", v.getText().toString());
					editor.putString("no_attendees", ((EditText) rootView.findViewById(R.id.number_of_attendees)).getText().toString());
					editor.commit(); // commit changes
				}

				return false;
			}
		});

		return rootView;
	}
}
