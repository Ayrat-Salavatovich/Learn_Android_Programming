package ayrat.salavatovich.gmail.com.day_172.matrixtransform;

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
		case R.id.buttonOpenTranslateActivity:

			startActivity(new Intent(MainActivity.this, TranslateActivity.class));

			break;

		case R.id.buttonOpenScaleActivity:

			startActivity(new Intent(MainActivity.this, ScaleActivity.class));

			break;

		case R.id.buttonOpenRotateActivity:

			startActivity(new Intent(MainActivity.this, RotateActivity.class));

			break;

		case R.id.buttonOpenPostPreTranslateActivity:

			startActivity(new Intent(MainActivity.this,
					PostPreTranslateActivity.class));

			break;

		case R.id.buttonOpenSkewActivity:

			startActivity(new Intent(MainActivity.this, SkewActivity.class));

			break;

		case R.id.buttonOpenMapActivity:

			startActivity(new Intent(MainActivity.this, MapActivity.class));

			break;

		default:
			break;
		}
	}

}
