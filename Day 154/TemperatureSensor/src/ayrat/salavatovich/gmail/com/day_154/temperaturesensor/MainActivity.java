package ayrat.salavatovich.gmail.com.day_154.temperaturesensor;

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

	private float currentTemperature = Float.NaN;
	private SensorManager sensorManager;
	private Sensor temperatureSensor;
	private TextView textViewInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);

		new Timer("temperatureUpdate").scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				updateGUI();
			}
		}, 0, 1000);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private boolean isSensorSupported(final int type) {
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
		for (Sensor sensor : sensors) {
			if (sensor.getType() == type)
				return true;
		}
		return false;
	}

	@Override
	protected void onPause() {
		sensorManager.unregisterListener(temperatureEventListener);

		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (isAmbientTemperatureAvailable()) {
			temperatureSensor = sensorManager
					.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
			sensorManager.registerListener(temperatureEventListener,
					temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
		} else
			textViewInfo.setText(R.string.temperature_sensor_unavailable);
	}

	public boolean isAmbientTemperatureAvailable() {
		return isSensorSupported(Sensor.TYPE_AMBIENT_TEMPERATURE);
	}

	private SensorEventListener temperatureEventListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			currentTemperature = event.values[0];
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};

	private void updateGUI() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (!Float.isNaN(currentTemperature)) {
					textViewInfo.setText(getString(
							R.string.current_temperature, currentTemperature));
					textViewInfo.invalidate();
				}
			}
		});
	}

}
