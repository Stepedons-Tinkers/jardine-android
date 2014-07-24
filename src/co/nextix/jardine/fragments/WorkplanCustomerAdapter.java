package co.nextix.jardine.fragments;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.WorkplanRecord;

public class WorkplanCustomerAdapter extends ArrayAdapter<WorkplanRecord> {

	private Context context;
	private View viewN, viewDD;

	public WorkplanCustomerAdapter(Context context, int resource,
			List<WorkplanRecord> objects) {
		super(context, resource, objects);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewN = convertView;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		viewN = inflater.inflate(R.layout.workplan_spinner_row, parent, false);
		TextView txtView = (TextView) viewN;
		txtView.setText(this.getItem(position).getCrm());

		return viewN;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		viewDD = convertView;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();

		viewDD = inflater.inflate(R.layout.workplan_spinner_row, parent, false);
		TextView txtView = (TextView) viewDD;
		txtView.setText(this.getItem(position).getCrm());
		txtView.setTextSize(context.getResources().getDimension(
				R.dimen.text_xxxlarge));
		txtView.setTextColor(context.getResources().getColor(R.color.white));
		txtView.setBackgroundColor(Color.DKGRAY);
		return viewDD;
	}

}