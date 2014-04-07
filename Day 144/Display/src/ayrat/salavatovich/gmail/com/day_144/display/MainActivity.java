package ayrat.salavatovich.gmail.com.day_144.display;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final int VERSION_SDK = Build.VERSION.SDK_INT;
	private SeekBar seekBarBackLightControl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		seekBarBackLightControl = (SeekBar) findViewById(R.id.seekBarBackLightControl);
		seekBarBackLightControl
				.setOnSeekBarChangeListener(seekBarChangeListener);

		outputInfo();
	}

	private OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			android.provider.Settings.System.putInt(getContentResolver(),
					android.provider.Settings.System.SCREEN_BRIGHTNESS,
					progress);

			outputScreenBrightness();
		}
	};

	private void outputInfo() {
		outputDiplaySize();
		outputScreenSize();
		outputScreenOrientation();
		outputLogicalDensity();
		outputScalingFactorFonts();
		outputScreenBrightness();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void outputDiplaySize() {
		int x, y;
		Display display;
		if (Build.VERSION.SDK_INT > 16) {
			display = ((WindowManager) getSystemService(WINDOW_SERVICE))
					.getDefaultDisplay();
			DisplayMetrics displayMetrics = new DisplayMetrics();
			display.getMetrics(displayMetrics);
			x = displayMetrics.widthPixels;
			y = displayMetrics.heightPixels;
		} else if (VERSION_SDK >= 13) {
			display = ((WindowManager) getSystemService(WINDOW_SERVICE))
					.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			x = size.x;
			y = size.y;
		} else {
			display = getWindowManager().getDefaultDisplay();
			x = display.getWidth();
			y = display.getHeight();
		}

		((TextView) findViewById(R.id.textViewDisplaySize)).setText(getString(
				R.string.size_display, x, y));
	}

	private void outputScreenSize() {
		String size = "";
		int screenSize = getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK;

		switch (screenSize) {
		case Configuration.SCREENLAYOUT_SIZE_XLARGE:
			size = "XLarge screen";

			break;
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			size = "Large screen";

			break;
		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
			size = "Normal screen";

			break;
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			size = "Small screen";

			break;
		default:
			size = "Screen size is neither xlarge, large, normal or small";
		}

		((TextView) findViewById(R.id.textViewScreenSize)).setText(getString(
				R.string.screen_size, size));
	}

	private void outputScreenOrientation() {
		String orientation = "";
		switch (getResources().getConfiguration().orientation) {
		case Configuration.ORIENTATION_LANDSCAPE:
			orientation = "Landscape";

			break;
		case Configuration.ORIENTATION_PORTRAIT:
			orientation = "Portrait";

			break;
		}

		((TextView) findViewById(R.id.textViewScreenOrientation))
				.setText(getString(R.string.screen_orientation, orientation));
	}

	private void outputLogicalDensity() {
		DisplayMetrics displayMetrics = getApplicationContext().getResources()
				.getDisplayMetrics();
		String screenInfo = "";
		screenInfo += getString(R.string.logical_density,
				displayMetrics.density) + "\n";
		screenInfo += getString(R.string.dimension_x, displayMetrics.xdpi)
				+ "\n";
		screenInfo += getString(R.string.dimension_y, displayMetrics.ydpi)
				+ "\n";
		screenInfo += getString(R.string.density_dpi, displayMetrics.densityDpi);

		((TextView) findViewById(R.id.textViewLogicalDensity))
				.setText(screenInfo);
	}

	private void outputScalingFactorFonts() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		((TextView) findViewById(R.id.textViewScalingFactorFonts))
				.setText(getString(R.string.scaling_factor_fonts,
						displayMetrics.scaledDensity));
	}

	private void outputScreenBrightness() {
		((TextView) findViewById(R.id.textViewScreenBrightness))
				.setText(getString(R.string.screen_brightness,
						getScreenBrightness()));
		((TextView) findViewById(R.id.textViewBackLightSetting)).setText(String
				.valueOf(getScreenBrightness() * 100 / 255) + "%");
	}

	private int getScreenBrightness() {
		int currentBrightnessValue = 0;
		try {
			currentBrightnessValue = android.provider.Settings.System.getInt(
					getContentResolver(),
					android.provider.Settings.System.SCREEN_BRIGHTNESS);
		} catch (SettingNotFoundException ignore) {
		}

		return currentBrightnessValue;
	}

}
