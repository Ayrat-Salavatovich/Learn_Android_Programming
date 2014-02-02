package ayrat.salavatovich.gmail.com.day_108.servicekillserver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class MyService extends Service {

	private final int SECOND = 1000;
	private ExecutorService executorService;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		LogTag.v(getString(R.string.create_service));
		executorService = Executors.newFixedThreadPool(1);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		LogTag.v(getString(R.string.destroy_service));
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogTag.v(getString(R.string.start_command_service));
		final int action = readIntent(intent);
		readFlags(flags);
		MyRun myRun = new MyRun(startId);
		executorService.execute(myRun);
		return action;
	}

	private void readFlags(int flags) {
		// Параметр Intent был повторно передан при принудительном завершении
		// работы сервиса
		if ((flags & START_FLAG_REDELIVERY) == 0)
			LogTag.v(getString(R.string.start_flag_redelivery));
		// Сервис повторно запущен после непредвиденного завершения работы.
		// Передается если ранее сервис работал в режиме START_STICKY
		if ((flags & START_FLAG_RETRY) == 0)
			LogTag.v(getString(R.string.start_flag_retry));
	}

	private int readIntent(Intent intent) {
		if (intent == null) {
			LogTag.v(getString(R.string.intent_is_null));

			return START_STICKY;
		}

		int flag = START_NOT_STICKY;
		String flagName = getString(R.string.start_not_sticky);

		switch (intent.getIntExtra(getString(R.string.name), START_NOT_STICKY)) {
		case START_STICKY:

			flagName = getString(R.string.start_sticky);
			flag = START_STICKY;

			break;

		case START_NOT_STICKY:

			flagName = getString(R.string.start_not_sticky);
			flag = START_NOT_STICKY;

			break;

		case START_REDELIVER_INTENT:

			flagName = getString(R.string.start_redeliver_intent);
			flag = START_REDELIVER_INTENT;

			break;

		default:

			break;
		}

		LogTag.v(getString(R.string.value, getString(R.string.name), flagName));

		return flag;
	}

	private class MyRun implements Runnable {

		private int startId;
		private String className;

		public MyRun(int startId) {
			this.startId = startId;
			this.className = this.getClass().getSimpleName();
			LogTag.d(getString(R.string.create_runnable, className, startId));
		}

		@Override
		public void run() {
			LogTag.d(getString(R.string.start_runnable, className, startId));
			SystemClock.sleep(15 * SECOND);
			stop();
		}

		private void stop() {
			LogTag.d(getString(R.string.end_with_result_runnable, className,
					startId, startId, stopSelfResult(startId)));
		}

	}

}
