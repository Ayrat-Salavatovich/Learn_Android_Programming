package ayrat.salavatovich.gmail.com.day_95.preferencessimple;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;
	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);

		prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
	}

	@Override
	protected void onResume() {
		Boolean notifications = prefs.getBoolean(
				getString(R.string.preference_notifications_key), false);
		String address = prefs.getString(
				getString(R.string.preference_address_key), "");
		outputInfo(notifications, address);
		super.onResume();
	}

	private void outputInfo(boolean enabledNotifications, String address) {
		if (enabledNotifications)
			textView.setText(getString(R.string.info_notifications_enabled,
					address));
		else
			textView.setText(R.string.info_notifications_disabled);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		final MenuItem menuItem = (MenuItem) menu
				.findItem(R.id.action_preferences);
		menuItem.setIntent(new Intent(this, PreferenceActivity.class));
		return super.onCreateOptionsMenu(menu);
	}

}
