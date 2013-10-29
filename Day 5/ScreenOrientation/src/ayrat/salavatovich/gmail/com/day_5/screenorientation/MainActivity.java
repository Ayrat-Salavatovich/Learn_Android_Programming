package ayrat.salavatovich.gmail.com.day_5.screenorientation;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.Surface;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		if (v.getId() % 2 == 0)
			Toast.makeText(this, getScreenOrientation(), Toast.LENGTH_SHORT)
					.show();
		else
			Toast.makeText(this, getLandscapeMode(), Toast.LENGTH_SHORT).show();

	}

	private int getScreenOrientation() {
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
			return R.string.orientation_portrait;
		else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
			return R.string.orientation_landscape;
		return R.string.orientation_undefined;
	}

	private int getLandscapeMode() {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int Width = displaymetrics.widthPixels;
		if (height > Width)
			return R.string.orientation_portrait;
		else
			return R.string.orientation_landscape;
	}

	private String getRotateOrientation() {
		int rotate = getWindowManager().getDefaultDisplay().getRotation();
		switch (rotate) {
		case Surface.ROTATION_0:
			return "Не поворачивали";
		case Surface.ROTATION_90:
			return "Повернули на 90 градусов по часовой стрелке";
		case Surface.ROTATION_180:
			return "Повернули на 180 градусов";
		case Surface.ROTATION_270:
			return "Повернули на 90 градусов по часовой стрелке";
		default:
			return "Не понятно";
		}
	}

}
