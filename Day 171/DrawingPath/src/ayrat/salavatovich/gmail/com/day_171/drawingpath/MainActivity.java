package ayrat.salavatovich.gmail.com.day_171.drawingpath;

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
		case R.id.buttonOpenSimpleShapesActivity:

			startActivity(new Intent(MainActivity.this,
					SimpleShapesActivity.class));

			break;

		case R.id.buttonOpenCurvesActivity:

			startActivity(new Intent(MainActivity.this, CurvesActivity.class));

			break;

		case R.id.buttonOpenTextOfFigureActivity:

			startActivity(new Intent(MainActivity.this,
					TextOfFigureActivity.class));

			break;

		default:
			break;
		}
	}

}
