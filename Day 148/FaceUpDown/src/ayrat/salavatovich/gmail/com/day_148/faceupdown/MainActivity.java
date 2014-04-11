package ayrat.salavatovich.gmail.com.day_148.faceupdown;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private SensorManager sensorManager;
	private TextView textView;
	private Sensor accelerometerSensor;
	private final int Z = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
		accelerometerSensor = getAccelerometerSensor();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	void displayMessage(int idRes) {
		displayMessage(getString(idRes));
	}

	void displayMessage(String text) {
		textView.setText(text);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (accelerometerSensor != null) {
			getSensorManager().registerListener(accelerometerListener,
					accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (accelerometerSensor != null) {
			getSensorManager().unregisterListener(accelerometerListener);
		}
	}

	SensorManager getSensorManager() {
		if (sensorManager == null)
			sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		return sensorManager;
	}

	Sensor getAccelerometerSensor() {
		Sensor accelerometerSensor = null;
		if (hasSensor(Sensor.TYPE_ACCELEROMETER)) {
			accelerometerSensor = getSensorManager().getDefaultSensor(
					Sensor.TYPE_ACCELEROMETER);
		}

		return accelerometerSensor;
	}

	boolean hasSensor(final int SENSOR_TYPE) {
		boolean result = false;
		List<Sensor> s = getSensorManager().getSensorList(Sensor.TYPE_ALL);
		for (int i = 0; i < s.size(); i++) {
			Sensor tmp = s.get(i);
			if (tmp.getType() == SENSOR_TYPE)
				result = true;

		}

		return result;
	}

	private SensorEventListener accelerometerListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			float zValue = event.values[Z];
			if (zValue >= 0) {
				displayMessage(R.string.screen_up);
			} else {
				displayMessage(R.string.screen_down);
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
	};
}
