package ayrat.salavatovich.gmail.com.day_118.drawingfigure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonOne:

			startActivity(ActivityOne.class);

			break;

		case R.id.buttonTwo:

			startActivity(ActivityTwo.class);

			break;

		case R.id.buttonThree:

			startActivity(ActivityThree.class);

			break;

		default:
			break;
		}
	}

	private void startActivity(Class<?> cls) {
		startActivity(new Intent(this, cls));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
