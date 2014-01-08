package ayrat.salavatovich.gmail.com.day_80.simplecursoradapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {

	private static final String DB_NAME = "mydb";
	private static final int DB_VERSION = 1;
	private static final String DB_TABLE = "my_table";

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_IMAGE_VIEW = "image";
	public static final String COLUMN_TEXT_VIEW = "text";
	public static final String format = "Sometext %d";

	private final Context context;

	private DBHelper mDBHelper;
	private SQLiteDatabase mDB;

	public DB(Context context) {
		this.context = context;
	}

	public void openDB() {
		mDBHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
		mDB = mDBHelper.getWritableDatabase();
	}

	public void closeDB() {
		if (mDBHelper != null)
			mDBHelper.close();
	}

	public Cursor selectAll() {
		return mDB.query(DB_TABLE, null, null, null, null, null, null);
	}

	public long insert(String text, int imageRes) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_TEXT_VIEW, text);
		values.put(COLUMN_IMAGE_VIEW, imageRes);

		return mDB.insert(DB_TABLE, null, values);
	}

	public void update(long id, String text, int imageRes) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_TEXT_VIEW, text);
		values.put(COLUMN_IMAGE_VIEW, imageRes);

		mDB.update(DB_TABLE, values, COLUMN_ID + " = " + id, null);
	}

	public void delete(long id) {
		mDB.delete(DB_TABLE, COLUMN_ID + " = " + id, null);
	}

	private class DBHelper extends SQLiteOpenHelper {
		public DBHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + DB_TABLE + "(" + COLUMN_ID
					+ " integer primary key autoincrement, "
					+ COLUMN_IMAGE_VIEW + " integer, " + COLUMN_TEXT_VIEW
					+ " text);");

			ContentValues values = new ContentValues();
			for (int i = 1; i < 5; i++) {
				values.put(COLUMN_IMAGE_VIEW, R.drawable.ic_launcher);
				values.put(COLUMN_TEXT_VIEW, String.format(format, i));

				db.insert(DB_TABLE, null, values);
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}
}
