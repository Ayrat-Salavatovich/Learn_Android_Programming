package ayrat.salavatovich.gmail.com.day_78.simpleadaptercustomwithprogressbar;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	final String ATTRIBUTE_NAME_TEXT_VIEW = "TextView";
	final String ATTRIBUTE_NAME_PROGRESS_BAR = "ProgressBar";
	final String ATTRIBUTE_NAME_LINEAR_LAYOUT = "LinearLayout";

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		App[] load = getRunningApp(this);

		ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
				load.length);
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		percentFormat.setMaximumFractionDigits(3);
		Map<String, Object> m;
		for (int i = 0; i < load.length; i++) {
			m = new HashMap<String, Object>();
			m.put(ATTRIBUTE_NAME_TEXT_VIEW,
					load[i].getLabel()
							+ ", "
							+ percentFormat.format(load[i]
									.getMemoryUsageInPercentage() / 100));
			m.put(ATTRIBUTE_NAME_PROGRESS_BAR,
					(int) load[i].getMemoryUsageInPercentage());
			m.put(ATTRIBUTE_NAME_LINEAR_LAYOUT,
					(int) load[i].getMemoryUsageInPercentage());
			data.add(m);
		}

		String[] from = { ATTRIBUTE_NAME_TEXT_VIEW,
				ATTRIBUTE_NAME_PROGRESS_BAR, ATTRIBUTE_NAME_LINEAR_LAYOUT };
		int[] to = { R.id.textViewItem, R.id.progressBarItem,
				R.id.linearLayoutItem };

		SimpleAdapter mAdapter = new SimpleAdapter(this, data, R.layout.item,
				from, to);
		mAdapter.setViewBinder(new MyViewBinder());

		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(mAdapter);
	}

	private App[] getRunningApp(Context ctx) {
		TaskManager taskManager = new TaskManager(ctx);
		List<RunningAppProcessInfo> apps = taskManager.getRunningProcesses();
		List<App> result = new ArrayList<App>();
		Iterator<RunningAppProcessInfo> i = apps.iterator();

		while (i.hasNext()) {
			result.add(new App(ctx, i.next(), taskManager));
		}

		return result.toArray(new App[result.size()]);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
