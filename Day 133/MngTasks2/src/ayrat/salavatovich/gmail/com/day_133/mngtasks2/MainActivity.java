package ayrat.salavatovich.gmail.com.day_133.mngtasks2;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClick {

	private TextView textViewInfo;
	private ActivityManager activityManager;
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

	@Override
	public void onClickStart(View v) {
		switch (v.getId()) {
		case R.id.buttonStartLaunchModeStandard:

			startActivity(new Intent(
					"ayrat.salavatovich.gmail.com.day_133.mngtasks1.ActivityC"));

			break;

		case R.id.buttonStartLaunchModeSingleTop:

			startActivity(new Intent(
					"ayrat.salavatovich.gmail.com.day_133.mngtasks1.ActivityD"));

			break;

		case R.id.buttonStartLaunchModeSingleTask:

			startActivity(new Intent(
					"ayrat.salavatovich.gmail.com.day_133.mngtasks1.ActivityE"));

			break;

		case R.id.buttonStartLaunchModeSingleInstance:

			startActivity(new Intent(
					"ayrat.salavatovich.gmail.com.day_133.mngtasks1.ActivityB"));

			break;

		default:
			break;
		}
	}

}
