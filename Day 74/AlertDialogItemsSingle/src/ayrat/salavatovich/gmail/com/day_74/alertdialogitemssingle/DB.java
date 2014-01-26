package ayrat.salavatovich.gmail.com.day_74.alertdialogitemssingle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
	private static final String DB_NAME = "myDB";
	private static final int DB_VERSION = 1;
	private static final String TABLE_NAME = "my_table";

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_VALUE = "text";

	private static final String DB_CREATE_SQL = "create table " + TABLE_NAME
			+ "(" + COLUMN_ID + " integer primary key, " + COLUMN_VALUE
			+ " text" + ");";

	private final Context ctx;

	private DBHelper dbHelper;
	private SQLiteDatabase db;
	private final String[] data;

	public DB(Context ctx, String[] data) {
		this.ctx = ctx;
		this.data = data;
	}

	public void open() {
		dbHelper = new DBHelper(ctx, data, DB_NAME, null, DB_VERSION);
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		if (dbHelper != null)
			dbHelper.close();
	}

	public Cursor selectAll() {
		Cursor cursor = null;

		db.beginTransaction();
		try {
			cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		return cursor;
	}

	private static class DBHelper extends SQLiteOpenHelper {

		private Context ctx;
		private final String[] data;

		public DBHelper(Context context, String[] data, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			this.ctx = context;
			this.data = data;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DB_CREATE_SQL);

			ContentValues values = new ContentValues();
			db.beginTransaction();
			try {
				for (int i = 0; i < data.length; i++) {
					values.clear();
					values.put(COLUMN_ID, i);
					values.put(COLUMN_VALUE, data[i]);
					db.insert(TABLE_NAME, null, values);
				}
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}

	}
}
