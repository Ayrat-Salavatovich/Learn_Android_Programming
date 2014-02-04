package ayrat.salavatovich.gmail.com.day_110.servicebackbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewTask1, textViewTask2, textViewTask3;
	private BroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewTask1 = (TextView) findViewById(R.id.textViewTask1);
		textViewTask2 = (TextView) findViewById(R.id.textViewTask2);
		textViewTask3 = (TextView) findViewById(R.id.textViewTask3);

		textViewTask1.setText(R.string.task1);
		textViewTask2.setText(R.string.task2);
		textViewTask3.setText(R.string.task3);

		receiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				int resultCode = intent.getIntExtra(Constants.NAME_RESULT_CODE,
						Constants.ZERO);
				int requestCode = intent.getIntExtra(
						Constants.NAME_REQUEST_CODE, Constants.ZERO);

				if (resultCode == Constants.CODE_START) {
					switch (requestCode) {
					case Constants.TASK1_REQUEST_CODE:
						textViewTask1.setText(getString(R.string.task_start,
								getString(R.string.task1)));

						break;

					case Constants.TASK2_REQUEST_CODE:
						textViewTask2.setText(getString(R.string.task_start,
								getString(R.string.task2)));

						break;

					case Constants.TASK3_REQUEST_CODE:
						textViewTask3.setText(getString(R.string.task_start,
								getString(R.string.task3)));

						break;

					default:
						break;
					}
				}

				if (resultCode == Constants.CODE_FINISH) {
					int result = intent.getIntExtra(Constants.NAME_RESULT,
							Constants.ZERO);
					switch (requestCode) {
					case Constants.TASK1_REQUEST_CODE:
						textViewTask1.setText(getString(R.string.task_finish,
								getString(R.string.task1), result));

						break;

					case Constants.TASK2_REQUEST_CODE:
						textViewTask2.setText(getString(R.string.task_finish,
								getString(R.string.task2), result));

						break;

					case Constants.TASK3_REQUEST_CODE:
						textViewTask3.setText(getString(R.string.task_finish,
								getString(R.string.task3), result));

						break;

					default:
						break;
					}
				}
			}

		};

		IntentFilter filter = new IntentFilter(Constants.RECEIVER_ACTION);
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	public void onStart(View v) {
		switch (v.getId()) {
		case R.id.buttonStart:
			startServices();

			break;

		default:
			break;
		}
	}

	private void startServices() {
		Intent service;

		service = new Intent(this, MyService.class).putExtra(
				Constants.NAME_TIME, 7 * Constants.SECOND).putExtra(
				Constants.NAME_TASK, Constants.TASK1_REQUEST_CODE);
		startService(service);

		service = new Intent(this, MyService.class).putExtra(
				Constants.NAME_TIME, 3 * Constants.SECOND).putExtra(
				Constants.NAME_TASK, Constants.TASK2_REQUEST_CODE);
		startService(service);

		service = new Intent(this, MyService.class).putExtra(
				Constants.NAME_TIME, 5 * Constants.SECOND).putExtra(
				Constants.NAME_TASK, Constants.TASK3_REQUEST_CODE);
		startService(service);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
