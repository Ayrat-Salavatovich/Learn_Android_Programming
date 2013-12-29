package ayrat.salavatovich.gmail.com.day_72.progressbarsample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ProgressBar progressBar;
	private final int MAX_PROGRESS = 100;
	private TextView textView;
	private final int STEP_INCREMENT_PROGRESS = 1;
	private final int SLEEP_INTERVAL = 100;
	private int mProgressStatus;
	private volatile boolean threadSuspended = false;
	private final String TAG = "ANALYST";
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			incrementPorgressBar(STEP_INCREMENT_PROGRESS);
		}

	};
	private Thread mThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		progressBar.setMax(MAX_PROGRESS);

		textView = (TextView) findViewById(R.id.textView);

		showProgress(mProgressStatus);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonStart:
			start();
			break;
		case R.id.buttonStop:
			stop();
			break;
		case R.id.buttonPause:
			pause();
			break;
		default:
			break;
		}
	}

	public void start() {
		threadSuspended = false;

		if (getCurrentProgress() == MAX_PROGRESS)
			setCurrentProgress(0);

		if (mThread == null || getCurrentProgress() < MAX_PROGRESS)
			mThread = new Thread(new Runnable() {

				@Override
				public void run() {
					Thread thisThread = Thread.currentThread();
					while (mThread == thisThread
							&& getCurrentProgress() < MAX_PROGRESS) {
						try {
							Thread.sleep(SLEEP_INTERVAL);
							if (threadSuspended)
								synchronized (this) {
									while (threadSuspended
											&& mThread == thisThread)
										wait();
								}
						} catch (InterruptedException ignore) {
						} finally {
							mHandler.sendMessage(mHandler.obtainMessage());
						}
					}
				}
			});
		mThread.start();
	}

	public void stop() {
		threadSuspended = true;

		if (mThread != null) {
			Thread thread = mThread;
			mThread = null;
			thread.interrupt();
		}

		setCurrentProgress(0);
	}

	public void pause() {
		threadSuspended = true;
	}

	private int getCurrentProgress() {
		return mProgressStatus;
	}

	private boolean setCurrentProgress(int progress) {
		if (progressBar == null || progress > MAX_PROGRESS)
			return false;

		mProgressStatus = progress;
		showProgress(progress);

		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private boolean incrementPorgressBar(final int diff) {
		if (!threadSuspended && setCurrentProgress(getCurrentProgress() + diff))
			return true;

		return false;
	}

	private void showProgress(int value) {
		progressBar.setProgress(value);
		textView.setText(getString(R.string.progress, value));
	}
}
