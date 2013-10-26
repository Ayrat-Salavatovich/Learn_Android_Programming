package ayrat.salavatovich.gmail.com.day_2.hellopreferences;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button showButton = (Button) findViewById(R.id.showButton);
		Button changeButton = (Button) findViewById(R.id.changeButton);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);

		showButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String username = preferences.getString("username", "n/a");
				String password = preferences.getString("password", "n/a");

				Toast.makeText(
						MainActivity.this,
						"You entered user: " + username + " and password: "
								+ password, Toast.LENGTH_LONG).show();
			}
		});

		changeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Editor edit = preferences.edit();
				String username = preferences.getString("username", "n/a");
				StringBuffer buffer = new StringBuffer();
				for (int i = username.length() - 1; i >= 0; i--)
					buffer.append(username.charAt(i));
				edit.putString("username", buffer.toString());
				edit.commit();

				Toast.makeText(MainActivity.this,
						"Reverted string sequence of user name.",
						Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.preferences:
			Intent i = new Intent(MainActivity.this, Preferences.class);
			startActivity(i);

			Toast.makeText(this,
					"Here you can maintain your user credentials.",
					Toast.LENGTH_LONG).show();

			break;

		default:
			Toast.makeText(this, "Just a test", Toast.LENGTH_SHORT).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
