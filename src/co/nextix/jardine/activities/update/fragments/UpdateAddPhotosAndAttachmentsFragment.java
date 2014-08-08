package co.nextix.jardine.activities.update.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import co.nextix.jardine.R;

import com.dd.CircularProgressButton;

public class UpdateAddPhotosAndAttachmentsFragment extends Fragment {

	private View view;

	private boolean flag = false;

	private CircularProgressButton cpbCancel;
	private CircularProgressButton cpbAdd;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.update_add_photos_and_attachments,
				container, false);

		cpbCancel = (CircularProgressButton) view
				.findViewById(R.id.btnWithText2);
		cpbCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager();
				getActivity().getSupportFragmentManager().popBackStack(
						"to_add_photos",
						FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
		});
		
		cpbAdd = (CircularProgressButton) view.findViewById(R.id.btnWithText1);
		cpbAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager();
				getActivity().getSupportFragmentManager().popBackStack(
						"to_add_photos",
						FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
		});

		return view;
	}
}
