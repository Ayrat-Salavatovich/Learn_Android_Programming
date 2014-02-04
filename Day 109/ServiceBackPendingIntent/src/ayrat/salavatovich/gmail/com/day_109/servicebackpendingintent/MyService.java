package ayrat.salavatovich.gmail.com.day_109.servicebackpendingintent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class MyService extends Service {

	ExecutorService executorService;

	@Override
	public IBinder onBind(Intent intent) {
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
		PendingIntent pendingIntent = intent
				.getParcelableExtra(Constants.NAME_PENDING_INTENT);

		executorService.execute(new MyRun(pendingIntent, time, startId));

		return super.onStartCommand(intent, flags, startId);
	}

	private class MyRun implements Runnable {

		private int startId;
		private int time;
		private PendingIntent pendingIntent;

		private MyRun(PendingIntent pendingIntent, int time, int startId) {
			this.pendingIntent = pendingIntent;
			this.startId = startId;
			this.time = time;
		}

		@Override
		public void run() {
			Intent intentResult;
			try {
				pendingIntent.send(Constants.CODE_START);
				SystemClock.sleep(time);
				intentResult = new Intent().putExtra(Constants.NAME_RESULT,
						new Random().nextInt(time));
				pendingIntent.send(MyService.this, Constants.CODE_FINISH,
						intentResult);
			} catch (CanceledException e) {
				e.printStackTrace();
			}
			stop();
		}

		private void stop() {
			stopSelfResult(startId);
		}

	}

}
