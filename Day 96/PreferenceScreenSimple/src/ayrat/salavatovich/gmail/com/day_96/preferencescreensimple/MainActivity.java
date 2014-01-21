package ayrat.salavatovich.gmail.com.day_96.preferencescreensimple;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	}

	@Override
	protected void onResume() {
		String listValue = sharedPreferences.getString(
				getString(R.string.preference_key_list),
				getString(R.string.not_select));
		outputInfo(listValue);
		super.onResume();
	}

	private void outputInfo(String value) {
		textView.setText(getString(R.string.output_list_value, value));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem menuItem = menu.findItem(R.id.action_preferences);
		menuItem.setIntent(new Intent(this, PreferenceScreen.class));
		return true;
	}

}
