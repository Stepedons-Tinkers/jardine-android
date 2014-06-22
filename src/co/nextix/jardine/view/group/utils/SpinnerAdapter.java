package co.nextix.jardine.view.group.utils;

import java.util.List;

import co.nextix.jardine.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SpinnerAdapter extends ArrayAdapter<String> {

	private List<String> objects;
	private int layout;
	private Context context;

	public SpinnerAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		this.layout = resource;
		this.objects = objects;
		this.context = context;
	}

	private static class ViewHolder {

		String list;
		TextView txtViewer;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		ViewHolder holder = null;
		View view = convertView;
		view = inflater.inflate(this.layout, parent, false);
		holder = new ViewHolder();
		// holder.txtViewer = (TextView) view
		// .findViewById(R.id.tvWorkPlanSpinnerRow);

		holder.list = getItem(position);
		holder.txtViewer.setText(holder.list);

		return view;
	}

}
