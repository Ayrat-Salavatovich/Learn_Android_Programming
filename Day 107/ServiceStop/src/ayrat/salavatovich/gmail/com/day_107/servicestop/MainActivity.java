package ayrat.salavatovich.gmail.com.day_107.servicestop;

import android.app.Activity;
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

	public void onСlick(View v) {
		switch (v.getId()) {
		case R.id.buttonStart:
			startService(new Intent(this, MyService.class).putExtra(
					MyService.NAME_TIME, 8 * MyService.SECOND));
			startService(new Intent(this, MyService.class).putExtra(
					MyService.NAME_TIME, 2 * MyService.SECOND));
			startService(new Intent(this, MyService.class).putExtra(
					MyService.NAME_TIME, 4 * MyService.SECOND));

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
