package ayrat.salavatovich.gmail.com.day_106.servicesimple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		setVisibilityProgressBar(false);
	}

	private void setVisibilityProgressBar(boolean visibility) {
		progressBar.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonStartService:
			startService();

			break;

		case R.id.buttonStopService:
			stopService();

			break;

		default:
			break;
		}
	}

	private void startService() {
		startService(new Intent(this, MyFirstService.class));
		setVisibilityProgressBar(true);
	}

	private void stopService() {
		stopService(new Intent(this, MyFirstService.class));
		setVisibilityProgressBar(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
