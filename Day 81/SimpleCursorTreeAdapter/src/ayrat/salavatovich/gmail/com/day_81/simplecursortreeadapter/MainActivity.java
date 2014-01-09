package ayrat.salavatovich.gmail.com.day_81.simplecursortreeadapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;

public class MainActivity extends Activity {

	DB db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		db = new DB(this);
		db.openDB();

		Cursor cursor = db.selectCompanies();
		startManagingCursor(cursor);

		String[] groupFrom = { DB.COMPANY_COLUMN_NAME };
		int[] groupTo = { android.R.id.text1 };

		String[] childFrom = { DB.PHONE_COLUMN_NAME };
		int[] childTo = { android.R.id.text1 };

		SimpleCursorTreeAdapter adapter = new MyAdapter(this, cursor,
				android.R.layout.simple_expandable_list_item_1, groupFrom,
				groupTo, android.R.layout.simple_list_item_1, childFrom,
				childTo);

		((ExpandableListView) findViewById(R.id.expandableListView))
				.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class MyAdapter extends SimpleCursorTreeAdapter {

		public MyAdapter(Context context, Cursor cursor, int groupLayout,
				String[] groupFrom, int[] groupTo, int childLayout,
				String[] childFrom, int[] childTo) {
			super(context, cursor, groupLayout, groupFrom, groupTo,
					childLayout, childFrom, childTo);
		}

		@Override
		protected Cursor getChildrenCursor(Cursor groupCursor) {
			int idColumn = groupCursor.getColumnIndex(DB.COMPANY_COLUMN_ID);
			return db.selectPhones(groupCursor.getInt(idColumn));
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (db != null)
			db.closeDB();
	}

}
