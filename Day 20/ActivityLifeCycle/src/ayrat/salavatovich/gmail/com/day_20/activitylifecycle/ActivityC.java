package ayrat.salavatovich.gmail.com.day_20.activitylifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityC extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_c);

		Toast.makeText(this, "ActivityC created!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStart() {
		super.onStart();
		Toast.makeText(this, "ActivityC started!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onResume() {
		super.onResume();
		Toast.makeText(this, "ActivityC resumed!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPause() {
		super.onPause();
		Toast.makeText(this, "ActivityC paused!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStop() {
		super.onStop();
		Toast.makeText(this, "ActivityC stoped!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRestart() {
		super.onRestart();
		Toast.makeText(this, "ActivityC restarted!", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "ActivityC destroyed!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_close:
			this.finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
