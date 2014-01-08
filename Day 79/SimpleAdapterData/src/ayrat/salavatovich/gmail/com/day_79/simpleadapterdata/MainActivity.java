package ayrat.salavatovich.gmail.com.day_79.simpleadapterdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private static final int DELETE_ID = 1;
	private final int DEFAULT_MIN = 1;
	private final int DEFAULT_MAX = 5;
	private final String ATTRIBUTE_NAME_TEXT_VIEW = "textView";
	private final String ATTRIBUTE_NAME_IMAGE_VIEW = "imageView";
	private ListView listView;
	private SimpleAdapter adapter;
	private List<Map<String, Object>> data;
	private Map<String, Object> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		data = new ArrayList<Map<String, Object>>();
		for (int i = DEFAULT_MIN; i <= DEFAULT_MAX; i++) {
			map = new HashMap<String, Object>();
			map.put(ATTRIBUTE_NAME_TEXT_VIEW,
					getString(R.string.template_sometext, i));
			map.put(ATTRIBUTE_NAME_IMAGE_VIEW, R.drawable.ic_launcher);

			data.add(map);
		}

		String[] from = { ATTRIBUTE_NAME_TEXT_VIEW, ATTRIBUTE_NAME_IMAGE_VIEW };
		int[] to = { R.id.textViewItem, R.id.imageViewItem };

		adapter = new SimpleAdapter(this, data, R.layout.item, from, to);

		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);

		registerForContextMenu(listView);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item
					.getMenuInfo();
			data.remove(acmi.position);
			adapter.notifyDataSetChanged();

			return true;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			map = new HashMap<String, Object>();
			map.put(ATTRIBUTE_NAME_TEXT_VIEW,
					getString(
							R.string.template_sometext,
							getNumber((String) data.get(data.size() - 1).get(
									ATTRIBUTE_NAME_TEXT_VIEW)) + 1));
			map.put(ATTRIBUTE_NAME_IMAGE_VIEW, R.drawable.ic_launcher);

			data.add(map);
			adapter.notifyDataSetChanged();

			break;

		default:
			break;
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		switch (v.getId()) {
		case R.id.listView:
			menu.add(Menu.NONE, DELETE_ID, Menu.NONE, R.string.remove_record);
			break;

		default:
			break;
		}
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private int getNumber(String name) {
		int length = name.length();
		String result = "";
		for (int i = 0; i < length; i++) {
			Character character = name.charAt(i);
			if (Character.isDigit(character)) {
				result += character;
			}
		}
		return Integer.valueOf(result);
	}

}
