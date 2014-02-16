package ayrat.salavatovich.gmail.com.day_121.contentproviderclient;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final Uri URI = Uri
			.parse("content://ayrat.salavatovich.gmail.com.day_121.contentprovider.providers.AdressBook/contacts");
	private final Uri ERROR_URI = Uri
			.parse("content://ayrat.salavatovich.gmail.com.day_121.contentprovider.providers.AdressBook/phones");
	private final String CONTACT_NAME = "name";
	private final String CONTACT_EMAIL = "email";
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);

		Cursor cursor = managedQuery(URI, null, null, null, null);

		int[] to = { android.R.id.text1, android.R.id.text2 };
		String[] from = { CONTACT_NAME, CONTACT_EMAIL };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, cursor, from, to);

		ListView listViewContacts = (ListView) findViewById(R.id.listViewContacts);
		listViewContacts.setAdapter(adapter);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonInsert:

			outputText("insert, result Uri : " + insert().toString());

			break;

		case R.id.buttonUpdate:

			outputText("update, count = " + update());

			break;

		case R.id.buttonDelete:

			outputText("delete, count = " + delete());

			break;

		case R.id.buttonError:

			error();

			break;

		default:
			break;
		}
	}

	/**
	 * Добавления записей в провайдер
	 */
	private Uri insert() {
		ContentValues values = new ContentValues();
		values.put(CONTACT_NAME, MyRandom.randomName(10));
		values.put(CONTACT_EMAIL, MyRandom.randomEmail());

		return getContentResolver().insert(URI, values);
	}

	/**
	 * Обновляем запись в провайдере
	 */
	private int update() {
		ContentValues values = new ContentValues();
		values.put(CONTACT_NAME, MyRandom.randomName(10));
		values.put(CONTACT_EMAIL, MyRandom.randomEmail());

		Uri uri = ContentUris.withAppendedId(URI, 2);

		return getContentResolver().update(uri, values, null, null);
	}

	/**
	 * Удаляем запись в провайдере
	 */
	private int delete() {
		Uri uri = ContentUris.withAppendedId(URI, 3);
		return getContentResolver().delete(uri, null, null);
	}

	/**
	 * Ловим исключение по Uri, который не знает провайдер
	 */
	private void error() {
		try {
			getContentResolver().query(ERROR_URI, null, null, null, null);
		} catch (Exception e) {
			outputText("Error: " + e.getClass() + ", " + e.getMessage());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void outputText(String text) {
		textView.setText(text);
	}

}
