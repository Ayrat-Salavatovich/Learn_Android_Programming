package ayrat.salavatovich.gmail.com.day_106.servicesimple;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyFirstService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		toDo();
		return super.onStartCommand(intent, flags, startId);
	}

	private void toDo() {
		// TODO
	}

}
