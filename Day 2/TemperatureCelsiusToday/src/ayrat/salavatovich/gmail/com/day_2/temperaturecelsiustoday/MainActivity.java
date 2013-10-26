package ayrat.salavatovich.gmail.com.day_2.temperaturecelsiustoday;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView currentWeatherTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		currentWeatherTextView = (TextView) findViewById(R.id.currentWeatherTextView);

		RefreshCurrentTemperature();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_menu, menu);
		return true;
	}

	private void RefreshCurrentTemperature() {
		new MyTask().execute();
	}

	private String fetchCurrentTemperature(String url) throws IOException {
		String temperature = "";
		URLConnection conn;
		BufferedReader reader;
		StringBuilder page;
		String line;
		conn = new URL(url).openConnection();
		reader = new BufferedReader(
				new InputStreamReader(conn.getInputStream()));
		page = new StringBuilder();
		while ((line = reader.readLine()) != null)
			page.append(line);

		temperature = findTemperatureInPage(page.toString());

		return String.valueOf(convertFahrenheitToCelsius(Integer
				.valueOf(temperature)));
	}

	private void showTemperature(String temperature) {
		currentWeatherTextView.setText(temperature + "°");
	}

	private int convertFahrenheitToCelsius(int fahrenheit) {
		return ((fahrenheit - 32) * 5 / 9);
	}

	private String findTemperatureInPage(final String str) {
		final Pattern pattern = Pattern
				.compile("<span class=\"wx-value\" itemprop=\"temperature-fahrenheit\">([-+0-9]+)</span>");
		Matcher matcher = pattern.matcher(str.toString());
		if (matcher.find())
			return matcher.group(1);
		return "";
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.update:
			RefreshCurrentTemperature();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	class MyTask extends AsyncTask<Void, Void, String> {

		final String url = "http://www.weather.com/weather/today/RSXX0111:1";

		@Override
		protected String doInBackground(Void... noargs) {
			String currentTemperature = "";
			try {
				currentTemperature = fetchCurrentTemperature(url);
			} catch (IOException ignore) {
			}
			return currentTemperature;
		}

		@Override
		protected void onPostExecute(String result) {
			showTemperature(result);
		}
	}

}
