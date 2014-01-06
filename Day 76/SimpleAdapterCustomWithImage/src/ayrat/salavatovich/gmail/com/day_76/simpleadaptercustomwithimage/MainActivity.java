package ayrat.salavatovich.gmail.com.day_76.simpleadaptercustomwithimage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	private final String ATTRIBUTE_NAME_TEXT = "text";
	private final String ATTRIBUTE_NAME_VALUE = "value";
	private final String ATTRIBUTE_NAME_IMAGE = "image";

	private int[] values;
	private final int LENGTH_ARRAY_VALUES = 10;
	private final int MIN_VALUE = -10;
	private final int MAX_VALUE = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		makeValues(LENGTH_ARRAY_VALUES, MIN_VALUE, MAX_VALUE);

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
				values.length);
		Map<String, Object> m;
		Integer img = null;
		for (int i = 0; i < values.length; i++) {
			m = new HashMap<String, Object>();
			m.put(ATTRIBUTE_NAME_TEXT, getString(R.string.day, i + 1));
			m.put(ATTRIBUTE_NAME_VALUE, values[i]);

			if (values[i] == 0)
				img = null;
			else
				img = (values[i] > 0) ? MySimpleAdapter.UP_ARROW
						: MySimpleAdapter.DOWN_ARROW;
			m.put(ATTRIBUTE_NAME_IMAGE, img);

			data.add(m);
		}

		String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_VALUE,
				ATTRIBUTE_NAME_IMAGE };

		int[] to = { R.id.textViewText, R.id.textViewValue, R.id.imageView };

		MySimpleAdapter mAdapter = new MySimpleAdapter(this, data,
				R.layout.item, from, to);

		((ListView) findViewById(R.id.listView)).setAdapter(mAdapter);
	}

	private void makeValues(final int length, final int from, final int to) {
		if (to < from)
			return;

		values = new int[length];
		Random rand = new Random();
		for (int i = 0; i < length; i++)
			values[i] = rand.nextInt(to - from + 1) + from;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
