package ayrat.salavatovich.gmail.com.day_7.contextmenusample;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

		registerForContextMenu(relativeLayout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		switch (v.getId()) {
		case R.id.relativeLayout:
			menu.setHeaderIcon(R.drawable.ic_launcher);
			menu.setHeaderTitle(R.string.menu);
			getMenuInflater().inflate(R.menu.main, menu);

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_open:
		case R.id.action_save:
		case R.id.action_edit:
		case R.id.action_help:
		case R.id.action_exit:

			notification(item.getTitle());
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_open:
		case R.id.action_save:
		case R.id.action_edit:
		case R.id.action_help:
		case R.id.action_exit:

			notification(item.getTitle());
			return true;

		default:
			return super.onContextItemSelected(item);
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
