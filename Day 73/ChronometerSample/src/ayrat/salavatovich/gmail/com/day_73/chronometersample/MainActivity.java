package ayrat.salavatovich.gmail.com.day_73.chronometersample;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.Chronometer;

public class MainActivity extends Activity {

	Chronometer chronometer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		chronometer = (Chronometer) findViewById(R.id.chronometer);
	}

	public void onClick(View btn) {
		switch (btn.getId()) {
		case R.id.buttonStart:
			chronometer.start();
			break;
		case R.id.buttonStop:
			chronometer.stop();
			break;
		case R.id.buttonReset:
			chronometer.setBase(SystemClock.elapsedRealtime());
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
