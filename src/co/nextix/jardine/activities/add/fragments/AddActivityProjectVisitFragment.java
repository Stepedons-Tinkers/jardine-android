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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView.OnEditorActionListener;
import co.nextix.jardine.R;

public class AddActivityProjectVisitFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.add_activity_project_visit, container, false);
		((EditText) rootView.findViewById(R.id.project_name)).setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
					Editor editor = pref.edit();
					editor.putString("project_name", v.getText().toString());
					editor.putString("project_stage", (String) ((Spinner) rootView.findViewById(R.id.project_stage)).getSelectedItem());
					editor.putString("project_category",
							(String) ((Spinner) rootView.findViewById(R.id.project_category)).getSelectedItem());
					editor.commit(); // commit changes
				}

				return false;
			}
		});

		((Spinner) rootView.findViewById(R.id.project_stage)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
				Editor editor = pref.edit();
				editor.putString("project_name", ((EditText) rootView.findViewById(R.id.project_name)).getText().toString());
				editor.putString("project_stage", (String) parent.getSelectedItem());
				editor.putString("project_category", (String) ((Spinner) rootView.findViewById(R.id.project_category)).getSelectedItem());
				editor.commit(); // commit changes
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				if (parent.getSelectedItemPosition() == 0) {
					Toast.makeText(getActivity(), "This field requires data", Toast.LENGTH_SHORT).show();
				}
			}
		});

		((Spinner) rootView.findViewById(R.id.project_category)).setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);
				Editor editor = pref.edit();
				editor.putString("project_name", ((EditText) rootView.findViewById(R.id.project_name)).getText().toString());
				editor.putString("project_category", (String) parent.getSelectedItem());
				editor.putString("project_stage", (String) ((Spinner) rootView.findViewById(R.id.project_stage)).getSelectedItem());
				editor.commit(); // commit changes
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				if (parent.getSelectedItemPosition() == 0) {
					Toast.makeText(getActivity(), "This field requires data", Toast.LENGTH_SHORT).show();
				}
			}
		});
		return rootView;
	}
}
