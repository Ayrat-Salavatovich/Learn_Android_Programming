package ayrat.salavatovich.gmail.com.day_139.todo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoProvider {

	private static final String DB_NAME = "TASKS";
	private static final String TABLE_NAME = "tasks";
	private static final String ROW_PK = "id";
	private static final String ROW_TITLE = "title";
	private static final int DB_VERSION = 1;
	private static final String CREATE_TABLE_QUERY = "CREATE TABLE "
			+ TABLE_NAME + " (" + ROW_PK
			+ " integer primary key autoincrement, " + ROW_TITLE
			+ " text not null);";

	private SQLiteDatabase storage;
	private SQLiteOpenHelper helper;

	public ToDoProvider(final Context ctx) {
		helper = new SQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion,
					int newVersion) {
				db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
				this.onCreate(db);
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				db.execSQL(CREATE_TABLE_QUERY);
			}
		};

		storage = helper.getWritableDatabase();
	}

	public synchronized void addTask(final String title) {
		ContentValues data = new ContentValues();
		data.put(ROW_TITLE, title);
		storage.insert(TABLE_NAME, null, data);
	}

	public synchronized void deleteAll() {
		storage.delete(TABLE_NAME, null, null);
	}

	public synchronized void deleteTask(final long id) {
		storage.delete(TABLE_NAME, ROW_PK + "=" + id, null);
	}

	public synchronized void deleteTask(final String title) {
		storage.delete(TABLE_NAME, ROW_TITLE + "='" + title + "'", null);
	}

	public synchronized List<String> findAll() {
		List<String> tasks = new ArrayList<String>();
		Cursor cursor = storage.query(TABLE_NAME, new String[] { ROW_TITLE },
				null, null, null, null, null);
		if (cursor == null) {
			return null;
		} else if (!cursor.moveToFirst()) {
			cursor.close();
			return tasks;
		}

		while (cursor.isAfterLast() == false) {
			tasks.add(cursor.getString(0));
			cursor.moveToNext();
		}
		cursor.close();
		
		return tasks;
	}

}
