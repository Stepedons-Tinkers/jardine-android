package co.nextix.jardine.activities.update.fragments;

import co.nextix.jardine.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UpdatePhotosAndAttachmentsDetailFragment extends Fragment {
	
	private View view;
	
	private boolean flag = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.update_photos_and_attachments_detail, container, false);
		
		
		return view;
	}
}
