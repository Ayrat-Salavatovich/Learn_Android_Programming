package ayrat.salavatovich.gmail.com.day_7.optionsexpandedmenusample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_open:
		case R.id.action_save:
		case R.id.action_edit:
		case R.id.action_help:
		case R.id.action_exit:
		case R.id.action_find_replace:
		case R.id.action_find_next:
		case R.id.action_find_previous:

			notification(item.getTitle());
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void notification(CharSequence text) {
		Toast toast = Toast.makeText(getApplicationContext(),
				getString(R.string.toast_notification, text),
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

}
