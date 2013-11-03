package ayrat.salavatovich.gmail.com.day_9.showtoast;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showToast(View v) {
		switch (v.getId()) {
		case R.id.buttonTop:
			showMessage(getApplicationContext(), R.string.top,
					Toast.LENGTH_LONG, Gravity.TOP);
			break;
		case R.id.buttonCenter:
			showMessage(getApplicationContext(), R.string.center,
					Toast.LENGTH_LONG, Gravity.CENTER);
			break;
		case R.id.buttonBottom:
			showMessage(getApplicationContext(), R.string.bottom,
					Toast.LENGTH_LONG, Gravity.BOTTOM);
			break;
		case R.id.buttonLeft:
			showMessage(getApplicationContext(), R.string.left,
					Toast.LENGTH_LONG, Gravity.LEFT);
			break;
		case R.id.buttonRight:
			showMessage(getApplicationContext(), R.string.right,
					Toast.LENGTH_LONG, Gravity.RIGHT);
			break;
		default:
			break;
		}
	}

	public static void showMessage(Context context, CharSequence text,
			int duration) {
		Toast.makeText(context, text, duration).show();
	}

	public static void showMessage(Context context, int resId, int duration) {
		Toast.makeText(context, resId, duration).show();
	}

	public static void showMessage(Context context, String text, int duration,
			int gravity) {
		Toast t = Toast.makeText(context, text, duration);
		t.setGravity(gravity, 0, 0);
		t.show();
	}

	public static void showMessage(Context context, int resId, int duration,
			int gravity) {
		Toast t = Toast.makeText(context, resId, duration);
		t.setGravity(gravity, 0, 0);
		t.show();
	}

	public static void showMessage(Context context, CharSequence text,
			int duration, int gravity, int[] offset) {
		Toast t = Toast.makeText(context, text, duration);
		t.setGravity(gravity, offset[0], offset[1]);
		t.show();
	}

	public static void showMessage(Context context, int resId, int duration,
			int gravity, int[] offset) {
		Toast t = Toast.makeText(context, resId, duration);
		t.setGravity(gravity, offset[0], offset[1]);
		t.show();
	}

}
