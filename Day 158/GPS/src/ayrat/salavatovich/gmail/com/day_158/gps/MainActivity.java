package ayrat.salavatovich.gmail.com.day_158.gps;

import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewInfo;
	private LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		setupGPS();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void setupGPS() {
		showStatusGPS();
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 10, new LocationListener() {

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {

					}

					@Override
					public void onProviderEnabled(String provider) {
						showStatusGPS();
					}

					@Override
					public void onProviderDisabled(String provider) {
						showStatusGPS();
					}

					@Override
					public void onLocationChanged(Location location) {

					}
				});
	}

	private void showStatusGPS() {
		if (isEnableGPS()) {
			textViewInfo.setText(R.string.text_view_gps_enable);
		} else {
			textViewInfo.setText(R.string.text_view_gps_disable);
		}
	}

	private boolean isEnableGPS() {
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			return true;
		} else {
			return false;
		}
	}

	public void enableGPS(View v) {
		if (!isEnableGPS())
			turnGPSOnOff();
	}

	public void disableGPS(View v) {
		if (isEnableGPS())
			turnGPSOnOff();
	}

	private void turnGPSOnOff() {
		startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
	}

}
