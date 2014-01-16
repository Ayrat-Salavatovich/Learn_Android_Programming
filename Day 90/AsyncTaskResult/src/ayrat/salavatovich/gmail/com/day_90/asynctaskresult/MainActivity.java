package ayrat.salavatovich.gmail.com.day_90.asynctaskresult;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewStatus;
	private MyTask myTask;
	private final int SECOND = 1;
	private final int SLEEP_TIME = 5 * SECOND;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewStatus = (TextView) findViewById(R.id.textViewStatus);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void start(View v) {
		executeTask();
	}

	public void get(View v) {
		showResult();
	}

	private void executeTask() {
		myTask = new MyTask();

		myTask.execute();
	}

	private void showResult() {
		if (myTask == null)
			return;

		int result = -1;
		try {
			result = myTask.get();
			textViewStatus.setText(getString(R.string.show_result, result));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private class MyTask extends AsyncTask<Void, Integer, Integer> {

		private final Random random = new Random();

		@Override
		protected Integer doInBackground(Void... params) {
			for (int i = SLEEP_TIME; i > 0; i--) {
				sleep(SECOND);
				publishProgress(i);
			}
			sleep(SECOND);
			return random.nextInt();
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			textViewStatus.setText(R.string.end);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			textViewStatus.setText(R.string.begin);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			textViewStatus.setText(getString(R.string.indication, values[0]));
		}

		void sleep(int sec) {
			try {
				TimeUnit.SECONDS.sleep(sec);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
