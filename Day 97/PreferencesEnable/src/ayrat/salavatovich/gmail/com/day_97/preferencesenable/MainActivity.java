package ayrat.salavatovich.gmail.com.day_97.preferencesenable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonPreferences:
		case R.id.action_preferences:
			showPreferenceScreen();

			break;

		default:
			break;
		}
	}

	private void showPreferenceScreen() {
		Intent intent = new Intent(getApplicationContext(),
				PreferenceScreenActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
