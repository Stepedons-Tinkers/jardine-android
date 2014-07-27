package co.nextix.jardine.activities.add.fragments;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;

import com.dd.CircularProgressButton;

public class AddJDIProductStockFragment extends Fragment {

	private boolean flag = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_activity_add_jdi_product_stock_check, container, false);

		((CircularProgressButton) view.findViewById(R.id.btnWithText1)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				if (((CircularProgressButton) v).getProgress() == 0) {

					ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
					widthAnimation.setDuration(1500);
					widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
					widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(ValueAnimator animation) {

							Integer value = (Integer) animation.getAnimatedValue();
							((CircularProgressButton) v).setProgress(value);

							if (!flag) {

								((CircularProgressButton) v).setProgress(-1);
							}
						}
					});

					widthAnimation.start();

					long stockStatus = ((JDIproductStockCheckRecord) ((Spinner) view.findViewById(R.id.stock_status)).getSelectedItem())
							.getId();
					long loadedOnShelves = ((CheckBox) view.findViewById(R.id.loaded_on_shelves)).isChecked() ? 1 : 0;
					String otherTypeRemarks = ((EditText) view.findViewById(R.id.other_type_remarks)).getText().toString();

					SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("ActivityInfo", 0);

					if (otherTypeRemarks != null && !otherTypeRemarks.isEmpty()) {
						Editor editor = pref.edit();
						editor.putString("other_type_remarks", otherTypeRemarks);
						editor.putLong("stock_status", stockStatus);
						editor.putLong("loaded_on_shelves", loadedOnShelves);
						editor.commit(); // commit changes
					} else {
						flag = false;
						Toast.makeText(getActivity(), "Please fill up required (RED COLOR) fields", Toast.LENGTH_SHORT).show();

						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								((CircularProgressButton) v).setProgress(0);

							}
						}, 1500);
					}
				} else {

					((CircularProgressButton) v).setProgress(0);
				}
			}
		});

		return view;
	}

	public void onCancel(View view) {
		getActivity().getSupportFragmentManager().popBackStackImmediate();
	}

	public void createJDIProductStockCheck(View view) {

	}
}
