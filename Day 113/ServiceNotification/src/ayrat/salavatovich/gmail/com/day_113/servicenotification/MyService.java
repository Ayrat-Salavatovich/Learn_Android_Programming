package ayrat.salavatovich.gmail.com.day_113.servicenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class MyService extends Service {

	private NotificationManager notificationManager;
	private final int DELAY_SEC = 5;
	private static int notifyID = 1;
	private Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = this;
		notificationManager = (NotificationManager) context
				.getSystemService(context.NOTIFICATION_SERVICE);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		sleep(DELAY_SEC);
		sendNotification();
		return super.onStartCommand(intent, flags, startId);
	}

	private void sleep(int sec) {
		SystemClock.sleep(sec * 1000);
	}

	private void sendNotification() {
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra(Constants.PORTUGAL_DOMAIN_NAMES, getString(R.string.pt));
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, 0);

		Notification.Builder builder = new Notification.Builder(context);
		builder.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle(getString(R.string.title_notification))
				.setContentText(getString(R.string.text_notification))
				.setTicker(getString(R.string.portugal_domain_names))
				.setWhen(System.currentTimeMillis())
				.setContentIntent(pendingIntent).setAutoCancel(true);

		Notification notification = builder.build();
		notification.number = notifyID;

		notificationManager.notify(notifyID, notification);
		notifyID++;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
