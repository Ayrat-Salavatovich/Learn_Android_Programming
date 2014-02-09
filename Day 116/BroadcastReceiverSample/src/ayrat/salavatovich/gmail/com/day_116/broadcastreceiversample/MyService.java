package ayrat.salavatovich.gmail.com.day_116.broadcastreceiversample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.d(MyService.class.getCanonicalName(),
				getString(R.string.started_service));
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.d(MyService.class.getCanonicalName(),
				getString(R.string.service_stopped));
		super.onDestroy();
	}

}
