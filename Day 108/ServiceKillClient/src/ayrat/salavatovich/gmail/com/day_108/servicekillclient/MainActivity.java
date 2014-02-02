package ayrat.salavatovich.gmail.com.day_108.servicekillclient;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonStartSticky:

			startService(new Intent(getString(R.string.action)).putExtra(
					getString(R.string.name), Service.START_STICKY));

			break;

		case R.id.buttonStartNotSticky:

			startService(new Intent(getString(R.string.action)).putExtra(
					getString(R.string.name), Service.START_NOT_STICKY));

			break;

		case R.id.buttonStartRedeliverIntent:

			startService(new Intent(getString(R.string.action)).putExtra(
					getString(R.string.name), Service.START_REDELIVER_INTENT));

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
