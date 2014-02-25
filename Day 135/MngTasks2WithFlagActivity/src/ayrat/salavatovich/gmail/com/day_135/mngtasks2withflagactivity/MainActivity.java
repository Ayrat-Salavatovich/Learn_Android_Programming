package ayrat.salavatovich.gmail.com.day_135.mngtasks2withflagactivity;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Intent;
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonFlagActivityNewTask:

			// создает новое Activity сверху
			startActivity(new Intent(
					"ayrat.salavatovich.gmail.com.day_135.mngtasks1withflagactivity.ActivityA")
					.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

			break;

		case R.id.buttonFlagActivityClearTask:

			// таск для вызываемого Activity будет очищен, а вызываемое Activity
			// станет в нем корневым
			startActivity(new Intent(
					"ayrat.salavatovich.gmail.com.day_135.mngtasks1withflagactivity.ActivityG")
					.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(
							Intent.FLAG_ACTIVITY_NEW_TASK));

			break;

		case R.id.buttonFlagActivityClearTop:

			// ищет в таске создаваемое Activity. Если находит, то открывает, а
			// все, что выше – закрывае
			startActivity(new Intent(
					"ayrat.salavatovich.gmail.com.day_135.mngtasks1withflagactivity.ActivityC")
					.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

			break;

		case R.id.buttonFlagActivityClearWhenTaskReset:

			// помечает вызванное Activity. При следующем вызове таска из Home,
			// это Activity и все вышерасположенные будут закрыты
			startActivity(new Intent(
					"ayrat.salavatovich.gmail.com.day_135.mngtasks1withflagactivity.ActivityD")
					.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET));

			break;

		case R.id.buttonFlagActivityNoHistory:

			// Activity не будет сохранено в таске
			startActivity(new Intent(
					"ayrat.salavatovich.gmail.com.day_135.mngtasks1withflagactivity.ActivityE")
					.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));

			break;

		case R.id.buttonFlagActivityReorderToFront:

			// если Activity уже есть в таске оно переместится в топ
			startActivity(new Intent(
					"ayrat.salavatovich.gmail.com.day_135.mngtasks1withflagactivity.ActivityF")
					.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));

			break;

		case R.id.buttonFlagActivitySingleTop:

			// аналогичен значению singleTop для launchMode
			startActivity(new Intent(
					"ayrat.salavatovich.gmail.com.day_135.mngtasks1withflagactivity.ActivityB"));

			break;

		case R.id.buttonFlagActivityTaskOnHome:

			// таск для вызываемого Activity будет располагаться сразу после
			// Home
			startActivity(new Intent(
					"ayrat.salavatovich.gmail.com.day_135.mngtasks1withflagactivity.ActivityH")
					.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

			break;

		default:
			break;
		}
	}
}
