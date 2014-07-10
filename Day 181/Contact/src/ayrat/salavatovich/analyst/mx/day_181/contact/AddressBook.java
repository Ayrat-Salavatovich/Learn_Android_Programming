package ayrat.salavatovich.analyst.mx.day_181.contact;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class AddressBook extends ListActivity {
	public static final String ROW_ID = "row_id"; // ключ для передачи в другую
													// активность
	private ListView contactListView; // встроенный в ListActivity элемент
										// ListView
	private CursorAdapter contactAdapter; // адаптер для ListView

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		contactListView = getListView(); // доступ к встроенному ListView
		contactListView.setOnItemClickListener(viewContactListener);

		// отображение имени каждого контакта на TextView в разметке ListView
		String[] from = new String[] { "name" };
		int[] to = new int[] { R.id.contactTextView };
		contactAdapter = new SimpleCursorAdapter(AddressBook.this,
				R.layout.contact_list_item, null, from, to);
		setListAdapter(contactAdapter);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// создает новый объект GetContactsTask и вызывает его
		new GetContactsTask().execute((Object[]) null);
	}

	@Override
	protected void onStop() {
		Cursor cursor = contactAdapter.getCursor(); // доступ к текущему Cursor

		if (cursor != null)
			cursor.deactivate(); // деактивирование

		contactAdapter.changeCursor(null); // у адаптера нет Cursor
		super.onStop();
	}

	// выполнение запроса к базе данных за пределами потока GUI
	private class GetContactsTask extends AsyncTask<Object, Object, Cursor> {
		DatabaseConnector databaseConnector = new DatabaseConnector(
				AddressBook.this);

		// выполнение доступа к базе данных
		@Override
		protected Cursor doInBackground(Object... params) {
			databaseConnector.open();

			// доступ к курсору, включая вызов всех контактов
			return databaseConnector.getAllContacts();
		}

		// использование Cursor, возвращенного методом doInBackground
		@Override
		protected void onPostExecute(Cursor result) {
			contactAdapter.changeCursor(result); // установка Cursor адаптера
			databaseConnector.close();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.addressbook_menu, menu);
		return true;
	}

	// обработка варианта, выбранного из меню параметров
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// создание нового объекта Intent для запуска метода
		// AddEditContact класса Activity
		Intent addNewContact = new Intent(AddressBook.this,
				AddEditContact.class);
		startActivity(addNewContact); // запускаем AddEditContact
		return super.onOptionsItemSelected(item);
	}

	// слушатель события, которое отвечает на выбор имени контакта в ListView
	OnItemClickListener viewContactListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// создание Intent для запуска ViewContact Activity
			Intent viewContact = new Intent(AddressBook.this, ViewContact.class);

			// передача ID строки выбранного контакта в качестве
			// расширения объекта Intent
			viewContact.putExtra(ROW_ID, arg3);
			startActivity(viewContact); // start the ViewContact Activity
		}
	};
}
