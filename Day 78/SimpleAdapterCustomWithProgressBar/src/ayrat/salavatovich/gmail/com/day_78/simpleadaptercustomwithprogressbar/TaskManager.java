package ayrat.salavatovich.gmail.com.day_78.simpleadaptercustomwithprogressbar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;

public class TaskManager {

	private ActivityManager activityManager;
	private MemoryInfo memoryInfo = new MemoryInfo();
	private List<RunningAppProcessInfo> apps;
	private int dalvikPrivateDirtyTotal;

	public TaskManager(Context ctx) {
		activityManager = (ActivityManager) ctx
				.getSystemService(Context.ACTIVITY_SERVICE);
		activityManager.getMemoryInfo(memoryInfo);
		refresh();
	}

	public long getAvailableMemory() {
		activityManager.getMemoryInfo(memoryInfo);
		return memoryInfo.availMem;
	}

	public long getTotalMemory() {
		return memoryInfo.totalMem;
	}

	public int getDalvikPrivateDirtyTotal() {
		return dalvikPrivateDirtyTotal;
	}

	public void refresh() {
		apps = activityManager.getRunningAppProcesses();

		if (apps == null)
			apps = new ArrayList<RunningAppProcessInfo>();

		Iterator<RunningAppProcessInfo> i = apps.iterator();
		while (i.hasNext()) {
			android.os.Debug.MemoryInfo[] memoryInfo = activityManager
					.getProcessMemoryInfo(new int[] { i.next().pid });
			dalvikPrivateDirtyTotal += memoryInfo[0].dalvikPrivateDirty;
		}
	}

	public List<RunningAppProcessInfo> getRunningProcesses() {
		return apps;
	}
}
