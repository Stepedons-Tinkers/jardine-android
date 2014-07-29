package co.nextix.jardine.utils;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import co.nextix.jardine.database.records.ActivityTypeRecord;

public class MultiSpinner extends Spinner implements OnMultiChoiceClickListener, OnCancelListener {

	private List<ActivityTypeRecord> items;
	private boolean[] selected;
	private String defaultText;
	private MultiSpinnerListener listener;

	public MultiSpinner(Context context) {
		super(context);
	}

	public MultiSpinner(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
	}

	public MultiSpinner(Context arg0, AttributeSet arg1, int arg2) {
		super(arg0, arg1, arg2);
	}

	@Override
	public void onClick(DialogInterface dialog, int which, boolean isChecked) {
		if (isChecked)
			this.selected[which] = true;
		else
			this.selected[which] = false;
	}

	@Override
	public void onCancel(DialogInterface dialog) {

		// refresh text on spinner
		String spinnerText;
		StringBuffer spinnerBuffer = new StringBuffer();

		int numberOfChecks = 0;
		int i;

		for (i = 0; i < items.size(); i++) {
			if (this.selected[i]) {
				spinnerBuffer.append(items.get(i));
				spinnerBuffer.append(", ");

			}
		}

		int hasCheck = 0;
		while (this.selected[hasCheck]) {
			hasCheck++;
		}

		// Toast.makeText(getContext(), "" + hasCheck,
		// Toast.LENGTH_SHORT).show();

		if (hasCheck > 5) {
			spinnerText = this.defaultText;
			Toast.makeText(getContext(), "Maximum of 5 only", Toast.LENGTH_SHORT).show();

		} else if (hasCheck < 1) {
			spinnerText = this.defaultText;
			Toast.makeText(getContext(), "Minimum of 1 selection must be observed", Toast.LENGTH_SHORT).show();

		} else {

			spinnerText = spinnerBuffer.toString();
			if (spinnerText.length() > 2)
				spinnerText = spinnerText.substring(0, spinnerText.length() - 2);
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
				new String[] { spinnerText });

		setAdapter(adapter);
		this.listener.onItemsSelected(this.selected);
	}

	@Override
	public boolean performClick() {
		String[] stringArray = new String[this.items.size()];

		for (int i = 0; i < this.items.size(); i++) {
			stringArray[i] = this.items.get(i).toString();
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setMultiChoiceItems(stringArray, selected, this);
		builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		builder.setOnCancelListener(this);
		builder.show();

		return true;
	}

	public void setItems(List<ActivityTypeRecord> items, String allText, MultiSpinnerListener listener) {
		this.items = items;
		this.defaultText = allText;
		this.listener = listener;

		// all selected by default
		this.selected = new boolean[items.size()];
		for (int i = 0; i < this.selected.length; i++)
			this.selected[i] = false;

		// all text on the spinner
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
				new String[] { allText });

		setAdapter(adapter);
	}

	public interface MultiSpinnerListener {
		public void onItemsSelected(boolean[] selected);
	}
}