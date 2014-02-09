package ayrat.salavatovich.gmail.com.day_113.notificationforeground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Intent service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		service = new Intent(this, MyService.class);

		Intent intent = getIntent();
		String portugal = intent.getStringExtra(getString(R.string.portugal));
		if (!TextUtils.isEmpty(portugal))
			outputInfo(portugal);
	}

	private void outputInfo(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonStart:

			startService();

			break;

		case R.id.buttonStop:

			stopService();

			break;

		default:
			break;
		}
	}

	private void stopService() {
		stopService(service);
	}

	private void startService() {
		startService(service);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
