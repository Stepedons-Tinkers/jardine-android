package co.nextix.jardine.activities.add.fragments;

import co.nextix.jardine.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddJDIProductStockFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View myFragmentView = inflater.inflate(R.layout.fragment_activity_add_jdi_product_stock_check, container, false);
		return myFragmentView;
	}

	public void onCancel(View view) {
		getActivity().getSupportFragmentManager().popBackStackImmediate();
	}
	
	public void createJDIProductStockCheck(View view){
		
	}
}
