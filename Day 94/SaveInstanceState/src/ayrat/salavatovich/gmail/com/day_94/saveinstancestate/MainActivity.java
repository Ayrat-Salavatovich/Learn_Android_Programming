package ayrat.salavatovich.gmail.com.day_94.saveinstancestate;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int count;
	private final String COUNT = "ayrat.salavatovich.gmail.com.day_94.saveinstancestate.MainActivity.COUNT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		showLifecycle(R.string.on_create_title, R.string.on_create_message);
	}

	public void onClick(View v) {
		Toast.makeText(getApplicationContext(),
				getString(R.string.count_click, ++count), Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		count = savedInstanceState.getInt(COUNT);

		showLifecycle(R.string.on_restore_instance_state_title,
				R.string.on_restore_instance_state_message);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(COUNT, count);

		showLifecycle(R.string.on_save_instance_state_title,
				R.string.on_save_instance_state_message);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		showLifecycle(R.string.on_destroy_title, R.string.on_destroy_message);
	}

	@Override
	protected void onPause() {
		super.onPause();

		showLifecycle(R.string.on_pause_title, R.string.on_pause_message);
	}

	@Override
	protected void onRestart() {
		super.onRestart();

		showLifecycle(R.string.on_restart_title, R.string.on_restart_message);
	}

	@Override
	protected void onResume() {
		super.onResume();

		showLifecycle(R.string.on_resume_title, R.string.on_resume_message);
	}

	@Override
	protected void onStart() {
		super.onStart();

		showLifecycle(R.string.on_start_title, R.string.on_start_message);
	}

	@Override
	protected void onStop() {
		super.onStop();

		showLifecycle(R.string.on_stop_title, R.string.on_stop_message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showLifecycle(int resIdTitle, int resIdMessage) {
		LayoutInflater layoutInflater = getLayoutInflater();
		View view = layoutInflater.inflate(R.layout.my_toast, null);
		((TextView) view.findViewById(R.id.textViewToastTitle))
				.setText(resIdTitle);
		((TextView) view.findViewById(R.id.textViewToastMessage))
				.setText(resIdMessage);
		Toast toast = new Toast(getApplicationContext());
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setView(view);
		toast.show();
	}

}
