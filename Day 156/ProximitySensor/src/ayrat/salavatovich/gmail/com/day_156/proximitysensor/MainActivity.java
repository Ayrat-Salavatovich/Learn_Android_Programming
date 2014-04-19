package ayrat.salavatovich.gmail.com.day_156.proximitysensor;

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
	private float maximumRange = Float.NaN;
	private float proximityData = Float.NaN;
	private TextView textViewProximitySensor;
	private TextView textViewProximityMax;
	private TextView textViewProximityReading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		textViewProximitySensor = (TextView) findViewById(R.id.textViewProximitySensor);
		textViewProximityMax = (TextView) findViewById(R.id.textViewProximityMax);
		textViewProximityReading = (TextView) findViewById(R.id.textViewProximityReading);

		new Timer("ProximityUpdate").scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				updateGUI();
			}
		}, 0, 1000);
	}

	@Override
	protected void onPause() {
		super.onPause();

		disableProximitySensor();
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (!isProximitySensorAvailable()) {
			textViewProximitySensor.setText("Датчик расстояния отсутствует!");
		} else {
			enableProximitySensor();
			showMaximumRange();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean isSensorSupported(final int type) {
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
		for (Sensor sensor : sensors) {
			if (sensor.getType() == type)
				return true;
		}
		return false;
	}

	public boolean isProximitySensorAvailable() {
		return isSensorSupported(Sensor.TYPE_PROXIMITY);
	}

	public boolean enableProximitySensor() {
		if (isProximitySensorAvailable()) {
			registerSensorListener(Sensor.TYPE_PROXIMITY,
					SensorManager.SENSOR_DELAY_NORMAL);
			return true;
		} else {
			return false;
		}
	}

	public boolean disableProximitySensor() {
		if (isProximitySensorAvailable()) {
			unregisterSensorListener(Sensor.TYPE_PROXIMITY);
			return true;
		} else {
			return false;
		}
	}

	private void registerSensorListener(final int type, final int rateUs) {
		final Sensor sensor = sensorManager.getSensorList(type).get(0);
		setMaximumRange(sensor.getMaximumRange());
		textViewProximitySensor.setText(sensor.getName());
		sensorManager.registerListener(proximitySensorEventListener, sensor,
				rateUs);
	}

	private void unregisterSensorListener(final int type) {
		final Sensor sensor = sensorManager.getSensorList(type).get(0);
		sensorManager.unregisterListener(proximitySensorEventListener, sensor);
	}

	private SensorEventListener proximitySensorEventListener = new SensorEventListener() {

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
				proximityData = event.values[0];
			}
		}
	};

	private void setMaximumRange(final float maximumRange) {
		this.maximumRange = maximumRange;
	}

	private void showMaximumRange() {
		textViewProximityMax.setText("Максимальное расстояние: "
				+ String.valueOf(maximumRange));
	}

	private void updateGUI() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (!Float.isNaN(proximityData)) {
					textViewProximityReading.setText("Считывание показаний: "
							+ String.valueOf(proximityData));
					textViewProximityReading.invalidate();
				}
			}
		});
	}

}
