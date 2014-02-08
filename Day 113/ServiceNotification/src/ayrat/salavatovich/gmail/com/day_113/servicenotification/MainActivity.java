package ayrat.salavatovich.gmail.com.day_113.servicenotification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;
	private Intent service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
		service = new Intent(this, MyService.class);

		Intent intent = getIntent();
		String domain_names = intent
				.getStringExtra(Constants.PORTUGAL_DOMAIN_NAMES);
		if (!TextUtils.isEmpty(domain_names))
			outputInfo(domain_names);
	}

	private void outputInfo(String text) {
		textView.setText(text);
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
