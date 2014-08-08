package co.nextix.jardine.activities.update.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import co.nextix.jardine.R;

public class UpdateAddJDIProductStockCheckFragment extends Fragment {

	private View view;

	private boolean flag = false;

	private Button bAdd;
	private Button bCancel;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.update_add_jdi_product_stock_check,
				container, false);

		bCancel = (Button) view.findViewById(R.id.cancel);
		bCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager();
				getActivity().getSupportFragmentManager().popBackStack(
						"to_add_jdi_product",
						FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
		});

		return view;
	}
}
