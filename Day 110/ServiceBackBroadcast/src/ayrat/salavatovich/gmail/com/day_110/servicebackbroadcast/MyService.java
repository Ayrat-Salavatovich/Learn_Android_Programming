package ayrat.salavatovich.gmail.com.day_110.servicebackbroadcast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class MyService extends Service {

	private ExecutorService executorService;
	private static final long start = System.currentTimeMillis();

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		executorService = Executors.newFixedThreadPool(Constants.COUNT_THREADS);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int time = intent.getIntExtra(Constants.NAME_TIME,
				Constants.DEFAULT_TIME);
		int task = intent.getIntExtra(Constants.NAME_TASK, Constants.ZERO);
		executorService.execute(new MyRun(startId, time, task));

		return super.onStartCommand(intent, flags, startId);
	}

	private class MyRun implements Runnable {

		private int startId;
		private int time;
		private int task;

		private MyRun(int startId, int time, int task) {
			this.startId = startId;
			this.time = time;
			this.task = task;
		}

		@Override
		public void run() {
			Intent intent = new Intent(Constants.RECEIVER_ACTION);
			intent.putExtra(Constants.NAME_REQUEST_CODE, task).putExtra(
					Constants.NAME_RESULT_CODE, Constants.CODE_START);
			sendBroadcast(intent);
			SystemClock.sleep(time);
			intent.putExtra(Constants.NAME_REQUEST_CODE, task)
					.putExtra(Constants.NAME_RESULT_CODE, Constants.CODE_FINISH)
					.putExtra(Constants.NAME_RESULT,
							(int) (System.currentTimeMillis() - start));
			sendBroadcast(intent);
			stop();
		}

		private void stop() {
			stopSelfResult(startId);
		}

	}

}
