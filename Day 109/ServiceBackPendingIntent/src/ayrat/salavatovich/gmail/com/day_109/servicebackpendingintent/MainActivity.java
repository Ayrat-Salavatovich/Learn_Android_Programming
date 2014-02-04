package ayrat.salavatovich.gmail.com.day_109.servicebackpendingintent;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewTask1, textViewTask2, textViewTask3;

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
		PendingIntent pendingIntent;
		Intent service;

		pendingIntent = createPendingResult(Constants.TASK1_REQUEST_CODE,
				new Intent(), Constants.DEFAULT_TIME);

		service = new Intent(this, MyService.class).putExtra(
				Constants.NAME_TIME, 7 * Constants.SECOND).putExtra(
				Constants.NAME_PENDING_INTENT, pendingIntent);
		startService(service);

		pendingIntent = createPendingResult(Constants.TASK2_REQUEST_CODE,
				new Intent(), Constants.DEFAULT_TIME);

		service = new Intent(this, MyService.class).putExtra(
				Constants.NAME_TIME, 4 * Constants.SECOND).putExtra(
				Constants.NAME_PENDING_INTENT, pendingIntent);
		startService(service);

		pendingIntent = createPendingResult(Constants.TASK3_REQUEST_CODE,
				new Intent(), Constants.DEFAULT_TIME);

		service = new Intent(this, MyService.class).putExtra(
				Constants.NAME_TIME, 6 * Constants.SECOND).putExtra(
				Constants.NAME_PENDING_INTENT, pendingIntent);
		startService(service);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
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
			int result = data.getIntExtra(Constants.NAME_RESULT, 0);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
