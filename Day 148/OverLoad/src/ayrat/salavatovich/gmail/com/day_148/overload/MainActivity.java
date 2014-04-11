package ayrat.salavatovich.gmail.com.day_148.overload;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
	private TextView textViewX, textViewY, textViewZ;
	private TextView textViewAcceleration;
	private TextView textViewMaxAcceleration;
	private Acceleration current = new Acceleration();
	private Acceleration max = new Acceleration();
	private final int X = 0;
	private final int Y = 1;
	private final int Z = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initTextView();
		initSensor();
		Timer updateTimer = new Timer();
		updateTimer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				updateGUI();
			}
		}, 0, 100);
	}

	void initTextView() {
		textViewX = (TextView) findViewById(R.id.textViewX);
		textViewY = (TextView) findViewById(R.id.textViewY);
		textViewZ = (TextView) findViewById(R.id.textViewZ);

		textViewAcceleration = (TextView) findViewById(R.id.textViewAcceleration);
		textViewMaxAcceleration = (TextView) findViewById(R.id.textViewMaxAcceleration);
	}

	void initSensor() {
		sensorManager = getSensorManager();
		Sensor accelerometerSensor = getAccelerometerSensor();
		sensorManager.registerListener(sensorEventListener,
				accelerometerSensor, SensorManager.SENSOR_DELAY_FASTEST);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private final SensorEventListener sensorEventListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			current.setValues(event.values[X], event.values[Y], event.values[Z]);
			max = Acceleration.max(current, max);
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};

	private void updateGUI() {
		runOnUiThread(new Runnable() {
			public void run() {
				String currentX = getString(R.string.x, current.getX());
				String currentY = getString(R.string.y, current.getY());
				String currentZ = getString(R.string.z, current.getZ());

				String currentG = getString(R.string.value,
						current.getGravity());
				String maxG = getString(R.string.value, max.getGravity());

				displayMessage(textViewX, currentX);
				displayMessage(textViewY, currentY);
				displayMessage(textViewZ, currentZ);
				displayMessage(textViewAcceleration, currentG);
				displayMessage(textViewMaxAcceleration, maxG);
			}
		});
	}

	void displayMessage(final TextView textView, final String text) {
		textView.setText(text);
		textView.invalidate();
	}

}
