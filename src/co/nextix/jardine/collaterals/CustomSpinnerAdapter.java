package co.nextix.jardine.collaterals;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import co.nextix.jardine.R;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

	private Context context;
	private View viewN, viewDD;

	public CustomSpinnerAdapter(Context context, int resource,
			List<String> objects) {
		super(context, resource, objects);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewN = convertView;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		viewN = inflater.inflate(R.layout.workplan_spinner_row, parent, false);
		TextView txtView = (TextView) viewN;
		txtView.setText(this.getItem(position));

		return viewN;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		viewDD = convertView;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();

		viewDD = inflater.inflate(R.layout.workplan_spinner_row, parent, false);
		TextView txtView = (TextView) viewDD;
		txtView.setText(this.getItem(position));
		txtView.setTextSize(context.getResources().getDimension(
				R.dimen.text_xxxlarge));
		txtView.setTextColor(context.getResources().getColor(R.color.white));
		return viewDD;
	}

}