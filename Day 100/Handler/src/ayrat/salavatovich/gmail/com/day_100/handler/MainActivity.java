package ayrat.salavatovich.gmail.com.day_100.handler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Handler handler;
	private TextView textView;
	private Button buttonStart;
	private Context context;

	private final int SLEEP_TIME_MS = 1000;
	private final int COUNT_FILES = 10;
	private int countDownloadedFiles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = this;
		buttonStart = (Button) findViewById(R.id.buttonStart);
		textView = (TextView) findViewById(R.id.textView);
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				outputCountDownloadedFiles(countDownloadedFiles);
				showInfo(getString(R.string.downloaded_file_id, msg.what));

				if (countDownloadedFiles == COUNT_FILES)
					buttonStart.setEnabled(true);
			}

		};
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonStart:
			startDownloadFiles();

			break;

		case R.id.buttonTest:
			showInfo(R.string.test);

			break;

		default:
			break;
		}
	}

	private void startDownloadFiles() {
		buttonStart.setEnabled(false);

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < COUNT_FILES; i++) {
					downloadFile();
					countDownloadedFiles++;
					handler.sendEmptyMessage(i);
				}
			}
		}).start();
	}

	private void downloadFile() {
		SystemClock.sleep(SLEEP_TIME_MS);
	}

	@Override
	protected void onDestroy() {
		if (handler != null)
			handler.removeCallbacksAndMessages(null);

		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showInfo(int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
	}

	private void showInfo(String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	private void outputCountDownloadedFiles(int count) {
		outputMessage(getResources().getQuantityString(
				R.plurals.number_downloaded_files, count, count));
	}

	private void outputMessage(String text) {
		textView.setText(text);
	}

}
