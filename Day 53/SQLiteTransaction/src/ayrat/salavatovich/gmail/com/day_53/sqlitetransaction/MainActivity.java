package ayrat.salavatovich.gmail.com.day_53.sqlitetransaction;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editTextRecord = (EditText) findViewById(R.id.editTextRecord);
		textViewOutput = (TextView) findViewById(R.id.textViewOutput);

		dbHelper = new DBHelper(this, null);
		db = dbHelper.getWritableDatabase();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void clearRecordField(View v) {
		editTextRecord.setText("");
	}

	public void deleteAllRecords(View v) {
		Log.append("Delete all from table " + DBHelper.TABLE_NAME);
		db.beginTransaction();
		try {
			db.delete(DBHelper.TABLE_NAME, null, null);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
		outputLog();
	}

	public void selectAllRecords(View v) {
		Log.append("Read table " + DBHelper.TABLE_NAME);
		Cursor cursor = null;
		db.beginTransaction();
		try {
			cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null,
					null, null);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		logRecords(cursor);
		cursor.close();
		outputLog();
	}

	public void insertRecord(View v) {
		Log.append("Insert in table " + DBHelper.TABLE_NAME + " value = "
				+ getValue());
		ContentValues values = new ContentValues();
		values.put(DBHelper.COLUMN_VALUE, getValue());
		db.beginTransaction();
		try {
			db.insert(DBHelper.TABLE_NAME, null, values);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
		outputLog();
	}

	private String getValue() {
		return editTextRecord.getText().toString();
	}

	private void outputLog() {
		textViewOutput.setText(Log.trace());
		Log.clear();
	}

	private void logRecords(Cursor cursor) {
		if (cursor == null) {
			Log.append("Cursor is null");
		} else if (cursor.moveToFirst()) {
			Log.append("Records count = " + cursor.getCount());
			String str;
			do {
				str = "";
				for (String cn : cursor.getColumnNames()) {
					str = str.concat(cn + " = "
							+ cursor.getString(cursor.getColumnIndex(cn))
							+ "; ");
				}
				Log.append(str);

			} while (cursor.moveToNext());
		}
	}

	private DBHelper dbHelper;
	private SQLiteDatabase db;

	private EditText editTextRecord;
	private TextView textViewOutput;
}
