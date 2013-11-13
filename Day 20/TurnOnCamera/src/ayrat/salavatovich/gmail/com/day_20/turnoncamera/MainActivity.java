package ayrat.salavatovich.gmail.com.day_20.turnoncamera;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
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

	public void onClickActionCameraButton(View v) {
		PackageManager pm = getApplicationContext().getPackageManager();
		if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_CAMERA_BUTTON);
			intent.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(
					KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_CAMERA));
			sendOrderedBroadcast(intent, null);
		} else
			Toast.makeText(getApplicationContext(), R.string.camera_not_found,
					Toast.LENGTH_SHORT).show();
	}
}
