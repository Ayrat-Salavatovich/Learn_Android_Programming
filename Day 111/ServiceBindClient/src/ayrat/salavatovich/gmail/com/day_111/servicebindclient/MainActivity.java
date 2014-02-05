package ayrat.salavatovich.gmail.com.day_111.servicebindclient;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Intent intent;
	private ServiceConnection serviceConnection;
	private boolean is_connection;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		intent = new Intent(getString(R.string.intent_myservice));

		textView = (TextView) findViewById(R.id.textView);

		serviceConnection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				disconnected();
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				connected();
			}
		};
	}

	private void disconnected() {
		is_connection = false;
		outputInfo(R.string.disconnected);
	}

	private void connected() {
		is_connection = true;
		outputInfo(R.string.connected);
	}

	private void outputInfo(int resId) {
		textView.setText(resId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonStartService:

			startService();

			break;

		case R.id.buttonStopService:

			stopService();

			break;

		case R.id.buttonBindService:

			bindService();

			break;

		case R.id.buttonUnbindService:

			unbindService();

			break;

		default:
			break;
		}
	}

	private void startService() {
		startService(intent);
	}

	private void stopService() {
		stopService(intent);
	}

	private void bindService() {
		bindService(intent, serviceConnection, BIND_AUTO_CREATE);
	}

	private void unbindService() {
		if (is_connection)
			unbindService(serviceConnection);

		disconnected();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		unbindService();
	}

}
