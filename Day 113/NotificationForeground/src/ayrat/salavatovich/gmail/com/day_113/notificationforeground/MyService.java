package ayrat.salavatovich.gmail.com.day_113.notificationforeground;

import java.util.Random;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

	private static int notifyID = 1;
	private Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = this;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		sendNotification();
		return super.onStartCommand(intent, flags, startId);
	}

	private void sendNotification() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(context, MainActivity.class);
				intent.putExtra(getString(R.string.portugal),
						getString(R.string.the_best));
				PendingIntent pendingIntent = PendingIntent.getActivity(
						context, 0, intent, 0);

				String[] titleNotifications = getResources().getStringArray(
						R.array.title_notifications);
				String[] textNotifications = getResources().getStringArray(
						R.array.text_notifications);

				int idNotification = new Random()
						.nextInt(titleNotifications.length);

				Notification.Builder builder = new Notification.Builder(context);
				builder.setSmallIcon(R.drawable.ic_launcher)
						.setContentTitle(titleNotifications[idNotification])
						.setContentText(textNotifications[idNotification])
						.setTicker(getString(R.string.notification))
						.setWhen(System.currentTimeMillis())
						.setContentIntent(pendingIntent).setAutoCancel(true);

				Notification notification = builder.build();
				notification.number = notifyID;
				notification.flags |= Notification.FLAG_NO_CLEAR;

				startForeground(notifyID, notification);
				notifyID++;
			}
		}).start();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
