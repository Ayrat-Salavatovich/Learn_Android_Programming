package ayrat.salavatovich.gmail.com.day_7.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView textViewInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		boolean result = true;

		switch (item.getItemId()) {
		case R.id.action_java:
			textViewInfo.setText("Вы выбрали Java");

			break;

		case R.id.action_clojure:
			textViewInfo.setText("Вы выбрали Clojure");

			break;

		case R.id.action_html:
			textViewInfo.setText("Вы выбрали HTML");

			break;

		case R.id.action_javascript:
			textViewInfo.setText("Вы выбрали JavaScript");

			break;

		case R.id.action_css:
			textViewInfo.setText("Вы выбрали CSS");

			break;

		default:
			result = super.onOptionsItemSelected(item);

			break;
		}
		return result;
	}

	public void onSettingsMenuClick(MenuItem item) {
		textViewInfo.setText("Вы выбрали пункт Settings");
	}

}
