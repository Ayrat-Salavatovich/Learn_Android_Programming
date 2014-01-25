package ayrat.salavatovich.gmail.com.day_74.alertdialogitems;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final int ID_DIALOG_ITEMS = 0;
	private final int ID_DIALOG_ADAPTER = 1;
	private final int ID_DIALOG_CURSOR = 2;

	private DB db;
	private Cursor cursor;
	private Context ctx;

	private int count;
	private String data[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		data = getResources().getStringArray(R.array.beaches_of_portugal);

		ctx = this;
		db = new DB(ctx, data);
		db.open();
		cursor = db.selectAll();
	}

	public void onClick(View v) {
		updateCount();

		switch (v.getId()) {
		case R.id.buttonItems:
			showDialog(ID_DIALOG_ITEMS);

			break;

		case R.id.buttonAdapter:
			showDialog(ID_DIALOG_ADAPTER);

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
			builder.setItems(data, mClickListener);

			return builder.create();
		case ID_DIALOG_ADAPTER:
			builder.setTitle(R.string.adapter);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx,
					android.R.layout.select_dialog_item, data);
			builder.setAdapter(adapter, mClickListener);

			return builder.create();
		case ID_DIALOG_CURSOR:
			builder.setTitle(R.string.cursor);
			builder.setCursor(cursor, mClickListener, DB.COLUMN_VALUE);

			return builder.create();

		default:
			return super.onCreateDialog(id);
		}
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		AlertDialog alertDialog = (AlertDialog) dialog;
		ListAdapter listAdapter = alertDialog.getListView().getAdapter();

		switch (id) {
		case ID_DIALOG_ITEMS:
		case ID_DIALOG_ADAPTER:
			if (listAdapter instanceof BaseAdapter)
				((BaseAdapter) listAdapter).notifyDataSetChanged();

			break;

		default:
			super.onPrepareDialog(id, dialog);
		}
	}

	private void updateCount() {
		count++;
		data[data.length - 1] = String.valueOf(count);
		db.updateValue(data.length - 1, String.valueOf(count));
		cursor.requery();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public String getBeache(int item) {
		return data[item];
	}

	private OnClickListener mClickListener = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			Toast.makeText(ctx, getBeache(which), Toast.LENGTH_SHORT).show();
		}
	};

}
