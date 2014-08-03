package co.nextix.jardine.activites.fragments.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import co.nextix.jardine.JardineApp;
import co.nextix.jardine.R;
import co.nextix.jardine.database.records.ActivityRecord;
import co.nextix.jardine.database.records.JDIproductStockCheckRecord;
import co.nextix.jardine.database.records.PicklistRecord;
import co.nextix.jardine.database.records.ProductRecord;
import co.nextix.jardine.database.records.UserRecord;
import co.nextix.jardine.database.tables.ActivityTable;
import co.nextix.jardine.database.tables.ProductTable;
import co.nextix.jardine.database.tables.UserTable;
import co.nextix.jardine.database.tables.picklists.PJDIprodStatusTable;

public class JDIproductStockListAdapter extends
		ArrayAdapter<JDIproductStockCheckRecord> {

	private final String TAG = JardineApp.TAG;
	Context context;
	List<JDIproductStockCheckRecord> list;
	ViewHolder holder;

	public JDIproductStockListAdapter(Context context, int resource,
			List<JDIproductStockCheckRecord> objects) {
		super(context, resource, objects);
		this.context = context;
		this.list = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		holder = null;
		LayoutInflater layoutInflator = LayoutInflater.from(getContext());

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflator.inflate(
					R.layout.table_row_items_six_columns, null);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder = new ViewHolder();
		holder.table_row_clickable = (LinearLayout) convertView
				.findViewById(R.id.table_row_clickable);
		holder.crm_no_txt = (TextView) convertView
				.findViewById(R.id.column_one);
		holder.activity_txt = (TextView) convertView
				.findViewById(R.id.column_two);
		holder.product_brand_txt = (TextView) convertView
				.findViewById(R.id.column_three);
		holder.stock_status_txt = (TextView) convertView
				.findViewById(R.id.column_four);
		holder.loaded_on_shelves_txt = (TextView) convertView
				.findViewById(R.id.column_five);
		holder.created_by_txt = (TextView) convertView
				.findViewById(R.id.column_six);
		holder.edit_txt = (TextView) convertView
				.findViewById(R.id.action_edit_txt);
		holder.delete_txt = (TextView) convertView
				.findViewById(R.id.action_delete_txt);

//		holder.crm_no_txt.setText(getItem(position).getCrm());
		holder.crm_no_txt.setText("Auto-on sync");

		ActivityTable act = JardineApp.DB.getActivity();
		if (act != null) {
			ActivityRecord actRec = act
					.getById(getItem(position).getActivity());
			holder.activity_txt.setText("Auto-on save");
			if (actRec != null) {
				holder.activity_txt.setText(actRec.toString());
			}
		}

		ProductTable product = JardineApp.DB.getProduct();
		if (product != null) {
			ProductRecord rec = product.getById(getItem(position)
					.getProductBrand());
			holder.product_brand_txt.setText("");
			if (rec != null) {
				holder.product_brand_txt.setText(rec.toString());
			}
		}

		PJDIprodStatusTable status = JardineApp.DB.getJDIproductStatus();
		if (status != null) {
			PicklistRecord pick = status.getById(getItem(position)
					.getStockStatus());
			holder.stock_status_txt.setText("");
			if (pick != null) {
				holder.stock_status_txt.setText(pick.toString());
			}
		}

		if (getItem(position).getLoadedOnShelves() == 0)
			holder.loaded_on_shelves_txt.setText("No");
		else
			holder.loaded_on_shelves_txt.setText("Yes");

		UserTable user = JardineApp.DB.getUser();
		if (user != null) {
			UserRecord rec = user.getById(getItem(position).getCreatedBy());
			holder.created_by_txt.setText("");
			if (rec != null) {
				holder.created_by_txt.setText(rec.toString());
			}
		}
		//
		//
		// if (holder.crm_no_txt.getText().toString().equals("")) {
		// holder.edit_txt.setText(null);
		// holder.delete_txt.setText(null);
		// holder.activity_txt.setText(null);
		// holder.product_brand_txt.setText(null);
		// holder.stock_status_txt.setText(null);
		// holder.loaded_on_shelves_txt.setText(null);
		// holder.created_by_txt.setText(null);
		// holder.edit_txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		// }else{
		// holder.table_row_clickable.setOnClickListener(new
		// OnItemClickListener(pos));
		// }

		return convertView;
	}

	@Override
	public JDIproductStockCheckRecord getItem(int position) {
		// return super.getItem(position);
		return list.get(position);
	}

	@Override
	public int getCount() {
		// return models.size();
		return list != null ? list.size() : 0;
	}

	public class ViewHolder {
		JDIproductStockCheckRecord row;
		public LinearLayout table_row_clickable;
		public TextView crm_no_txt;
		public TextView activity_txt;
		public TextView product_brand_txt;
		public TextView stock_status_txt;
		public TextView loaded_on_shelves_txt;
		public TextView created_by_txt;
		public TextView action_txt;
		public TextView edit_txt;
		public TextView delete_txt;
	}
}
