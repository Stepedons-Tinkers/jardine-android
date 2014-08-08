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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.CompetitorProductRecord;
import co.nextix.jardine.database.records.UserRecord;

import com.dd.CircularProgressButton;

public class UpdateAddMarketingIntelFragment extends Fragment {

	private View view;

	private boolean flag = false;

	private CircularProgressButton cpbAdd;
	private CircularProgressButton cpbCancel;
	
	private TextView tvCreatedby;	
	private Spinner sCompProduct;	
	private EditText etDetails;
	
	private List<CompetitorProductRecord> compProductList = null;
	
	private ArrayAdapter<CompetitorProductRecord> compProductAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.update_add_marketing_intel, container,
				false);
		
		compProductList = JardineApp.DB.getCompetitorProduct().getAllRecords();
		compProductAdapter = new ArrayAdapter<CompetitorProductRecord>(
				JardineApp.context, R.layout.add_activity_textview,
				compProductList);
		
		tvCreatedby = (TextView) view.findViewById(R.id.update_add_mark_created_by);
		sCompProduct = (Spinner) view.findViewById(R.id.update_add_mark_competitor_product);
		etDetails = (EditText) view.findViewById(R.id.update_add_mark_details);

		sCompProduct.setAdapter(compProductAdapter);
		
		cpbCancel = (CircularProgressButton) view
				.findViewById(R.id.btnWithText2);
		cpbCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager();
				getActivity().getSupportFragmentManager()
						.popBackStack("to_add_mark",
								FragmentManager.POP_BACK_STACK_INCLUSIVE);
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

					String product = sCompProduct.getSelectedItem().toString();
					String detail = etDetails.getText().toString();

					if (detail != null && !detail.isEmpty() && !product.equalsIgnoreCase("- Select -")) {
						flag = true;

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								cpbAdd.setClickable(true);
								cpbAdd.setEnabled(true);

								// save it to constants then update the DATABASE
								// then notify the listview

								getActivity().getSupportFragmentManager();
								getActivity()
										.getSupportFragmentManager()
										.popBackStack(
												"to_add_mark",
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
