package ayrat.salavatovich.gmail.com.day_155.lightsensor;

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

	public final int LIGHT_NOT_SUPPORTED = 0;
	private SensorManager sensorManager;
	private TextView textViewCurrentValue;
	private TextView textViewMaxValue;
	private float currentLight = Float.NaN;
	private float maxLight = Float.NaN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewCurrentValue = (TextView) findViewById(R.id.textViewCurrentValue);
		textViewMaxValue = (TextView) findViewById(R.id.textViewMaxValue);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		new Timer("lightUpdate").scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				updateGUI();
			}
		}, 0, 1000);
	}

	private final SensorEventListener lightSensorEventListener = new SensorEventListener() {

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		public void onSensorChanged(SensorEvent event) {
			currentLight = event.values[0];
		}
	};

	private void updateGUI() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (!Float.isNaN(currentLight)) {
					textViewCurrentValue.setText(getString(
							R.string.current_light, currentLight));
					textViewMaxValue.setText(getString(R.string.max_light,
							maxLight));
					textViewCurrentValue.invalidate();
					textViewMaxValue.invalidate();
				}
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (isLightAvailable()) {
			Sensor lightSensor = sensorManager
					.getDefaultSensor(Sensor.TYPE_LIGHT);
			sensorManager.registerListener(lightSensorEventListener,
					lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
			maxLight = lightSensor.getMaximumRange();
		} else
			showDialogFragment(this, LIGHT_NOT_SUPPORTED);
	}

	@Override
	protected void onPause() {
		sensorManager.unregisterListener(lightSensorEventListener);
		super.onPause();
	}

	void showDialogFragment(Context context, int dialogId) {
		switch (dialogId) {
		case LIGHT_NOT_SUPPORTED:
			AlertDialog.newInstance("Sorry!", "Light Sensor Unavailable").show(
					getFragmentManager(), "LIGHT_NOT_SUPPORTED");

			break;
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

	public boolean isLightAvailable() {
		return isSensorSupported(Sensor.TYPE_LIGHT);
	}

}
