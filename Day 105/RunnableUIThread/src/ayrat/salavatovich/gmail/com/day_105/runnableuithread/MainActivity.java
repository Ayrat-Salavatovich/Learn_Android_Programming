package ayrat.salavatovich.gmail.com.day_105.runnableuithread;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;

	private final int SECOND = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);

		runProcess();
	}

	private void runProcess() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				SystemClock.sleep(SECOND);
				runOnUiThreadRunnable(getString(R.string.message_1));
				SystemClock.sleep(SECOND);
				postRunnable(getString(R.string.message_2));
				postRunnable(getString(R.string.message_3), SECOND);
			}
		}).start();
		;
	}

	private void showInfo(String text) {
		textView.setText(text);
	}

	private void postRunnable(final String message) {
		final Runnable action = newMessage(message);

		textView.post(action);
	}

	private void postRunnable(final String message, long delayMillis) {
		final Runnable action = newMessage(message);

		textView.postDelayed(action, delayMillis);
	}

	private void runOnUiThreadRunnable(final String message) {
		final Runnable action = newMessage(message);

		runOnUiThread(action);
	}

	private Runnable newMessage(final String text) {
		return new Runnable() {

			@Override
			public void run() {
				showInfo(text);
			}
		};

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
