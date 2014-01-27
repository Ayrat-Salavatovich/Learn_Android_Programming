package ayrat.salavatovich.gmail.com.day_74.alertdialogcustom;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	final int ID_DIALOG = 0;
	private TextView textViewCountItems;
	private LinearLayout viewDialogLinearLayout;
	private ArrayList<TextView> items;
	private Context context;

	private enum Action {
		ADD, REMOVE
	};

	private Action action;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = this;
		items = new ArrayList<TextView>();
	}

	public void addItem(View v) {
		if (v.getId() == R.id.buttonAdd) {
			action = Action.ADD;
			showDialog(ID_DIALOG);
		}
	}

	public void removeItem(View v) {
		if (v.getId() == R.id.buttonRemove) {
			action = Action.REMOVE;
			showDialog(ID_DIALOG);
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ID_DIALOG:
			AlertDialog.Builder dialog = new AlertDialog.Builder(context);
			dialog.setTitle(R.string.title_dialog);
			viewDialogLinearLayout = (LinearLayout) getLayoutInflater()
					.inflate(R.layout.dialog, null);
			dialog.setView(viewDialogLinearLayout);
			textViewCountItems = (TextView) viewDialogLinearLayout
					.findViewById(R.id.textViewCountItems);

			return dialog.create();

		default:
			return super.onCreateDialog(id);
		}
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case ID_DIALOG:
			TextView textViewTime = (TextView) dialog.getWindow().findViewById(
					R.id.textViewTime);
			textViewTime.setText(getString(R.string.time,
					Calendar.getInstance()));

			if (action == Action.ADD) {
				TextView newItem = new TextView(context);
				viewDialogLinearLayout.addView(newItem, new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				newItem.setText(getString(R.string.name_item, countItems() + 1));
				items.add(newItem);
			} else if (action == Action.REMOVE && countItems() > 0) {
				TextView item = items.get(countItems() - 1);
				viewDialogLinearLayout.removeView(item);
				items.remove(item);
			}

			textViewCountItems.setText(getString(R.string.count_items,
					countItems()));

			break;

		default:
			super.onPrepareDialog(id, dialog);
		}
	}

	private int countItems() {
		return items.size();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
