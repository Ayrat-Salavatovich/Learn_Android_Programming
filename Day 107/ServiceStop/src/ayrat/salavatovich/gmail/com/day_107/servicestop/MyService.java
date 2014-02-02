package ayrat.salavatovich.gmail.com.day_107.servicestop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class MyService extends Service {

	private ExecutorService executorService;
	private Object someResource;
	public static final String NAME_TIME = "ayrat.salavatovich.gmail.com.day_107.servicestop.TIME";
	public static final int SECOND = 1000;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		LogTag.d(getString(R.string.create_service));
		executorService = Executors.newFixedThreadPool(1);
		someResource = new Object();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		LogTag.d(getString(R.string.destroy_service));
		someResource = null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogTag.d(getString(R.string.start_command_service));
		int time = intent.getIntExtra(NAME_TIME, SECOND);
		MyRun myRun = new MyRun(time, startId);
		executorService.execute(myRun);
		return super.onStartCommand(intent, flags, startId);
	}

	private class MyRun implements Runnable {

		private int time, startId;
		private String className;

		public MyRun(int time, int startId) {
			this.time = time;
			this.startId = startId;
			this.className = this.getClass().getSimpleName();
			LogTag.d(getString(R.string.create_runnable, className, startId));
		}

		@Override
		public void run() {
			LogTag.d(getString(R.string.start_runnable, className, startId,
					time));
			SystemClock.sleep(time);
			try {
				LogTag.d(getString(R.string.get_result_runnable, className,
						startId, someResource.getClass()));
			} catch (NullPointerException e) {
				LogTag.d(getString(R.string.error_runnable, className, startId));
			}
			stop();
		}

		private void stop() {
			// Вызов сервиса три раза
			if (startId == 3)
				LogTag.d(getString(R.string.end_with_result_runnable,
						className, startId, startId, stopSelfResult(startId)));
			else {
				LogTag.d(getString(R.string.end_runnable, className, startId,
						startId));
				stopSelf(startId);
			}
		}

	}

}
