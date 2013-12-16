package ayrat.salavatovich.gmail.com.day_54.sqliteonupgradedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "myDB";
	public static final String TABLE_POSITION = "position";
	public static final String TABLE_PEOPLE = "people";
	public static final String COLUMN_POSITION_ID = "_id";
	public static final String COLUMN_PEOPLE_ID = "_id";
	public static final String COLUMN_POSITION_NAME = "name";
	public static final String COLUMN_PEOPLE_NAME = "name";
	public static final String COLUMN_PEOPLE_POSITION_NAME = "position";
	public static final String COLUMN_POSITION_SALARY = "salary";
	public static final String COLUMN_PEOPLE_SALARY = "salary";
	public static final String COLUMN_POSITION_FOREIGN_KEY = "position_foreign_key";
	public static final int OLD_VERSION = 1;
	public static final int NEW_VERSION = 2;
	public static int current_version = OLD_VERSION;

	public DBHelper(Context context, CursorFactory factory, int version) {
		super(context, DB_NAME, factory, version);
		current_version = version;
	}

	public DBHelper(Context context, CursorFactory factory) {
		super(context, DB_NAME, factory, OLD_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		if (current_version == OLD_VERSION) {
			db.beginTransaction();
			try {
				db.execSQL("create table " + TABLE_PEOPLE + " ("
						+ COLUMN_PEOPLE_ID
						+ " integer primary key autoincrement,"
						+ COLUMN_PEOPLE_NAME + " text,"
						+ COLUMN_PEOPLE_POSITION_NAME + " text,"
						+ COLUMN_PEOPLE_SALARY + " integer);");
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
		}
		if (current_version == NEW_VERSION) {
			db.beginTransaction();
			try {
				db.execSQL("create table " + TABLE_POSITION + " ("
						+ COLUMN_POSITION_ID
						+ " integer primary key autoincrement,"
						+ COLUMN_POSITION_NAME + " text, "
						+ COLUMN_POSITION_SALARY + " integer);");
				db.execSQL("create table " + TABLE_PEOPLE + " ("
						+ COLUMN_PEOPLE_ID
						+ " integer primary key autoincrement,"
						+ COLUMN_PEOPLE_NAME + " text, "
						+ COLUMN_POSITION_FOREIGN_KEY + " integer);");
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion == OLD_VERSION && newVersion == NEW_VERSION) {
			db.beginTransaction();
			try {
				db.execSQL("create table " + TABLE_POSITION + " ("
						+ COLUMN_POSITION_ID
						+ " integer primary key autoincrement,"
						+ COLUMN_POSITION_NAME + " text, "
						+ COLUMN_POSITION_SALARY + " integer);");

				db.execSQL("insert into " + TABLE_POSITION + " ("
						+ COLUMN_POSITION_NAME + ", " + COLUMN_POSITION_SALARY
						+ ") select " + COLUMN_PEOPLE_POSITION_NAME + ", "
						+ COLUMN_PEOPLE_SALARY + " from " + TABLE_PEOPLE
						+ " group by " + COLUMN_PEOPLE_POSITION_NAME + ";");

				db.execSQL("alter table " + TABLE_PEOPLE + " add column "
						+ COLUMN_POSITION_FOREIGN_KEY + " integer;");

				String table_people_temporary = TABLE_PEOPLE + "_temporary";

				db.execSQL("create temporary table " + table_people_temporary
						+ " (" + COLUMN_PEOPLE_ID + " integer, "
						+ COLUMN_PEOPLE_NAME + " text, "
						+ COLUMN_PEOPLE_POSITION_NAME + " text, "
						+ COLUMN_POSITION_FOREIGN_KEY + " integer);");

				db.execSQL("insert into " + table_people_temporary
						+ " select PL." + COLUMN_PEOPLE_ID + ", PL."
						+ COLUMN_PEOPLE_NAME + ", PL."
						+ COLUMN_PEOPLE_POSITION_NAME + ", PS."
						+ COLUMN_POSITION_ID + " from " + TABLE_PEOPLE
						+ " as PL inner join " + TABLE_POSITION
						+ " as PS on PL." + COLUMN_PEOPLE_POSITION_NAME
						+ " = PS." + COLUMN_POSITION_NAME + ";");

				db.execSQL("drop table " + TABLE_PEOPLE + ";");

				db.execSQL("create table " + TABLE_PEOPLE + " ("
						+ COLUMN_PEOPLE_ID
						+ " integer primary key autoincrement,"
						+ COLUMN_PEOPLE_NAME + " text, "
						+ COLUMN_POSITION_FOREIGN_KEY + " integer);");

				db.execSQL("insert into people select " + COLUMN_PEOPLE_ID
						+ ", " + COLUMN_PEOPLE_NAME + ", "
						+ COLUMN_POSITION_FOREIGN_KEY + " from "
						+ table_people_temporary + ";");

				db.execSQL("drop table " + table_people_temporary + ";");

				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
		}
	}

}
