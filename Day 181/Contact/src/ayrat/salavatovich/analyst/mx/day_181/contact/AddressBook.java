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
	public static final String ROW_ID = "row_id"; // ���� ��� �������� � ������
													// ����������
	private ListView contactListView; // ���������� � ListActivity �������
										// ListView
	private CursorAdapter contactAdapter; // ������� ��� ListView

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		contactListView = getListView(); // ������ � ����������� ListView
		contactListView.setOnItemClickListener(viewContactListener);

		// ����������� ����� ������� �������� �� TextView � �������� ListView
		String[] from = new String[] { "name" };
		int[] to = new int[] { R.id.contactTextView };
		contactAdapter = new SimpleCursorAdapter(AddressBook.this,
				R.layout.contact_list_item, null, from, to);
		setListAdapter(contactAdapter);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// ������� ����� ������ GetContactsTask � �������� ���
		new GetContactsTask().execute((Object[]) null);
	}

	@Override
	protected void onStop() {
		Cursor cursor = contactAdapter.getCursor(); // ������ � �������� Cursor

		if (cursor != null)
			cursor.deactivate(); // ���������������

		contactAdapter.changeCursor(null); // � �������� ��� Cursor
		super.onStop();
	}

	// ���������� ������� � ���� ������ �� ��������� ������ GUI
	private class GetContactsTask extends AsyncTask<Object, Object, Cursor> {
		DatabaseConnector databaseConnector = new DatabaseConnector(
				AddressBook.this);

		// ���������� ������� � ���� ������
		@Override
		protected Cursor doInBackground(Object... params) {
			databaseConnector.open();

			// ������ � �������, ������� ����� ���� ���������
			return databaseConnector.getAllContacts();
		}

		// ������������� Cursor, ������������� ������� doInBackground
		@Override
		protected void onPostExecute(Cursor result) {
			contactAdapter.changeCursor(result); // ��������� Cursor ��������
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

	// ��������� ��������, ���������� �� ���� ����������
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// �������� ������ ������� Intent ��� ������� ������
		// AddEditContact ������ Activity
		Intent addNewContact = new Intent(AddressBook.this,
				AddEditContact.class);
		startActivity(addNewContact); // ��������� AddEditContact
		return super.onOptionsItemSelected(item);
	}

	// ��������� �������, ������� �������� �� ����� ����� �������� � ListView
	OnItemClickListener viewContactListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// �������� Intent ��� ������� ViewContact Activity
			Intent viewContact = new Intent(AddressBook.this, ViewContact.class);

			// �������� ID ������ ���������� �������� � ��������
			// ���������� ������� Intent
			viewContact.putExtra(ROW_ID, arg3);
			startActivity(viewContact); // start the ViewContact Activity
		}
	};
}
