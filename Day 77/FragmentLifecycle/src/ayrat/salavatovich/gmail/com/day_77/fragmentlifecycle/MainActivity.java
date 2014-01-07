package ayrat.salavatovich.gmail.com.day_77.fragmentlifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onPause();
	}

	@Override
	protected void onRestart() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onRestart();
	}

	@Override
	protected void onResume() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onResume();
	}

	@Override
	protected void onStart() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onStart();
	}

	@Override
	protected void onStop() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onStop();
	}

	private void log(String msg) {
		Log.w(new Exception().getStackTrace()[0].getClassName(), msg);
	}

}
