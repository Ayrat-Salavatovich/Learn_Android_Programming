package ayrat.salavatovich.gmail.com.day_89.asynctaskparams;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private MyTask myTask;
	private TextView textView;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		myTask = new MyTask();
		myTask.execute(getResources().getStringArray(R.array.partitions));
	}

	class MyTask extends AsyncTask<String, Integer, Void> {

		private final int TIME_SLEEP = 5;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			textView.setText(R.string.begin);
		}

		@Override
		protected Void doInBackground(String... urls) {
			for (int i = 0; i < urls.length; i++) {
				downloadFile(urls[i]);
				publishProgress(i + 1);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			textView.setText(getString(R.string.readed, values[0]));
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			textView.setText(R.string.end);
		}

		private void downloadFile(String url) {
			try {
				TimeUnit.SECONDS.sleep(TIME_SLEEP);
			} catch (InterruptedException ignore) {
			}
		}
	}

}
