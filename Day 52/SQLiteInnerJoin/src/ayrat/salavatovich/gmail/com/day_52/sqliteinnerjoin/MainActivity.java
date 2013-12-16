package ayrat.salavatovich.gmail.com.day_52.sqliteinnerjoin;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	int[] position_id = { 1, 2, 3, 4 };
	String[] position_name = { "Директор", "Программер", "Бухгалтер",
			"Охранник" };
	int[] position_salary = { 15000, 13000, 10000, 8000 };

	String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
			"Костя", "Игорь" };
	int[] people_posid = { 2, 3, 2, 2, 3, 1, 2, 4 };

	EditText editTextPeopleWithSalaryMore, editTextPeopleWithSalaryLess;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editTextPeopleWithSalaryLess = (EditText) findViewById(R.id.editTextPeopleWithSalaryLess);
		editTextPeopleWithSalaryMore = (EditText) findViewById(R.id.editTextPeopleWithSalaryMore);

		dbHelper = new DBHelper(this, null);
		db = dbHelper.getWritableDatabase();

		fillDB();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void select(View v) {
		switch (v.getId()) {
		case R.id.buttonAll:
			select(null, null, null);

			break;
		case R.id.buttonAllPeople:
			select(DBHelper.TABLE_PEOPLE, null, null);

			break;
		case R.id.buttonAllPosition:
			select(DBHelper.TABLE_POSITION, null, null);

			break;
		case R.id.buttonPeopleWithSalaryLess:
			select(null, "salary < ?", editTextPeopleWithSalaryLess.getText()
					.toString());

			break;
		case R.id.buttonPeopleWithSalaryMore:
			select(null, "salary > ?", editTextPeopleWithSalaryMore.getText()
					.toString());

			break;

		default:
			break;
		}
	}

	private void select(String table, String selection, String selectionArg) {
		Cursor cursor = null;

		if (table == null) {
			if (selection == null) {
				table = DBHelper.TABLE_PEOPLE + " as PL inner join "
						+ DBHelper.TABLE_POSITION + " as PS on PL."
						+ DBHelper.COLUMN_POSITION_FOREIGN_KEY + " = PS."
						+ DBHelper.COLUMN_POSITION_ID;
				String columns[] = {
						"PL." + DBHelper.COLUMN_PEOPLE_NAME + " as Name",
						"PS." + DBHelper.COLUMN_POSITION_NAME
								+ " as Position, " + DBHelper.COLUMN_SALARY
								+ " as Salary" };
				cursor = db.query(table, columns, null, null, null, null, null);
			} else if (selection.equals("salary < ?")) {
				Log.append("--- INNER JOIN with query ---");
				table = DBHelper.TABLE_PEOPLE + " as PL inner join "
						+ DBHelper.TABLE_POSITION + " as PS on PL."
						+ DBHelper.COLUMN_POSITION_FOREIGN_KEY + " = PS."
						+ DBHelper.COLUMN_POSITION_ID;
				String columns[] = {
						"PL." + DBHelper.COLUMN_PEOPLE_NAME + " as Name",
						"PS." + DBHelper.COLUMN_POSITION_NAME
								+ " as Position, " + DBHelper.COLUMN_SALARY
								+ " as Salary" };
				String[] selectionArgs = { selectionArg };
				cursor = db.query(table, columns, selection, selectionArgs,
						null, null, null);
			} else if (selection.equals("salary > ?")) {
				Log.append("--- INNER JOIN with rawQuery ---");
				String sqlQuery = "select PL." + DBHelper.COLUMN_PEOPLE_NAME
						+ " as Name, PS." + DBHelper.COLUMN_POSITION_NAME
						+ " as Position, " + DBHelper.COLUMN_SALARY
						+ " as Salary " + "from " + DBHelper.TABLE_PEOPLE
						+ " as PL " + "inner join " + DBHelper.TABLE_POSITION
						+ " as PS " + "on PL."
						+ DBHelper.COLUMN_POSITION_FOREIGN_KEY + " = PS."
						+ DBHelper.COLUMN_POSITION_ID + " " + "where "
						+ selection;
				cursor = db.rawQuery(sqlQuery, new String[] { selectionArg });
			}
		} else {
			if (table.equals(DBHelper.TABLE_PEOPLE)) {
				Log.append("--- Table " + table + " ---");
				cursor = db.query(table, null, null, null, null, null, null);
			} else if (table.equals(DBHelper.TABLE_POSITION)) {
				Log.append("--- Table " + table + " ---");
				cursor = db.query(table, null, null, null, null, null, null);
			}
		}

		logRecords(cursor);
		cursor.close();
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
			Log.append("--- ---");
		}
		Log.trace(this, OutputActivity.OUTPUT);
		Log.clear();
	}

	private int countRecords(String tableName) {
		Cursor cursor = db.query(tableName, null, null, null, null, null, null);
		int countRecords = cursor.getCount();
		cursor.close();

		return countRecords;
	}

	private void fillDB() {
		if (countRecords(DBHelper.TABLE_PEOPLE) == 0)
			fillPeopleTable();
		if (countRecords(DBHelper.TABLE_POSITION) == 0)
			fillPositionTable();
	}

	private void fillPositionTable() {
		ContentValues values = new ContentValues();
		for (int i = 0; i < position_id.length; i++) {
			values.clear();
			values.put(DBHelper.COLUMN_POSITION_ID, position_id[i]);
			values.put(DBHelper.COLUMN_POSITION_NAME, position_name[i]);
			values.put(DBHelper.COLUMN_SALARY, position_salary[i]);
			Log.append("id = "
					+ db.insert(DBHelper.TABLE_POSITION, null, values));
		}
	}

	private void fillPeopleTable() {
		ContentValues values = new ContentValues();
		for (int i = 0; i < people_name.length; i++) {
			values.clear();
			values.put(DBHelper.COLUMN_PEOPLE_NAME, people_name[i]);
			values.put(DBHelper.COLUMN_POSITION_FOREIGN_KEY, people_posid[i]);
			Log.append("id = " + db.insert(DBHelper.TABLE_PEOPLE, null, values));
		}
	}

	private DBHelper dbHelper;
	private SQLiteDatabase db;

}
