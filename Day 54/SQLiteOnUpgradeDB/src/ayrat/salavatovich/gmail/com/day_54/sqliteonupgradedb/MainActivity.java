package ayrat.salavatovich.gmail.com.day_54.sqliteonupgradedb;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	String[] position_name = { "Директор", "Программер", "Бухгалтер",
			"Охранник" };
	int[] position_salary = { 15000, 13000, 10000, 8000 };

	String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
			"Костя", "Игорь" };
	int[] people_posid = { 1, 2, 1, 1, 2, 0, 1, 3 };
	private TextView textViewOutput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewOutput = (TextView) findViewById(R.id.textViewOutput);

		dbHelper = new DBHelper(this, null);
		db = dbHelper.getWritableDatabase();

		fillDB();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void update(View v) {
		dbHelper = new DBHelper(this, null, DBHelper.NEW_VERSION);
		db = dbHelper.getWritableDatabase();

		fillDB();
	}

	public void select(View v) {
		Cursor cursor = null;
		if (dbHelper.current_version == dbHelper.OLD_VERSION) {
			String columns[] = { DBHelper.COLUMN_PEOPLE_NAME + ", "
					+ DBHelper.COLUMN_PEOPLE_POSITION_NAME + ", "
					+ DBHelper.COLUMN_PEOPLE_SALARY };
			cursor = db.query(DBHelper.TABLE_PEOPLE, columns, null, null, null,
					null, null);
		} else if (dbHelper.current_version == dbHelper.NEW_VERSION) {
			String table = DBHelper.TABLE_PEOPLE + " as PL inner join "
					+ DBHelper.TABLE_POSITION + " as PS on PL."
					+ DBHelper.COLUMN_POSITION_FOREIGN_KEY + " = PS."
					+ DBHelper.COLUMN_POSITION_ID;
			String columns[] = {
					"PL." + DBHelper.COLUMN_PEOPLE_NAME + " as Name",
					"PS." + DBHelper.COLUMN_POSITION_NAME + " as Position, "
							+ DBHelper.COLUMN_POSITION_SALARY + " as Salary" };
			cursor = db.query(table, columns, null, null, null, null, null);
		}
		output(cursor);
		if (cursor != null)
			cursor.close();
	}

	private void fillDB() {
		if (dbHelper.current_version == dbHelper.OLD_VERSION) {
			if (countRecords(DBHelper.TABLE_PEOPLE) == 0)
				fillPeopleTable();
		} else if (dbHelper.current_version == dbHelper.NEW_VERSION) {
			if (countRecords(DBHelper.TABLE_PEOPLE) == 0)
				fillPeopleTable();
			else if (countRecords(DBHelper.TABLE_POSITION) == 0)
				fillPositionTable();
		}
	}

	private int countRecords(String tableName) {
		Cursor cursor = db.query(tableName, null, null, null, null, null, null);
		int countRecords = cursor.getCount();
		cursor.close();

		return countRecords;
	}

	private void fillPositionTable() {
		ContentValues values = new ContentValues();
		if (dbHelper.current_version == dbHelper.NEW_VERSION) {
			for (int i = 0; i < position_name.length; i++) {
				values.clear();
				values.put(DBHelper.COLUMN_POSITION_NAME, position_name[i]);
				values.put(DBHelper.COLUMN_POSITION_SALARY, position_salary[i]);
				db.insert(DBHelper.TABLE_POSITION, null, values);
			}
		}
	}

	private void fillPeopleTable() {
		ContentValues values = new ContentValues();
		if (dbHelper.current_version == dbHelper.NEW_VERSION) {
			for (int i = 0; i < people_name.length; i++) {
				values.clear();
				values.put(DBHelper.COLUMN_PEOPLE_NAME, people_name[i]);
				values.put(DBHelper.COLUMN_POSITION_FOREIGN_KEY,
						people_posid[i]);
				db.insert(DBHelper.TABLE_PEOPLE, null, values);
			}
		} else if (dbHelper.current_version == dbHelper.OLD_VERSION) {
			for (int i = 0; i < people_name.length; i++) {
				values.clear();
				values.put(DBHelper.COLUMN_PEOPLE_NAME, people_name[i]);
				values.put(DBHelper.COLUMN_PEOPLE_POSITION_NAME,
						position_name[people_posid[i]]);
				values.put(DBHelper.COLUMN_PEOPLE_SALARY,
						position_salary[people_posid[i]]);
				db.insert(DBHelper.TABLE_PEOPLE, null, values);
			}
		}
	}

	public void version(View v) {
		textViewOutput.setText("Version: " + DBHelper.current_version);
	}

	private void output(Cursor cursor) {
		StringBuilder sb = new StringBuilder();
		if (cursor == null) {
			sb.append("Cursor is null");
		} else if (cursor.moveToFirst()) {
			String str;
			do {
				str = "";
				for (String cn : cursor.getColumnNames()) {
					str = str.concat(cn + " = "
							+ cursor.getString(cursor.getColumnIndex(cn))
							+ "; ");
				}
				sb.append(str).append(System.lineSeparator());

			} while (cursor.moveToNext());
			sb.append("--- ---");
		}
		textViewOutput.setText(sb.toString());
	}

	private DBHelper dbHelper;
	private SQLiteDatabase db;

}
