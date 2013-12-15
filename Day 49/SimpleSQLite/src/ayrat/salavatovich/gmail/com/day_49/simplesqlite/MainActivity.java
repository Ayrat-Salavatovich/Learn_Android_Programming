package ayrat.salavatovich.gmail.com.day_49.simplesqlite;

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

		editTextName = (EditText) findViewById(R.id.editTextName);
		editTextEmail = (EditText) findViewById(R.id.editTextEmail);
		textViewOut = (TextView) findViewById(R.id.textViewOut);
		dbHelper = new DBHelper(this, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClickAdd(View v) {
		StringBuilder sb = new StringBuilder();

		ContentValues values = new ContentValues();
		values.put(DBHelper.COLUMN_NAME, getName());
		values.put(DBHelper.COLUMN_EMAIL, getEmail());

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		sb.append("--- Insert in " + DBHelper.TABLE_NAME + ": ---").append(
				System.lineSeparator());
		long rowID = db.insert(DBHelper.TABLE_NAME, null, values);
		sb.append("row inserted, ID = " + rowID).append(System.lineSeparator());

		db.close();

		outLog(sb.toString());
	}

	public void onClickRead(View v) {
		StringBuilder sb = new StringBuilder();

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null,
				null, null);

		sb.append("--- Rows in " + DBHelper.TABLE_NAME + ": ---").append(
				System.lineSeparator());
		if (cursor.moveToFirst()) {
			int idColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_ID);
			int nameColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_NAME);
			int emailColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_EMAIL);

			do {
				sb.append(
						DBHelper.COLUMN_ID + " = "
								+ cursor.getInt(idColumnIndex) + ", "
								+ DBHelper.COLUMN_NAME + " = "
								+ cursor.getString(nameColumnIndex) + ", "
								+ DBHelper.COLUMN_EMAIL + " = "
								+ cursor.getString(emailColumnIndex)).append(
						System.lineSeparator());
			} while (cursor.moveToNext());
		} else
			sb.append("0 rows").append(System.lineSeparator());

		cursor.close();
		db.close();

		outLog(sb.toString());
	}

	public void onClickClear(View v) {
		StringBuilder sb = new StringBuilder();

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int clearCount = db.delete(DBHelper.TABLE_NAME, null, null);
		sb.append("deleted rows count = " + clearCount).append(
				System.lineSeparator());

		db.close();

		outLog(sb.toString());
	}

	private String getName() {
		return editTextName.getText().toString();
	}

	private String getEmail() {
		return editTextEmail.getText().toString();
	}

	private void outLog(String text) {
		textViewOut.setText(text);
	}

	private EditText editTextName, editTextEmail;
	private TextView textViewOut;
	private DBHelper dbHelper;

}
