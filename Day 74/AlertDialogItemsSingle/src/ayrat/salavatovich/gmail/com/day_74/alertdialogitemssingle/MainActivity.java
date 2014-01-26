package ayrat.salavatovich.gmail.com.day_74.alertdialogitemssingle;

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
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final int ID_DIALOG_ITEMS = 0;
	private final int ID_DIALOG_ADAPTER = 1;
	private final int ID_DIALOG_CURSOR = 2;
	private final int NO_ITEMS_CHECKED = -1;

	private DB db;
	private Cursor cursor;
	private Context ctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ctx = this;
		db = new DB(ctx, getResources().getStringArray(
				R.array.beaches_of_portugal));
		db.open();
		cursor = db.selectAll();
		startManagingCursor(cursor);
	}

	public void onClick(View v) {
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
			builder.setSingleChoiceItems(R.array.beaches_of_portugal,
					NO_ITEMS_CHECKED, mClickListener);
			builder.setPositiveButton(android.R.string.ok, mClickListener);

			return builder.create();
		case ID_DIALOG_ADAPTER:
			builder.setTitle(R.string.adapter);
			ArrayAdapter<CharSequence> adapter = ArrayAdapter
					.createFromResource(ctx, R.array.beaches_of_portugal,
							android.R.layout.select_dialog_singlechoice);
			builder.setSingleChoiceItems(adapter, NO_ITEMS_CHECKED,
					mClickListener);
			builder.setPositiveButton(android.R.string.ok, mClickListener);

			return builder.create();
		case ID_DIALOG_CURSOR:
			builder.setTitle(R.string.cursor);
			builder.setSingleChoiceItems(cursor, NO_ITEMS_CHECKED,
					DB.COLUMN_VALUE, mClickListener);
			builder.setPositiveButton(android.R.string.ok, mClickListener);

			return builder.create();

		default:
			return super.onCreateDialog(id);
		}
	}

	private OnClickListener mClickListener = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			ListView listView = ((AlertDialog) dialog).getListView();
			if (which == Dialog.BUTTON_POSITIVE)
				Toast.makeText(
						ctx,
						getString(R.string.item_position,
								listView.getCheckedItemPosition()),
						Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(ctx, getString(R.string.which, which),
						Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (db != null)
			db.close();
	}

}
