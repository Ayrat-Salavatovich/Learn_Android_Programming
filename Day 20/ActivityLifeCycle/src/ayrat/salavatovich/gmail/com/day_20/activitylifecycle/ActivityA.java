package ayrat.salavatovich.gmail.com.day_20.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ActivityA extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_a);

		Toast.makeText(this, "ActivityA created!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStart() {
		super.onStart();
		Toast.makeText(this, "ActivityA started!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onResume() {
		super.onResume();
		Toast.makeText(this, "ActivityA resumed!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPause() {
		super.onPause();
		Toast.makeText(this, "ActivityA paused!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStop() {
		super.onStop();
		Toast.makeText(this, "ActivityA stoped!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRestart() {
		super.onRestart();
		Toast.makeText(this, "ActivityA restarted!", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "ActivityA destroyed!", Toast.LENGTH_SHORT).show();
	}

	public void onClick(View v) {
		startActivity(new Intent(ActivityA.this, ActivityB.class));
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
