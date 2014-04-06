package ayrat.salavatovich.gmail.com.day_141.magic8ball;

import java.util.Random;

import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {

	private Random randomizer = new Random();

	private SharedPreferences preferences;
	private Vibrator vibrator;
	private SensorManager sensorManager;
	private Sensor sensor;
	private float lastX;
	private float lastY;
	private float lastZ;
	private int shakeCount = 0;

	private boolean isSensorRegistered() {
		return sensor != null;
	}

	private String getAnswer() {
		int randomInt = randomizer.nextInt(getResources().getStringArray(
				R.array.responses).length);
		return getResources().getStringArray(R.array.responses)[randomInt];
	}

	private void registerSensorListener() {
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_UI);
	}

	private void unregisterSensorListener() {
		sensorManager.unregisterListener(this);
	}

	private void initialization() {
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		// Данные приходящие в метод onSensorChanged будут в единицах ускорения
		// (м/с^2).
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initialization();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.shake:
			showMessage(getAnswer());

			return true;
		case R.id.action_settings:
			startActivity(new Intent(this, Preferences.class));

			return true;
		}

		return false;
	}

	@Override
	public void onResume() {
		super.onResume();
		registerSensorListener();
		if (isSensorRegistered())
			showMessage(getString(R.string.shake_me_caption));
		else
			showMessage(getString(R.string.menu_shake_caption));
	}

	@Override
	public void onPause() {
		unregisterSensorListener();
		super.onPause();
	}

	private void showMessage(String message) {
		TextView triangle = (TextView) findViewById(R.id.textViewMessage);
		triangle.setVisibility(TextView.INVISIBLE);
		triangle.setText(message);

		AlphaAnimation animation = new AlphaAnimation(0, 1);
		animation.setStartOffset(Constants.START_OFFSET);
		triangle.setVisibility(TextView.VISIBLE);
		animation.setDuration(Constants.FADE_DURATION);
		triangle.startAnimation(animation);
		try {
			int milliseconds = Integer.parseInt(preferences.getString(
					getString(R.string.vibrate_time_id),
					getString(R.string.vibrate_time)));
			vibrator.vibrate(milliseconds);
		} catch (NumberFormatException e) {
			Toast.makeText(this, getString(R.string.empty), Toast.LENGTH_LONG)
					.show();
		}
	}

	/*
	 * Вызывается при изменениях значений датчика Значениям по трем осям X, Y и
	 * Z будут соответствовать значения event.values[0], event.values[1],
	 * event.values[2] класса SensorEvent.
	 */
	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
			if (isShakeEnough(event.values[0], event.values[1], event.values[2]))
				showMessage(getAnswer());
	}

	private boolean isShakeEnough(float x, float y, float z) {
		double force = 0.0d;
		force += Math.pow((x - lastX) / SensorManager.GRAVITY_EARTH, 2.0);
		force += Math.pow((y - lastY) / SensorManager.GRAVITY_EARTH, 2.0);
		force += Math.pow((z - lastZ) / SensorManager.GRAVITY_EARTH, 2.0);
		force = Math.sqrt(force);

		lastX = x;
		lastY = y;
		lastZ = z;

		if (force > Float.parseFloat(preferences
				.getString(getString(R.string.threshold_id),
						getString(R.string.threshold)))) {
			shakeCount++;
			if (shakeCount > Integer.parseInt(preferences.getString(
					getString(R.string.shake_count_id),
					getString(R.string.shake_count)))) {
				shakeCount = 0;
				lastX = 0;
				lastY = 0;
				lastZ = 0;
				return true;
			}
		}
		return false;
	}

	/*
	 * Вызывается когда изменилась точность датчика
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

}
