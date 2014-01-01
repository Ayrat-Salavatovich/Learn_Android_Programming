package ayrat.salavatovich.gmail.com.day_75.simpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private Map<String, Map<String, Object>> data = new HashMap<String, Map<String, Object>>();
	private final int TEXT = 0;
	private final int IMAGE = 1;
	private final int CHECKED = 2;
	private final int CHECKED_TEXT = 3;
	private final String[] ATTRIBUTES = new String[] { "text", "checked",
			"image", "text" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		makeData();

		List<Map<String, Object>> adapterData = new ArrayList<Map<String, Object>>(
				data.size());

		transformToList(adapterData, data);

		String[] from = ATTRIBUTES;

		int[] to = { R.id.textView, R.id.imageView, R.id.checkBox,
				R.id.checkBox };

		SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
				adapterData, R.layout.item, from, to);

		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
	}

	private void makeData() {
		data.put(
				"sometext 1",
				(Map) build(ATTRIBUTES[IMAGE], R.drawable.ic_launcher,
						ATTRIBUTES[CHECKED], true, ATTRIBUTES[CHECKED_TEXT],
						"sometext 1"));
		data.put(
				"sometext 2",
				(Map) build(ATTRIBUTES[IMAGE], R.drawable.ic_launcher,
						ATTRIBUTES[CHECKED], false, ATTRIBUTES[CHECKED_TEXT],
						"sometext 2"));
		data.put(
				"sometext 3",
				(Map) build(ATTRIBUTES[IMAGE], R.drawable.ic_launcher,
						ATTRIBUTES[CHECKED], false, ATTRIBUTES[CHECKED_TEXT],
						"sometext 3"));
		data.put(
				"sometext 4",
				(Map) build(ATTRIBUTES[IMAGE], R.drawable.ic_launcher,
						ATTRIBUTES[CHECKED], true, ATTRIBUTES[CHECKED_TEXT],
						"sometext 4"));
		data.put(
				"sometext 5",
				(Map) build(ATTRIBUTES[IMAGE], R.drawable.ic_launcher,
						ATTRIBUTES[CHECKED], false, ATTRIBUTES[CHECKED_TEXT],
						"sometext 5"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private <K, V> Map<K, V> build(Object... data) {
		Map<K, V> result = new HashMap<K, V>();

		if (data.length % 2 != 0)
			throw new IllegalArgumentException("Odd number of arguments");

		Object key = null;
		Integer step = -1;

		for (Object value : data) {
			step++;
			switch (step % 2) {
			case 0:
				if (value == null)
					throw new NullPointerException("Null key value");
				key = value;
				continue;
			case 1:
				result.put((K) key, (V) value);
				break;
			}
		}

		return result;
	}

	private void transformToList(List<Map<String, Object>> data,
			Map<String, Map<String, Object>> identity) {

		Map<String, Object> transformedMap;

		for (Entry<String, Map<String, Object>> entry : identity.entrySet()) {
			transformedMap = new HashMap<String, Object>();

			transformedMap.put(ATTRIBUTES[TEXT], entry.getKey());
			for (Map.Entry<String, Object> entryAttribute : entry.getValue()
					.entrySet())
				transformedMap.put(entryAttribute.getKey().toString(),
						entryAttribute.getValue());

			data.add(transformedMap);
		}
	}

}
