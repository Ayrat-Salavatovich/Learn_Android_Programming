package ayrat.salavatovich.gmail.com.day_134.killallrunningapplications;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity implements OnClick {

	private Context context;

	@Override
	protected void onStart() {
		super.onStart();

		/*
		 * Check if the app was just launched. If the app was just launched then
		 * assume that the HOME key will be pressed next unless a navigation
		 * event by the user or the app occurs. Otherwise the user or the app
		 * navigated to this activity so the HOME key was not pressed.
		 */

		UIHelper.checkJustLaunced();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = this;
	}

	@Override
	public void finish() {
		/*
		 * This can only invoked by the user or the app finishing the activity
		 * by navigating from the activity so the HOME key was not pressed.
		 */
		UIHelper.homeKeyPressed = false;
		super.finish();
	}

	@Override
	protected void onStop() {
		super.onStop();

		/*
		 * Check if the HOME key was pressed. If the HOME key was pressed then
		 * the app will be killed. Otherwise the user or the app is navigating
		 * away from this activity so assume that the HOME key will be pressed
		 * next unless a navigation event by the user or the app occurs.
		 */
		UIHelper.checkHomeKeyPressed(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		/*
		 * Assume that the HOME key will be pressed next unless a navigation
		 * event by the user or the app occurs.
		 */
		UIHelper.homeKeyPressed = true;

		return true;
	}

	@Override
	public boolean onSearchRequested() {
		/*
		 * Disable the SEARCH key.
		 */
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.actionKillAllProcesses:

			killAllProcesses(context);

			break;

		default:
			break;
		}
	}

	private void killAllProcesses(Context context) {
		final List<ApplicationInfo> packages;
		final PackageManager packageManager;
		packageManager = getPackageManager();
		// get a list of installed apps
		packages = packageManager.getInstalledApplications(0);

		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

		for (ApplicationInfo packageInfo : packages) {
			if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1)
				continue;
			if (packageInfo.packageName.equals(context.getPackageName()))
				continue;

			activityManager.killBackgroundProcesses(packageInfo.packageName);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		/*
		 * Kill application when the root activity is killed.
		 */
		UIHelper.killApp(true);
	}

}
