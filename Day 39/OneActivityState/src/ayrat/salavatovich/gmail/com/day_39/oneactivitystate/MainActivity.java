package ayrat.salavatovich.gmail.com.day_39.oneactivitystate;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	/*
	 * Вызывается при первом создании Activity
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		showToast(R.string.on_create);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * Вызывается перед тем, как Activity будет уничтожено
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		showToast(R.string.on_destroy);
	}

	/*
	 * Вызывается перед тем, как будет показано другое Activity
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		showToast(R.string.on_pause);
	}

	/*
	 * Вызывается перед тем как будет доступно для активности пользователя
	 * (взаимодействие)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		showToast(R.string.on_resume);
	}

	/*
	 * Вызывается перед тем, как Activity будет видно пользователю
	 * 
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		showToast(R.string.on_start);
	}

	/*
	 * Вызывается когда Activity становится не видно пользователю
	 * 
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		showToast(R.string.on_stop);
	}

	/*
	 * Вызывается перед методом onStart, если Activity не создается с нуля, а
	 * восстанавливается из состояния Stoped
	 * 
	 * @see android.app.Activity#onRestart()
	 */
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		showToast(R.string.on_restart);
	}

	private void showToast(final int resId) {
		showToast(getString(resId));
	}

	private void showToast(final String activity) {
		Toast.makeText(this, getString(R.string.аctivity, activity),
				Toast.LENGTH_SHORT).show();
	}

}
