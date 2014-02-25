package ayrat.salavatovich.gmail.com.day_135.mngtasks1withflagactivity;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public abstract class MainActivity extends Activity implements OnClick {

	protected TextView textViewInfo;
	protected ActivityManager activityManager;
	private List<ActivityManager.RunningTaskInfo> runningTaskInfos;
	private final int MAX_NUMBER = 13;
	private final String LINE_SEPARATOR = "\n";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewInfo = (TextView) findViewById(R.id.textViewInfo);

		setTitle(getString(R.string.app_name) + ": " + getLocalClassName());
		activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClickInfo(View v) {
		// список текущих работающих тасков
		runningTaskInfos = activityManager.getRunningTasks(MAX_NUMBER);
		StringBuilder log = new StringBuilder();
		for (RunningTaskInfo runningTaskInfo : runningTaskInfos) {
			log.append("-------------");
			log.append(LINE_SEPARATOR);
			log.append("Count: " + runningTaskInfo.numActivities); // кол-во
																	// Activity
			log.append(LINE_SEPARATOR);
			log.append("Root: "
					+ runningTaskInfo.baseActivity.flattenToShortString()); // корневое
																			// Activity
			log.append(LINE_SEPARATOR);
			log.append("Top: "
					+ runningTaskInfo.topActivity.flattenToShortString()); // верхнее
																			// Activity
			log.append(LINE_SEPARATOR);
		}

		outputInfo(log.toString());
	}

	private void outputInfo(String text) {
		textViewInfo.setText(text);
	}

}