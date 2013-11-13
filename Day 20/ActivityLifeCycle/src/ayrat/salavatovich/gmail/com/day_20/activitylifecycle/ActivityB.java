package ayrat.salavatovich.gmail.com.day_20.activitylifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ActivityB extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_b);

		Toast.makeText(this, "ActivityB created!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStart() {
		super.onStart();
		Toast.makeText(this, "ActivityB started!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onResume() {
		super.onResume();
		Toast.makeText(this, "ActivityB resumed!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPause() {
		super.onPause();
		Toast.makeText(this, "ActivityB paused!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStop() {
		super.onStop();
		Toast.makeText(this, "ActivityB stoped!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRestart() {
		super.onRestart();
		Toast.makeText(this, "ActivityB restarted!", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "ActivityB destroyed!", Toast.LENGTH_SHORT).show();
	}

	public void onClick(View v) {
		startActivity(new Intent(ActivityB.this, ActivityC.class));
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
