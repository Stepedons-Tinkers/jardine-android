package co.nextix.jardine.activites.fragments;

import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import co.nextix.jardine.R;
import co.nextix.jardine.activities.add.fragments.AddProjectRequirementsFragment;

public class ProjectRequirementsFragment extends Fragment {

	private Bundle bundle;
	private int frag_layout_id;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		final View myFragmentView = inflater.inflate(R.layout.fragment_activity_project_requirements, container, false);
		
		bundle = getArguments();
		
		if(bundle != null){
			frag_layout_id = bundle.getInt("layoutID");
		}
		
		((Button) myFragmentView.findViewById(R.id.add_proj_requirement)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));

				android.support.v4.app.Fragment newFragment = new AddProjectRequirementsFragment();

				// Create new transaction
				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
						.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);

				// Replace whatever is in the fragment_container view with this
				// fragment,
				// and add the transaction to the back stack
				transaction.replace(frag_layout_id, newFragment);
				transaction.addToBackStack(null);

				// Commit the transaction
				transaction.commit();
			}
		});

		((ImageButton) myFragmentView.findViewById(R.id.imageButton1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "<==== ni sud here", Toast.LENGTH_SHORT).show();
			}
		});

		((ImageButton) myFragmentView.findViewById(R.id.imageButton3)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "ni sud here ====>", Toast.LENGTH_SHORT).show();
			}
		});

		return myFragmentView;
	}
}
