package ayrat.salavatovich.gmail.com.day_112.servicebindinglocal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final long GAP = 100;
	private boolean bound = false;
	private ServiceConnection serviceConnection;
	private Intent service;
	private MyService myService;
	private TextView textViewInfo;
	private long period;
	private Button buttonUp, buttonDown;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewInfo = (TextView) findViewById(R.id.textViewInfo);

		buttonUp = (Button) findViewById(R.id.buttonUp);
		buttonDown = (Button) findViewById(R.id.buttonDown);

		setEnabledActionChangeTimePeriod(bound);

		service = new Intent(this, MyService.class);
		serviceConnection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				bound = false;
				setEnabledActionChangeTimePeriod(bound);
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				myService = ((MyService.MyBinder) service).getService();
				bound = true;
				setEnabledActionChangeTimePeriod(bound);
			}
		};
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonUp:

			upTimePeriod(GAP);

			break;

		case R.id.buttonDown:

			downTimePeriod(GAP);

			break;

		case R.id.buttonStart:

			startService();

			break;

		default:
			break;
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		bindService(service, serviceConnection, 0);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (!bound)
			return;

		unbindService(serviceConnection);
		bound = false;
	}

	private void startService() {
		startService(service);
	}

	private void upTimePeriod(long gap) {
		period = myService.upPeriod(gap);
		outputCurrentTimePeriod(period);
	}

	private void downTimePeriod(long gap) {
		period = myService.downPeriod(gap);
		outputCurrentTimePeriod(period);
	}

	private void outputCurrentTimePeriod(long period) {
		textViewInfo.setText(getString(R.string.period, period));
	}

	private void setEnabledActionChangeTimePeriod(boolean enabled) {
		buttonUp.setEnabled(enabled);
		buttonDown.setEnabled(enabled);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
