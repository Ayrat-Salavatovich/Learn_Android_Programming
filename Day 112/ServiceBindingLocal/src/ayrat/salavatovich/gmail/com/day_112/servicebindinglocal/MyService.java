package ayrat.salavatovich.gmail.com.day_112.servicebindinglocal;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

	private Timer timer;
	private long period = 1000;
	private final long MIN_PERIOD = 1;
	private long delay = 1000;
	private TimerTask timerTask;
	private final MyBinder myBinder = new MyBinder();

	@Override
	public IBinder onBind(Intent intent) {
		return myBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		timer = new Timer();
		schedule();
	}

	private void schedule() {
		// ќтмененную задачу нельз€ больше запланировать. ƒл€ повторного
		// включени€ необходимо создать новый экземпл€р TimerTask и передать
		// таймеру.
		if (timerTask != null)
			timerTask.cancel();

		if (period > 0) {
			timerTask = new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
				}
			};
		}

		timer.schedule(timerTask, delay, period);
	}

	long upPeriod(long gap) {
		period += gap;

		schedule();

		return period;
	}

	long downPeriod(long gap) {
		period -= gap;
		if (period <= 0)
			period = MIN_PERIOD;

		schedule();

		return period;
	}

	class MyBinder extends Binder {
		MyService getService() {
			return MyService.this;
		}
	}
}
