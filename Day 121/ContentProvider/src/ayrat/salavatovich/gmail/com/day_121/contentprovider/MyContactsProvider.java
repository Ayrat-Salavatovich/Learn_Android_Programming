package ayrat.salavatovich.gmail.com.day_121.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;

public class MyContactsProvider extends ContentProvider {

	static final String DB_NAME = "myDB";
	static final int DB_VERSION = 1;

	// Таблица
	static final String CONTACT_TABLE = "contacts";

	// Поля таблицы
	static final String CONTACT_COLUMN_ID = "_id";
	static final String CONTACT_COLUMN_NAME = "name";
	static final String CONTACT_COLUMN_EMAIL = "email";

	// Скрипт создания таблицы
	private static final String DB_CREATE = "CREATE TABLE " + CONTACT_TABLE
			+ "(" + CONTACT_COLUMN_ID + " INTEGER primary key autoincrement, "
			+ CONTACT_COLUMN_NAME + " TEXT, " + CONTACT_COLUMN_EMAIL + " TEXT"
			+ ");";

	static final String CONTENT = "content://";

	// Uri authority - провайдер
	static final String AUTHORITY = "ayrat.salavatovich.gmail.com.day_121.contentprovider.providers.AdressBook";

	// Uri path - данные от провайдера
	static final String PATH = "contacts";

	// Uri
	public static final Uri URI = Uri.parse(CONTENT + AUTHORITY + "/" + PATH);

	// Типы данных набор строк
	static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
			+ AUTHORITY + "." + PATH;

	// Типы данных одна строка
	static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
			+ AUTHORITY + "." + PATH;

	// UriMatcher
	static final int URI_CONTACTS = 1;

	// Uri с указанным ID
	static final int URI_CONTACTS_ID = 2;

	// Описание и создание UriMatcher
	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, PATH, URI_CONTACTS);
		uriMatcher.addURI(AUTHORITY, PATH + "/#", URI_CONTACTS_ID);
	}

	DBHelper dbHelper;
	SQLiteDatabase db;

	/**
	 * @param uri
	 *            Uri
	 * @param selection
	 *            условие
	 * @param selectionArgs
	 *            аргументы для условия
	 * 
	 * @return кол-во удаленных записей
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		switch (uriMatcher.match(uri)) {
		case URI_CONTACTS: // общий Uri

			break;

		case URI_CONTACTS_ID: // Uri с ID
			String id = uri.getLastPathSegment();

			if (TextUtils.isEmpty(selection))
				selection = CONTACT_COLUMN_ID + " = " + id;
			else
				selection = selection + " AND " + CONTACT_COLUMN_ID + " = "
						+ id;

			break;

		default:

			throw new IllegalArgumentException("Wrong URI: " + uri);
		}

		int cnt = 0;
		db = dbHelper.getWritableDatabase();
		db.beginTransaction();
		try {
			cnt = db.delete(CONTACT_TABLE, selection, selectionArgs);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		// уведомляем, что данные изменились
		getContext().getContentResolver().notifyChange(uri, null);

		return cnt;
	}

	/**
	 * @param uri
	 *            Uri
	 * 
	 * @return тип соответствующий типу Uri – общий или с ID.
	 */
	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case URI_CONTACTS:

			return CONTACT_CONTENT_TYPE;

		case URI_CONTACTS_ID:

			return CONTACT_CONTENT_ITEM_TYPE;

		default:

			return null;
		}
	}

	/**
	 * @param uri
	 *            Uri
	 * @param values
	 *            данные
	 * 
	 * @return Uri добавленной записи
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if (uriMatcher.match(uri) != URI_CONTACTS)
			throw new IllegalArgumentException("Wrong URI: " + uri);

		long rowID = 0;
		db = dbHelper.getWritableDatabase();
		db.beginTransaction();
		try {
			rowID = db.insert(CONTACT_TABLE, null, values);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		Uri resultUri = ContentUris.withAppendedId(URI, rowID);

		// уведомляем ContentResolver, что данные по адресу resultUri изменились
		getContext().getContentResolver().notifyChange(resultUri, null);

		return resultUri;
	}

	@Override
	public boolean onCreate() {
		dbHelper = new DBHelper(getContext());
		return true;
	}

	/**
	 * @param uri
	 *            Uri
	 * @param projection
	 *            столбцы
	 * @param selection
	 *            условие
	 * @param selectionArgs
	 *            аргументы для условия
	 * @param sortOrder
	 *            сортировка
	 * 
	 * @return возвращаем Cursor
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// проверяем Uri
		switch (uriMatcher.match(uri)) {
		case URI_CONTACTS: // общий Uri
			// если сортировка не указана, ставим свою - по имени
			if (TextUtils.isEmpty(sortOrder))
				sortOrder = CONTACT_COLUMN_NAME + " ASC";

			break;

		case URI_CONTACTS_ID: // Uri с ID
			// извлекаем ID
			String id = uri.getLastPathSegment();

			// добавляем ID к условию выборки
			if (TextUtils.isEmpty(selection))
				selection = CONTACT_COLUMN_ID + " = " + id;
			else
				selection = selection + " AND " + CONTACT_COLUMN_ID + " = "
						+ id;

			break;

		default:

			throw new IllegalArgumentException("Wrong URI: " + uri);
		}

		db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query(CONTACT_TABLE, projection, selection,
				selectionArgs, null, null, sortOrder);

		// просим ContentResolver уведомлять этот курсор об изменениях данных в
		// CONTACT_CONTENT_URI
		cursor.setNotificationUri(getContext().getContentResolver(), URI);

		return cursor;
	}

	/**
	 * @param uri
	 *            Uri
	 * @param values
	 *            данные
	 * @param selection
	 *            условие
	 * @param selectionArgs
	 *            аргументы для условия
	 * 
	 * @return кол-во обновленных записей
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		switch (uriMatcher.match(uri)) {
		case URI_CONTACTS: // общий Uri

			break;

		case URI_CONTACTS_ID: // Uri с ID
			// извлекаем ID
			String id = uri.getLastPathSegment();

			if (TextUtils.isEmpty(selection))
				selection = CONTACT_COLUMN_ID + " = " + id;
			else
				selection = selection + " AND " + CONTACT_COLUMN_ID + " = "
						+ id;

			break;

		default:

			throw new IllegalArgumentException("Wrong URI: " + uri);
		}

		int cnt = 0;
		db = dbHelper.getWritableDatabase();
		db.beginTransaction();
		try {
			cnt = db.update(CONTACT_TABLE, values, selection, selectionArgs);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		// уведомляем, что данные изменились
		getContext().getContentResolver().notifyChange(uri, null);

		return cnt;
	}

	private static class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		public void onCreate(SQLiteDatabase db) {
			db.beginTransaction();
			try {
				db.execSQL(DB_CREATE);
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

}
