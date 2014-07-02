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

public class AddActivityDIYorSupermarketFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.add_activity_diy_or_supermarket, container, false);
		((EditText) rootView.findViewById(R.id.ongoing_campaigns)).setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					Editor editor = pref.edit();
					editor.putString("issues_identified", ((EditText) rootView.findViewById(R.id.issues_identified)).getText().toString());
					editor.putString("feedback_from_customer", ((EditText) rootView.findViewById(R.id.feedback_from_customer)).getText()
							.toString());
					editor.putString("ongoing_campaigns", v.getText().toString());
					editor.commit(); // commit changes
				}

				return false;
			}
		});

		return rootView;
	}

}
