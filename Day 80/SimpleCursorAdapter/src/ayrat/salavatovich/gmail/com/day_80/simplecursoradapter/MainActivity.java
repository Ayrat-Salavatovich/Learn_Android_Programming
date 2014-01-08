package ayrat.salavatovich.gmail.com.day_80.simplecursoradapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {

	private DB db;
	private Cursor cursor;
	private ListView listView;
	private SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		db = new DB(this);
		db.openDB();

		cursor = db.selectAll();
		startManagingCursor(cursor);

		String[] from = new String[] { DB.COLUMN_IMAGE_VIEW,
				DB.COLUMN_TEXT_VIEW };
		int[] to = new int[] { R.id.imageViewItem, R.id.textViewItem };

		adapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to);

		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);

		registerForContextMenu(listView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			long id = db.insert("", R.drawable.ic_launcher);
			db.update(id, String.format(DB.format, id), R.drawable.ic_launcher);
			cursor.requery();

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.remove_record:
			AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item
					.getMenuInfo();
			db.delete(acmi.id);
			cursor.requery();

			return true;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		switch (v.getId()) {
		case R.id.listView:
			getMenuInflater().inflate(R.menu.context_menu, menu);
			break;

		default:
			break;
		}
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.closeDB();
	}

}
