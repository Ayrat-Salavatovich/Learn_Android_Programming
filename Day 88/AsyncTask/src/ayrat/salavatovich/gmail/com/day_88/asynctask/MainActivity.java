package ayrat.salavatovich.gmail.com.day_88.asynctask;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;

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
		new MyTask().execute();
	}

	private class MyTask extends AsyncTask<Void, Void, Void> {

		private final int SLEEP_TIME = 5;

		// будет выполнен в новом потоке, здесь решаем все свои тяжелые задачи.
		// Т.к. поток не основной - не имеет доступа к UI.
		@Override
		protected Void doInBackground(Void... params) {
			sleep(SLEEP_TIME);
			return null;
		}

		// выполняется после doInBackground (не срабатывает в случае, если
		// AsyncTask был отменен - об этом в следующих уроках), имеет доступ к
		// UI
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			textView.setText(R.string.end);
		}

		// выполняется перед doInBackground, имеет доступ к UI
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			textView.setText(R.string.begin);
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
