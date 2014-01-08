package ayrat.salavatovich.gmail.com.day_78.simpleadaptercustomwithprogressbar;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageManager;

public class App {

	private TaskManager taskManager;
	private RunningAppProcessInfo app;
	private PackageManager pm;
	private ActivityManager activityManager;

	public App(Context ctx, RunningAppProcessInfo app, TaskManager taskManager) {
		this.app = app;
		this.pm = ctx.getPackageManager();
		this.activityManager = (ActivityManager) ctx
				.getSystemService(Context.ACTIVITY_SERVICE);
		this.taskManager = taskManager;
	}

	public String getLabel() {
		ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) app;
		String label = null;
		try {
			label = pm.getApplicationLabel(
					pm.getApplicationInfo(info.processName,
							PackageManager.GET_META_DATA)).toString();
		} catch (Exception ignore) {
		}

		return label;
	}

	public long getMemorySize() {
		android.os.Debug.MemoryInfo[] memoryInfo = activityManager
				.getProcessMemoryInfo(new int[] { app.pid });
		return memoryInfo[0].dalvikPrivateDirty;

	}

	public double getMemoryUsageInPercentage() {
		return getMemorySize() * 100.
				/ taskManager.getDalvikPrivateDirtyTotal();
	}

	public int getPid() {
		return app.pid;
	}

	public int getUid() {
		return app.uid;
	}

}
