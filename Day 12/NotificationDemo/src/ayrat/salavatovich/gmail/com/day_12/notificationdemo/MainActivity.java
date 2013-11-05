package ayrat.salavatovich.gmail.com.day_12.notificationdemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private static final int VISIT_NOTIFY_ID = 1;
	private static final int SITE_NOTIFY_ID = 2;
	private Context context;
	private NotificationManager nm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = getApplicationContext();
		nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonCreateNotify:
			createNotifies();
			break;
		case R.id.buttonRemoveNotify:
			removeNotifies();
			break;
		default:
			break;
		}
	}

	private void createNotifies() {
		createNotify(VISIT_NOTIFY_ID);
		createNotify(SITE_NOTIFY_ID);
	}

	private void removeNotifies() {
		removeNotify(SITE_NOTIFY_ID);
		removeNotify(VISIT_NOTIFY_ID);
	}

	public void createNotify(final int id) {
		Intent notificationIntent;
		PendingIntent pendingIntent;
		Resources resources = context.getResources();
		Notification.Builder builder = new Notification.Builder(context);

		switch (id) {
		case VISIT_NOTIFY_ID:
			notificationIntent = new Intent(context, MainActivity.class);
			pendingIntent = PendingIntent.getActivity(context, 0,
					notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

			builder.setContentIntent(pendingIntent)
					.setTicker(resources.getString(R.string.title_visit_notify))
					.setWhen(System.currentTimeMillis())
					.setContentTitle(
							resources.getString(R.string.title_visit_notify))
					.setContentText(
							resources.getString(R.string.text_visit_notify))
					.setAutoCancel(true)
					.setSmallIcon(R.drawable.fg_portu)
					.setLargeIcon(
							BitmapFactory.decodeResource(resources,
									R.drawable.fg_portu));

			nm.notify(VISIT_NOTIFY_ID, builder.getNotification());

			break;
		case SITE_NOTIFY_ID:
			notificationIntent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.visitportugal.com/"));
			pendingIntent = PendingIntent.getActivity(context, 0,
					notificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

			builder.setContentIntent(pendingIntent)
					.setTicker(resources.getString(R.string.ticker_site_notify))
					.setWhen(System.currentTimeMillis())
					.setContentTitle(
							resources.getString(R.string.title_site_notify))
					.setContentText(
							resources.getString(R.string.text_site_notify))
					.setAutoCancel(true)
					.setSmallIcon(R.drawable.visitportugal)
					.setDefaults(
							Notification.DEFAULT_SOUND
									| Notification.DEFAULT_VIBRATE
									| Notification.DEFAULT_LIGHTS);

			nm.notify(SITE_NOTIFY_ID, builder.getNotification());

			break;
		default:
			break;
		}
	}

	public void removeNotify(final int id) {
		if (nm != null)
			nm.cancel(id);
	}

}
