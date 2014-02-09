package ayrat.salavatovich.gmail.com.day_116.broadcastreceiversample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private Intent service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		service = new Intent(this, MyService.class);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonStartService:

			startService(service);

			break;

		case R.id.buttonStopService:

			stopService(service);

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
