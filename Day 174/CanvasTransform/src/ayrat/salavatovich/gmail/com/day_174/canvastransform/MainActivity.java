package ayrat.salavatovich.gmail.com.day_174.canvastransform;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

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

	public void onClickOpenActivity(View v) {
		switch (v.getId()) {
		case R.id.buttonOpenCanvasTranslateActivity:

			startActivity(new Intent(MainActivity.this,
					CanvasTranslateActivity.class));

			break;

		case R.id.buttonOpenPreTranslateActivity:

			startActivity(new Intent(MainActivity.this,
					PreTranslateActivity.class));

			break;

		case R.id.buttonOpenRestoreCanvasActivity:

			startActivity(new Intent(MainActivity.this,
					RestoreCanvasActivity.class));

			break;

		case R.id.buttonOpenRestoreToCountCanvasActivity:

			startActivity(new Intent(MainActivity.this,
					RestoreToCountCanvasActivity.class));

			break;

		case R.id.buttonOpenSaveCanvasActivity:

			startActivity(new Intent(MainActivity.this,
					SaveCanvasActivity.class));

			break;

		case R.id.buttonOpenSavePrivateStackCanvasActivity:

			startActivity(new Intent(MainActivity.this,
					SavePrivateStackCanvasActivity.class));

			break;

		default:
			break;
		}
	}

}
