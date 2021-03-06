package co.nextix.jardine.activites.fragments.backup;

import co.nextix.jardine.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DIYSupermarketPhotosFragment extends Fragment {

	private Bundle bundle;
	private int frag_layout_id;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View myFragmentView = inflater.inflate(
				R.layout.fragment_activity_diy_or_supermarket_photos,
				container, false);
		
		bundle = getArguments();
		
		if(bundle != null){
			frag_layout_id = bundle.getInt("layoutID");
		}

		return myFragmentView;
	}
}
