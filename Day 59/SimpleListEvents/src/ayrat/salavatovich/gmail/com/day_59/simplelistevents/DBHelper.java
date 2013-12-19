package ayrat.salavatovich.gmail.com.day_59.simplelistevents;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "PORTUGAL";
	public static final String TABLE_NAME = "cities";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final int VERSION = 1;

	public DBHelper(Context context, CursorFactory factory) {
		super(context, DB_NAME, factory, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.beginTransaction();
		try {
			db.execSQL("create table " + TABLE_NAME + " (" + COLUMN_ID
					+ " integer primary key autoincrement," + COLUMN_NAME
					+ " text);");
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
