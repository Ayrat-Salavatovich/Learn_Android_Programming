package ayrat.salavatovich.gmail.com.day_93.asynctaskrotate;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final int MAX_VALUE = 100;
	private final int SLEEP_TIME_MS = 100;
	private TextView textView;
	private MyTask myTask;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		progressBar.setMax(MAX_VALUE);
		setVisibilityProgress(true);

		restoreOrInitTask();
	}

	private void restoreOrInitTask() {
		myTask = (MyTask) getLastNonConfigurationInstance();

		if (myTask == null) {
			myTask = new MyTask();
			myTask.execute(MAX_VALUE, SLEEP_TIME_MS);
		}

		myTask.setContent(this);
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		myTask.setContent(null);
		return myTask;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	static class MyTask extends AsyncTask<Integer, Integer, Void> {

		private MainActivity activity;

		private void setContent(MainActivity activity) {
			this.activity = activity;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			if (activity != null)
				activity.setVisibilityProgress(true);
		}

		@Override
		protected Void doInBackground(Integer... params) {
			for (int i = 0; i <= params[0]; i++) {
				SystemClock.sleep(params[1]);
				if (isCancelled())
					return null;

				publishProgress(i);
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);

			if (activity != null)
				activity.showProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (activity != null)
				activity.setVisibilityProgress(false);
		}
	}

	protected void setVisibilityProgress(boolean visible) {
		if (visible)
			progressBar.setVisibility(View.VISIBLE);
		else
			progressBar.setVisibility(View.GONE);
	}

	private void showProgress(int value) {
		progressBar.setProgress(value);
		outputValue(value);
	}

	private void outputValue(int value) {
		textView.setText(getString(R.string.show_value, value));
	}
}
