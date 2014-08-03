package co.nextix.jardine.activites.fragments.backup;

import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import co.nextix.jardine.R;

public class MoreActivityInformationFragment extends Fragment {
	private View myFragmentView = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.myFragmentView = inflater.inflate(R.layout.static_more_information_fragment, container, false);

		((Button) this.myFragmentView.findViewById(R.id.jdi_stock_check)).getBackground().setColorFilter(
				new LightingColorFilter(0x0033FF, 0x0066FF));

		/*** Appoint to the first fragment ***/
//		android.support.v4.app.Fragment newFragment = new JDIProductStockFragment();
//		android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//		transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//		transaction.replace(R.id.activity_fragment, newFragment);
//		transaction.addToBackStack(null);
//		transaction.commit();

		// Event Listeners
		((Button) this.myFragmentView.findViewById(R.id.jdi_stock_check)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));

				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_merchandising_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.competitor_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.marketing_intel));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.project_requirements));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.diy_supermarket_photos));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.customer_contact_person));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.products));
				this.appointFragment();
			}

			private void clearColorFilter(View findViewById) {
				Drawable d = findViewById.getBackground();
				findViewById.invalidateDrawable(d);
				d.clearColorFilter();
			}

			private void appointFragment() {
//				android.support.v4.app.Fragment newFragment = new JDIProductStockFragment();
//
//				// Create new transaction
//				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//				transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//
//				// Replace whatever is in the fragment_container view with this
//				// fragment,
//				// and add the transaction to the back stack
//				transaction.replace(R.id.activity_fragment, newFragment);
//				transaction.addToBackStack(null);
//
//				// Commit the transaction
//				transaction.commit();
			}
		});

		((Button) this.myFragmentView.findViewById(R.id.jdi_merchandising_check)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.competitor_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.marketing_intel));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.project_requirements));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.diy_supermarket_photos));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.customer_contact_person));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.products));
				this.appointFragment();
			}

			private void appointFragment() {
//				android.support.v4.app.Fragment newFragment = new JDIMerchandisingCheckFragmentAdd();
//
//				// Create new transaction
//				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//				transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//				// Replace whatever is in the fragment_container view with this
//				// fragment,
//				// and add the transaction to the back stack
//				transaction.replace(R.id.activity_fragment, newFragment);
//				transaction.addToBackStack(null);
//
//				// Commit the transaction
//				transaction.commit();
			}

			private void clearColorFilter(View findViewById) {
				Drawable d = findViewById.getBackground();
				findViewById.invalidateDrawable(d);
				d.clearColorFilter();
			}
		});

		((Button) this.myFragmentView.findViewById(R.id.competitor_stock_check)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_merchandising_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.marketing_intel));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.project_requirements));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.diy_supermarket_photos));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.customer_contact_person));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.products));
				this.appointFragment();
			}

			private void appointFragment() {
//				android.support.v4.app.Fragment newFragment = new CompetitorStockCheckFragmentAdd();
//				// Create new transaction
//				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//				transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//				// Replace whatever is in the fragment_container view with this
//				// fragment,
//				// and add the transaction to the back stack
//				transaction.replace(R.id.activity_fragment, newFragment);
//				transaction.addToBackStack(null);
//
//				// Commit the transaction
//				transaction.commit();
			}

			private void clearColorFilter(View findViewById) {
				Drawable d = findViewById.getBackground();
				findViewById.invalidateDrawable(d);
				d.clearColorFilter();
			}
		});

		((Button) this.myFragmentView.findViewById(R.id.marketing_intel)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_merchandising_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.competitor_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.project_requirements));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.diy_supermarket_photos));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.customer_contact_person));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.products));
				this.appointFragment();
			}

			private void appointFragment() {
//				android.support.v4.app.Fragment newFragment = new MarketingIntelFragmentAdd();
//
//				// Create new transaction
//				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//				transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//				// Replace whatever is in the fragment_container view with this
//				// fragment,
//				// and add the transaction to the back stack
//				transaction.replace(R.id.activity_fragment, newFragment);
//				transaction.addToBackStack(null);
//
//				// Commit the transaction
//				transaction.commit();
			}

			private void clearColorFilter(View findViewById) {
				Drawable d = findViewById.getBackground();
				findViewById.invalidateDrawable(d);
				d.clearColorFilter();
			}
		});

		((Button) this.myFragmentView.findViewById(R.id.project_requirements)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_merchandising_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.competitor_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.marketing_intel));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.diy_supermarket_photos));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.customer_contact_person));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.products));
				this.appointFragment();
			}

			private void appointFragment() {
//				android.support.v4.app.Fragment newFragment = new ProjectRequirementsFragment();
//
//				// Create new transaction
//				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//				transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//				// Replace whatever is in the fragment_container view with this
//				// fragment,
//				// and add the transaction to the back stack
//				transaction.replace(R.id.activity_fragment, newFragment);
//				transaction.addToBackStack(null);
//
//				// Commit the transaction
//				transaction.commit();
			}

			private void clearColorFilter(View findViewById) {
				Drawable d = findViewById.getBackground();
				findViewById.invalidateDrawable(d);
				d.clearColorFilter();
			}
		});

		((Button) this.myFragmentView.findViewById(R.id.diy_supermarket_photos)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_merchandising_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.competitor_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.project_requirements));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.marketing_intel));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.customer_contact_person));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.products));
				this.appointFragment();
			}

			private void appointFragment() {
//				android.support.v4.app.Fragment newFragment = new DIYSupermarketPhotosFragment();
//
//				// Create new transaction
//				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//				transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//				// Replace whatever is in the fragment_container view with this
//				// fragment,
//				// and add the transaction to the back stack
//				transaction.replace(R.id.activity_fragment, newFragment);
//				transaction.addToBackStack(null);
//
//				// Commit the transaction
//				transaction.commit();
			}

			private void clearColorFilter(View findViewById) {
				Drawable d = findViewById.getBackground();
				findViewById.invalidateDrawable(d);
				d.clearColorFilter();
			}
		});

		((Button) this.myFragmentView.findViewById(R.id.customer_contact_person)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_merchandising_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.competitor_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.project_requirements));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.marketing_intel));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.diy_supermarket_photos));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.products));
				this.appointFragment();
			}

			private void clearColorFilter(View findViewById) {
				Drawable d = findViewById.getBackground();
				findViewById.invalidateDrawable(d);
				d.clearColorFilter();
			}

			private void appointFragment() {
//				android.support.v4.app.Fragment newFragment = new CustomerContactPersonFragment();
//
//				// Create new transaction
//				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//				transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//				// Replace whatever is in the fragment_container view with this
//				// fragment,
//				// and add the transaction to the back stack
//				transaction.replace(R.id.activity_fragment, newFragment);
//				transaction.addToBackStack(null);
//
//				// Commit the transaction
//				transaction.commit();

			}
		});

		((Button) this.myFragmentView.findViewById(R.id.products)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				v.getBackground().setColorFilter(new LightingColorFilter(0x0033FF, 0x0066FF));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.jdi_merchandising_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.competitor_stock_check));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.project_requirements));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.marketing_intel));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.customer_contact_person));
				this.clearColorFilter(MoreActivityInformationFragment.this.myFragmentView.findViewById(R.id.diy_supermarket_photos));
				this.appointFragment();
			}

			private void appointFragment() {
//				android.support.v4.app.Fragment newFragment = new ProductsFragment();
//
//				// Create new transaction
//				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//				transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
//				// Replace whatever is in the fragment_container view with this
//				// fragment,
//				// and add the transaction to the back stack
//				transaction.replace(R.id.activity_fragment, newFragment);
//				transaction.addToBackStack(null);
//
//				// Commit the transaction
//				transaction.commit();
			}

			private void clearColorFilter(View findViewById) {
				Drawable d = findViewById.getBackground();
				findViewById.invalidateDrawable(d);
				d.clearColorFilter();
			}
		});

		return this.myFragmentView;
	}
}
