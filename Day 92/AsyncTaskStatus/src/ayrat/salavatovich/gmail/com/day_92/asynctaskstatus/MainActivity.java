package ayrat.salavatovich.gmail.com.day_92.asynctaskstatus;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final boolean MAY_INTERRUPT_IF_RUNNING = false;
	private TextView textView;
	private MyTask myTask;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		setVisibility(false);

		myTask = new MyTask();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButtonStart:
			startTask();

			break;
		case R.id.imageButtonStatus:
			showStatusTask();

			break;
		case R.id.imageButtonCancel:
			stopTask();

			break;
		default:
			break;
		}
	}

	protected void setVisibility(boolean visible) {
		if (visible)
			progressBar.setVisibility(View.VISIBLE);
		else
			progressBar.setVisibility(View.GONE);
	}

	protected void startTask() {
		myTask = new MyTask();
		myTask.execute();
	}

	protected void stopTask() {
		if (myTask == null)
			return;

		myTask.cancel(MAY_INTERRUPT_IF_RUNNING);
	}

	protected void showStatusTask() {
		if (myTask.isCancelled())
			info(R.string.cancelled);
		else if (myTask.getStatus() == AsyncTask.Status.PENDING)
			info(R.string.pending);
		else if (myTask.getStatus() == AsyncTask.Status.RUNNING)
			info(R.string.running);
		else if (myTask.getStatus() == AsyncTask.Status.FINISHED)
			info(R.string.finished);
	}

	protected void info(int resId) {
		textView.setText(getString(resId));
	}

	protected void info(String text) {
		textView.setText(text);
	}

	private class MyTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			for (int i = 0; i < 50; i++) {
				SystemClock.sleep(100);
				if (isCancelled())
					return null;
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			info(R.string.end);
			setVisibility(false);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			info(R.string.begin);
			setVisibility(true);
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
			info(R.string.cancel);
			setVisibility(false);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
