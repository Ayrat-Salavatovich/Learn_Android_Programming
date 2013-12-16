package ayrat.salavatovich.gmail.com.day_53.sqlitetransaction;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "myDB";
	public static final String TABLE_NAME = "my_table";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_VALUE = "value";
	public static final int VERSION = 1;

	public DBHelper(Context context, CursorFactory factory) {
		super(context, DB_NAME, factory, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.append("--- onCreate database ---");
		db.execSQL("create table " + TABLE_NAME + " (" + COLUMN_ID
				+ " integer primary key autoincrement," + COLUMN_VALUE
				+ " text" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
