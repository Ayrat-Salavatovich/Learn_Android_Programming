package ayrat.salavatovich.gmail.com.day_73.digitalclock;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.view.Menu;
import android.widget.DigitalClock;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Timer mTimer = new Timer();
	final Handler uiHandler = new Handler();
	private TextView textViewHeapSize, textViewHeapFreeSize;
	DigitalClock digitalClock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		digitalClock = (DigitalClock) findViewById(R.id.digitalClock);
		textViewHeapFreeSize = (TextView) findViewById(R.id.textViewHeapFreeSize);
		textViewHeapSize = (TextView) findViewById(R.id.textViewHeapSize);

		// https://code.google.com/p/android/issues/detail?id=17015
		mTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				uiHandler.post(new Runnable() {
					@Override
					public void run() {
						printHeapMemoryStatistic();
					}
				});
			}
		}, 0, 1000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void printHeapMemoryStatistic() {
		textViewHeapSize.setText(getString(R.string.heap_size,
				Debug.getNativeHeapSize()));
		textViewHeapFreeSize.setText(getString(R.string.heap_free_size,
				Debug.getNativeHeapFreeSize()));
	}

}
