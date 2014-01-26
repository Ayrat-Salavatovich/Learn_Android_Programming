package ayrat.salavatovich.gmail.com.day_74.alertdialogitemsmulti;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final int ID_DIALOG_ITEMS = 0;
	private final int ID_DIALOG_CURSOR = 2;

	private DB db;
	private Cursor cursor;
	private Context ctx;

	private int count;
	private String[] data;
	private boolean[] checkedItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		data = getResources().getStringArray(R.array.beaches_of_portugal);
		checkedItems = new boolean[data.length];

		ctx = this;
		db = new DB(ctx, data);
		db.open();
		cursor = db.selectAll();

		startManagingCursor(cursor);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonItems:
			showDialog(ID_DIALOG_ITEMS);

			break;

		case R.id.buttonCursor:
			showDialog(ID_DIALOG_CURSOR);

			break;

		default:
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		switch (id) {
		case ID_DIALOG_ITEMS:
			builder.setTitle(R.string.items);
			builder.setMultiChoiceItems(data, checkedItems, mMultiClickListener);
			builder.setPositiveButton(android.R.string.ok, mClickListener);

			return builder.create();
		case ID_DIALOG_CURSOR:
			builder.setTitle(R.string.cursor);
			builder.setMultiChoiceItems(cursor, DB.COLUMN_CHECKED,
					DB.COLUMN_VALUE, mMultiClickListener);
			builder.setPositiveButton(android.R.string.ok, mClickListener);

			return builder.create();

		default:
			return super.onCreateDialog(id);
		}
	}

	private OnClickListener mClickListener = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			StringBuilder stringBuilder = new StringBuilder();
			SparseBooleanArray sparseBooleanArray = ((AlertDialog) dialog)
					.getListView().getCheckedItemPositions();

			for (int i = 0; i < sparseBooleanArray.size(); i++) {
				int key = sparseBooleanArray.keyAt(i);
				if (sparseBooleanArray.get(key))
					stringBuilder
							.append(getString(R.string.checked_items, key));

			}

			Toast.makeText(ctx, stringBuilder.toString(), Toast.LENGTH_LONG)
					.show();
		}
	};

	private OnMultiChoiceClickListener mMultiClickListener = new OnMultiChoiceClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which, boolean isChecked) {
			updateCheckedData(which, isChecked);
			Toast.makeText(ctx, getString(R.string.select, which, isChecked),
					Toast.LENGTH_SHORT).show();
		}

	};

	private void updateCheckedData(int id, boolean isChecked) {
		db.updateValue(id, isChecked);
		cursor.requery();
		checkedItems[id] = isChecked;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (db != null)
			db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
