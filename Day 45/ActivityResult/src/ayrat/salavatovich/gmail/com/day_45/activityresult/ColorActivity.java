package ayrat.salavatovich.gmail.com.day_45.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class ColorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.color);
	}

	public void selectColor(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.buttonRed:
			intent.putExtra(MainActivity.COLOR, Color.RED);

			break;
		case R.id.buttonGreen:
			intent.putExtra(MainActivity.COLOR, Color.GREEN);

			break;
		case R.id.buttonBlue:
			intent.putExtra(MainActivity.COLOR, Color.BLUE);

			break;

		default:
			break;
		}
		setResult(RESULT_OK, intent);
		finish();
	}

}
