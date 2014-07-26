package co.nextix.jardine.activities.add.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityTypeRecord;

public class AddActivityFullBrandActivationFragment extends Fragment {
	private ArrayAdapter<ActivityTypeRecord> endUserActivityTypesAdapter = null;
	private View rootView = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.add_activity_full_brand, container, false);
		List<ActivityTypeRecord> activityTypeList = JardineApp.DB.getActivityType().getAllRecords();

		// ArrayAdapter for spinners
		this.endUserActivityTypesAdapter = new ArrayAdapter<ActivityTypeRecord>(JardineApp.context,
				R.layout.add_activity_multi_select_listview, activityTypeList);

		((Spinner) this.rootView.findViewById(R.id.end_user_activity_types)).setAdapter(this.endUserActivityTypesAdapter);
		return this.rootView;
	}
}
