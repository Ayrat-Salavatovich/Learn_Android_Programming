package ayrat.salavatovich.gmail.com.day_52.sqliteinnerjoin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "myDB";
	public static final String TABLE_POSITION = "position";
	public static final String TABLE_PEOPLE = "people";
	public static final String COLUMN_POSITION_ID = "id";
	public static final String COLUMN_PEOPLE_ID = "id";
	public static final String COLUMN_POSITION_NAME = "name";
	public static final String COLUMN_PEOPLE_NAME = "name";
	public static final String COLUMN_SALARY = "salary";
	public static final String COLUMN_POSITION_FOREIGN_KEY = "position_foreign_key";
	public static final int VERSION = 1;

	public DBHelper(Context context, CursorFactory factory) {
		super(context, DB_NAME, factory, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.append("--- onCreate database ---");

		Log.append("--- create table " + TABLE_POSITION + " ---");
		db.execSQL("create table " + TABLE_POSITION + " (" + COLUMN_POSITION_ID
				+ " integer primary key," + COLUMN_POSITION_NAME + " text,"
				+ COLUMN_SALARY + " integer" + ");");

		Log.append("--- create table " + TABLE_PEOPLE + " ---");
		db.execSQL("create table " + TABLE_PEOPLE + " (" + COLUMN_PEOPLE_ID
				+ " integer primary key autoincrement," + COLUMN_PEOPLE_NAME
				+ " text," + COLUMN_POSITION_FOREIGN_KEY + " integer" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
