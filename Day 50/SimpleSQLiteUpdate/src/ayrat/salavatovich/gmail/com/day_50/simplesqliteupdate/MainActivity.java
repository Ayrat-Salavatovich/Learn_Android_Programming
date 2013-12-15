package ayrat.salavatovich.gmail.com.day_50.simplesqliteupdate;

import java.util.Hashtable;
import java.util.Map;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements CRUD {

	private DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editTextId = (EditText) findViewById(R.id.editTextId);
		editTextName = (EditText) findViewById(R.id.editTextName);
		editTextEmail = (EditText) findViewById(R.id.editTextEmail);

		textViewOut = (TextView) findViewById(R.id.textViewOut);

		dbHelper = new DBHelper(this, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void crud(View v) {
		StringBuilder sb = new StringBuilder();

		switch (v.getId()) {
		case R.id.buttonAdd:
			sb.append(
					getString(R.string.inserted, DBHelper.COLUMN_ID, create()))
					.append(System.lineSeparator());

			break;
		case R.id.buttonRead:
			sb.append(contactsToString((Map<Long, Contact>) select())).append(
					System.lineSeparator());

			break;
		case R.id.buttonUpdate:
			sb.append(getString(R.string.updated, update())).append(
					System.lineSeparator());

			break;
		case R.id.buttonDelete:
			sb.append(getString(R.string.deleted, delete())).append(
					System.lineSeparator());

			break;
		case R.id.buttonDeleteAll:
			sb.append(getString(R.string.deleted, deleteAll())).append(
					System.lineSeparator());

			break;

		default:
			break;
		}

		log(sb.toString());
	}

	private long create() {
		return create(new Contact(null, getName(), getEmail()));
	}

	public long create(Object object) {
		Contact contact = (Contact) object;

		ContentValues values = new ContentValues();
		values.put(DBHelper.COLUMN_NAME, contact.getName());
		values.put(DBHelper.COLUMN_EMAIL, contact.getEmail());

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rowID = db.insert(DBHelper.TABLE_NAME, null, values);

		db.close();

		return rowID;
	}

	public Object select() {
		return selectAll();
	}

	private Object selectAll() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null,
				null, null);
		final Map<Long, Contact> contacts = new Hashtable<Long, Contact>();

		if (cursor.moveToFirst()) {
			int idColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_ID);
			int nameColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_NAME);
			int emailColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_EMAIL);

			do {
				contacts.put(
						cursor.getLong(idColumnIndex),
						new Contact(cursor.getLong(idColumnIndex), cursor
								.getString(nameColumnIndex), cursor
								.getString(emailColumnIndex)));
			} while (cursor.moveToNext());
		}

		cursor.close();
		db.close();

		return contacts;
	}

	private int update() {
		if (isEmpty(getId()))
			return 0;
		return update(new Contact(Long.valueOf(getId()), getName(), getEmail()));
	}

	public int update(Object object) {
		Contact contact = (Contact) object;

		ContentValues values = new ContentValues();
		values.put(DBHelper.COLUMN_NAME, contact.getName());
		values.put(DBHelper.COLUMN_EMAIL, contact.getEmail());

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int updatedCount = db.update(DBHelper.TABLE_NAME, values,
				DBHelper.COLUMN_ID + " = ?", new String[] { contact.getId()
						.toString() });
		return updatedCount;
	}

	public int delete() {
		if (isEmpty(getId()))
			return 0;
		return delete(new Contact(Long.valueOf(getId()), getName(), getEmail()));
	}

	public int delete(Object object) {
		Contact contact = (Contact) object;

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		int deletedCount = db.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_ID
				+ " = " + contact.getId().toString(), null);
		return deletedCount;
	}

	private int deleteAll() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		int deletedCount = db.delete(DBHelper.TABLE_NAME, null, null);
		return deletedCount;
	}

	private String contactsToString(Map<Long, Contact> contacts) {
		StringBuilder sb = new StringBuilder("");
		for (Contact contact : contacts.values()) {
			sb.append(
					DBHelper.COLUMN_ID + " = " + contact.getId() + ", "
							+ DBHelper.COLUMN_NAME + " = " + contact.getName()
							+ ", " + DBHelper.COLUMN_EMAIL + " = "
							+ contact.getEmail())
					.append(System.lineSeparator());
			;
		}
		return sb.toString();
	}

	private boolean isEmpty(String text) {
		if (text.length() > 0)
			return false;

		return true;
	}

	private String getId() {
		return editTextId.getText().toString();
	}

	private String getName() {
		return editTextName.getText().toString();
	}

	private String getEmail() {
		return editTextEmail.getText().toString();
	}

	private void log(String text) {
		textViewOut.setText(text);
	}

	private EditText editTextId, editTextName, editTextEmail;
	private TextView textViewOut;

}
