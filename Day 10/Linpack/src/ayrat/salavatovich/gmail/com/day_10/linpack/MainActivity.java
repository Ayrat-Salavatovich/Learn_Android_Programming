package ayrat.salavatovich.gmail.com.day_10.linpack;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
		linpack = new Linpack();
	}

	private void showResult(CharSequence text) {
		textView.setText(text);
	}

	public void onClickStartBenchmark(View v) {
		new MyTask().execute();
	}

	class MyTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			return linpack.run_benchmark();
		}

		@Override
		protected void onPostExecute(String result) {
			showResult(result);
		}

	}

	private Linpack linpack;
	private TextView textView;

}
