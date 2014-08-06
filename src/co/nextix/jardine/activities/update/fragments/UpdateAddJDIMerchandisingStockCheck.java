package co.nextix.jardine.activities.update.fragments;

import java.util.List;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;

import com.dd.CircularProgressButton;

public class UpdateAddJDIMerchandisingStockCheck extends Fragment {
	
	private View view;
	
	private CircularProgressButton cpbAdd;
	private CircularProgressButton cpbCancel;
	
	private boolean flag = false;
	
	private List<ProductRecord> productList = null;
	private List<PicklistRecord> statusList = null;
	
	private ArrayAdapter<ProductRecord> productAdapter = null;
	private ArrayAdapter<PicklistRecord> statusAdapter = null;
	
	private Spinner sProduct;
	private Spinner sStatus;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.update_add_jdi_merchandising_stock_check, container, false);
		
		statusList = JardineApp.DB.getJDIproductStatus().getAllRecords();
		productList = JardineApp.DB.getProduct().getAllRecords();
		
		productAdapter = new ArrayAdapter<ProductRecord>(JardineApp.context,
				R.layout.add_activity_textview, productList);
		statusAdapter = new ArrayAdapter<PicklistRecord>(JardineApp.context,
				R.layout.add_activity_textview, statusList);
		
		sProduct = (Spinner) view.findViewById(R.id.update_add_jdi_product);
		sStatus = (Spinner) view.findViewById(R.id.update_add_jdi_status);
		
		sProduct.setAdapter(productAdapter);
		sStatus.setAdapter(statusAdapter);
		
		cpbCancel = (CircularProgressButton) view.findViewById(R.id.btnWithText2);
		cpbCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getActivity().getSupportFragmentManager();
				getActivity().getSupportFragmentManager().popBackStack("to_add_jdi", FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}
		});
		
		cpbAdd = (CircularProgressButton) view.findViewById(R.id.btnWithText1);
		cpbAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v.setClickable(false);
				v.setEnabled(false);

				if (cpbAdd.getProgress() == 0) {
					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(500);
					widthAnimation
							.setInterpolator(new AccelerateDecelerateInterpolator());
					widthAnimation
							.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

								@Override
								public void onAnimationUpdate(
										ValueAnimator animation) {

									Integer value = (Integer) animation
											.getAnimatedValue();
									cpbAdd.setProgress(value);

									if (!flag) {
										cpbAdd.setProgress(-1);
									}
								}
							});

					widthAnimation.start();
					
					if (true) {
						flag = true;
						
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								cpbAdd.setClickable(true);
								cpbAdd.setEnabled(true);
								
								// save it to constants then update the DATABASE then notify the listview
								
								getActivity().getSupportFragmentManager();
								getActivity().getSupportFragmentManager().popBackStack(
										"to_add_jdi",
										FragmentManager.POP_BACK_STACK_INCLUSIVE);
							}
						}, 1500);
					} else {
						flag = false;
						Toast.makeText(getActivity(),
								"Please fill up required fields",
								Toast.LENGTH_SHORT).show();
						cpbAdd.setClickable(true);
						cpbAdd.setEnabled(true);
					}
				} else {
					cpbAdd.setProgress(0);
					v.setClickable(true);
					v.setEnabled(true);
				}
			}
		});
		
		return view;
	}
}
