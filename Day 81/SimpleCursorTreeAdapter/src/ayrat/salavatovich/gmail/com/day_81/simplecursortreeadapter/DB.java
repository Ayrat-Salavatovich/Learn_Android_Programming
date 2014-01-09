package ayrat.salavatovich.gmail.com.day_81.simplecursortreeadapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
	private static final String DB_NAME = "myDB";
	private static final int DB_VERSION = 1;
	private static final String COMPANY_TABLE = "companies";
	private static final String PHONE_TABLE = "phones";

	public static final String COMPANY_COLUMN_ID = "_id";
	public static final String COMPANY_COLUMN_NAME = "name";
	private static final String COMPANY_TABLE_CREATE = "create table "
			+ COMPANY_TABLE + "(" + COMPANY_COLUMN_ID
			+ " integer primary key autoincrement, " + COMPANY_COLUMN_NAME
			+ " text" + ");";

	public static final String PHONE_COLUMN_ID = "_id";
	public static final String PHONE_COLUMN_NAME = "name";
	public static final String PHONE_COLUMN_COMPANY_ID_FK = "company_id";
	private static final String PHONE_TABLE_CREATE = "create table "
			+ PHONE_TABLE + "(" + PHONE_COLUMN_ID
			+ " integer primary key autoincrement, " + PHONE_COLUMN_NAME
			+ " text, " + PHONE_COLUMN_COMPANY_ID_FK + " integer" + ");";

	private final Context ctx;
	private DBHelper dbHelper;
	private SQLiteDatabase db;

	public DB(Context ctx) {
		this.ctx = ctx;
	}

	public void openDB() {
		dbHelper = new DBHelper(ctx, DB_NAME, null, DB_VERSION);
		db = dbHelper.getWritableDatabase();
	}

	public void closeDB() {
		if (dbHelper != null)
			dbHelper.close();
	}

	public Cursor selectCompanies() {
		return db.query(COMPANY_TABLE, null, null, null, null, null, null);
	}

	public Cursor selectPhones(long companyId) {
		return db.query(PHONE_TABLE, null, PHONE_COLUMN_COMPANY_ID_FK + " = "
				+ companyId, null, null, null, null);
	}

	private class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			ContentValues values = new ContentValues();

			Map<String, List<String>> collection = newCollection();

			db.execSQL(COMPANY_TABLE_CREATE);
			db.execSQL(PHONE_TABLE_CREATE);
			for (String company : collection.keySet()) {
				db.beginTransaction();
				try {
					values.clear();
					values.put(COMPANY_COLUMN_NAME, company);

					long id = db.insert(COMPANY_TABLE, null, values);

					for (String phone : collection.get(company)) {
						values.clear();
						values.put(PHONE_COLUMN_COMPANY_ID_FK, id);
						values.put(PHONE_COLUMN_NAME, phone);

						db.insert(PHONE_TABLE, null, values);
					}
					db.setTransactionSuccessful();
				} finally {
					db.endTransaction();
				}
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}

		private void cleanupTables() {
			db.delete(PHONE_TABLE, null, null);
			db.delete(COMPANY_TABLE, null, null);
		}

		private Map<String, List<String>> newCollection() {
			Map<String, List<String>> collection = new HashMap<String, List<String>>();
			collection.put("Sony", Arrays.asList("Sony Xperia Z Ultra",
					"Xperia E Dual", "Xperia Z", "Xperia ZL", "Xperia L",
					"Xperia SP"));
			collection.put("HTC", Arrays.asList("One", "One max"));
			collection.put("Samsung", Arrays.asList("Galaxy S3 16 GB",
					"Galaxy S3 Mini i8190", "Galaxy S DUOS S7562",
					"Galaxy S2 Plus i9105P", "Galaxy Xcover 2 S7710",
					"Galaxy Young S6310N", "Galaxy S4 16GB LTE i9505",
					"Galaxy Tab 3 10.1 16GB LTE P5220",
					"Galaxy Young DUOS S6312", "Galaxy Note 8.0 3G N5100",
					"Galaxy Tab 3 7.0 8GB 3G T2110", "Galaxy Mega i9205",
					"Galaxy S4 mini i9195", "Galaxy Tab 3 10.1 16GB 3G P5200",
					"Galaxy S4 Active i9295",
					"Galaxy Note 10.1 2014 Edition LTE P", "Galaxy Gear V700"));
			collection.put("LG", Arrays.asList("E975 Optimus G",
					"E460 Optimus L5 II", "E430 Optimus L3 II",
					"P710 Optimus L7 II", "P875 Optimus F5", "Nexus 5 16GB",
					"G2 32GB"));
			collection.put("Caterpillar", Arrays.asList("B25"));
			collection.put("Nokia", Arrays.asList("Lumia 520", "Lumia 920",
					"Lumia 925", "Lumia 720", "Lumia 1520"));
			collection.put("Apple", Arrays.asList("iPhone 5 64GB"));
			collection.put("Motorola", Arrays.asList("RAZR i", "RAZR HD",
					"Moto G 8GB", "Moto G 16GB"));
			collection.put("Huawei", Arrays.asList("Ascend G510",
					"Ascend Y300", "Ascend P2", "Ascend Mate"));
			collection.put("Blackberry", Arrays.asList("Z10", "Q10", "Z30"));

			return collection;
		}
	}
}
