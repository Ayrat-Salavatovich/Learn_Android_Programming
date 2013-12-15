package ayrat.salavatovich.gmail.com.day_45.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

public class GravityActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gravity);
	}

	public void selectGravity(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.buttonLeft:
			intent.putExtra(MainActivity.GRAVITY, Gravity.LEFT);

			break;
		case R.id.buttonCenter:
			intent.putExtra(MainActivity.GRAVITY, Gravity.CENTER);

			break;
		case R.id.buttonRigth:
			intent.putExtra(MainActivity.GRAVITY, Gravity.RIGHT);

			break;

		default:
			break;
		}
		setResult(RESULT_OK, intent);
		finish();
	}

}
