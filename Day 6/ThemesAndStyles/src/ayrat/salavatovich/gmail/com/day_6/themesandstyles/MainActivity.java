package ayrat.salavatovich.gmail.com.day_6.themesandstyles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

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
		Intent intent = new Intent(MainActivity.this, ThemeActivity.class);

		switch (v.getId()) {
		case R.id.buttonTheme:
			intent = new Intent(MainActivity.this, ThemeActivity.class);

			break;
		case R.id.buttonThemeBlack:
			intent = new Intent(MainActivity.this, ThemeBlackActivity.class);

			break;
		case R.id.buttonThemeBlackNoTitleBar:
			intent = new Intent(MainActivity.this,
					ThemeBlackNoTitleBarActivity.class);

			break;
		case R.id.buttonThemeLight:
			intent = new Intent(MainActivity.this, ThemeLightActivity.class);

			break;
		case R.id.buttonThemeLightNoTitleBar:
			intent = new Intent(MainActivity.this,
					ThemeLightNoTitleBarActivity.class);

			break;
		case R.id.buttonThemeNoTitleBar:
			intent = new Intent(MainActivity.this,
					ThemeNoTitleBarActivity.class);

			break;
		case R.id.buttonThemeTranslucent:
			intent = new Intent(MainActivity.this,
					ThemeTranslucentActivity.class);

			break;
		case R.id.buttonThemeTranslucentNoTitleBar:
			intent = new Intent(MainActivity.this,
					ThemeTranslucentNoTitleBarActivity.class);

			break;

		default:
			break;
		}

		startActivity(intent);
	}

}
