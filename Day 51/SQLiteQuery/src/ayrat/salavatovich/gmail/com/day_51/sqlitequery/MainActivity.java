package ayrat.salavatovich.gmail.com.day_51.sqlitequery;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

	public static final String RECORDS = "ayrat.salavatovich.gmail.com.day_51.sqlitequery.RECORDS";
	String name[] = { "Португалия", "США", "Бразилия", "Россия", "Япония",
			"Германия", "Египет", "Италия", "Франция", "Канада" };
	double people[] = { 10.53, 313.9, 198.7, 143.5, 127.6, 81.89, 80.72, 60.92,
			65.7, 34.88 };
	String region[] = { "Европа", "Америка", "Америка", "Европа", "Азия",
			"Европа", "Африка", "Европа", "Европа", "Америка" };
	private EditText editTextFunc, editTextPeople, editTextHaving;
	private RadioGroup radioGroupSort;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editTextFunc = (EditText) findViewById(R.id.editTextFunc);
		editTextPeople = (EditText) findViewById(R.id.editTextPeople);
		editTextHaving = (EditText) findViewById(R.id.editTextHaving);
		radioGroupSort = (RadioGroup) findViewById(R.id.radioGroupSort);

		dbHelper = new DBHelper(this, null);
		db = dbHelper.getWritableDatabase();

		if (countRecords() == 0)
			fillEmptyDB();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void selectAll(View v) {
		Log.append("--- Все записи ---");
		Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null,
				null, null);
		logRecords(cursor);
	}

	public void select(View v) {
		Log.append("--- Население больше "
				+ editTextPeople.getText().toString() + " ---");
		String selection = DBHelper.COLUMN_PEOPLE + " > ?";
		String[] selectionArgs = new String[] { editTextPeople.getText()
				.toString() };
		Cursor cursor = db.query(DBHelper.TABLE_NAME, null, selection,
				selectionArgs, null, null, null);
		logRecords(cursor);
	}

	public void function(View v) {
		Log.append("--- Функция " + editTextFunc.getText().toString() + " ---");
		String[] columns = new String[] { editTextFunc.getText().toString() };
		Cursor cursor = db.query(DBHelper.TABLE_NAME, columns, null, null,
				null, null, null);
		logRecords(cursor);
	}

	public void group(View v) {
		Log.append("--- Население по региону ---");
		String[] columns = new String[] { DBHelper.COLUMN_REGION,
				"sum(" + DBHelper.COLUMN_PEOPLE + ") as people" };
		String groupBy = DBHelper.COLUMN_REGION;
		Cursor cursor = db.query(DBHelper.TABLE_NAME, columns, null, null,
				groupBy, null, null);
		logRecords(cursor);
	}

	public void having(View v) {
		Log.append("--- Регионы с населением больше "
				+ editTextHaving.getText().toString() + " ---");
		String[] columns = new String[] { DBHelper.COLUMN_REGION,
				"sum(" + DBHelper.COLUMN_PEOPLE + ") as people" };
		String groupBy = DBHelper.COLUMN_REGION;
		String having = "sum(" + DBHelper.COLUMN_PEOPLE + ") > "
				+ editTextHaving.getText().toString();
		Cursor cursor = db.query(DBHelper.TABLE_NAME, columns, null, null,
				groupBy, having, null);
		logRecords(cursor);
	}

	public void sort(View v) {
		String orderBy = null;

		switch (radioGroupSort.getCheckedRadioButtonId()) {
		// наименование
		case (R.id.radioButtonName):
			Log.append("--- Сортировка по наименованию ---");
			orderBy = DBHelper.COLUMN_NAME;

			break;
		// население
		case (R.id.radioButtonPeople):
			Log.append("--- Сортировка по населению ---");
			orderBy = DBHelper.COLUMN_PEOPLE;

			break;
		// регион
		case (R.id.radioButtonRegion):
			Log.append("--- Сортировка по региону ---");
			orderBy = DBHelper.COLUMN_REGION;

			break;
		}

		Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null,
				null, orderBy);
		logRecords(cursor);
	}

	private int countRecords() {
		Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null,
				null, null);
		int countRecords = cursor.getCount();
		cursor.close();

		return countRecords;
	}

	private void fillEmptyDB() {
		ContentValues values = new ContentValues();
		for (int i = 0; i < name.length; i++) {
			values.put(DBHelper.COLUMN_NAME, name[i]);
			values.put(DBHelper.COLUMN_PEOPLE, people[i]);
			values.put(DBHelper.COLUMN_REGION, region[i]);

			Log.append("id = " + db.insert(DBHelper.TABLE_NAME, null, values));
		}
	}

	private void logRecords(Cursor cursor) {
		if (cursor == null) {
			Log.append("Cursor is null");
		} else if (cursor.moveToFirst()) {
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
		cursor.close();
		Log.show(this);
		Log.clear();
	}

	private DBHelper dbHelper;
	private SQLiteDatabase db;

}
