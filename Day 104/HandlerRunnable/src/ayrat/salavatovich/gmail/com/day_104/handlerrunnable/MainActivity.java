package ayrat.salavatovich.gmail.com.day_104.handlerrunnable;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;
	private CheckBox checkBox;
	private ProgressBar progressBar;

	private final int MAX_PROGRESS = 100;
	private final int START_PROGRESS = 0;

	private final int TIME_UPDATE_INFO = 1000;
	private final int TIME_INCREMENT_PROGRESS = 100;

	private int count;

	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		handler = new Handler();

		textView = (TextView) findViewById(R.id.textView);

		checkBox = (CheckBox) findViewById(R.id.checkBox);
		checkBox.setOnCheckedChangeListener(myCheckedChangeListener);

		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		configProgressBar(progressBar, MAX_PROGRESS, MAX_PROGRESS);

		runProcess();
	}

	private void configProgressBar(final ProgressBar progressBar, int max,
			int startProgress) {
		progressBar.setMax(max);
		updateProgress(progressBar, startProgress);
	}

	private void runProcess() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (count = 1; count < MAX_PROGRESS; count++) {
					SystemClock.sleep(TIME_INCREMENT_PROGRESS);
					handler.post(incrementProgress);
				}
			}
		}).start();
	}

	private void updateProgress(final ProgressBar progressBar, int progress) {
		progressBar.setProgress(progress);
	}

	private void setShowDetails(boolean visibility) {
		textView.setVisibility(visibility ? View.VISIBLE : View.GONE);
	}

	private void showDetails(String text) {
		textView.setText(text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnCheckedChangeListener myCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			setShowDetails(isChecked);
			if (isChecked)
				handler.post(showDetails);
			else
				handler.removeCallbacks(showDetails);
		}
	};

	private Runnable showDetails = new Runnable() {

		@Override
		public void run() {
			showDetails(getString(R.string.info, count));
			handler.postDelayed(showDetails, TIME_UPDATE_INFO);
		}
	};

	private Runnable incrementProgress = new Runnable() {

		@Override
		public void run() {
			updateProgress(progressBar, count);
		}
	};

}
